package com.skula.paniclab.constants;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.skula.paniclab.R;


// TODO: image a 150x150 sous photoshop
public class PictureLibrary {
	private Map<Integer, Bitmap> map;

	@SuppressLint("UseSparseArrays")
	public PictureLibrary(Resources res) {
		this.map = new HashMap<Integer, Bitmap>();
		this.map.put(R.drawable.ventilation, BitmapFactory.decodeResource(res, R.drawable.ventilation));
		
		this.map.put(R.drawable.lab_blue, BitmapFactory.decodeResource(res, R.drawable.lab_blue));
		this.map.put(R.drawable.lab_red, BitmapFactory.decodeResource(res, R.drawable.lab_red));
		this.map.put(R.drawable.lab_yellow, BitmapFactory.decodeResource(res, R.drawable.lab_yellow));
		
		this.map.put(R.drawable.mutation_color, BitmapFactory.decodeResource(res, R.drawable.mutation_color));
		this.map.put(R.drawable.mutation_shape, BitmapFactory.decodeResource(res, R.drawable.mutation_shape));
		this.map.put(R.drawable.mutation_pattern, BitmapFactory.decodeResource(res, R.drawable.mutation_pattern));

		this.map.put(R.drawable.tentacles_blue_peas, BitmapFactory.decodeResource(res, R.drawable.tentacles_blue_peas));
		this.map.put(R.drawable.tentacles_blue_strips, BitmapFactory.decodeResource(res, R.drawable.tentacles_blue_strips));
		this.map.put(R.drawable.tentacles_orange_peas, BitmapFactory.decodeResource(res, R.drawable.tentacles_orange_peas));
		this.map.put(R.drawable.tentacles_orange_strips, BitmapFactory.decodeResource(res, R.drawable.tentacles_orange_strips));
		this.map.put(R.drawable.feelers_blue_peas, BitmapFactory.decodeResource(res, R.drawable.feelers_blue_peas));
		this.map.put(R.drawable.feelers_blue_strips, BitmapFactory.decodeResource(res, R.drawable.feelers_blue_strips));
		this.map.put(R.drawable.feelers_orange_peas, BitmapFactory.decodeResource(res, R.drawable.feelers_orange_peas));
		this.map.put(R.drawable.feelers_orange_strips, BitmapFactory.decodeResource(res, R.drawable.feelers_orange_strips));
		
		this.map.put(R.drawable.dice_orange, BitmapFactory.decodeResource(res, R.drawable.dice_orange));
		this.map.put(R.drawable.dice_blue, BitmapFactory.decodeResource(res, R.drawable.dice_blue));
		this.map.put(R.drawable.dice_tentacles, BitmapFactory.decodeResource(res, R.drawable.dice_tentacles));
		this.map.put(R.drawable.dice_feelers, BitmapFactory.decodeResource(res, R.drawable.dice_feelers));
		this.map.put(R.drawable.dice_peas, BitmapFactory.decodeResource(res, R.drawable.dice_peas));
		this.map.put(R.drawable.dice_strips, BitmapFactory.decodeResource(res, R.drawable.dice_strips));
		this.map.put(R.drawable.dice_lab_blue_clockwise, BitmapFactory.decodeResource(res, R.drawable.dice_lab_blue_clockwise));
		this.map.put(R.drawable.dice_lab_blue_counterclockwise, BitmapFactory.decodeResource(res, R.drawable.dice_lab_blue_counterclockwise));
		this.map.put(R.drawable.dice_lab_red_clockwise, BitmapFactory.decodeResource(res, R.drawable.dice_lab_red_clockwise));
		this.map.put(R.drawable.dice_lab_red_counterclockwise, BitmapFactory.decodeResource(res, R.drawable.dice_lab_red_counterclockwise));
		this.map.put(R.drawable.dice_lab_yellow_clockwise, BitmapFactory.decodeResource(res, R.drawable.dice_lab_yellow_clockwise));
		this.map.put(R.drawable.dice_lab_yellow_counterclockwise, BitmapFactory.decodeResource(res, R.drawable.dice_lab_yellow_counterclockwise));

		this.map.put(R.drawable.point_minus, BitmapFactory.decodeResource(res, R.drawable.point_minus));
		this.map.put(R.drawable.point_plus, BitmapFactory.decodeResource(res, R.drawable.point_plus));

		this.map.put(R.drawable.btn_roll, BitmapFactory.decodeResource(res, R.drawable.btn_roll));
		

		this.map.put(R.drawable.answer_found, BitmapFactory.decodeResource(res, R.drawable.answer_found));
		this.map.put(R.drawable.answer_wrong, BitmapFactory.decodeResource(res, R.drawable.answer_wrong));
		this.map.put(R.drawable.answer_right, BitmapFactory.decodeResource(res, R.drawable.answer_right));
	}

	public Bitmap get(int id) {
		return map.get(id);
	}
}