package com.aisteps.model;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowContentResolver;
import org.robolectric.util.ActivityController;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;

import com.aisteps.Aisteps;
import com.aisteps.constants.ApplicationConstant;

@RunWith(RobolectricTestRunner.class)
public class DataBaseProvider_Test {

	DataBaseProvider provider;
	ActivityController<Aisteps> aistepActivity;
	ContentResolver resolver;

	@Before
	public void setup() {

		provider = new DataBaseProvider();
		aistepActivity = Robolectric.buildActivity(Aisteps.class);
		resolver = aistepActivity.get().getContentResolver();
		provider.onCreate();
		ShadowContentResolver.registerProvider(DataBaseProvider.AUTHORITY,
				provider);
	}

	@Test
	public void whenInsertSongIntoDataBaseWithfreeContent() {
		Uri uri_ = resolver.insert(DataBaseProvider.CONTENT_URI,
				new ContentValues());
		if (uri_ == null)
			fail("uri is null");
	}

	@Test
	public void whenInsertSongIntoDataBaseWithNullContent() {
		Uri uri_ = resolver.insert(DataBaseProvider.CONTENT_URI,
				getTestContent());
		if (uri_ == null)
			fail("uri is null");

	}

	@Test
	public void whenInsertSongIntoDataBase() {
		Uri uri_ = resolver.insert(DataBaseProvider.CONTENT_URI,
				getTestContent());
		if (uri_ == null)
			fail("uri is null");
	}

	public ContentValues getTestContent() {
		ContentValues values = new ContentValues();
		values.put(ApplicationConstant.SONG_TITLE, "title");
		values.put(ApplicationConstant.SONG_IMAGE_LINK, "ImageLink");
		values.put(ApplicationConstant.SONG_AUDIO_LINK, "imageLink");
		return values;
	}

}
