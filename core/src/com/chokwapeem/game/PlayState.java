package com.chokwapeem.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PlayState extends GameState {
	
	private ShapeRenderer sr;
	
	private Player player;
	private Player2 player2;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
		
	}

	@Override
	public void init() {
		sr = new ShapeRenderer();
		player = new Player();
		player2 = new Player2();
	}

	@Override
	public void update(float dt) {
		handleInput();
		player.update(dt);
		player2.update(dt);
	}

	@Override
	public void draw() {
		player.draw(sr);
		player2.draw(sr);
	}

	@Override
	public void handleInput() {
		player.setLeft(GameKeys.isDown(GameKeys.LEFT));
		player.setRight(GameKeys.isDown(GameKeys.RIGHT));
		player.setUp(GameKeys.isDown(GameKeys.UP));
		player2.setLeft(GameKeys.isDown(GameKeys.LEFT2));
		player2.setRight(GameKeys.isDown(GameKeys.RIGHT2));
		player2.setUp(GameKeys.isDown(GameKeys.UP2));
	}

	@Override
	public void dispose() {
		
	}
	
	
}
