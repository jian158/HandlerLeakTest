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


  private SoftReference<AvoidLeakHandler> mHandlerSoftReference;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_second);
    mHandlerSoftReference = new SoftReference<>(new AvoidLeakHandler(this));
    Handler mHandler = mHandlerSoftReference.get();
    if (mHandler != null) {
      mHandler.sendEmptyMessageDelayed(0, 2000);
    }
    Toast.makeText(this, "auto finish 2s after", Toast.LENGTH_SHORT).show();
  }

  private static class AvoidLeakHandler extends Handler {
    private Activity mActivity;

    public AvoidLeakHandler(Activity activity) {
      mActivity = activity;
    }

    /*
    * 这种情况软引用,static解决不了,需在onDestroy remove
    * */
    @Override
    public void handleMessage(Message msg) {
      Log.i("SecondActiviy", "msg:" + msg.what);
      if (msg.what > 10) {
        return;
      }
      if (msg.what == 0) {
        mActivity.finish();
      }
      sendEmptyMessageDelayed(msg.what + 1, 1000);
      super.handleMessage(msg);
    }
  };


  @Override
  protected void onDestroy() {
    super.onDestroy();
    Handler handler=mHandlerSoftReference.get();
    if(handler!=null){
       handler.removeCallbacksAndMessages(null);
    }
  }
}
