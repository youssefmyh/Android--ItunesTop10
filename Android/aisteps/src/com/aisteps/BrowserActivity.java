package com.aisteps;

import com.aisteps.constants.ApplicationConstant;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/*
 * Browser Activity used to Load Image 
 * using Web Browser Control
 * */
public class BrowserActivity extends Activity {

	WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browser);
		String imageUrl = getIntent().getStringExtra(
				ApplicationConstant.SONG_IMAGE_LINK);
		webView = (WebView) findViewById(R.id.webview);

		if (imageUrl != null) {
			webView.loadUrl(imageUrl);
		}
		webView.setWebViewClient(new WebViewClient() {
		    public boolean shouldOverrideUrlLoading(WebView view, String url){
		        webView.loadUrl(url);
		        return false; // then it is not handled by default action
		   }
		});
	}
	
}
