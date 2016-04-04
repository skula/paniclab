package com.skula.paniclab.constants;

import android.graphics.Rect;

import com.skula.paniclab.models.Point;

public class DrawAreas {
	public static final Point P0 = new Point(0, 0);

	public static final int TILE_SIZE = 200;
	public static final int TILES_DX = 10;
	public static final int TILES_DY = 10;

	public static final Point[] TILES;
	static {
		TILES = new Point[Cnst.TILES_COUNT];
		TILES[0] = P0;
		for (int i = 1; i < 9; i++) {
			TILES[i] = new Point(TILES[i - 1].getX() + TILE_SIZE + TILES_DX, TILES[i - 1].getY());
		}
	}

	public static int getTileId(int x, int y) {
		Rect r = null;
		for (int i = 1; i < Cnst.TILES_COUNT; i++) {
			r = new Rect(TILES[i].getX(), TILES[i].getY(), TILES[i].getX() + TILE_SIZE, TILES[i].getY() + TILE_SIZE);
			if (r.contains(x, y)) {
				return i;
			}
		}
		return -1;
	}
}
