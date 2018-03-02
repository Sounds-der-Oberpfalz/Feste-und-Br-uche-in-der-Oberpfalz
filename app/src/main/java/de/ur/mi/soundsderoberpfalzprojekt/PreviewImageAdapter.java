package de.ur.mi.soundsderoberpfalzprojekt;

import java.util.List;

import android.Manifest.permission;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class PreviewImageAdapter extends ArrayAdapter<PreviewImage> {
	private List<PreviewImage> imageList;
	private Context context;

	public PreviewImageAdapter(Context context, List<PreviewImage> images) {
		super(context, R.layout.preview_image, images);

		this.context = context;
		imageList = images;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View personListViewItem;

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			personListViewItem = inflater.inflate(R.layout.preview_image, null);
		} else {
			personListViewItem = convertView;
		}

		ImageView previewImageView = (ImageView) personListViewItem
				.findViewById(R.id.preview_image_view);
		previewImageView.setImageBitmap(imageList.get(position).getImage());

		return personListViewItem;
	}
}