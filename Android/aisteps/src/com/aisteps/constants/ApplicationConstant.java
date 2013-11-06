package com.aisteps.constants;

public class ApplicationConstant {

	// ------------------------EntrieManagement
	// Parameters------------------------//

	/*
	 * Filter Parameters used by EntrieManagement to extract title,image,song
	 * url from Entry Node
	 */
	public static final String ENTRIES_URL = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=10/xml";
	public static final String ENTRY_TAG = "entry";
	public static final String TITLE_TAG = "title";
	public static final String ID_TAG = "id";
	public static final String IMAGE_TAG = "im:image";
	public static final String LINK_TAG = "link";
	// --------------------------------------------------------

	// ------------------------TOAST
	// TIME--------------------------------------//

	public static final int TOASTTIME = 2000;

	// ------------------------DATABASE Constants-----------------------------//

	public static final String DATABASE_NAME = "aisteps";
	public static final int DATABASE_VERSION = 1;
	public static final String SONGS_TABLE_NAME = "aisongs";
	public static final String TABLE_ID = "_id";
	public static final String SONG_ID = "SONG_ID";
	public static final String SONG_TITLE = "title";
	public static final String SONG_IMAGE_LINK = "imagelink";
	public static final String SONG_AUDIO_LINK = "songlink";

	public static final String PROVIDER_CONTENT = "content://";

	// ------------------------DATABASE TABLES-----------------------------//

	public static final String CREATE_SONG_TABLE = "create table if not exists "
			+ SONGS_TABLE_NAME
			+ "("
			+ TABLE_ID
			+ " integer primary key autoincrement,"
			+ SONG_TITLE
			+ " text not null ,"
			+ SONG_IMAGE_LINK
			+ " text not null ,"
			+ SONG_AUDIO_LINK + " text not null ," + SONG_ID + "" + ");";

	// Projection used to select Content from DataBase
	public static final String PROJECTION[] = { TABLE_ID, SONG_TITLE,
			SONG_IMAGE_LINK, SONG_AUDIO_LINK };

	// ----------------------------------------------------------------------//

}
