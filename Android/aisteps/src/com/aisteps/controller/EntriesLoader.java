package com.aisteps.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.aisteps.constants.ApplicationConstant;

/*
 * EntrieManagement 
 * its class used to Manage Load entries from XML and save it into DataBase  
 * */
public class EntriesLoader {
	/*
	 * loadEntriesList method used to load entries from specific url and fill a
	 * List of Entries based on Node name
	 * 
	 * @param urlpath its the the XML path and can't be null
	 * 
	 * @param nodename its the Tag name used by Document Object to filter all
	 * entries related to this Tag
	 * 
	 * @param entries its the List filled with Song Objects
	 */
	public static void loadEntriesList(final String urlpath,
			final String nodeName, final ArrayList<Entry> entries,
			final LoadingFinish loadingfinish)

	{
		Thread thread = new Thread() {

			public void run() {
				try {
					if (urlpath == null || urlpath.length() <= 0
							|| nodeName == null || nodeName.length() <= 0) {
						return;
					}
					if (entries == null) {
						return;
					}

					URL url = new URL(urlpath);
					DocumentBuilderFactory dbf = DocumentBuilderFactory
							.newInstance();
					DocumentBuilder db = dbf.newDocumentBuilder();
					Document doc = db.parse(new InputSource(url.openStream()));
					doc.getDocumentElement().normalize();
					NodeList nodeList = doc.getElementsByTagName(nodeName);
					for (int i = 0; i < nodeList.getLength(); i++) {
						Node node = nodeList.item(i);
						Element fstElmnt = (Element) node;
						Entry entry = new Entry();

						entry.setId(fstElmnt
								.getElementsByTagName(
										ApplicationConstant.ID_TAG).item(0)
								.getAttributes().getNamedItem("im:id")
								.getNodeValue());
						entry.setTitle(fstElmnt
								.getElementsByTagName(
										ApplicationConstant.TITLE_TAG).item(0)
								.getChildNodes().item(0).getNodeValue());
						entry.setImageLink(fstElmnt
								.getElementsByTagName(
										ApplicationConstant.IMAGE_TAG).item(0)
								.getChildNodes().item(0).getNodeValue());
						String songLink = fstElmnt
								.getElementsByTagName(
										ApplicationConstant.LINK_TAG).item(0)
								.getAttributes().getNamedItem("href")
								.getNodeValue();
						entry.setSongWebLink(songLink);
						entries.add(entry);
					}
				} catch (SAXException ee) {
					ee.printStackTrace();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				}
				if (loadingfinish != null)
					loadingfinish.loadingFinish(entries);

			}

		};
		thread.start();

	}

}
