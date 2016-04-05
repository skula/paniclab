package com.skula.paniclab.activities.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.skula.paniclab.constants.TouchArea;
import com.skula.paniclab.services.Drawer;
import com.skula.paniclab.services.GameEngine;

public class BoardView extends View {
	private Paint paint;
	private Resources res;
	private Drawer drawer;
	private GameEngine ge;
	private int tmpTile;
	
	public BoardView(Context context) {
		super(context);
		this.paint = new Paint();
		this.ge = new GameEngine(2);
		this.res = context.getResources();
		this.drawer = new Drawer(this, ge);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			tmpTile = TouchArea.getTileId(x, y);
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		case MotionEvent.ACTION_UP:
			break;
		}
		invalidate();
		return true;
	}

	@Override
	public void draw(Canvas canvas) {
		drawer.draw(canvas);
		Paint paint = new Paint();
		paint.setColor(Color.YELLOW);
		paint.setTextSize(30f);
		canvas.drawText("" + tmpTile, 300, 300, paint);
	}
}
