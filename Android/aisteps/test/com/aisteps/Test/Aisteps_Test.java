package com.aisteps.Test;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.ActivityController;

import com.aisteps.Aisteps;

@RunWith(RobolectricTestRunner.class)
public class Aisteps_Test {

	ActivityController<Aisteps> aistepActivity = null;
	Aisteps activity;

	public Aisteps_Test() {
	}

	@Before
	public void setUp() throws Exception {
		aistepActivity = Robolectric.buildActivity(Aisteps.class);
		activity = aistepActivity.get();
	}

	@Test
	public void TestSetup() {
		if (activity == null)
			fail("Fail to intialize Aisteps Activity");

	}

}
