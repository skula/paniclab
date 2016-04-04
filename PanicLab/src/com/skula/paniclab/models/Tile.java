package com.skula.paniclab.models;

import java.util.ArrayList;
import java.util.List;

import com.skula.paniclab.R;
import com.skula.paniclab.constants.Cnst;

public class Tile {
	private int drawId;
	private int type;
	private int shape;
	private int color;
	private int pattern;
	private int mutation;

	public Tile(int drawId, int type, int shape, int color, int pattern, int mutation) {
		this.drawId = drawId;
		this.type = type;
		this.shape = shape;
		this.color = color;
		this.pattern = pattern;
		this.mutation = mutation;
	}

	public boolean isLab(int color) {
		return type == Cnst.TILE_LAB && this.color == color;
	}

	public static List<Tile> getTiles() {
		List<Tile> tmp = new ArrayList<Tile>();
		// ventilations
		tmp.add(new Tile(R.drawable.ventilation, Cnst.TILE_VENTILATION, Cnst.VOID, Cnst.VOID, Cnst.VOID, Cnst.VOID));
		tmp.add(new Tile(R.drawable.ventilation, Cnst.TILE_VENTILATION, Cnst.VOID, Cnst.VOID, Cnst.VOID, Cnst.VOID));
		tmp.add(new Tile(R.drawable.ventilation, Cnst.TILE_VENTILATION, Cnst.VOID, Cnst.VOID, Cnst.VOID, Cnst.VOID));
		// labos
		tmp.add(new Tile(R.drawable.lab_blue, Cnst.TILE_LAB, Cnst.VOID, Cnst.LAB_BLUE, Cnst.VOID, Cnst.VOID));
		tmp.add(new Tile(R.drawable.lab_red, Cnst.TILE_LAB, Cnst.VOID, Cnst.LAB_RED, Cnst.VOID, Cnst.VOID));
		tmp.add(new Tile(R.drawable.lab_yellow, Cnst.TILE_LAB, Cnst.VOID, Cnst.LAB_YELLOW, Cnst.VOID, Cnst.VOID));
		// salles de mutation
		tmp.add(new Tile(R.drawable.mutation_color, Cnst.TILE_MUTATION, Cnst.VOID, Cnst.VOID, Cnst.VOID,
				Cnst.MUTATION_COLOR));
		tmp.add(new Tile(R.drawable.mutation_pattern, Cnst.TILE_MUTATION, Cnst.VOID, Cnst.VOID, Cnst.VOID,
				Cnst.MUTATION_PATTERN));
		tmp.add(new Tile(R.drawable.mutation_shape, Cnst.TILE_MUTATION, Cnst.VOID, Cnst.VOID, Cnst.VOID,
				Cnst.MUTATION_SHAPE));
		// amibes
		tmp.add(new Tile(R.drawable.feelers_blue_peas, Cnst.TILE_AMOEBA, Cnst.AMOEBA_FEELERS, Cnst.AMOEBA_BLUE,
				Cnst.AMOEBA_PEAS, Cnst.VOID));
		tmp.add(new Tile(R.drawable.feelers_blue_peas, Cnst.TILE_AMOEBA, Cnst.AMOEBA_FEELERS, Cnst.AMOEBA_BLUE,
				Cnst.AMOEBA_PEAS, Cnst.VOID));

		tmp.add(new Tile(R.drawable.feelers_blue_strips, Cnst.TILE_AMOEBA, Cnst.AMOEBA_FEELERS, Cnst.AMOEBA_BLUE,
				Cnst.AMOEBA_STRIPS, Cnst.VOID));
		tmp.add(new Tile(R.drawable.feelers_blue_strips, Cnst.TILE_AMOEBA, Cnst.AMOEBA_FEELERS, Cnst.AMOEBA_BLUE,
				Cnst.AMOEBA_STRIPS, Cnst.VOID));

		tmp.add(new Tile(R.drawable.feelers_orange_peas, Cnst.TILE_AMOEBA, Cnst.AMOEBA_FEELERS, Cnst.AMOEBA_ORANGE,
				Cnst.AMOEBA_PEAS, Cnst.VOID));
		tmp.add(new Tile(R.drawable.feelers_orange_peas, Cnst.TILE_AMOEBA, Cnst.AMOEBA_FEELERS, Cnst.AMOEBA_ORANGE,
				Cnst.AMOEBA_PEAS, Cnst.VOID));

		tmp.add(new Tile(R.drawable.feelers_orange_strips, Cnst.TILE_AMOEBA, Cnst.AMOEBA_FEELERS, Cnst.AMOEBA_ORANGE,
				Cnst.AMOEBA_STRIPS, Cnst.VOID));
		tmp.add(new Tile(R.drawable.feelers_orange_strips, Cnst.TILE_AMOEBA, Cnst.AMOEBA_FEELERS, Cnst.AMOEBA_ORANGE,
				Cnst.AMOEBA_STRIPS, Cnst.VOID));

		tmp.add(new Tile(R.drawable.tentacles_blue_peas, Cnst.TILE_AMOEBA, Cnst.AMOEBA_TENTACLES, Cnst.AMOEBA_BLUE,
				Cnst.AMOEBA_PEAS, Cnst.VOID));
		tmp.add(new Tile(R.drawable.tentacles_blue_peas, Cnst.TILE_AMOEBA, Cnst.AMOEBA_TENTACLES, Cnst.AMOEBA_BLUE,
				Cnst.AMOEBA_PEAS, Cnst.VOID));

		tmp.add(new Tile(R.drawable.tentacles_blue_strips, Cnst.TILE_AMOEBA, Cnst.AMOEBA_TENTACLES, Cnst.AMOEBA_BLUE,
				Cnst.AMOEBA_STRIPS, Cnst.VOID));
		tmp.add(new Tile(R.drawable.tentacles_blue_strips, Cnst.TILE_AMOEBA, Cnst.AMOEBA_TENTACLES, Cnst.AMOEBA_BLUE,
				Cnst.AMOEBA_STRIPS, Cnst.VOID));

		tmp.add(new Tile(R.drawable.tentacles_orange_peas, Cnst.TILE_AMOEBA, Cnst.AMOEBA_TENTACLES, Cnst.AMOEBA_ORANGE,
				Cnst.AMOEBA_PEAS, Cnst.VOID));
		tmp.add(new Tile(R.drawable.tentacles_orange_peas, Cnst.TILE_AMOEBA, Cnst.AMOEBA_TENTACLES, Cnst.AMOEBA_ORANGE,
				Cnst.AMOEBA_PEAS, Cnst.VOID));

		tmp.add(new Tile(R.drawable.tentacles_orange_strips, Cnst.TILE_AMOEBA, Cnst.AMOEBA_TENTACLES,
				Cnst.AMOEBA_ORANGE, Cnst.AMOEBA_STRIPS, Cnst.VOID));
		tmp.add(new Tile(R.drawable.tentacles_orange_strips, Cnst.TILE_AMOEBA, Cnst.AMOEBA_TENTACLES,
				Cnst.AMOEBA_ORANGE, Cnst.AMOEBA_STRIPS, Cnst.VOID));

		return tmp;
	}

	public boolean matchs(int color, int shape, int pattern) {
		return this.color == color && this.shape == shape && this.pattern == pattern;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getShape() {
		return shape;
	}

	public void setShape(int shape) {
		this.shape = shape;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getPattern() {
		return pattern;
	}

	public void setPattern(int pattern) {
		this.pattern = pattern;
	}

	public int getMutation() {
		return mutation;
	}

	public void setMutation(int mutation) {
		this.mutation = mutation;
	}

	@Override
	public String toString() {
		String res = "";
		switch (type) {
		case Cnst.TILE_AMOEBA:
			res += "AMIBE ";
			switch (color) {
			case Cnst.AMOEBA_BLUE:
				res += "bleu ";
				break;
			case Cnst.AMOEBA_ORANGE:
				res += "orange ";
				break;
			}
			switch (shape) {
			case Cnst.AMOEBA_TENTACLES:
				res += "a tentacules et ";
				break;
			case Cnst.AMOEBA_FEELERS:
				res += "a antenes et ";
				break;
			}
			switch (pattern) {
			case Cnst.AMOEBA_PEAS:
				res += "a pois";
				break;
			case Cnst.AMOEBA_STRIPS:
				res += "a rayures";
				break;
			}
			break;
		case Cnst.TILE_LAB:
			res += "LABO ";
			switch (color) {
			case Cnst.LAB_BLUE:
				res += "bleu";
				break;
			case Cnst.LAB_RED:
				res += "rouge";
				break;
			case Cnst.LAB_YELLOW:
				res += "jaune";
				break;
			}
			break;
		case Cnst.TILE_VENTILATION:
			res += "VENTILATION";
			break;
		case Cnst.TILE_MUTATION:
			res += "MUTATION ";
			switch (mutation) {
			case Cnst.MUTATION_COLOR:
				res += " de couleur";
				break;
			case Cnst.MUTATION_PATTERN:
				res += " de motif";
				break;
			case Cnst.MUTATION_SHAPE:
				res += " de forme";
				break;
			}
			break;
		}

		return res;
	}

	public int getDrawId() {
		return drawId;
	}

}
