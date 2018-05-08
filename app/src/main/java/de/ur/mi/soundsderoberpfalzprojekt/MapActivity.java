package de.ur.mi.soundsderoberpfalzprojekt;

import java.util.ArrayList;
import java.util.Collections;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class MapActivity extends Activity implements OnClickListener,
		OnItemSelectedListener {

	// instance variables

	public static final String MAP_LOG = "map";

	private Button buttonHelp, buttonStart, buttonBergfeste, buttonBraeuche,
			buttonKirwas, buttonDetail;

	private Button kirwaErbendorf, kirwaNeustadt, kirwaAmberg, kirwaFronberg,
			kirwaThalersdorf, kirwaTrautmannshofen, kirwaKallmuenz,
			brauchDrachenstich, brauchPfingstritt, brauchFischzug,
			brauchChinesenfasching, brauchOiasinga, brauchMaibaumaufstellen,
			brauchPavillon, brauchSpecht, brauchOarschboussn,
			brauchMoisterratschn, bergfestSchmidmuehlen, bergfestAuerbach,
			bergfestFreudenberg, bergfestAmberg, bergfestSulzbachRosenberg,
			bergfestSchnaittenbach, bergfestHahnbach, bergfestMausberg,
			bergfestKreuzberg, bergfestEnsdorf, bergfestWaldthurn,
			bergfestPfreimd, bergfestPleystein;

	private ArrayList<Button> kirwas, braeuche, bergfeste;
	private Spinner searchCelebration;
	private TextView selectedCelebrationtitle, celebrationDescription;
	private ImageView map;
	private int contentID;
	private Button clickedButton;
	private ArrayAdapter adapter;
	ArrayList<String> celebrationTitle;
	private CountDownTimer countDown;

	private boolean spinnerActivated = false;

	// setup UI components

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupUIComponents();
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

	private void resetTimer() {
		countDown.cancel();
		countDown.start();
		// TODO Auto-generated method stub

	}

	private void stopCountDown() {
		if (countDown != null) {
			countDown.cancel();
		}
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		setupTimer();
	}

	@SuppressLint("NewApi")
	private void setupUIComponents() {
		hideActionbar();
		setupUI();
		setupSpinner();
		setupButtons();
		showMapKirwas();
		setLayoutOverview();

	}

	private void hideActionbar() {
		int currentApiVersion = android.os.Build.VERSION.SDK_INT;
		if (currentApiVersion >= 11)
			getActionBar().hide();
	}

	private void setupUI() {
		setContentView(R.layout.activity_map);

		buttonHelp = (Button) findViewById(R.id.buttonHilfe);
		buttonStart = (Button) findViewById(R.id.buttonToStart);
		buttonKirwas = (Button) findViewById(R.id.buttonKirwas);
		buttonBergfeste = (Button) findViewById(R.id.buttonBergfeste);
		buttonBraeuche = (Button) findViewById(R.id.buttonBraeuche);
		selectedCelebrationtitle = (TextView) findViewById(R.id.selectedCelebrationTitle);
		celebrationDescription = (TextView) findViewById(R.id.celebrationDescription);
		// celebrationDescription.setScrollbarFadingEnabled(false);
		map = (ImageView) findViewById(R.id.map);
		buttonDetail = (Button) findViewById(R.id.buttonDetail);
		searchCelebration = (Spinner) findViewById(R.id.search_celebration);

		selectedCelebrationtitle.setTypeface(null, Typeface.ITALIC);
		selectedCelebrationtitle.setVisibility(View.VISIBLE);

	}

	private void setupSpinner() {
		celebrationTitle = new ArrayList<String>();

		for (int i = 0; i < SDOManager.getNumCelebrations(); i++) {
			int titleSource = SDOManager.getCelebrationList().get(i)
					.getTitleSource();
			celebrationTitle.add(this.getString(titleSource));
		}

		Collections.sort(celebrationTitle);
		celebrationTitle.add(0, "Vyhledávání");

		adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,
				celebrationTitle);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		searchCelebration.setAdapter(adapter);
		searchCelebration.setOnItemSelectedListener(this);

	}

	private void setupButtons() {

		kirwaErbendorf = (Button) findViewById(R.id.button_kirwa_erbendorf);
		kirwaNeustadt = (Button) findViewById(R.id.button_kirwa_neustadt);
		kirwaAmberg = (Button) findViewById(R.id.button_kirwa_amberg);
		kirwaFronberg = (Button) findViewById(R.id.button_kirwa_fronberg);
		kirwaThalersdorf = (Button) findViewById(R.id.button_kirwa_thalersdorf);
		kirwaTrautmannshofen = (Button) findViewById(R.id.button_kirwa_trautmannshofen);
		kirwaKallmuenz = (Button) findViewById(R.id.button_kirwa_kallmuenz);

		brauchDrachenstich = (Button) findViewById(R.id.button_brauch_drachenstich);
		brauchPfingstritt = (Button) findViewById(R.id.button_brauch_pfingstritt);
		brauchFischzug = (Button) findViewById(R.id.button_brauch_fischzug);
		brauchChinesenfasching = (Button) findViewById(R.id.button_brauch_chinesenfasching);
		brauchOiasinga = (Button) findViewById(R.id.button_brauch_oiasinga);
		brauchMaibaumaufstellen = (Button) findViewById(R.id.button_brauch_maibaumaufstellen);
		brauchPavillon = (Button) findViewById(R.id.button_brauch_pavillon);
		brauchSpecht = (Button) findViewById(R.id.button_brauch_specht);
		brauchOarschboussn = (Button) findViewById(R.id.button_brauch_oarschboussn);
		brauchMoisterratschn = (Button) findViewById(R.id.button_brauch_moisterratschn);

		bergfestSchmidmuehlen = (Button) findViewById(R.id.button_bergfest_schmidmuehlen);
		bergfestAuerbach = (Button) findViewById(R.id.button_bergfest_auerbach);
		bergfestFreudenberg = (Button) findViewById(R.id.button_bergfest_freudenberg);
		bergfestAmberg = (Button) findViewById(R.id.button_bergfest_amberg);
		bergfestSulzbachRosenberg = (Button) findViewById(R.id.button_bergfest_sulzbach_rosenberg);
		bergfestSchnaittenbach = (Button) findViewById(R.id.button_bergfest_schnaittenbach);
		bergfestHahnbach = (Button) findViewById(R.id.button_bergfest_hahnbach);
		bergfestMausberg = (Button) findViewById(R.id.button_bergfest_mausberg);
		bergfestKreuzberg = (Button) findViewById(R.id.button_bergfest_kreuzberg);
		bergfestEnsdorf = (Button) findViewById(R.id.button_bergfest_ensdorf);
		bergfestWaldthurn = (Button) findViewById(R.id.button_bergfest_waldthurn);
		bergfestPfreimd = (Button) findViewById(R.id.button_bergfest_pfreimd);
		bergfestPleystein = (Button) findViewById(R.id.button_bergfest_pleystein);

		setOnClickListeners();
	}

	private void setOnClickListeners() {

		buttonHelp.setOnClickListener(this);
		buttonStart.setOnClickListener(this);
		buttonBergfeste.setOnClickListener(this);
		buttonBraeuche.setOnClickListener(this);
		buttonKirwas.setOnClickListener(this);
		buttonDetail.setOnClickListener(this);

		kirwas = new ArrayList<Button>();
		braeuche = new ArrayList<Button>();
		bergfeste = new ArrayList<Button>();

		braeuche.add(brauchDrachenstich);
		braeuche.add(brauchPfingstritt);
		braeuche.add(brauchFischzug);
		braeuche.add(brauchChinesenfasching);
		braeuche.add(brauchOiasinga);
		braeuche.add(brauchMaibaumaufstellen);
		braeuche.add(brauchPavillon);
		braeuche.add(brauchSpecht);
		braeuche.add(brauchOarschboussn);
		braeuche.add(brauchMoisterratschn);

		kirwas.add(kirwaErbendorf);
		kirwas.add(kirwaNeustadt);
		kirwas.add(kirwaAmberg);
		kirwas.add(kirwaFronberg);
		kirwas.add(kirwaThalersdorf);
		kirwas.add(kirwaTrautmannshofen);
		kirwas.add(kirwaKallmuenz);

		bergfeste.add(bergfestSchmidmuehlen);
		bergfeste.add(bergfestAuerbach);
		bergfeste.add(bergfestFreudenberg);
		bergfeste.add(bergfestAmberg);
		bergfeste.add(bergfestSulzbachRosenberg);
		bergfeste.add(bergfestSchnaittenbach);
		bergfeste.add(bergfestHahnbach);
		bergfeste.add(bergfestMausberg);
		bergfeste.add(bergfestKreuzberg);
		bergfeste.add(bergfestEnsdorf);
		bergfeste.add(bergfestWaldthurn);
		bergfeste.add(bergfestPfreimd);
		bergfeste.add(bergfestPleystein);

		for (int i = 0; i < bergfeste.size(); i++) {
			bergfeste.get(i).setOnClickListener(this);
		}
		for (int i = 0; i < kirwas.size(); i++) {
			kirwas.get(i).setOnClickListener(this);
		}
		for (int i = 0; i < braeuche.size(); i++) {
			braeuche.get(i).setOnClickListener(this);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map_kirwas, menu);
		return true;
	}

	// handle onClick-events

	@Override
	public void onClick(View v) {
		resetTimer();
		celebrationDescription.scrollTo(0, 0);
		switch (v.getId()) {
		case R.id.buttonToStart:
			Log.d(MAP_LOG, "clicked");
			returnToStartScreen();
			break;
		case R.id.buttonHilfe:
			Log.d(MAP_LOG, "clicked");
			showHelp();
			break;
		case R.id.buttonBergfeste:
			resetButtons();
			showMapBergfeste();
			setLayoutOverview();
			break;
		case R.id.buttonBraeuche:
			resetButtons();
			showMapBraeuche();
			setLayoutOverview();
			break;
		case R.id.buttonKirwas:
			resetButtons();
			showMapKirwas();
			setLayoutOverview();
			break;
		case R.id.buttonDetail:
			stopCountDown();
			Log.d(MAP_LOG, "clicked");
			showCelebrationDetail();
			break;
		// marks of category Kirwan
		case R.id.button_kirwa_erbendorf:
			if (clickedButton != null) {
				clickedButton
						.setBackgroundResource(R.drawable.map_button_unclicked2);
			}
			clickedButton = kirwaErbendorf;
			contentID = Constants.KirwaErbendorf;
			setContent(contentID);
			break;
		case R.id.button_kirwa_neustadt:
			if (clickedButton != null) {
				clickedButton
						.setBackgroundResource(R.drawable.map_button_unclicked2);
			}
			clickedButton = kirwaNeustadt;
			contentID = Constants.KirwaNeustadt;
			setContent(contentID);
			break;
		case R.id.button_kirwa_amberg:
			if (clickedButton != null) {
				clickedButton
						.setBackgroundResource(R.drawable.map_button_unclicked2);
			}
			clickedButton = kirwaAmberg;
			contentID = Constants.KirwaLkrAmberg;
			setContent(contentID);
			break;
		case R.id.button_kirwa_fronberg:
			if (clickedButton != null) {
				clickedButton
						.setBackgroundResource(R.drawable.map_button_unclicked2);
			}
			clickedButton = kirwaFronberg;
			contentID = Constants.KirwaFronberg;
			setContent(contentID);
			break;
		case R.id.button_kirwa_thalersdorf:
			if (clickedButton != null) {
				clickedButton
						.setBackgroundResource(R.drawable.map_button_unclicked2);
			}
			clickedButton = kirwaThalersdorf;
			contentID = Constants.KirwaThalersdorf;
			setContent(contentID);
			break;
		case R.id.button_kirwa_trautmannshofen:
			if (clickedButton != null) {
				clickedButton
						.setBackgroundResource(R.drawable.map_button_unclicked2);
			}
			clickedButton = kirwaTrautmannshofen;
			contentID = Constants.KirwaTrautmannshofen;
			setContent(contentID);
			break;
		case R.id.button_kirwa_kallmuenz:
			if (clickedButton != null) {
				clickedButton
						.setBackgroundResource(R.drawable.map_button_unclicked2);
			}
			clickedButton = kirwaKallmuenz;
			contentID = Constants.KirwaKallmuenz;
			setContent(contentID);
			break;

		// marks of category Bergfeste

		case R.id.button_bergfest_schmidmuehlen:
			if (clickedButton != null) {
				clickedButton
						.setBackgroundResource(R.drawable.map_button_unclicked2);
			}
			clickedButton = bergfestSchmidmuehlen;
			contentID = Constants.BergfestSchmidmuehlen;
			setContent(contentID);
			break;
		case R.id.button_bergfest_auerbach:
			if (clickedButton != null) {
				clickedButton
						.setBackgroundResource(R.drawable.map_button_unclicked2);
			}
			clickedButton = bergfestAuerbach;
			contentID = Constants.BergfestAuerbach;
			setContent(contentID);
			break;
		case R.id.button_bergfest_freudenberg:
			if (clickedButton != null) {
				clickedButton
						.setBackgroundResource(R.drawable.map_button_unclicked2);
			}
			clickedButton = bergfestFreudenberg;
			contentID = Constants.BergfestFreudenberg;
			setContent(contentID);
			break;
		case R.id.button_bergfest_amberg:
			if (clickedButton != null) {
				clickedButton
						.setBackgroundResource(R.drawable.map_button_unclicked2);
			}
			clickedButton = bergfestAmberg;
			contentID = Constants.BergfestAmberg;
			setContent(contentID);
			break;
		case R.id.button_bergfest_sulzbach_rosenberg:
			if (clickedButton != null) {
				clickedButton
						.setBackgroundResource(R.drawable.map_button_unclicked2);
			}
			clickedButton = bergfestSulzbachRosenberg;
			contentID = Constants.BergfestSulzbachRosenberg;
			setContent(contentID);
			break;
		case R.id.button_bergfest_schnaittenbach:
			if (clickedButton != null) {
				clickedButton
						.setBackgroundResource(R.drawable.map_button_unclicked2);
			}
			clickedButton = bergfestSchnaittenbach;
			contentID = Constants.BergfestSchnaittenbach;
			setContent(contentID);
			break;
		case R.id.button_bergfest_hahnbach:
			if (clickedButton != null) {
				clickedButton
						.setBackgroundResource(R.drawable.map_button_unclicked2);
			}
			clickedButton = bergfestHahnbach;
			contentID = Constants.BergfestHahnbach;
			setContent(contentID);
			break;
		case R.id.button_bergfest_mausberg:
			if (clickedButton != null) {
				clickedButton
						.setBackgroundResource(R.drawable.map_button_unclicked2);
			}
			clickedButton = bergfestMausberg;
			contentID = Constants.BergfestMausberg;
			setContent(contentID);
			break;
		case R.id.button_bergfest_kreuzberg:
			if (clickedButton != null) {
				clickedButton
						.setBackgroundResource(R.drawable.map_button_unclicked2);
			}
			clickedButton = bergfestKreuzberg;
			contentID = Constants.BergfestKreuzberg;
			setContent(contentID);
			break;
		case R.id.button_bergfest_ensdorf:
			if (clickedButton != null) {
				clickedButton
						.setBackgroundResource(R.drawable.map_button_unclicked2);
			}
			clickedButton = bergfestEnsdorf;
			contentID = Constants.BergfestEnsdorf;
			setContent(contentID);
			break;
		case R.id.button_bergfest_waldthurn:
			if (clickedButton != null) {
				clickedButton
						.setBackgroundResource(R.drawable.map_button_unclicked2);
			}
			clickedButton = bergfestWaldthurn;
			contentID = Constants.BergfestWaldthurn;
			setContent(contentID);
			break;
		case R.id.button_bergfest_pfreimd:
			if (clickedButton != null) {
				clickedButton
						.setBackgroundResource(R.drawable.map_button_unclicked2);
			}
			clickedButton = bergfestPfreimd;
			contentID = Constants.BergfestPfreimd;
			setContent(contentID);
			break;
		case R.id.button_bergfest_pleystein:
			if (clickedButton != null) {
				clickedButton
						.setBackgroundResource(R.drawable.map_button_unclicked2);
			}
			clickedButton = bergfestPleystein;
			contentID = Constants.BergfestPleystein;
			setContent(contentID);
			break;

		// marks of category Kuriosa

		case R.id.button_brauch_drachenstich:
			if (clickedButton != null) {
				clickedButton
						.setBackgroundResource(R.drawable.map_button_unclicked2);
			}
			clickedButton = brauchDrachenstich;
			contentID = Constants.BrauchDrachenstich;
			setContent(contentID);
			break;
		case R.id.button_brauch_pfingstritt:
			if (clickedButton != null) {
				clickedButton
						.setBackgroundResource(R.drawable.map_button_unclicked2);
			}
			clickedButton = brauchPfingstritt;
			contentID = Constants.BrauchPfingstritt;
			setContent(contentID);
			break;
		case R.id.button_brauch_fischzug:
			if (clickedButton != null) {
				clickedButton
						.setBackgroundResource(R.drawable.map_button_unclicked2);
			}
			clickedButton = brauchFischzug;
			contentID = Constants.BrauchFischzug;
			setContent(contentID);
			break;
		case R.id.button_brauch_chinesenfasching:
			if (clickedButton != null) {
				clickedButton
						.setBackgroundResource(R.drawable.map_button_unclicked2);
			}
			clickedButton = brauchChinesenfasching;
			contentID = Constants.BrauchChinesenfasching;
			setContent(contentID);
			break;
		case R.id.button_brauch_oiasinga:
			if (clickedButton != null) {
				clickedButton
						.setBackgroundResource(R.drawable.map_button_unclicked2);
			}
			clickedButton = brauchOiasinga;
			contentID = Constants.BrauchOiasinga;
			setContent(contentID);
			break;
		case R.id.button_brauch_maibaumaufstellen:
			if (clickedButton != null) {
				clickedButton
						.setBackgroundResource(R.drawable.map_button_unclicked2);
			}
			clickedButton = brauchMaibaumaufstellen;
			contentID = Constants.BrauchMaibaumaufstellen;
			setContent(contentID);
			break;
		case R.id.button_brauch_pavillon:
			if (clickedButton != null) {
				clickedButton
						.setBackgroundResource(R.drawable.map_button_unclicked2);
			}
			clickedButton = brauchPavillon;
			contentID = Constants.BrauchPavillon;
			setContent(contentID);
			break;
		case R.id.button_brauch_specht:
			if (clickedButton != null) {
				clickedButton
						.setBackgroundResource(R.drawable.map_button_unclicked2);
			}
			clickedButton = brauchSpecht;
			contentID = Constants.BrauchSpecht;
			setContent(contentID);
			break;
		case R.id.button_brauch_oarschboussn:
			if (clickedButton != null) {
				clickedButton
						.setBackgroundResource(R.drawable.map_button_unclicked2);
			}
			clickedButton = brauchOarschboussn;
			contentID = Constants.BrauchOarschboussn;
			setContent(contentID);
			break;
		case R.id.button_brauch_moisterratschn:
			if (clickedButton != null) {
				clickedButton
						.setBackgroundResource(R.drawable.map_button_unclicked2);
			}
			clickedButton = brauchMoisterratschn;
			contentID = Constants.BrauchMoisterratschn;
			setContent(contentID);
			break;
		}

	}

	// reset background for map buttons
	private void resetButtons() {
		for (int i = 0; i < kirwas.size(); i++) {
			kirwas.get(i).setBackgroundResource(
					R.drawable.map_button_unclicked2);
		}
		for (int i = 0; i < bergfeste.size(); i++) {
			bergfeste.get(i).setBackgroundResource(
					R.drawable.map_button_unclicked2);
		}
		for (int i = 0; i < braeuche.size(); i++) {
			braeuche.get(i).setBackgroundResource(
					R.drawable.map_button_unclicked2);
		}
	}

	// set textviews with content of selected celebrations
	private void setContent(int contentID) {

		Celebration clickedCelebration = SDOManager.getCelebration(contentID);
		String title = this.getString(clickedCelebration.getTitleSource());

		clickedButton.setBackgroundResource(R.drawable.map_button_clicked);
		setCelebrationTitle(title);

		if (clickedCelebration.getImageSources().size() == 0) {
			setDetailButton(false);
			String description = this.getString(clickedCelebration
					.getTextSource());
			setCelebrationDescription(description);
		} else {
			setDetailButton(true);
			setCelebrationDescription("");
		}

	}

	// shows title of selected celebration
	private void setCelebrationTitle(String title) {
		selectedCelebrationtitle.setTextColor(Color.WHITE);
		selectedCelebrationtitle.setText(title);

		ScaleAnimation animation = new ScaleAnimation(0,
				Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF,
				map.getX() + 100, map.getY() + 50);
		animation.setDuration(500);

		selectedCelebrationtitle.setVisibility(View.VISIBLE);
		selectedCelebrationtitle.startAnimation(animation);
		selectedCelebrationtitle.setTypeface(null, Typeface.ITALIC);

	}

	// shows description of appropriate celebration next to map
	private void setCelebrationDescription(String description) {
		celebrationDescription.setText(description);
		celebrationDescription.setLineSpacing(1, (float) 1.2);
		celebrationDescription.setMovementMethod(new ScrollingMovementMethod());
		celebrationDescription.setScrollY(0);
	}

	// shows/hides detailButton
	private void setDetailButton(Boolean isVisible) {
		if (isVisible) {
			buttonDetail.setVisibility(View.VISIBLE);
			buttonDetail.setEnabled(true);
		} else {
			buttonDetail.setVisibility(View.INVISIBLE);
			buttonDetail.setEnabled(false);
		}
	}

	// opens detailActivity with appropriate content
	private void showCelebrationDetail() {
		Intent i = new Intent(MapActivity.this, DetailActivity.class);
		i.putExtra(Constants.INT, contentID);
		startActivity(i);

	}

	// layout components after a category was selected
	private void setLayoutOverview() {

		selectedCelebrationtitle.setTextColor(getResources().getColor(
				R.color.custom_white));
		celebrationDescription.setTextColor(Color.WHITE);

		ScaleAnimation animation = new ScaleAnimation(0,
				Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF,
				buttonKirwas.getX(), buttonKirwas.getY());
		animation.setDuration(500);

		selectedCelebrationtitle.startAnimation(animation);
		selectedCelebrationtitle.setTypeface(null, Typeface.BOLD_ITALIC);
		celebrationDescription.startAnimation(animation);
		celebrationDescription.setVisibility(View.VISIBLE);
		celebrationDescription.setScrollX(0);
		buttonDetail.setVisibility(View.INVISIBLE);

	}

	// sets up UI for category Kirwan
	private void showMapKirwas() {
		showKirwas();
		setButton(buttonKirwas, buttonBergfeste, buttonBraeuche);
		selectedCelebrationtitle.setText(getString(R.string.button_kirwan));
		celebrationDescription.setText(getString(R.string.description_kirwan));

	}

	// sets up UI for category Kuriosa
	private void showMapBraeuche() {
		showBraeuche();
		setButton(buttonBraeuche, buttonKirwas, buttonBergfeste);

		selectedCelebrationtitle.setText(getString(R.string.button_kuriosa));
		celebrationDescription.setText(getString(R.string.description_kuriosa));

	}

	// sets up UI for category Bergfeste
	private void showMapBergfeste() {
		showBergfeste();
		setButton(buttonBergfeste, buttonBraeuche, buttonKirwas);

		selectedCelebrationtitle.setText(getString(R.string.button_bergfeste));
		celebrationDescription
				.setText(getString(R.string.description_bergfeste));

	}

	// opens helpActivity
	private void showHelp() {
		stopCountDown();
		Intent i = new Intent(MapActivity.this, HelpActivity.class);
		startActivity(i);
	}

	// finishes mapActivity returns to startActivity
	private void returnToStartScreen() {
		stopCountDown();
		Intent intent = new Intent(MapActivity.this, StartScreen.class);
		startActivity(intent);
		finish();
	}

	private void preventOutOfMemoryError() {
		cleanImage(map.getDrawable());
		map.destroyDrawingCache();

		for (int i = 0; i < kirwas.size(); i++) {
			cleanImage(kirwas.get(i).getBackground());
			kirwas.get(i).destroyDrawingCache();
		}

		for (int i = 0; i < bergfeste.size(); i++) {
			cleanImage(bergfeste.get(i).getBackground());
			bergfeste.get(i).destroyDrawingCache();
		}

		for (int i = 0; i < braeuche.size(); i++) {
			cleanImage(braeuche.get(i).getBackground());
			braeuche.get(i).destroyDrawingCache();
		}

		kirwas = null;
		bergfeste = null;
		braeuche = null;
		map = null;
	}

	private void cleanImage(Drawable image) {
		BitmapDrawable bitmapDrawable = (BitmapDrawable) image;
		Bitmap bitmap = bitmapDrawable.getBitmap();
		bitmap.recycle();
	}

	// show and enable marks of Kirwa on the map
	private void showKirwas() {
		buttonKirwas.setEnabled(false);
		buttonBraeuche.setEnabled(true);
		buttonBergfeste.setEnabled(true);

		for (int i = 0; i < kirwas.size(); i++) {
			kirwas.get(i).setVisibility(View.VISIBLE);
			kirwas.get(i).setEnabled(true);
		}
		for (int i = 0; i < bergfeste.size(); i++) {
			bergfeste.get(i).setVisibility(View.INVISIBLE);
			bergfeste.get(i).setEnabled(false);
		}
		for (int i = 0; i < braeuche.size(); i++) {
			braeuche.get(i).setVisibility(View.INVISIBLE);
			braeuche.get(i).setEnabled(false);
		}
	}

	// show and enable marks of Bergfeste on the map
	private void showBergfeste() {
		buttonKirwas.setEnabled(true);
		buttonBraeuche.setEnabled(true);
		buttonBergfeste.setEnabled(false);

		for (int i = 0; i < kirwas.size(); i++) {
			kirwas.get(i).setVisibility(View.INVISIBLE);
			kirwas.get(i).setEnabled(false);
		}
		for (int i = 0; i < bergfeste.size(); i++) {
			bergfeste.get(i).setVisibility(View.VISIBLE);
			bergfeste.get(i).setEnabled(true);
		}
		for (int i = 0; i < braeuche.size(); i++) {
			braeuche.get(i).setVisibility(View.INVISIBLE);
			braeuche.get(i).setEnabled(false);
		}
	}

	// show and enable marks of Kuriosa on the map
	private void showBraeuche() {
		buttonKirwas.setEnabled(true);
		buttonBraeuche.setEnabled(false);
		buttonBergfeste.setEnabled(true);

		for (int i = 0; i < kirwas.size(); i++) {
			kirwas.get(i).setVisibility(View.INVISIBLE);
			kirwas.get(i).setEnabled(false);
		}
		for (int i = 0; i < bergfeste.size(); i++) {
			bergfeste.get(i).setVisibility(View.INVISIBLE);
			bergfeste.get(i).setEnabled(false);
		}
		for (int i = 0; i < braeuche.size(); i++) {
			braeuche.get(i).setVisibility(View.VISIBLE);
			braeuche.get(i).setEnabled(true);
		}
	}

	// disable and scale selected button
	private void setButton(Button disabledButton, Button enabledButton1,
			Button enabledButton2) {

		disabledButton.setEnabled(false);
		disabledButton.setTextColor(getResources().getColor(
				R.color.custom_black_1));
		enabledButton1.setEnabled(true);
		enabledButton1.setTextColor(Color.WHITE);
		enabledButton2.setEnabled(true);
		enabledButton2.setTextColor(Color.WHITE);

		disabledButton.setScaleX((float) 1.18);
		disabledButton.setScaleY((float) 1.18);

		enabledButton1.setScaleX((float) 0.9);
		enabledButton1.setScaleY((float) 0.9);

		enabledButton2.setScaleX((float) 0.9);
		enabledButton2.setScaleY((float) 0.9);
	}

	// TODO was passiert hier?

	// private void showCelebrationTitle () {
	// selectedCelebrationtitle.setVisibility(View.VISIBLE);
	// buttonDetail.setVisibility(View.VISIBLE);
	// buttonDetail.setEnabled(true);
	// }

	// handle user interaction with spinner
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int pos,
			long arg3) {
		int originalPos = 0;
		Log.d(DetailActivity.DETAIL_LOG_KEY, "ADAPTER POSITION: " + pos);
		if (pos != 0) {
			for (int i = 0; i < SDOManager.getNumCelebrations(); i++) {
				int nextTitleResource = SDOManager.getCelebrationList().get(i)
						.getTitleSource();
				if (this.getString(nextTitleResource).equals(
						celebrationTitle.get(pos))) {
					originalPos = i;
					Log.d(DetailActivity.DETAIL_LOG_KEY, "ADAPTER POSITION: "
							+ originalPos);

					break;
				}
			}
			for (int i = 0; i < braeuche.size(); i++) {
				if (originalPos == i) {
					if (buttonBraeuche.isEnabled()) {
						buttonBraeuche.performClick();
					}
					braeuche.get(i).performClick();
				}
			}
			for (int i = 0; i < bergfeste.size(); i++) {
				if (originalPos == i + braeuche.size()) {
					if (buttonBergfeste.isEnabled()) {
						buttonBergfeste.performClick();
					}
					bergfeste.get(i).performClick();
				}
			}
			for (int i = 0; i < kirwas.size(); i++) {
				if (originalPos == i + braeuche.size() + bergfeste.size()) {
					if (buttonKirwas.isEnabled()) {
						buttonKirwas.performClick();

					}
					kirwas.get(i).performClick();
				}
			}
		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
