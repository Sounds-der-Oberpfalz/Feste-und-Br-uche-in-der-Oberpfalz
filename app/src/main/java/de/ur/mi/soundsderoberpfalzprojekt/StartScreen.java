package de.ur.mi.soundsderoberpfalzprojekt;

import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class StartScreen extends Activity implements OnClickListener {

	// views

	private TextView headline;
	private Button buttonStart;
	private ImageView[] imageArray;
	private int images[];

	// instances for slide show

	private Boolean buttonClicked = false;
	private Timer timer;
	private Handler handler;
	private TranslateAnimation animation;

	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupUI();
		setAnimation();

		if (!Constants.appStarted) {
			createCelebrationList();
		}
	}

	// create list of celebrations

	private void createCelebrationList() {
		SDOManager.createHashMap();
		Constants.appStarted = true;
		Log.d(DetailActivity.DETAIL_LOG_KEY, "LISTE ERSTELLT");
	}

	// user interface

	private void setupUI() {
		setContentView(R.layout.activity_start_screen);
		hideActionBar();
		setupTitle();
		setupButton();
		setupImages();
	}

	private void hideActionBar() {
		int currentApiVersion = android.os.Build.VERSION.SDK_INT;
		if (currentApiVersion >= 11)
			getActionBar().hide();
	}

	private void setupTitle() {
		headline = (TextView) findViewById(R.id.title_start_screen);
		headline.setTypeface(null, Typeface.BOLD_ITALIC);
	}

	private void setupButton() {
		buttonStart = (Button) findViewById(R.id.button_start);
		buttonStart.setOnClickListener(this);
	}

	// create ImageArray for slide show
	private void setupImages() {
		imageArray = new ImageView[9];
		imageArray[0] = (ImageView) findViewById(R.id.image_1);
		imageArray[1] = (ImageView) findViewById(R.id.image_2);
		imageArray[2] = (ImageView) findViewById(R.id.image_3);
		imageArray[3] = (ImageView) findViewById(R.id.image_4);
		imageArray[4] = (ImageView) findViewById(R.id.image_5);
		imageArray[5] = (ImageView) findViewById(R.id.image_6);
		imageArray[6] = (ImageView) findViewById(R.id.image_7);
		imageArray[7] = (ImageView) findViewById(R.id.image_8);
		imageArray[8] = (ImageView) findViewById(R.id.image_9);
		setImageProperties();

		images = new int[9];

		images[0] = R.drawable.img_sprecht_1;
		images[1] = R.drawable.img_drachenstich_1;
		images[2] = R.drawable.img_maibaum_5;
		images[3] = R.drawable.img_pfingstritt_2;
		images[4] = R.drawable.img_drachenstich_3;
		images[5] = R.drawable.img_maibaum_4;
		images[6] = R.drawable.img_drachenstich_5;
		images[7] = R.drawable.img_sprecht_5;
		images[8] = R.drawable.img_fischzug_6;

	}

	private void addImage(int i, int sourceID) {

		if (imageArray[i].getDrawable() == null) {
			BitmapFactory.Options options = new BitmapFactory.Options();

			// options.inSampleSize = ;
			options.inPurgeable = true;
			Bitmap icon = BitmapFactory.decodeResource(getResources(),
					sourceID, options);
			imageArray[i].setImageBitmap(icon);
		}

	}

	private void setImageProperties() {
		for (int i = 0; i < imageArray.length; i++) {
			ImageView nextImage = imageArray[i];
			LayoutParams params = (LayoutParams) nextImage.getLayoutParams();
			params.width = Constants.PICTURE_WIDTH;
			params.height = Constants.PICTURE_HEIGHT;
			nextImage.setLayoutParams(params);
			nextImage.setVisibility(View.INVISIBLE);

		}
	}

	// initialize slide show

	private void setAnimation() {
		initHandler();
		timer = new Timer();
		buttonClicked = false;

		TimerTask animationTimerTask = new TimerTask() {
			int counter = 0;

			public void run() {
				if (buttonClicked) {
					timer.cancel();
				}

				setAnimationProperties();
				handler.sendEmptyMessage(counter);
				counter++;

				if (counter >= imageArray.length) {
					counter = 0;
				}
			}
		};
		timer.scheduleAtFixedRate(animationTimerTask, 0, 5000);
	}

	private void setAnimationProperties() {
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;

		animation = new TranslateAnimation(width + Constants.PICTURE_WIDTH,
				Constants.END_X, Constants.START_Y, Constants.END_Y);
		animation.setDuration(Constants.DURATION);
		animation.setInterpolator(new LinearInterpolator());
		animation.setFillBefore(true);
		animation.setFillAfter(true);
	}

	// initialize handler to start animation for one picture

	private void initHandler() {
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (!buttonClicked) {
					addImage(msg.what, images[msg.what]);
					ImageView actualImage = imageArray[msg.what];
					actualImage.setVisibility(View.VISIBLE);
					actualImage.setAlpha(0.8f);
					actualImage.startAnimation(animation);
				}
			}
		};
	}

	// handle user interaction
	@Override
	public void onClick(View v) {
		Intent intent;
		buttonClicked = true;

		preventOutOfMemoryError();

		intent = new Intent(StartScreen.this, MapActivity.class);
		startActivity(intent);
		finish();

	}

	private void preventOutOfMemoryError() {
		timer.cancel();

		for (int i = 0; i < imageArray.length; i++) {
			if (imageArray[i].getDrawable() != null) {
				imageArray[i].clearAnimation();
				imageArray[i].setVisibility(View.INVISIBLE);
				imageArray[i].destroyDrawingCache();

				BitmapDrawable bitmapDrawable = (BitmapDrawable) imageArray[i]
						.getDrawable();
				Bitmap bitmap = bitmapDrawable.getBitmap();
				bitmap.recycle();

			}

		}

	}

}
