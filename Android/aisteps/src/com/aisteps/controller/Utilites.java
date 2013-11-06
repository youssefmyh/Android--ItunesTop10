package com.aisteps.controller;

import com.aisteps.constants.ApplicationConstant;

import android.content.Context;
import android.widget.Toast;

public class Utilites {

	/*
	 * showing Toast
	 * 
	 * @param context this current Activity or application context
	 * 
	 * @param message message should shown to the customer
	 */
	public static void showToastMessage(Context context, String message) {
		if (context != null && message != null && message.length() > 0) {
			Toast.makeText(context, message, ApplicationConstant.TOASTTIME)
					.show();
		}

	}

}
