package de.ur.mi.soundsderoberpfalzprojekt;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HelpActivity extends Activity {

	private Button back;
	private TextView help;
	private TextView[] helpDescriptions;
	private CountDownTimer countDown;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupUI();
		setupTimer();
	}

	@SuppressLint("NewApi")
	private void setupUI() {
		setContentView(R.layout.activity_help);
		hideActionBar();
		setupButton();
		setText();
	}

	private void setupTimer() {
		stopCountDown();
		countDown = new CountDownTimer(Constants.TIMEOUT_DURATION, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				Log.d(Constants.DEBUG, "Remaining Time: " + millisUntilFinished);
			}

			@Override
			public void onFinish() {
				Log.d(Constants.DEBUG, "Count Down finished");
				finish();
			}
		}.start();
	}

	private void stopCountDown() {
		if (countDown != null) {
			countDown.cancel();
		}
	}

	private void hideActionBar() {
		int currentApiVersion = android.os.Build.VERSION.SDK_INT;
		if (currentApiVersion >= 11)
			getActionBar().hide();
	}

	private void setupButton() {
		back = (Button) findViewById(R.id.button_back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				stopCountDown();
				finish();

			}
		});
	}

	private void setText() {
		helpDescriptions = new TextView[4];
		helpDescriptions[0] = (TextView) findViewById(R.id.help_Text_1);
		helpDescriptions[1] = (TextView) findViewById(R.id.help_Text_2);
		helpDescriptions[2] = (TextView) findViewById(R.id.help_Text_3);
		helpDescriptions[3] = (TextView) findViewById(R.id.help_Text_4);

		setTextProperties();
	}

	private void setTextProperties() {
		for (int i = 0; i < helpDescriptions.length; i++) {
			TextView help = helpDescriptions[i];
			help.setLineSpacing(1, (float) 1.2);
			help.setTypeface(null, Typeface.ITALIC);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.help, menu);
		return true;
	}
}
