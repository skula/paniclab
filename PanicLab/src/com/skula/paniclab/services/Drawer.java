package com.skula.paniclab.services;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.skula.paniclab.R;
import com.skula.paniclab.activities.views.BoardView;
import com.skula.paniclab.constants.DrawAreas;
import com.skula.paniclab.constants.PictureLibrary;
import com.skula.paniclab.models.Point;

public class Drawer {
	private PictureLibrary lib;
	private Paint paint;
	private AssetManager assetManager;
	private GameEngine engine;

	public Drawer(BoardView view, GameEngine engine) {
		this.engine = engine;
		this.paint = new Paint();
		this.assetManager = view.getResources().getAssets();
		this.lib = new PictureLibrary(view.getResources());
	}

	public void draw(Canvas c) {
		int w = lib.get(R.drawable.ventilation).getWidth();
		Point p = new Point(0,0);
		for(int i =0; i <9; i++){
			drawPict(c, R.drawable.ventilation, p);
			p.move(w + 10, 0);
		}
		p.move(-w - 10, w+10);
		drawPict(c, R.drawable.ventilation, p);
		p.move(0, w+10);
		drawPict(c, R.drawable.ventilation, p);
		p.move(0, w+10);
		drawPict(c, R.drawable.ventilation, p);
		p.move(0, w+10);
		drawPict(c, R.drawable.ventilation, p);
		
		//drawTiles(c);
		switch (engine.getTimeline()) {
		case ROLL_DICES:
			drawDicesButton(c);
			break;
		case SELECT_TILE:
			drawDices(c);
			break;
		case TAKE_POINT:
			drawDices(c);
			drawPointsButton(c);
			break;
		}
		drawScore(c);
		drawTouchArea(c);
	}

	private void drawTiles(Canvas c) {
		for (int i = 0; i < 10; i++) {
			drawPict(c, engine.getTile(i).getDrawId(), DrawAreas.TILES[i]);
		}
	}

	private void drawDicesButton(Canvas c) {

	}

	private void drawDices(Canvas c) {

	}

	private void drawScore(Canvas c) {

	}

	private void drawPointsButton(Canvas c) {

	}

	private void drawTouchArea(Canvas c) {
		paint.setColor(Color.RED);
		paint.setStyle(Paint.Style.STROKE);
	}

	private void drawPict(Canvas c, int id, Point p) {
		Bitmap bmp = lib.get(id);
		Rect src = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());
		Rect dest = new Rect(0 + p.getX(), 0 + p.getY(), bmp.getWidth() + p.getX(), bmp.getHeight() + p.getY());
		c.drawBitmap(bmp, src, dest, paint);
	}

	private void drawPict(Canvas c, int id, Rect dest) {
		Bitmap bmp = lib.get(id);
		Rect src = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());
		c.drawBitmap(bmp, src, dest, paint);
	}
}