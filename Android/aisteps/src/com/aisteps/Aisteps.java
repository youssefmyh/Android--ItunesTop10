package com.aisteps;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.aisteps.constants.ApplicationConstant;
import com.aisteps.controller.EntriesLoader;
import com.aisteps.controller.Entry;
import com.aisteps.controller.ImageLoader;
import com.aisteps.controller.LoadingFinish;
import com.aisteps.model.DataBaseProvider;

@SuppressLint("NewApi")
public class Aisteps extends ListActivity implements LoaderCallbacks<Cursor>,
		LoadingFinish, OnClickListener {
	// Loader Manger Identifier
	private static final int LOADER_ID = 1;
	private static final Void Void = null;
	// Entries Loaded List
	private ArrayList<Entry> entriesList;
	// Call Back Loader Object
	private LoaderCallbacks<Cursor> loadCallbacks;
	// List Adapter
	private ListViewAdapter listAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aisteps);

		entriesList = new ArrayList<Entry>();
		listAdapter = new ListViewAdapter(this, entriesList);
		setListAdapter(listAdapter);

		/*
		 * Callbacks initalization
		 */
		loadCallbacks = this;
		LoaderManager manger = getLoaderManager();
		manger.initLoader(LOADER_ID, null, loadCallbacks);

		loadEntries();
	}

	/*
	 * Load Entries throw EntriesLoader
	 */
	private void loadEntries() {
		EntriesLoader.loadEntriesList(ApplicationConstant.ENTRIES_URL,
				ApplicationConstant.ENTRY_TAG, entriesList, this);

	}

	/*
	 * onCreateLoader called when Loader start create and it fetch data from
	 * database
	 */
	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		// TODO Auto-generated method stub
		return new CursorLoader(this, DataBaseProvider.CONTENT_URI,
				ApplicationConstant.PROJECTION, null, null, null);
	}

	/*
	 * LoadFinished method notified by Loader when there is any insert or delete
	 * done in your Provider
	 * 
	 * insert Data from Cursor to entriesList and refresh List adapter
	 */

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		// TODO Auto-generated method stub
		if (loader.getId() == LOADER_ID) {
			entriesList.clear();

			if (cursor.getCount() > 0) {
				int firstMove = cursor.getCount() - 10;
				if (firstMove < 1)
					firstMove = 1;
				cursor.move(firstMove);
				do {
					Entry entry = new Entry();
					entry.setTitle(cursor.getString(cursor
							.getColumnIndex(ApplicationConstant.SONG_TITLE)));
					entry.setImageLink(cursor.getString(cursor
							.getColumnIndex(ApplicationConstant.SONG_IMAGE_LINK)));
					entry.setSongWebLink(cursor.getString(cursor
							.getColumnIndex(ApplicationConstant.SONG_AUDIO_LINK)));
					entriesList.add(entry);

				} while (cursor.moveToNext());
			}
			setListAdapter(listAdapter);
			/*
			 * start Image Loader
			 */
			if (entriesList.size() > 0) {
				new ImageLoader(entriesList).execute(Void);
			}

		}

	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * this interface called when Loading Entries or Songs Finish
	 */
	@Override
	public void loadingFinish(ArrayList<Entry> entries) {
		// TODO Auto-generated method stub
		if (entries != null) {
			for (int i = 0; i < entries.size(); i++) {
				Entry entry = entries.get(i);
				;
				ContentValues contentValues = new ContentValues();
				contentValues.put(ApplicationConstant.SONG_ID, entry.getId());
				contentValues.put(ApplicationConstant.SONG_TITLE,
						entry.getTitle());
				contentValues.put(ApplicationConstant.SONG_IMAGE_LINK,
						entry.getImageLink());
				contentValues.put(ApplicationConstant.SONG_AUDIO_LINK,
						entry.getSongWebLink());
				Aisteps.this.getContentResolver().insert(
						DataBaseProvider.CONTENT_URI, contentValues);
			}

		}

	}

	/*
	 * on click used to show the Save and Browse Button
	 */

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == null)
			return;
		LinearLayout savedLayout = (LinearLayout) v
				.findViewById(R.id.savelayout);
		if (savedLayout != null) {
			if (savedLayout.getVisibility() == View.GONE)
				savedLayout.setVisibility(View.VISIBLE);
			else
				savedLayout.setVisibility(View.GONE);

		}
	}

	/*
	 * open Activiy contain browser and path image url to BrowserActivity to
	 * display it
	 */
	public void browseImage(View view) {
		if (view == null)
			return;
		if (view.getTag() != null) {
			if (view.getTag() instanceof Entry) {
				Entry entry = (Entry) view.getTag();
				Intent intent = new Intent(this, BrowserActivity.class);
				intent.putExtra(ApplicationConstant.SONG_IMAGE_LINK,
						entry.getSongWebLink());
				startActivity(intent);
			}
		}

	}

	/*
	 * this method used to request the Downloader Manger to Download Song image
	 * and it will be saved in download folder
	 */

	public void saveImageToDisk(View view) {
		if (view == null)
			return;
		if (view.getTag() != null) {
			if (view.getTag() instanceof Entry) {
				Entry entry = (Entry) view.getTag();
				DownloadManager mgr = (DownloadManager) this
						.getSystemService(Context.DOWNLOAD_SERVICE);
				Uri downloadUri = Uri.parse(entry.getImageLink());
				DownloadManager.Request request = new DownloadManager.Request(
						downloadUri);
				request.setNotificationVisibility(Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
				mgr.enqueue(request);

			}
		}

	}

}
