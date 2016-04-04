package com.skula.paniclab.activities;

import android.app.Activity;
import android.os.Bundle;

import com.skula.paniclab.activities.views.BoardView;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(new BoardView(this));
	}
}
