package wei.com.handlerleaktest;

import java.lang.ref.SoftReference;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

  private Handler mHandler;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_second);
    mHandler = new AvoidLeakHandler(this);
    mHandler.sendEmptyMessageDelayed(0, 2000);
    Toast.makeText(this, "auto finish 2s after", Toast.LENGTH_SHORT).show();
  }

  private static class AvoidLeakHandler extends Handler {
    private SoftReference<Activity> mReference;

    public AvoidLeakHandler(Activity activity) {
      mReference = new SoftReference<>(activity);
    }

    @Override
    public void handleMessage(Message msg) {
      Log.i("SecondActiviy", "msg:" + msg.what);
      if (msg.what > 10) {
        return;
      }
      if (msg.what == 0 && mReference.get() != null) {
        mReference.get().finish();
      }
      sendEmptyMessageDelayed(msg.what + 1, 1000);
      super.handleMessage(msg);
    }
  };


  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (mHandler != null) {
      mHandler.removeCallbacksAndMessages(null);
    }
  }
}
