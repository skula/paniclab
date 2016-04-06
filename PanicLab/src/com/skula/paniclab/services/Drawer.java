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
import android.graphics.Typeface;

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

	private int measuredWidth;
	private int measuredHeight;

	private List<Integer> dicesOrder;

	public Drawer(BoardView view, GameEngine engine) {
		this.engine = engine;
		this.paint = new Paint();
		this.assetManager = view.getResources().getAssets();
		this.lib = new PictureLibrary(view.getResources());
		this.dicesOrder = new ArrayList<Integer>();
		this.measuredWidth = view.getMeasuredWidth();
		this.measuredHeight = view.getMeasuredHeight();
	}

	public void draw(Canvas c) {
		int a = lib.get(R.drawable.point_minus).getWidth();
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
		int cx = measuredWidth / 2;
		int cy = measuredHeight / 2;
		for (int i = 0; i < Cnst.TILES_COUNT; i++) {
			if (i >= 14 && i <= 21) {
				c.save();
				c.rotate(180, DrawAreas.TILES[i].getX(), DrawAreas.TILES[i].getY());
				drawPict(c, engine.getTile(i).getDrawId(), new Point(DrawAreas.TILES[i].getX() + cx
						- DrawAreas.TILE_SIZE, DrawAreas.TILES[i].getY() + cy - DrawAreas.TILE_SIZE));
				c.restore();
			} else if (i >= 11 && i <= 13) {
				c.save();
				c.rotate(90, DrawAreas.TILES[i].getX(), DrawAreas.TILES[i].getY());
				drawPict(c, engine.getTile(i).getDrawId(), new Point(DrawAreas.TILES[i].getX() + cx, DrawAreas.TILES[i].getY() + cy - DrawAreas.TILE_SIZE));
				c.restore();
			}else if (i >= 22 && i <= 24) {
				c.save();
				c.rotate(-90, DrawAreas.TILES[i].getX(), DrawAreas.TILES[i].getY());
				drawPict(c, engine.getTile(i).getDrawId(), new Point(DrawAreas.TILES[i].getX() + cx - DrawAreas.TILE_SIZE, DrawAreas.TILES[i].getY() + cy ));
				c.restore();
			}else{
				drawPict(c, engine.getTile(i).getDrawId(), DrawAreas.TILES[i]);
			}
		}
	}

	public void shuffleDicesOrder() {
		dicesOrder.clear();
		for (int i = 0; i < Cnst.DICES_COUNT; i++) {
			dicesOrder.add(i);
		}
		Collections.shuffle(dicesOrder);
	}

	private void drawDicesButton(Canvas c) {
		drawPict(c, R.drawable.btn_roll, DrawAreas.ROLL_DICES_BTN);
	}

	// TODO: a faire avec les images
	private void drawDices(Canvas c) {
		int diceColorId = engine.getDiceColor() == Cnst.AMOEBA_ORANGE ? R.drawable.dice_orange : R.drawable.dice_blue;
		int diceShapeId = engine.getDiceShape() == Cnst.AMOEBA_TENTACLES ? R.drawable.dice_tentacles
				: R.drawable.dice_feelers;
		int dicePatternId = engine.getDicePattern() == Cnst.AMOEBA_PEAS ? R.drawable.dice_peas : R.drawable.dice_strips;
		int diceLabId = 0;
		switch (engine.getDiceLab()) {
		case Cnst.LAB_BLUE:
			diceLabId = engine.getDiceDirection() == Cnst.DICE_CLOCKWISE ? R.drawable.dice_lab_blue_clockwise
					: R.drawable.dice_lab_blue_counterclockwise;
			break;
		case Cnst.LAB_RED:
			diceLabId = engine.getDiceDirection() == Cnst.DICE_CLOCKWISE ? R.drawable.dice_lab_red_clockwise
					: R.drawable.dice_lab_red_counterclockwise;
			break;
		case Cnst.LAB_YELLOW:
			diceLabId = engine.getDiceDirection() == Cnst.DICE_CLOCKWISE ? R.drawable.dice_lab_yellow_clockwise
					: R.drawable.dice_lab_yellow_counterclockwise;
			break;
		}

		Point p = new Point(DrawAreas.DICES.getX(), DrawAreas.DICES.getY());
		for (Integer i : dicesOrder) {
			switch (i) {
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
		paint.setColor(Color.WHITE);
		paint.setTextSize(40f);
		Typeface plain = Typeface.createFromAsset(assetManager, "fonts/OCRASTD.OTF");
		paint.setTypeface(plain);
		for (int i = 0; i < engine.getnPlayers(); i++) {
			if (i == 1 || i == 2) {
				c.drawText(engine.getScores()[i] + " pts", DrawAreas.POINT_BTNS[i].getX() - 165,
						DrawAreas.POINT_BTNS[i].getY() + 75, paint);
			} else {
				c.drawText(engine.getScores()[i] + " pts", DrawAreas.POINT_BTNS[i].getX() + 175,
						DrawAreas.POINT_BTNS[i].getY() + 75, paint);
			}
		}
	}

	// TODO: faire avec les images
	private void drawPointsButton(Canvas c) {
		int id = 0;
		if (engine.isGoodTile()) {
			id = R.drawable.point_plus;
			drawPict(c, R.drawable.answer_found, DrawAreas.TILES[engine.getResponseTile()]);
		} else {
			drawPict(c, R.drawable.answer_wrong, DrawAreas.TILES[engine.getUserSelTile()]);
			drawPict(c, R.drawable.answer_right, DrawAreas.TILES[engine.getResponseTile()]);
			id = R.drawable.point_minus;
		}

		for (int i = 0; i < engine.getnPlayers(); i++) {
			drawPict(c, id, DrawAreas.POINT_BTNS[i]);
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