package de.ur.mi.soundsderoberpfalzprojekt;

import java.util.ArrayList;

public class CelebrationCreator {

	private static ArrayList<Celebration> celebrationList = new ArrayList<Celebration>();

	private static ArrayList<Integer> actualImageSources = new ArrayList<Integer>();
	private static ArrayList<Integer> actualVideoSources = new ArrayList<Integer>();
	private static ArrayList<Integer> galleryImageSources = new ArrayList<Integer>();

	public CelebrationCreator() {
	}

	public static void createCelebrationList() {
		createKuriosa();
		createBergfeste();
		createKirwas();
		// createGallery();
	}

	// private static void createGallery() {
	// galleryImageSources.add(R.drawable.img_drachenstich_1);
	// galleryImageSources.add(R.drawable.img_drachenstich_2);
	// galleryImageSources.add(R.drawable.img_drachenstich_3);
	// galleryImageSources.add(R.drawable.img_drachenstich_4);
	// galleryImageSources.add(R.drawable.img_drachenstich_5);
	// galleryImageSources.add(R.drawable.img_fischzug_1);
	// galleryImageSources.add(R.drawable.img_fischzug_2);
	// galleryImageSources.add(R.drawable.img_fischzug_3);
	// galleryImageSources.add(R.drawable.img_fischzug_4);
	// galleryImageSources.add(R.drawable.img_fischzug_5);
	// galleryImageSources.add(R.drawable.img_fischzug_6);
	// galleryImageSources.add(R.drawable.img_maibaum_1);
	// galleryImageSources.add(R.drawable.img_maibaum_2);
	// galleryImageSources.add(R.drawable.img_maibaum_3);
	// galleryImageSources.add(R.drawable.img_maibaum_4);
	// galleryImageSources.add(R.drawable.img_maibaum_5);
	// galleryImageSources.add(R.drawable.img_pfingstritt_1);
	// galleryImageSources.add(R.drawable.img_pfingstritt_2);
	// galleryImageSources.add(R.drawable.img_pfingstritt_3);
	// galleryImageSources.add(R.drawable.img_pfingstritt_4);
	// galleryImageSources.add(R.drawable.img_sprecht_1);
	// galleryImageSources.add(R.drawable.img_sprecht_2);
	// galleryImageSources.add(R.drawable.img_sprecht_3);
	// galleryImageSources.add(R.drawable.img_sprecht_4);
	// galleryImageSources.add(R.drawable.img_sprecht_5);
	// galleryImageSources.add(R.drawable.img_sprecht_6);
	//
	//
	// // TODO Auto-generated method stub
	//
	// }

	/** 10 Kuriosit�ten werden erstellt **/

	private static void createKuriosa() {

		Celebration drachenstich = new Celebration(R.string.drachenstich_titel,
				R.string.drachenstich_detail, Constants.BrauchDrachenstich);
		actualImageSources.add(R.drawable.img_drachenstich_1);
		actualImageSources.add(R.drawable.img_drachenstich_2);
		actualImageSources.add(R.drawable.img_drachenstich_3);
		actualImageSources.add(R.drawable.img_drachenstich_4);
		actualImageSources.add(R.drawable.img_drachenstich_5);
		createCelebration(drachenstich);
		clearActualSources();

		Celebration pfingstritt = new Celebration(R.string.pfingstritt_titel,
				R.string.pfingstritt_detail, Constants.BrauchPfingstritt);
		actualImageSources.add(R.drawable.img_pfingstritt_1);
		actualImageSources.add(R.drawable.img_pfingstritt_2);
		actualImageSources.add(R.drawable.img_pfingstritt_3);
		actualImageSources.add(R.drawable.img_pfingstritt_4);
		createCelebration(pfingstritt);
		clearActualSources();

		Celebration fischzug = new Celebration(R.string.fischzug_titel,
				R.string.fischzug_detail, Constants.BrauchFischzug);
		actualImageSources.add(R.drawable.img_fischzug_1);
		actualImageSources.add(R.drawable.img_fischzug_2);
		actualImageSources.add(R.drawable.img_fischzug_3);
		actualImageSources.add(R.drawable.img_fischzug_4);
		actualImageSources.add(R.drawable.img_fischzug_5);
		actualImageSources.add(R.drawable.img_fischzug_6);
		createCelebration(fischzug);
		clearActualSources();

		Celebration chinesenfasching = new Celebration(
				R.string.chinesenfasching_titel,
				R.string.chinesenfasching_detail,
				Constants.BrauchChinesenfasching);
		actualImageSources.add(R.drawable.img_chinesenfasching_1);
		actualImageSources.add(R.drawable.img_chinesenfasching_2);
		actualImageSources.add(R.drawable.img_chinesenfasching_3);
		actualImageSources.add(R.drawable.img_chinesenfasching_4);
		actualImageSources.add(R.drawable.img_chinesenfasching_5);
		actualVideoSources.add(R.raw.chinesenfacshing_1);
		actualVideoSources.add(R.raw.chinesenfasching_2);

		createCelebration(chinesenfasching);
		clearActualSources();

		Celebration oisinga = new Celebration(R.string.oisinga_titel,
				R.string.oisinga_detail, Constants.BrauchOiasinga);
		createCelebration(oisinga);
		clearActualSources();

		Celebration maibaum = new Celebration(R.string.maibaum_titel,
				R.string.maibaum_detail, Constants.BrauchMaibaumaufstellen);
		actualImageSources.add(R.drawable.img_maibaum_1);
		actualImageSources.add(R.drawable.img_maibaum_2);
		actualImageSources.add(R.drawable.img_maibaum_3);
		actualImageSources.add(R.drawable.img_maibaum_4);
		actualImageSources.add(R.drawable.img_maibaum_5);
		actualVideoSources.add(R.raw.maibaumvideo);

		createCelebration(maibaum);
		clearActualSources();

		Celebration pavillion = new Celebration(R.string.pavillion_titel,
				R.string.pavillion_detail, Constants.BrauchPavillon);
		actualImageSources.add(R.drawable.img_pavillion_1);
		actualImageSources.add(R.drawable.img_pavillion_2);
		actualImageSources.add(R.drawable.img_pavillion_3);
		actualImageSources.add(R.drawable.img_pavillion_4);
		actualImageSources.add(R.drawable.img_pavillion_5);
		actualImageSources.add(R.drawable.img_pavillion_6);
		actualImageSources.add(R.drawable.img_pavillion_7);
		actualImageSources.add(R.drawable.img_pavillion_8);
		actualImageSources.add(R.drawable.img_pavillion_9);

		createCelebration(pavillion);
		clearActualSources();

		Celebration specht = new Celebration(R.string.specht_titel,
				R.string.specht_detail, Constants.BrauchSpecht);
		actualImageSources.add(R.drawable.img_sprecht_1);
		actualImageSources.add(R.drawable.img_sprecht_2);
		actualImageSources.add(R.drawable.img_sprecht_3);
		actualImageSources.add(R.drawable.img_sprecht_4);
		actualImageSources.add(R.drawable.img_sprecht_5);
		actualImageSources.add(R.drawable.img_sprecht_6);
		createCelebration(specht);
		clearActualSources();

		Celebration oarschboussn = new Celebration(R.string.oarschboussn_titel,
				R.string.oarschboussn_detail, Constants.BrauchOarschboussn);
		actualImageSources.add(R.drawable.img_oarschboussn_1);
		actualImageSources.add(R.drawable.img_oarschboussn_2);
		actualImageSources.add(R.drawable.img_oarschboussn_3);
		actualImageSources.add(R.drawable.img_oarschboussn_4);

		createCelebration(oarschboussn);
		clearActualSources();

		Celebration ratschn = new Celebration(R.string.ratschn_titel,
				R.string.ratschn_detail, Constants.BrauchMoisterratschn);
		createCelebration(ratschn);
		clearActualSources();

	}

	// 12 Bergfeste werden erstellt

	private static void createBergfeste() {
		Celebration bergfestSchmidmuehlen = new Celebration(
				R.string.bergfest_schmidmuehlen_titel,
				R.string.bergfest_schmidmuehlen_detail,
				Constants.BergfestSchmidmuehlen);
		clearActualSources();
		createCelebration(bergfestSchmidmuehlen);

		Celebration bergfestAuerbach = new Celebration(
				R.string.bergfest_auerbach_titel,
				R.string.bergfest_auerbach_detail, Constants.BergfestAuerbach);
		clearActualSources();
		createCelebration(bergfestAuerbach);

		Celebration bergfestFreudenberg = new Celebration(
				R.string.bergfest_freudenberg_titel,
				R.string.bergfest_freudenberg_detail,
				Constants.BergfestFreudenberg);
		clearActualSources();
		createCelebration(bergfestFreudenberg);

		Celebration bergfestAmberg = new Celebration(
				R.string.bergfest_amberg_titel,
				R.string.bergfest_amberg_detail, Constants.BergfestAmberg);
		clearActualSources();
		createCelebration(bergfestAmberg);

		Celebration bergfestSulzbach = new Celebration(
				R.string.bergfest_sulzbach_titel,
				R.string.bergfest_sulzbach_detail,
				Constants.BergfestSulzbachRosenberg);
		clearActualSources();
		createCelebration(bergfestSulzbach);

		Celebration bergfestSchnaittenbach = new Celebration(
				R.string.bergfest_schnaittenbach_titel,
				R.string.bergfest_schnaittenbach_detail,
				Constants.BergfestSchnaittenbach);
		clearActualSources();
		createCelebration(bergfestSchnaittenbach);

		Celebration bergfestHahnbach = new Celebration(
				R.string.bergfest_hahnbach_titel,
				R.string.bergfest_hahnbach_detail, Constants.BergfestHahnbach);
		clearActualSources();
		createCelebration(bergfestHahnbach);

		Celebration bergfestMausberg = new Celebration(
				R.string.bergfest_mausberg_titel,
				R.string.bergfest_mausberg_detail, Constants.BergfestMausberg);
		clearActualSources();
		createCelebration(bergfestMausberg);

		Celebration bergfestKreuzberg = new Celebration(
				R.string.bergfest_kreuzberg_titel,
				R.string.bergfest_kreuzberg_detail, Constants.BergfestKreuzberg);
		clearActualSources();
		createCelebration(bergfestKreuzberg);

		Celebration bergfestEnsdorf = new Celebration(
				R.string.bergfest_ensdorf_titel,
				R.string.bergfest_ensdorf_detail, Constants.BergfestEnsdorf);
		clearActualSources();
		createCelebration(bergfestEnsdorf);

		Celebration bergfestWaldthurn = new Celebration(
				R.string.bergfest_waldthurn_titel,
				R.string.bergfest_waldthurn_detail, Constants.BergfestWaldthurn);
		clearActualSources();
		createCelebration(bergfestWaldthurn);

		Celebration bergfestPfreimd = new Celebration(
				R.string.bergfest_pfreimd_titel,
				R.string.bergfest_pfreimd_detail, Constants.BergfestPfreimd);
		clearActualSources();
		createCelebration(bergfestPfreimd);

		Celebration bergfestPleystein = new Celebration(
				R.string.bergfest_pleystein_titel,
				R.string.bergfest_pleystein_detail, Constants.BergfestPleystein);
		clearActualSources();
		createCelebration(bergfestPleystein);
	}

	// 7 Kirwas werden erstellt

	private static void createKirwas() {
		Celebration kirwaErbendorf = new Celebration(
				R.string.kirwa_erbendorf_titel,
				R.string.kirwa_erbendorf_detail, Constants.KirwaErbendorf);
		clearActualSources();
		createCelebration(kirwaErbendorf);

		Celebration kirwaNeustadt = new Celebration(
				R.string.kirwa_neustadt_titel, R.string.kirwa_neustadt_detail,
				Constants.KirwaNeustadt);
		clearActualSources();
		createCelebration(kirwaNeustadt);

		Celebration kirwaAmberg = new Celebration(R.string.kirwa_amberg_titel,
				R.string.kirwa_amberg_detail, Constants.KirwaLkrAmberg);
		clearActualSources();
		createCelebration(kirwaAmberg);

		Celebration kirwaFronberg = new Celebration(
				R.string.kirwa_fronberg_titel, R.string.kirwa_fronberg_detail,
				Constants.KirwaFronberg);
		clearActualSources();
		createCelebration(kirwaFronberg);

		Celebration kirwaThalersdorf = new Celebration(
				R.string.kirwa_thalersdorf_titel,
				R.string.kirwa_thalersdorf_detail, Constants.KirwaThalersdorf);
		clearActualSources();
		createCelebration(kirwaThalersdorf);

		Celebration kirwaTrautmannshofen = new Celebration(
				R.string.kirwa_trautmannshofen_titel,
				R.string.kirwa_trautmannshofen_detail,
				Constants.KirwaTrautmannshofen);
		clearActualSources();
		createCelebration(kirwaTrautmannshofen);

		Celebration kirwaKallmuenz = new Celebration(
				R.string.kirwa_kallmuenz_titel,
				R.string.kirwa_kallmuenz_detail, Constants.KirwaKallmuenz);
		clearActualSources();
		createCelebration(kirwaKallmuenz);
	}

	public static ArrayList<Celebration> getCelebrationList() {
		return celebrationList;
	}

	public static ArrayList<Integer> getGalleryImageSources() {
		return galleryImageSources;
	}

	private static void createCelebration(Celebration celebration) {
		celebration.setImageSources(actualImageSources);
		celebration.setVideoSources(actualVideoSources);
		celebrationList.add(celebration);
	}

	private static void clearActualSources() {
		actualImageSources.clear();
		actualVideoSources.clear();
	}

}
