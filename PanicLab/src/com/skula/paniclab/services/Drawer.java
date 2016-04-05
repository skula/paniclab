package com.skula.paniclab.services;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.skula.paniclab.activities.views.BoardView;
import com.skula.paniclab.constants.Cnst;
import com.skula.paniclab.constants.DrawAreas;
import com.skula.paniclab.constants.PictureLibrary;
import com.skula.paniclab.constants.TouchArea;
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
		drawTiles(c);
		switch (engine.getTimeline()) {
		case ROLL_DICES:
			drawDicesButton(c);
			break;
		case SELECT_TILE:
			 drawDices(c);
			break;
		case TAKE_POINT:
			// drawDices(c);
			// drawPointsButton(c);
			break;
		}
		drawScore(c);
		drawTouchArea(c);
	}

	private void drawTiles(Canvas c) {
		for (int i = 0; i < Cnst.TILES_COUNT; i++) {
			drawPict(c, engine.getTile(i).getDrawId(), DrawAreas.TILES[i]);
		}
	}

	private void drawDicesButton(Canvas c) {
		paint.setColor(Color.WHITE);
		paint.setTextSize(40f);
		c.drawText("Lancer !", DrawAreas.ROLL_DICES_BTN.getX() + 20, DrawAreas.ROLL_DICES_BTN.getY() + 125, paint);
	}

	// TODO: a faire avec les images
	private void drawDices(Canvas c) {
		paint.setColor(Color.WHITE);
		paint.setTextSize(40f);

		int x0 = 800;
		int y0 = 500;
		int dy = 50;

		String tmp = "";
		switch (engine.getDiceLab()) {
		case Cnst.LAB_BLUE:
			tmp = "Labo: bleu ("
					+ (engine.getDiceDirection() == Cnst.DICE_CLOCKWISE ? "sens horaire)" : "sens inverse)");
			break;
		case Cnst.LAB_RED:
			tmp = "Labo: rouge ("
					+ (engine.getDiceDirection() == Cnst.DICE_CLOCKWISE ? "sens horaire)" : "sens inverse)");
			break;
		case Cnst.LAB_YELLOW:
			tmp = "Labo: jaune ("
					+ (engine.getDiceDirection() == Cnst.DICE_CLOCKWISE ? "sens horaire)" : "sens inverse)");
			break;
		}
		c.drawText(tmp, x0, y0, paint);
		y0 += dy;

		tmp = "Couleur: " + (engine.getDiceColor() == Cnst.AMOEBA_ORANGE ? "orange" : "bleue");
		c.drawText(tmp, x0, y0, paint);
		y0 += dy;

		tmp = "Forme: " + (engine.getDiceShape() == Cnst.AMOEBA_TENTACLES ? "tentacules" : "antenes");
		c.drawText(tmp, x0, y0, paint);
		y0 += dy;

		tmp = "Motif: " + (engine.getDicePattern() == Cnst.AMOEBA_PEAS ? "poids" : "rayures");
		c.drawText(tmp, x0, y0, paint);
	}

	private void drawScore(Canvas c) {
		paint.setColor(Color.BLUE);
		for (int i = 0; i < engine.getnPlayers(); i++) {
			c.drawText(engine.getScores()[i] + "", i % 2 == 0 ? DrawAreas.POINT_BTNS[i].getX() + 100
					: DrawAreas.POINT_BTNS[i].getX() - 100, DrawAreas.POINT_BTNS[i].getY(), paint);
		}
	}

	// TODO: faire avec les images
	private void drawPointsButton(Canvas c) {
		String txt = "";
		if (engine.isGoodTile()) {
			paint.setColor(Color.GREEN);
			txt = "+1";
		} else {
			paint.setColor(Color.GREEN);
			txt = "-1";
		}

		for (int i = 0; i < engine.getnPlayers(); i++) {
			c.drawText(txt, DrawAreas.POINT_BTNS[i].getX(), DrawAreas.POINT_BTNS[i].getY(), paint);
		}
	}

	private void drawTouchArea(Canvas c) {
		paint.setColor(Color.RED);
		paint.setStyle(Paint.Style.STROKE);
		for (int i = 0; i < Cnst.TILES_COUNT; i++) {
			c.drawRect(TouchArea.TILES[i], paint);
		}

		paint.setColor(Color.GREEN);
		for (int i = 0; i < 4; i++) {
			c.drawRect(TouchArea.POINT_BTNS[i], paint);
		}

		paint.setColor(Color.YELLOW);
		c.drawRect(TouchArea.ROLL_DICES_BTN, paint);
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