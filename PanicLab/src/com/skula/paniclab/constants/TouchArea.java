package com.skula.paniclab.constants;

import com.skula.paniclab.models.Point;

import android.graphics.Rect;

public class TouchArea {

	public static final Rect[] TILES;
	static {
		TILES = new Rect[Cnst.TILES_COUNT];
		for (int i = 0; i < Cnst.TILES_COUNT; i++) {
			TILES[i] = new Rect(DrawAreas.TILES[i].getX(), DrawAreas.TILES[i].getY(), DrawAreas.TILES[i].getX()
					+ DrawAreas.TILE_SIZE, DrawAreas.TILES[i].getY() + DrawAreas.TILE_SIZE);
		}
	}

	public static int getTileId(int x, int y) {
		for (int i = 0; i < Cnst.TILES_COUNT; i++) {
			if (TILES[i].contains(x, y)) {
				return i;
			}
		}
		return -1;
	}
}
