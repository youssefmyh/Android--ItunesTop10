package com.aisteps.controller;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.xml.sax.SAXException;

import com.aisteps.constants.ApplicationConstant;

@RunWith(RobolectricTestRunner.class)
public class EntrieManagement_Test {

	@Test
	public void whenCallingLoadTenEntriesList() throws SAXException,
			IOException, ParserConfigurationException {
		ArrayList<Entry> entries = new ArrayList<>();
		EntriesLoader.loadEntriesList(ApplicationConstant.ENTRIES_URL,
				ApplicationConstant.ENTRY_TAG, entries, null);
		if (entries.size() < 10 || entries.size() > 10) {

		} else {
			fail("loaded Entries Less than 10");

		}
	}

	@Test
	public void whenCallingLoadEntriesListwithNullURL() throws SAXException,
			IOException, ParserConfigurationException {
		ArrayList<Entry> entries = new ArrayList<>();
		EntriesLoader.loadEntriesList(null, ApplicationConstant.ENTRY_TAG,
				entries, null);
	}

	@Test
	public void whenCallingLoadEntriesListwithSpaceURL() throws SAXException,
			IOException, ParserConfigurationException {
		ArrayList<Entry> entries = new ArrayList<>();
		EntriesLoader.loadEntriesList("", ApplicationConstant.ENTRY_TAG,
				entries, null);
	}

	@Test
	public void whenCallingLoadEntriesListwithNullNodeName()
			throws SAXException, IOException, ParserConfigurationException {
		ArrayList<Entry> entries = new ArrayList<>();
		EntriesLoader.loadEntriesList(ApplicationConstant.ENTRIES_URL, null,
				entries, null);
	}

	@Test
	public void whenCallingLoadEntriesListwithFreeTextNodeName()
			throws SAXException, IOException, ParserConfigurationException {
		ArrayList<Entry> entries = new ArrayList<>();
		EntriesLoader.loadEntriesList(ApplicationConstant.ENTRIES_URL, "",
				entries, null);
	}

	@Test
	public void whenCallingLoadEntriesListwithNullEntriesArray()
			throws SAXException, IOException, ParserConfigurationException {
		EntriesLoader.loadEntriesList(ApplicationConstant.ENTRIES_URL,
				ApplicationConstant.ENTRY_TAG, null, null);
	}

}
