package com.skula.paniclab.constants;

import com.skula.paniclab.models.Point;

import android.graphics.Rect;

public class TouchArea {

	public static final Rect[] TILES;
	public static final Rect[] POINT_BTNS;

	static {
		TILES = new Rect[Cnst.TILES_COUNT];
		for (int i = 0; i < Cnst.TILES_COUNT; i++) {
			TILES[i] = createRect(DrawAreas.TILES[i], DrawAreas.TILE_SIZE, DrawAreas.TILE_SIZE);
		}

		POINT_BTNS = new Rect[4];
		for (int i = 0; i < 4; i++) {
			POINT_BTNS[i] = createRect(DrawAreas.POINT_BTNS[i], DrawAreas.POINT_BTN_SIZE, DrawAreas.POINT_BTN_SIZE);
		}
	}

	public static final Rect ROLL_DICES_BTN = createRect(DrawAreas.ROLL_DICES_BTN, DrawAreas.ROLL_DICES_BTN_SIZE,
			DrawAreas.ROLL_DICES_BTN_SIZE);

	public static int getTileId(int x, int y) {
		for (int i = 0; i < Cnst.TILES_COUNT; i++) {
			if (TILES[i].contains(x, y)) {
				return i;
			}
		}
		return -1;
	}

	public static int getPointBtnId(int x, int y, int nPlayer) {
		for (int i = 0; i < nPlayer; i++) {
			if (POINT_BTNS[i].contains(x, y)) {
				return i;
			}
		}
		return -1;
	}

	private static Rect createRect(Point p, int w, int h) {
		return new Rect(p.getX(), p.getY(), p.getX() + w, p.getY() + h);
	}
}
