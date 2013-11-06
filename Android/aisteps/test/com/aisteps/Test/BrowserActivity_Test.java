package com.aisteps.Test;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.ActivityController;

import com.aisteps.Aisteps;
import com.aisteps.BrowserActivity;

@RunWith(RobolectricTestRunner.class)
public class BrowserActivity_Test {

	ActivityController<BrowserActivity> roboElectricActivity = null;
	BrowserActivity activity;

	public BrowserActivity_Test() {
	}

	@Before
	public void setUp() throws Exception {
		roboElectricActivity = Robolectric.buildActivity(BrowserActivity.class);
		activity = roboElectricActivity.get();
	}

	@Test
	public void TestSetup() {

		if (activity == null)
			fail("Fail to intialize BrowserSetup Activity");

	}
}
