package com.aisteps.controller;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.ActivityController;

import android.widget.ImageView;

import com.aisteps.Aisteps;

@RunWith(RobolectricTestRunner.class)
public class ImageLoader_Test {

	private static final Void Void = null;
	ImageLoader imageLoader;
	ActivityController<Aisteps> aistepActivity = null;
	Aisteps activity;
	ArrayList<Entry> entriesArrayList;

	@Before
	public void setUp() throws Exception {
		aistepActivity = Robolectric.buildActivity(Aisteps.class);
		activity = aistepActivity.get();
		entriesArrayList = new ArrayList<Entry>();
		Entry entry = new Entry();
		entry.setEntryImageView(new ImageView(activity));
		entry.setImageLink("http://a1511.phobos.apple.com/us/r30/Music/v4/76/4a/f5/764af5fc-f75e-10fd-50d9-c9c463398d37/UMG_cvrart_00602537217885_01_RGB72_1800x1800_12UMGIM55707.55x55-70.jpg");

	}

	@Test
	public void whenCallingImageLoaderWithNullArray() {
		new ImageLoader(null).execute(Void);
	}

	@Test
	public void whenCallingImageLoaderWithArrayHaveNoImageLink() {
		entriesArrayList = new ArrayList<Entry>();
		Entry entry = new Entry();
		entry.setImageLink(null);
		new ImageLoader(null).execute(Void);
	}

	@Test
	public void whenCallingImageLoaderWithEntryWithNullImageView() {
		entriesArrayList = new ArrayList<Entry>();
		Entry entry = new Entry();
		entry.setEntryImageView(null);
		entry.setImageLink(null);
		new ImageLoader(null).execute(Void);
	}

}
