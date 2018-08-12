package wei.com.handlerleaktest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class LeakActivity extends Activity {

  private Handler mHandler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
      if (msg.what == 1) {
        finish();
      }

      super.handleMessage(msg);
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_leak);
    mHandler.sendEmptyMessageDelayed(1, 2000);
    mHandler.sendEmptyMessageDelayed(2, 10000);
    Toast.makeText(this, "auto finish 2s after", Toast.LENGTH_SHORT).show();
  }


}
