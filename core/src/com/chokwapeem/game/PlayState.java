package com.chokwapeem.game;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class PlayState extends GameState {
	private ShapeRenderer sr;

	private Player player;
	private Player2 player2;
	
	private ArrayList<Bullet> bullets;
	private ArrayList<Asteroid> asteroids;
	
	private int level;
	private int totalAsteroids;
	private int numAsteroidsLeft;

	public PlayState(GameStateManager gsm) {
		super(gsm);

	}

	@Override
	public void init() {
		sr = new ShapeRenderer();
		
		bullets = new ArrayList<Bullet>();
		player = new Player(bullets);
		player2 = new Player2(bullets);
		
		asteroids = new ArrayList<Asteroid>();
//		asteroids.add(new Asteroid(MathUtils.random(800),MathUtils.random(600),Asteroid.SMALL));
//		asteroids.add(new Asteroid(MathUtils.random(800),MathUtils.random(600),Asteroid.MEDIUM));
//		asteroids.add(new Asteroid(MathUtils.random(800),MathUtils.random(600),Asteroid.LARGE));
//		
		spawnAsteroids();
	}
	
	private void spawnAsteroids() {
		asteroids.clear();
		int numToSpawn = 6;
		totalAsteroids = 10;
		numAsteroidsLeft = totalAsteroids;
		
		for(int i = 0; i < numToSpawn; i++) {
			float x = MathUtils.random(Spaceshooter.WIDTH);
			float y = MathUtils.random(Spaceshooter.HEIGHT);
			
			float dx = x - player.getx();
			float dy = y - player.gety();
			float dist = (float)Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
			
			float dx2 = x - player2.getx();
			float dy2 = y - player2.gety();
			float dist2 = (float)Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
			
			while(dist < 100 && dist2 < 100) {
				x = MathUtils.random(Spaceshooter.WIDTH);
				y = MathUtils.random(Spaceshooter.HEIGHT);
				
				dx = x - player.getx();
				dy = y - player.gety();
				dist = (float)Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
				
				dx = x - player2.getx();
				dy = y - player2.gety();
				dist = (float)Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
			}
			
			asteroids.add(new Asteroid(x, y, Asteroid.LARGE));
		}
	}
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
		
		for(int i = 0; i < asteroids.size(); i++) {
			asteroids.get(i).update(dt);
			if(asteroids.get(i).shouldRemove()) {
				asteroids.remove(i);
				i--;
			}
		}
		checkCollisions();

	}
	
	private void checkCollisions() {
		for(int i = 0; i < asteroids.size(); i++) {
			Asteroid a = asteroids.get(i);
			//Not DONE
		}
		for(int i = 0; i < bullets.size(); i++) {
			Bullet b = bullets.get(i);
			for(int j = 0; j < asteroids.size(); j++) {
				Asteroid a = asteroids.get(j);
				if(a.pointToCheck(b.getx(), b.gety())) {
					bullets.remove(i);
					i--;
					asteroids.remove(j);
					j--;
					break;
				}
			}
		}
	}
	
	public void draw() {
		player.draw(sr);

		player2.draw(sr);
		
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw(sr);
		}
		
		for(int i = 0; i < asteroids.size(); i++) {
			asteroids.get(i).draw(sr);
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
