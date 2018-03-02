package de.ur.mi.soundsderoberpfalzprojekt;

import java.util.ArrayList;

import android.widget.ImageView;

public class Celebration {

	// instance variables

	private int titleSource, textSource;
	private int index;
	private ArrayList<Integer> imageSources;
	private ArrayList<Integer> videoSources;

	// constructor of celebration, needs title, description and index of the
	// celebration

	public Celebration(int titleSource, int textSource, int index) {
		this.titleSource = titleSource;
		this.textSource = textSource;
		this.index = index;

		imageSources = new ArrayList<Integer>();
		videoSources = new ArrayList<Integer>();
	}

	// setter-methods of the celebration to add pictures and videos

	public void setImageSources(ArrayList<Integer> imageSources) {
		for (int i = 0; i < imageSources.size(); i++) {
			if (imageSources.get(i) != null) {
				this.imageSources.add(imageSources.get(i));
			}
		}
	}

	public void setVideoSources(ArrayList<Integer> videoSources) {
		for (int i = 0; i < videoSources.size(); i++) {
			if (videoSources.get(i) != null) {
				this.videoSources.add(videoSources.get(i));
			}
		}
	}

	// getter-methods of the celebration

	public int getTitleSource() {
		return titleSource;
	}

	public int getTextSource() {
		return textSource;
	}

	public int getIndex() {
		return index;
	}

	public ArrayList<Integer> getImageSources() {
		return imageSources;
	}

	public ArrayList<Integer> getVideoSources() {
		return videoSources;
	}

	// TODO delete this getter method

	public ArrayList<Integer> getSoundSources() {
		return null;
	}
}
