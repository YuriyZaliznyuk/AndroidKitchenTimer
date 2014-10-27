package ua.android.kitchen.timer;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	final String LOG_TAG = "myLogs";
	private static final int MILLIS_PER_SECOND = 1000;

	EditText edtSeconds;
	TextView lblSeconds, lblTimer;
	Button btnStart;
	CountDownTimer timer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(LOG_TAG, "--- OnCreate started 1 ---");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Log.d(LOG_TAG, "--- OnCreate started 2 ---");

		edtSeconds = (EditText) findViewById(R.id.edtSeconds);
		lblSeconds = (TextView) findViewById(R.id.lblSeconds);
		btnStart = (Button) findViewById(R.id.btnStart);
		lblTimer = (TextView) findViewById(R.id.lblTimer);

		Log.d(LOG_TAG, "--- OnCreate started 3 ---");
		
		btnStart.setOnClickListener(this);
		Log.d(LOG_TAG, "--- OnCreate finishes ---");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnStart:
			Toast.makeText(this, edtSeconds.getText().toString() + " minutes", Toast.LENGTH_LONG).show();
			try {
				showTimer(Integer.parseInt(edtSeconds.getText().toString()) * MILLIS_PER_SECOND);
			} catch (NumberFormatException e) {
				// method ignores invalid (non-integer) input and waits
				// for something it can use
			}
			break;
		}
	}

	private void showTimer(int countdownMillis) {
		if (timer != null) {
			timer.cancel();
		}
		
		timer = new CountDownTimer(countdownMillis, MILLIS_PER_SECOND) {
			@Override
			public void onTick(long millisUntilFinished) {
				lblTimer.setText("" + millisUntilFinished / MILLIS_PER_SECOND);
			}

			@Override
			public void onFinish() {
				lblTimer.setText("KABOOM!");
			}
		}.start();
	}
}



