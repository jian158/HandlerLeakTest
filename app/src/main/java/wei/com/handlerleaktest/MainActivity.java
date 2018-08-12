package wei.com.handlerleaktest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void openLeak(View view) {
    Intent intent = new Intent(this, LeakActivity.class);
    startActivity(intent);
  }

  public void open(View view) {
    Intent intent = new Intent(this, SecondActivity.class);
    startActivity(intent);
  }
}
