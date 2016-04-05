package com.skula.paniclab.constants;

import android.graphics.Rect;

import com.skula.paniclab.models.Point;

public class DrawAreas {
	public static final Point P0 = new Point(20, 0);

	public static final int TILE_SIZE = 200;
	public static final int TILES_DX = 10;
	public static final int TILES_DY = 10;

	public static final Point[] TILES;
	static {
		TILES = new Point[Cnst.TILES_COUNT];
		TILES[0] = P0;
		for (int i = 1; i < 4; i++) {
			TILES[i] = new Point(TILES[i - 1].getX() + TILE_SIZE + TILES_DX, TILES[i - 1].getY());
		}

		TILES[4] = new Point(TILES[3].getX(), TILES[3].getY() + TILE_SIZE + TILES_DY);
		TILES[5] = new Point(TILES[4].getX() + TILE_SIZE + TILES_DX, TILES[4].getY());
		TILES[6] = new Point(TILES[5].getX() + TILE_SIZE + TILES_DX, TILES[5].getY());
		TILES[7] = new Point(TILES[6].getX(), TILES[6].getY() - TILE_SIZE - TILES_DY);
		TILES[8] = new Point(TILES[7].getX() + TILE_SIZE + TILES_DX, TILES[7].getY());
		TILES[9] = new Point(TILES[8].getX() + TILE_SIZE + TILES_DX, TILES[8].getY());
		TILES[10] = new Point(TILES[9].getX() + TILE_SIZE + TILES_DX, TILES[9].getY());
		TILES[11] = new Point(TILES[10].getX(), TILES[10].getY() + TILE_SIZE + TILES_DY);
		TILES[12] = new Point(TILES[11].getX(), TILES[11].getY() + TILE_SIZE + TILES_DY);
		TILES[13] = new Point(TILES[12].getX(), TILES[12].getY() + TILE_SIZE + TILES_DY);
		TILES[14] = new Point(TILES[13].getX(), TILES[13].getY() + TILE_SIZE + TILES_DY);
		TILES[15] = new Point(TILES[14].getX() - TILE_SIZE - TILES_DX, TILES[14].getY());
		TILES[16] = new Point(TILES[15].getX() - TILE_SIZE - TILES_DX, TILES[15].getY());
		TILES[17] = new Point(TILES[16].getX() - TILE_SIZE - TILES_DX, TILES[16].getY());
		TILES[18] = new Point(TILES[17].getX() - (TILE_SIZE + TILES_DX) * 2, TILES[17].getY());
		TILES[19] = new Point(TILES[18].getX() - TILE_SIZE - TILES_DX, TILES[18].getY());
		TILES[20] = new Point(TILES[19].getX() - TILE_SIZE - TILES_DX, TILES[19].getY());
		TILES[21] = new Point(TILES[20].getX() - TILE_SIZE - TILES_DX, TILES[20].getY());
		TILES[22] = new Point(TILES[21].getX(), TILES[21].getY() - TILE_SIZE - TILES_DY);
		TILES[23] = new Point(TILES[22].getX(), TILES[22].getY() - TILE_SIZE - TILES_DY);
		TILES[24] = new Point(TILES[23].getX(), TILES[23].getY() - TILE_SIZE - TILES_DY);
	}
}
