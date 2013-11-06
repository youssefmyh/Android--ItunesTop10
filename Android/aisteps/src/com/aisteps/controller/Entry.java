package com.aisteps.controller;

import android.graphics.Bitmap;
import android.widget.ImageView;

/*
 * Entry class contain Title , image , Link parameters of Song
 * 
 * */

public class Entry {
	private String id;
	private String title;
	private String imageLink;
	private String songWebLink;
	private ImageView entryImageView;
	private Bitmap bitmap;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String image) {
		this.imageLink = image;
	}

	public String getSongWebLink() {
		return songWebLink;
	}

	public void setSongWebLink(String link) {
		this.songWebLink = link;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ImageView getEntryImageView() {
		return entryImageView;
	}

	public void setEntryImageView(ImageView entryImageView) {
		this.entryImageView = entryImageView;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public void setBitmapToImageView() {
		if (entryImageView != null) {
			if (bitmap != null) {
				entryImageView.setImageBitmap(bitmap);
			}
		}
	}

}
