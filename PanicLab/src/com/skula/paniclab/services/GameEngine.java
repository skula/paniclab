package com.skula.paniclab.services;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.skula.paniclab.constants.Cnst;
import com.skula.paniclab.enums.Timeline;
import com.skula.paniclab.models.Tile;

public class GameEngine {
	private Tile[] tiles;
	private int nPlayers;
	private int[] scores;

	private int startTile;
	private int userSelTile;
	private int responseTile;
	private int diceDirection;
	private int diceLab;
	private int diceColor;
	private int diceShape;
	private int dicePattern;

	private Timeline timeline;

	public static void main(String[] args) {
		GameEngine ge = new GameEngine(2);
		ge.displayTiles();
		ge.rollDices();
		ge.displayDices();
		ge.calculResponse();
		ge.displayResponse();
	}

	public GameEngine(int nPlayers) {
		this.nPlayers = nPlayers;
		this.scores = new int[nPlayers];

		List<Tile> tmp = Tile.getTiles();
		Collections.shuffle(tmp);

		this.tiles = new Tile[Cnst.TILES_COUNT];
		for (int i = 0; i < Cnst.TILES_COUNT; i++) {
			tiles[i] = tmp.remove(0);
		}

		this.timeline = Timeline.ROLL_DICES;
	}

	public void process(int areaId) {
		switch (timeline) {
		case ROLL_DICES:
			rollDices();
			calculResponse();
			timeline = Timeline.SELECT_TILE;
			break;
		case SELECT_TILE:
			this.userSelTile = areaId;
			timeline = Timeline.TAKE_POINT;
			break;
		case TAKE_POINT:
			timeline = Timeline.ROLL_DICES;
			if(userSelTile == responseTile){
				scores[areaId]++;
			}else{
				scores[areaId] = scores[areaId] ==0?0: scores[areaId]-1;
			}
			break;
		}
	}

	public void displayResponse() {
		System.out.println("***** RESPONSE ******");
		System.out.println("Tuile départ: " + startTile);
		System.out.println("Tuile fin: " + responseTile);
	}

	public void displayTiles() {
		System.out.println("***** TUILES ******");
		for (int i = 0; i < Cnst.TILES_COUNT; i++) {
			System.out.println(i + ": " + tiles[i]);
		}
	}

	public void displayDices() {
		System.out.println("***** DES *****");
		switch (diceLab) {
		case Cnst.LAB_BLUE:
			System.out.println("Labo: bleu ("
					+ (diceDirection == Cnst.DICE_CLOCKWISE ? "sens horaire)"
							: "sens inverse)"));
			break;
		case Cnst.LAB_RED:
			System.out.println("Labo: rouge ("
					+ (diceDirection == Cnst.DICE_CLOCKWISE ? "sens horaire)"
							: "sens inverse)"));
			break;
		case Cnst.LAB_YELLOW:
			System.out.println("Labo: jaune ("
					+ (diceDirection == Cnst.DICE_CLOCKWISE ? "sens horaire)"
							: "sens inverse)"));
			break;
		}
		System.out.println("Couleur: "
				+ (diceColor == Cnst.AMOEBA_ORANGE ? "orange" : "bleue"));
		System.out.println("Forme: "
				+ (diceShape == Cnst.AMOEBA_TENTACLES ? "tentacules"
						: "antenes"));
		System.out.println("Motif: "
				+ (dicePattern == Cnst.AMOEBA_PEAS ? "poids" : "rayures"));
	}

	public void rollDices() {
		Random rand = new Random();
		switch (rand.nextInt(3)) {
		case 0:
			this.diceLab = Cnst.LAB_BLUE;
			break;
		case 1:
			this.diceLab = Cnst.LAB_RED;
			break;
		case 2:
			this.diceLab = Cnst.LAB_YELLOW;
			break;
		}

		this.diceDirection = rand.nextInt(2);

		if (rand.nextInt(2) == 0) {
			this.diceColor = Cnst.AMOEBA_BLUE;
		} else {
			this.diceColor = Cnst.AMOEBA_ORANGE;
		}

		if (rand.nextInt(2) == 0) {
			this.diceShape = Cnst.AMOEBA_TENTACLES;
		} else {
			this.diceShape = Cnst.AMOEBA_FEELERS;
		}

		if (rand.nextInt(2) == 0) {
			this.dicePattern = Cnst.AMOEBA_PEAS;
		} else {
			this.dicePattern = Cnst.AMOEBA_STRIPS;
		}
	}

	public boolean isGoodTile(){
		return userSelTile == responseTile;
	}
	
	public void calculResponse() {
		// trouver le labo de départ
		int curTile = getStartLab();
		startTile = curTile;
		int curColor = diceColor;
		int curShape = diceShape;
		int curPattern = dicePattern;

		boolean found = false;
		int mutationCount = 0;
		while (!found) {
			// recherche de la case suivante
			if (diceDirection == Cnst.DICE_CLOCKWISE) {
				if (curTile == 24) {
					curTile = 0;
				} else {
					curTile += 1;
				}
			} else {
				if (curTile == 0) {
					curTile = 24;
				} else {
					curTile -= 1;
				}
			}

			switch (tiles[curTile].getType()) {
			case Cnst.TILE_AMOEBA:
				if (tiles[curTile].matchs(curColor, curShape, curPattern)) {
					this.responseTile = curTile;
					found = true;
				}
				break;
			case Cnst.TILE_VENTILATION:
				// TODO: finir
				boolean tmpFound = false;
				int tmpCurTile = curTile;
				if (diceDirection == Cnst.DICE_CLOCKWISE) {
					while (!tmpFound) {
						if (tmpCurTile == 24) {
							tmpCurTile = 0;
						} else {
							tmpCurTile += 1;
						}
						if (tiles[tmpCurTile].getType() == Cnst.TILE_VENTILATION) {
							tmpFound = true;
							curTile = tmpCurTile;
						}
					}
				} else {
					while (!tmpFound) {
						if (tmpCurTile == 0) {
							tmpCurTile = 24;
						} else {
							tmpCurTile -= 1;
						}
						if (tiles[tmpCurTile].getType() == Cnst.TILE_VENTILATION) {
							tmpFound = true;
							curTile = tmpCurTile;
						}
					}
				}
				break;
			case Cnst.TILE_MUTATION:
				mutationCount++;
				if (mutationCount == 3) {
					this.responseTile = curTile;
					found = true;
				} else {
					switch (tiles[curTile].getMutation()) {
					case Cnst.MUTATION_COLOR:
						curColor = curColor == Cnst.AMOEBA_BLUE ? Cnst.AMOEBA_ORANGE
								: Cnst.AMOEBA_BLUE;
						break;
					case Cnst.MUTATION_SHAPE:
						curShape = curShape == Cnst.AMOEBA_TENTACLES ? Cnst.AMOEBA_FEELERS
								: Cnst.AMOEBA_TENTACLES;
						break;
					case Cnst.MUTATION_PATTERN:
						curPattern = curPattern == Cnst.AMOEBA_PEAS ? Cnst.AMOEBA_STRIPS
								: Cnst.AMOEBA_PEAS;
						break;
					}
				}
				break;
			default:
				break;
			}
		}
	}

	private int getStartLab() {
		switch (diceLab) {
		case Cnst.LAB_BLUE:
			for (int i = 0; i < Cnst.TILES_COUNT; i++) {
				if (tiles[i].isLab(Cnst.LAB_BLUE)) {
					return i;
				}
			}
		case Cnst.LAB_RED:
			for (int i = 0; i < Cnst.TILES_COUNT; i++) {
				if (tiles[i].isLab(Cnst.LAB_RED)) {
					return i;
				}
			}
		case Cnst.LAB_YELLOW:
			for (int i = 0; i < Cnst.TILES_COUNT; i++) {
				if (tiles[i].isLab(Cnst.LAB_YELLOW)) {
					return i;
				}
			}
		}
		return -1;
	}

	public Tile[] getTiles() {
		return tiles;
	}

	public void setTiles(Tile[] tiles) {
		this.tiles = tiles;
	}

	public int getnPlayers() {
		return nPlayers;
	}

	public void setnPlayers(int nPlayers) {
		this.nPlayers = nPlayers;
	}

	public int[] getScores() {
		return scores;
	}

	public void setScores(int[] scores) {
		this.scores = scores;
	}

	public int getResponseTile() {
		return responseTile;
	}

	public void setResponseTile(int responseTile) {
		this.responseTile = responseTile;
	}

	public int getDiceLab() {
		return diceLab;
	}

	public void setDiceLab(int diceLab) {
		this.diceLab = diceLab;
	}

	public int getDiceColor() {
		return diceColor;
	}

	public void setDiceColor(int diceColor) {
		this.diceColor = diceColor;
	}

	public int getDiceShape() {
		return diceShape;
	}

	public void setDiceShape(int diceShape) {
		this.diceShape = diceShape;
	}

	public int getDicePattern() {
		return dicePattern;
	}

	public void setDicePattern(int dicePattern) {
		this.dicePattern = dicePattern;
	}

	public int getStartTile() {
		return startTile;
	}

	public void setStartTile(int startTile) {
		this.startTile = startTile;
	}

	public int getDiceDirection() {
		return diceDirection;
	}

	public void setDiceDirection(int diceDirection) {
		this.diceDirection = diceDirection;
	}

	public Timeline getTimeline() {
		return timeline;
	}

	public Tile getTile(int i) {
		return tiles[i];
	}
}
