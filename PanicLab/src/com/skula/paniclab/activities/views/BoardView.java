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
		this.ge = new GameEngine(4);
		this.res = context.getResources();
		this.drawer = new Drawer(this, ge);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			switch(ge.getTimeline()){
			case ROLL_DICES:
				if(TouchArea.ROLL_DICES_BTN.contains(x, y)){
					ge.process(0);
					drawer.shuffleDicesOrder();
				}
				break;
			case SELECT_TILE:
				tmpTile = TouchArea.getTileId(x, y);
				if(tmpTile!=-1){
					ge.process(tmpTile);
				}
				break;
			case TAKE_POINT:
				int pId = TouchArea.getPointBtnId(x, y, ge.getnPlayers());
				if(pId != -1){
					ge.process(pId);
				}
				break;
			}
			invalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		case MotionEvent.ACTION_UP:
			break;
		}
		return true;
	}

	@Override
	public void draw(Canvas canvas) {
		drawer.draw(canvas);
	}
}
