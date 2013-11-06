package com.aisteps.controller;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

/*
 * Image Loader Extends AsyncTask 
 * used for Loading Images in Background without distribute Main Thread
 * @Param Void That is mean No Paramter for doInBackground Method
 * @Param Entry the Progress Update Method param
 * @param Void the result is Void 
 * */
public class ImageLoader extends AsyncTask<Void, Entry, Void> {

	/*
	 * Entries List contain Reference for ImageView and reference for Bitmap s
	 */
	ArrayList<Entry> entries;

	public ImageLoader(ArrayList<Entry> entries) {
		this.entries = entries;
	}

	/*
	 * Background Process
	 * 
	 * @Param Void because URL loaded from Entries List
	 */
	@Override
	protected Void doInBackground(Void... arg0) {

		if (entries != null) {
			for (int i = 0; i < entries.size(); i++) {
				Entry entry = entries.get(i);
				if (entry.getImageLink() == null)
					continue;
				Bitmap bitmap = null;
				try {
					URL url = new URL(entry.getImageLink());
					InputStream in = url.openStream();
					bitmap = BitmapFactory.decodeStream(in);
					entry.setBitmap(bitmap);
					publishProgress(entries.get(i));
				} catch (Exception ee) {
					ee.printStackTrace();

					continue;
				}

			}
		}

		return null;
	}

	/*
	 * doInBackground used to Set Bitmap Image for ImageView Throw Method
	 * setBitmapToImageView and Here is Passing by Ref because Bitmap is a Heavy
	 * Object
	 */
	@Override
	protected void onProgressUpdate(Entry... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
		values[0].setBitmapToImageView();
	}

}
