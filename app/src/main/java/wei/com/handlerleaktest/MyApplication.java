package wei.com.handlerleaktest;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class MyApplication extends Application {
  private RefWatcher mWatcher;

  @Override
  public void onCreate() {
    super.onCreate();
    if (LeakCanary.isInAnalyzerProcess(this)) {
      // This process is dedicated to LeakCanary for heap analysis.
      // You should not init your app in this process.
      return;
    }
    Log.i("TAG","isInstall");
    mWatcher = LeakCanary.install(this);
  }

  public static RefWatcher getWatcher(Context context) {
    MyApplication app = (MyApplication) context.getApplicationContext();
    return app.mWatcher;
  }
}
