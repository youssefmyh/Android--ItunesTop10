package com.aisteps.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.ActivityController;

import com.aisteps.Aisteps;
import com.aisteps.controller.Utilites;

@RunWith(RobolectricTestRunner.class)
public class Utilites_Test {

	@Test
	public void whenCallingShowToastWithNullContext() {
		Utilites.showToastMessage(null, "Help");

	}

	@Test
	public void whenCallingShowToastWithNullMessage() {
		Utilites.showToastMessage(null, null);
	}

	@Test
	public void whenCallingShowToast() {
		ActivityController<Aisteps> aistepActivity = null;
		aistepActivity = Robolectric.buildActivity(Aisteps.class);

		Utilites.showToastMessage(aistepActivity.get(), "Help");
	}

}
