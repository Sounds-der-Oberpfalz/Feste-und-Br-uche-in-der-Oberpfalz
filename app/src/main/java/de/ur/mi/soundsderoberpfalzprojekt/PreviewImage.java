package de.ur.mi.soundsderoberpfalzprojekt;

import android.graphics.Bitmap;

public class PreviewImage {

	private Bitmap image;

	public PreviewImage(Bitmap image) {
		this.image = image;
	}

	public Bitmap getImage() {
		return image;
	}
}
