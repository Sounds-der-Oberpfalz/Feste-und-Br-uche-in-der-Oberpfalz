package de.ur.mi.soundsderoberpfalzprojekt;

import java.util.ArrayList;

import android.R.anim;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
import android.view.View.OnTouchListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;

public class DetailActivity extends Activity {

	public static final String DETAIL_LOG_KEY = "detail";

	// Views

	private TextView headlineView, descriptionView, advice;
	private Button backButton;
	private ListView mediaView;
	private ImageView selectedMediaView, galleryView, swipeRight, swipeLeft;
	private VideoView videoPlayer;

	// variables

	private final GestureDetector gdt = new GestureDetector(
			new GestureListener());

	private int imgPos;

	private Celebration celebration;
	private String title, text;
	private ArrayList<Integer> imageSources, soundSources, videoSources;
	private ArrayList<PreviewImage> images;
	private PreviewImageAdapter adapter;
	private int selectedImage;
	private int soundID;
	private boolean clicked;
	private boolean videoPaused;
	private Bitmap iconBigger, iconBig;
	private CountDownTimer countDown;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_screen);
		setupUI();
		getContent();
		setupTimer();
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
				returnToStartScreen();
			}
		}.start();
	}

	private void returnToStartScreen() {
		stopCountDown();
		Intent intent = new Intent(DetailActivity.this, StartScreen.class);
		startActivity(intent);
		finish();
	}

	private void resetTimer() {
		countDown.cancel();
		countDown.start();
	}

	private void stopCountDown() {
		if (countDown != null) {
			countDown.cancel();
		}
	}

	private void addImageToGallery(int sourceID) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 8;
		Bitmap icon = BitmapFactory.decodeResource(getResources(), sourceID,
				options);
		images.add(new PreviewImage(icon));
	}

	private void setBigImage(int sourceID) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 1;
		if (iconBig != null) {
			iconBig.recycle();
			Log.d(DETAIL_LOG_KEY, "recycle");
		}
		iconBig = BitmapFactory.decodeResource(getResources(), sourceID,
				options);
		galleryView.setImageBitmap(iconBig);
	}

	private void setContent() {
		headlineView.setText(title);
		descriptionView.setText(text);

		Log.d(DETAIL_LOG_KEY, "Bild dateien: " + imageSources.size());
		Log.d(DETAIL_LOG_KEY, "Video dateien: " + videoSources.size());

		// if images

		if (imageSources.size() > 0) {
			for (int i = 0; i < imageSources.size(); i++) {
				addImageToGallery(imageSources.get(i));
				Log.d(DETAIL_LOG_KEY, "" + imageSources.get(i));

			}
			adapter.notifyDataSetChanged();

			Log.d(DETAIL_LOG_KEY, "vorschau geladen");

			selectedImage = imageSources.get(0);
			setBiggerImage(selectedImage);

			Log.d(DETAIL_LOG_KEY, "biggerImage geladen");

		} else {

			selectedImage = R.drawable.placeholder;
			setBiggerImage(selectedImage);
			mediaView.setVisibility(View.INVISIBLE);
			selectedMediaView.setClickable(false);
			advice.setVisibility(View.INVISIBLE);
		}

		// if sounds

		for (int i = 0; i < soundSources.size(); i++) {
			addImageToGallery(R.drawable.play_button_audio);
		}

		// if videos

		for (int i = 0; i < videoSources.size(); i++) {
			BitmapFactory.Options options = new BitmapFactory.Options();

			options.inSampleSize = 2;
			Bitmap icon = BitmapFactory.decodeResource(getResources(),
					R.drawable.play_button_video, options);
			images.add(new PreviewImage(icon));
		}

		Log.d(DETAIL_LOG_KEY, "content geladen");

	}

	private void setBiggerImage(int sourceID) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 2;
		if (iconBigger != null) {
			iconBigger.recycle();
		}
		iconBigger = BitmapFactory.decodeResource(getResources(), sourceID,
				options);
		selectedMediaView.setImageBitmap(iconBigger);
	}

	private void getContent() {

		// get celebration

		int celebrationNum = -1;
		Intent i = getIntent();
		Bundle bundle = i.getExtras();
		if (bundle.containsKey(Constants.INT)) {
			celebrationNum = bundle.getInt(Constants.INT);
			Log.d(DETAIL_LOG_KEY, "Brauchnummer: " + celebrationNum);
		}

		if (celebrationNum != -1) {

			celebration = SDOManager.getCelebration(celebrationNum);
			Log.d(DETAIL_LOG_KEY,
					"" + getResources().getString(celebration.getTitleSource()));

			// get content

			if (celebration != null) {
				title = getResources().getString(celebration.getTitleSource());
				text = getResources().getString(celebration.getTextSource());
				// category = celebration.getCategory();
				// soundSources = celebration.getSoundSources();

				imageSources = celebration.getImageSources();
				videoSources = celebration.getVideoSources();
				setContent();

			} else {
				Log.d(DETAIL_LOG_KEY, "Brauch nicht gefunden!");
			}

		} else {
			Log.d(DETAIL_LOG_KEY, "Fehler im Intent");
		}

	}

	private void setupUI() {

		// hide action bar

		int currentApiVersion = android.os.Build.VERSION.SDK_INT;
		if (currentApiVersion >= 11)
			getActionBar().hide();

		// register views

		headlineView = (TextView) findViewById(R.id.detail_screen_headline);
		descriptionView = (TextView) findViewById(R.id.detail_screen_text);
		backButton = (Button) findViewById(R.id.detail_screen_back_button);
		mediaView = (ListView) findViewById(R.id.detail_screen_media_selection);
		selectedMediaView = (ImageView) findViewById(R.id.detail_screen_main_image);
		galleryView = (ImageView) findViewById(R.id.detail_gallery);
		swipeRight = (ImageView) findViewById(R.id.swipeRight);
		swipeLeft = (ImageView) findViewById(R.id.swipeLeft);
		videoPlayer = (VideoView) findViewById(R.id.video_player);
		advice = (TextView) findViewById(R.id.detail_advice);

		// ListView
		imageSources = new ArrayList<Integer>();
		images = new ArrayList<PreviewImage>();
		soundSources = new ArrayList<Integer>();
		videoSources = new ArrayList<Integer>();

		adapter = new PreviewImageAdapter(this, images);
		mediaView.setAdapter(adapter);

		// register listeners

		backButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				stopCountDown();
				Log.d(DETAIL_LOG_KEY, "Zur�ck");
				finish();
			}
		});

		mediaView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				resetTimer();

				if (iconBig != null)
					iconBig.recycle();

				if (position >= imageSources.size()
						&& position < imageSources.size() + soundSources.size()) {

					// --- SOUNDS ---

					Log.d(DETAIL_LOG_KEY, "Sound angefordert: position = "
							+ position);
					soundID = soundSources.get(position - imageSources.size());

					MediaPlayer mp = MediaPlayer.create(DetailActivity.this,
							soundID);
					mp.setOnCompletionListener(new OnCompletionListener() {

						@Override
						public void onCompletion(MediaPlayer mp) {
							mp.release();
						}
					});
					mp.start();

				} else {

					// --- VIDEOS ---

					if (position >= imageSources.size() + soundSources.size()) {

						selectedMediaView.setVisibility(View.INVISIBLE);
						videoPlayer.setVisibility(View.VISIBLE);
						ScaleAnimation animation = new ScaleAnimation(0,
								Animation.RELATIVE_TO_SELF, 0,
								Animation.RELATIVE_TO_SELF, selectedMediaView
										.getRight(), view.getY());
						animation.setDuration(500);
						videoPlayer.startAnimation(animation);

						MediaController mediaController = new MediaController(
								DetailActivity.this);
						mediaController.setAnchorView(videoPlayer);
						Uri uri = Uri.parse("android.resource://"
								+ getPackageName()
								+ "/"
								+ videoSources.get(position
										- imageSources.size()
										- soundSources.size()));
						videoPlayer.setMediaController(mediaController);
						videoPlayer.setVideoURI(uri);
						videoPlayer.requestFocus();
						advice.setVisibility(view.INVISIBLE);
						videoPlayer.start();

					} else {

						// --- IMAGES ---

						videoPlayer.setVisibility(View.INVISIBLE);
						selectedMediaView.setVisibility(View.VISIBLE);

						ScaleAnimation animation = new ScaleAnimation(0,
								Animation.RELATIVE_TO_SELF, 0,
								Animation.RELATIVE_TO_SELF, selectedMediaView
										.getRight(), view.getY());
						animation.setDuration(500);
						selectedImage = imageSources.get(position);
						setBiggerImage(selectedImage);
						imgPos = position;
						selectedMediaView.startAnimation(animation);

					}

				}

			}
		});

		selectedMediaView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				resetTimer();
				Log.d(DETAIL_LOG_KEY, "Bild gew�hlt");

				Display display = getWindowManager().getDefaultDisplay();
				Point size = new Point();
				display.getSize(size);
				int width = size.x;
				int height = size.y;

				if (!clicked) {

					ScaleAnimation animation = new ScaleAnimation(0,
							Animation.RELATIVE_TO_SELF, 0,
							Animation.RELATIVE_TO_SELF, width / 2, height / 2);
					animation.setDuration(500);
					setBigImage(selectedImage);

					galleryView.setVisibility(View.VISIBLE);
					galleryView.startAnimation(animation);
					clicked = true;

					swipeRight.setVisibility(View.VISIBLE);
					swipeLeft.setVisibility(View.VISIBLE);

					if (imgPos == imageSources.size() - 1) {
						swipeRight.setVisibility(View.INVISIBLE);
					}

					if (imgPos == 0) {
						swipeLeft.setVisibility(View.INVISIBLE);
					}

					advice.setVisibility(View.INVISIBLE);

				} else {

				}

			}

		});

		galleryView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				resetTimer();

				Display display = getWindowManager().getDefaultDisplay();
				Point size = new Point();
				display.getSize(size);
				int width = size.x;
				int height = size.y;

				ScaleAnimation animation = new ScaleAnimation(
						Animation.RELATIVE_TO_SELF, 0,
						Animation.RELATIVE_TO_SELF, 0, width / 2, height / 2);
				animation.setDuration(500);
				galleryView.startAnimation(animation);
				galleryView.setVisibility(View.INVISIBLE);
				clicked = false;

				swipeRight.setVisibility(View.INVISIBLE);
				swipeLeft.setVisibility(View.INVISIBLE);
				advice.setVisibility(View.VISIBLE);

			}
		});

		galleryView.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(final View view, final MotionEvent event) {
				return gdt.onTouchEvent(event);
			}
		});

		// other

		galleryView.setVisibility(View.INVISIBLE);
		swipeRight.setVisibility(View.INVISIBLE);
		swipeLeft.setVisibility(View.INVISIBLE);
		descriptionView.setScrollbarFadingEnabled(false);

		// set text properties

		descriptionView.setLineSpacing(1, (float) 1.2);
		descriptionView.setMovementMethod(new ScrollingMovementMethod());
		headlineView.setTypeface(null, Typeface.BOLD_ITALIC);
		backButton.setTypeface(null, Typeface.BOLD);

	}

	private class GestureListener extends SimpleOnGestureListener {

		// GALLERY SWIPE

		private final int SWIPE_MIN_DISTANCE = 120;
		private final int SWIPE_THRESHOLD_VELOCITY = 200;

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
					&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {

				// Right to left

				Log.d(DETAIL_LOG_KEY, "NACH RECHTS");

				if (imgPos < imageSources.size() - 1) {
					imgPos++;
					TranslateAnimation animation = new TranslateAnimation(
							galleryView.getX() + galleryView.getWidth(),
							Animation.RELATIVE_TO_PARENT,
							Animation.RELATIVE_TO_PARENT,
							Animation.RELATIVE_TO_PARENT);
					animation.setDuration(500);
					setBigImage(imageSources.get(imgPos));
					galleryView.startAnimation(animation);
					swipeLeft.setVisibility(View.VISIBLE);
					if (imgPos == imageSources.size() - 1) {
						swipeRight.setVisibility(View.INVISIBLE);
					}
				}

				return true;

			} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
					&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {

				// Left to right

				Log.d(DETAIL_LOG_KEY, "NACH LINKS");

				if (imgPos > 0) {
					imgPos--;
					TranslateAnimation animation = new TranslateAnimation(
							galleryView.getX() - galleryView.getWidth(),
							Animation.RELATIVE_TO_PARENT,
							Animation.RELATIVE_TO_PARENT,
							Animation.RELATIVE_TO_PARENT);
					animation.setDuration(500);
					setBigImage(imageSources.get(imgPos));
					galleryView.startAnimation(animation);
					swipeRight.setVisibility(View.VISIBLE);

					if (imgPos == 0) {
						swipeLeft.setVisibility(View.INVISIBLE);
					}
				}

				return true;
			}

			return false;
		}
	}
}
