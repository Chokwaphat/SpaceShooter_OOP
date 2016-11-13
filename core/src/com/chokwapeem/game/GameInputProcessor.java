package com.chokwapeem.game;


import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;



public class GameInputProcessor extends InputAdapter {

	public boolean keyDown(int k) {
		if(k == Keys.UP) {
			GameKeys.setKey(GameKeys.UP, true);
		}
		if(k == Keys.LEFT) {
			GameKeys.setKey(GameKeys.LEFT, true);
		}
		if(k == Keys.RIGHT) {
			GameKeys.setKey(GameKeys.RIGHT, true);
		}
		if(k == Keys.DOWN) {
			GameKeys.setKey(GameKeys.DOWN, true);
		}
		if(k == Keys.W) {
			GameKeys.setKey(GameKeys.UP2, true);
		}
		if(k == Keys.A) {
			GameKeys.setKey(GameKeys.LEFT2, true);
		}
		if(k == Keys.D) {
			GameKeys.setKey(GameKeys.RIGHT2, true);
		}
		if(k == Keys.S) {
			GameKeys.setKey(GameKeys.DOWN2, true);
		}
		if(k == Keys.SPACE) {
			GameKeys.setKey(GameKeys.SPACE, true);
		}
		if(k == Keys.SHIFT_RIGHT) {
			GameKeys.setKey(GameKeys.SHIFT, true);
		}
		return true;

	}

	public boolean keyUp(int k) {
		if(k == Keys.UP) {
			GameKeys.setKey(GameKeys.UP, false);
		}
		if(k == Keys.LEFT) {
			GameKeys.setKey(GameKeys.LEFT, false);
		}
		if(k == Keys.RIGHT) {
			GameKeys.setKey(GameKeys.RIGHT, false);
		}
		if(k == Keys.DOWN) {
			GameKeys.setKey(GameKeys.DOWN, false);
		}
		if(k == Keys.W) {
			GameKeys.setKey(GameKeys.UP2, false);
		}
		if(k == Keys.A) {
			GameKeys.setKey(GameKeys.LEFT2, false);
		}
		if(k == Keys.D) {
			GameKeys.setKey(GameKeys.RIGHT2, false);
		}
		if(k == Keys.S) {
			GameKeys.setKey(GameKeys.DOWN2, false);
		}
		if(k == Keys.SPACE) {
			GameKeys.setKey(GameKeys.SPACE, false);
		}
		if(k == Keys.SHIFT_RIGHT) {
			GameKeys.setKey(GameKeys.SHIFT, false);
		}
		return true;
	}

}
