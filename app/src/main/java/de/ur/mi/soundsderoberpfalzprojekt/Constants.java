package de.ur.mi.soundsderoberpfalzprojekt;

import java.util.ArrayList;

import android.graphics.Point;
import android.view.Display;
import android.widget.ImageView;

public class Constants {
	public static final String KIRCHWEIH = "kirchweih";
	public static final String BERGFEST = "bergfest";
	public static final String KURIOSES = "kurioses";
	public static final String INT = "integer";
	public static final String DEBUG = "Debug";

	public static boolean appStarted = false;

	// Konstanten f�r Animation StartScreen

	public static final int PICTURE_WIDTH = 500;
	public static final int PICTURE_HEIGHT = 500;
	public static final int START_X = 900;
	public static final int START_Y = 20;
	public static final int END_X = -PICTURE_WIDTH;
	public static final int END_Y = 20;
	public static final int DURATION = 20000;
	public static final long TIMEOUT_DURATION = 60000; // value in ms

	// TODO ist es sinnvoll hier die Titel als Index zu nehemen??

	public static final int KirwaErbendorf = R.string.kirwa_erbendorf_titel;
	public static final int KirwaNeustadt = R.string.kirwa_neustadt_titel;
	public static final int KirwaLkrAmberg = R.string.kirwa_amberg_titel;
	public static final int KirwaFronberg = R.string.kirwa_fronberg_titel;
	public static final int KirwaThalersdorf = R.string.kirwa_thalersdorf_titel;
	public static final int KirwaTrautmannshofen = R.string.kirwa_trautmannshofen_titel;
	public static final int KirwaKallmuenz = R.string.kirwa_kallmuenz_titel;

	public static final int BergfestSchmidmuehlen = R.string.bergfest_schmidmuehlen_titel;
	public static final int BergfestAuerbach = R.string.bergfest_auerbach_titel;
	public static final int BergfestFreudenberg = R.string.bergfest_freudenberg_titel;
	public static final int BergfestAmberg = R.string.bergfest_amberg_titel;
	public static final int BergfestSulzbachRosenberg = R.string.bergfest_sulzbach_titel;
	public static final int BergfestSchnaittenbach = R.string.bergfest_schnaittenbach_titel;
	public static final int BergfestHahnbach = R.string.bergfest_hahnbach_titel;
	public static final int BergfestMausberg = R.string.bergfest_mausberg_titel;
	public static final int BergfestKreuzberg = R.string.bergfest_kreuzberg_titel;
	public static final int BergfestEnsdorf = R.string.bergfest_ensdorf_titel;
	public static final int BergfestWaldthurn = R.string.bergfest_waldthurn_titel;
	public static final int BergfestPfreimd = R.string.bergfest_pfreimd_titel;
	public static final int BergfestPleystein = R.string.bergfest_pleystein_titel;

	public static final int BrauchDrachenstich = R.string.drachenstich_titel;
	public static final int BrauchPfingstritt = R.string.pfingstritt_titel;
	public static final int BrauchOiasinga = R.string.oisinga_titel;
	public static final int BrauchFischzug = R.string.fischzug_titel;
	public static final int BrauchMaibaumaufstellen = R.string.maibaum_titel;
	public static final int BrauchChinesenfasching = R.string.chinesenfasching_titel;
	public static final int BrauchPavillon = R.string.pavillion_titel;
	public static final int BrauchSpecht = R.string.specht_titel;
	public static final int BrauchOarschboussn = R.string.oarschboussn_titel;
	public static final int BrauchMoisterratschn = R.string.ratschn_titel;

}
