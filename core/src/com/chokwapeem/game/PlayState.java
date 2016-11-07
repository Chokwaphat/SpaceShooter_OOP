package com.chokwapeem.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PlayState extends GameState {

	private ShapeRenderer sr;

	private Player player;
	private Player2 player2;
	
	private ArrayList<Bullet> bullets;

	public PlayState(GameStateManager gsm) {
		
		super(gsm);

	}

	@Override
	public void init() {
		
		sr = new ShapeRenderer();
		
		bullets = new ArrayList<Bullet>();
		player = new Player(bullets);
		player2 = new Player2(bullets);
	}

	@Override
	public void update(float dt) {
		
		handleInput();

		player.update(dt);

		player2.update(dt);
		
		for(int i = 0; i < bullets.size(); i++) {
			
			bullets.get(i).update(dt);
			if(bullets.get(i).shouldRemove()) {
				bullets.remove(i);
				i--;
			}
		}
	}

	@Override
	public void draw() {
		player.draw(sr);

		player2.draw(sr);
		
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw(sr);
		}
	}

	@Override
	public void handleInput() {
		
		player.setLeft(GameKeys.isDown(GameKeys.LEFT));
		player.setRight(GameKeys.isDown(GameKeys.RIGHT));
		player.setUp(GameKeys.isDown(GameKeys.UP));
		if(GameKeys.isPressed(GameKeys.SHIFT)) {
			
			player.shoot();
		}

		player2.setLeft(GameKeys.isDown(GameKeys.LEFT2));
		player2.setRight(GameKeys.isDown(GameKeys.RIGHT2));
		player2.setUp(GameKeys.isDown(GameKeys.UP2));
		if(GameKeys.isPressed(GameKeys.SPACE)) {
			
			player2.shoot();
		}
	}

	@Override
	public void dispose() {

	}


}
