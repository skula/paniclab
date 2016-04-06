package com.skula.paniclab.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.skula.paniclab.R;
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
	
	private List<Integer> dicesOrder;

	public Drawer(BoardView view, GameEngine engine) {
		this.engine = engine;
		this.paint = new Paint();
		this.assetManager = view.getResources().getAssets();
		this.lib = new PictureLibrary(view.getResources());
		this.dicesOrder = new ArrayList<Integer>();
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
			drawDices(c);
			drawPointsButton(c);
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

	public void shuffleDicesOrder(){
		dicesOrder.clear();
		for(int i=0; i<Cnst.DICES_COUNT; i++){
			dicesOrder.add(i);
		}
		Collections.shuffle(dicesOrder);
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

		int x0 = 1000;
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
		
		int diceColorId = engine.getDiceColor() == Cnst.AMOEBA_ORANGE? R.drawable.dice_orange:R.drawable.dice_blue;
		int diceShapeId = engine.getDiceShape() == Cnst.AMOEBA_TENTACLES? R.drawable.dice_tentacles:R.drawable.dice_feelers;
		int dicePatternId = engine.getDicePattern() == Cnst.AMOEBA_PEAS? R.drawable.dice_peas:R.drawable.dice_strips;
		int diceLabId = 0;
		switch (engine.getDiceLab()) {
		case Cnst.LAB_BLUE:
			diceLabId = engine.getDiceDirection() == Cnst.DICE_CLOCKWISE ? R.drawable.dice_lab_blue_clockwise:R.drawable.dice_lab_blue_counterclockwise;
			break;
		case Cnst.LAB_RED:
			diceLabId = engine.getDiceDirection() == Cnst.DICE_CLOCKWISE ? R.drawable.dice_lab_red_clockwise:R.drawable.dice_lab_red_counterclockwise;
			break;
		case Cnst.LAB_YELLOW:
			diceLabId = engine.getDiceDirection() == Cnst.DICE_CLOCKWISE ? R.drawable.dice_lab_yellow_clockwise:R.drawable.dice_lab_yellow_counterclockwise;
			break;
		}
		
		Point p = new Point(DrawAreas.DICES.getX(), DrawAreas.DICES.getY());
		for(Integer i : dicesOrder){
			switch(i){
			case 0:
				drawPict(c, diceColorId, p);
				break;
			case 1:
				drawPict(c, diceShapeId, p);
				break;
			case 2:
				drawPict(c, dicePatternId, p);
				break;
			case 3:
				drawPict(c, diceLabId, p);
				break;
			}
			p.move(DrawAreas.DICES_DX, 0);
		}
	}

	private void drawScore(Canvas c) {
		paint.setColor(Color.BLUE);
		for (int i = 0; i < engine.getnPlayers(); i++) {
			if(i==1 || i==2){
				c.drawText(engine.getScores()[i] + " pts", DrawAreas.POINT_BTNS[i].getX() - 100, DrawAreas.POINT_BTNS[i].getY() +50, paint);
			}else{
				c.drawText(engine.getScores()[i] + " pts", DrawAreas.POINT_BTNS[i].getX() + 100, DrawAreas.POINT_BTNS[i].getY()+50, paint);
			} 
		}
	}

	// TODO: faire avec les images
	private void drawPointsButton(Canvas c) {
		String txt = "";
		if (engine.isGoodTile()) {
			paint.setColor(Color.GREEN);
			txt = "+1";
		} else {
			paint.setColor(Color.RED);
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