package com.aisteps.controller;

import java.util.ArrayList;

/*
 * Interface used to notifiy AiSteps class that the Entries Loading finished  
 * */
public interface LoadingFinish {
	public void loadingFinish(ArrayList<Entry> entries);
}
