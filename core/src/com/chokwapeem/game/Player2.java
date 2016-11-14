package com.chokwapeem.game;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;

public class Player2 extends SpaceObject {
	private ArrayList<Bullet> bullets;
	private final int MAX_BULLETS = 3;
	
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;

	private float maxSpeed;
	private float accelerate;
	private float decelerate;
	
	private boolean hit;
	private boolean dead;
	
	private float hitTime;
	private float hitTimer;
	private Line2D.Float[] hitLines;
	private Point2D.Float[] hitLinesVector;
	
	public Player2 (ArrayList<Bullet> bullets) {
		this.bullets = bullets;
		
		x = Spaceshooter.WIDTH / 2 - 200;
		y = Spaceshooter.HEIGHT / 2;

		maxSpeed = 300;
		accelerate = 200;
		decelerate = 10;

		shapex = new float[4];
		shapey = new float[4];

		radians = (float)Math.PI  / 2;
		rotationSpeed = 3;


	}

	private void setShape() {
		shapex[0] = x + MathUtils.cos(radians) * 8;
		shapey[0] = y + MathUtils.sin(radians) * 8;

		shapex[1] = x + MathUtils.cos(radians - 4 * (float)Math.PI / 5) * 8;
		shapey[1] = y + MathUtils.sin(radians - 4 * (float)Math.PI  / 5) * 8;

		shapex[2] = x + MathUtils.cos(radians + (float)Math.PI ) * 5;
		shapey[2] = y + MathUtils.sin(radians + (float)Math.PI ) * 5;

		shapex[3] = x + MathUtils.cos(radians + 4 * (float)Math.PI  / 5) * 8;
		shapey[3] = y + MathUtils.sin(radians + 4 * (float)Math.PI  / 5) * 8;

	}

	public void setLeft(boolean b) {
		left = b;
	}

	public void setRight(boolean b) {
		right = b;
	}

	public void setUp(boolean b) {
		up = b;
	}
	
	public void setDown(boolean b) {
		down = b;
	}
	
	public void shoot() {
		if(bullets.size() == MAX_BULLETS) {
			return;
		}
		
		bullets.add(new Bullet(x, y, radians));
	}

	public void update(float dt) {
		if(hit) {
			hitTimer += dt;
			if(hitTimer > hitTime) {
				dead = true;
				hitTimer = 0;
			}
			for(int i = 0; i < hitLines.length; i++) {
				hitLines[i].setLine(hitLines[i].x1 + hitLinesVector[i].x * 10 * dt, hitLines[i].y1 + hitLinesVector[i].y * 10 * dt, hitLines[i].x2 + hitLinesVector[i].x * 10 * dt, hitLines[i].y2 + hitLinesVector[i].y * 10 * dt);
			}
			return;
		}
		if(left) {
			radians += rotationSpeed * dt;
		} else if(right) {
			radians -= rotationSpeed * dt;
		}
		if(up) {
			dx += MathUtils.cos(radians) * accelerate * dt;
			dy += MathUtils.sin(radians) * accelerate * dt;
		} else if(down) {
			dx -= MathUtils.cos(radians) * accelerate * dt;
			dy -= MathUtils.sin(radians) * accelerate * dt;
		}

		float vec = (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
		if(vec > 0) {
			dx -= (dx / vec) * decelerate * dt;
			dy -= (dy / vec) * decelerate * dt;
		}

		if(vec > maxSpeed) {
			dx = (dx / vec) * maxSpeed;
			dy = (dy / vec) * maxSpeed;
		}

		x += dx * dt;
		y += dy * dt;

		setShape();
		wrap();
	}

	public void draw(ShapeRenderer sr) {
		sr.setColor(0, 0, 255, 1);

		sr.begin(ShapeType.Line);
		if(hit) {
			for(int i = 0; i < hitLines.length; i++) {
				sr.line(hitLines[i].x1, hitLines[i].y1, hitLines[i].x2, hitLines[i].y2);
			}
			sr.end();
			return;
			
		}
		
		for(int i = 0, j = shapex.length - 1; i < shapex.length; j = i++) {
			sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);
		}

		sr.end();

	}
	
	public void hit() {
		if(hit) {
			return;
		}
		
		hit = true;
		dx = 0;
		dy = 0;
		left = false;
		right = false;
		up = false;
		down = false;
		
		hitLines = new Line2D.Float[4];
		for(int i = 0, j = hitLines.length - 1; i < hitLines.length; j = i++) {
			hitLines[i] = new Line2D.Float(shapex[i], shapey[i], shapex[j], shapey[j]);
		}
		hitLinesVector = new Point2D.Float[4];
		hitLinesVector[0] = new Point2D.Float(MathUtils.cos(radians + 1.5f), MathUtils.sin(radians + 1.5f));
		hitLinesVector[1] = new Point2D.Float(MathUtils.cos(radians - 1.5f), MathUtils.sin(radians - 1.5f));
		hitLinesVector[2] = new Point2D.Float(MathUtils.cos(radians - 2.8f), MathUtils.sin(radians - 2.8f));
		hitLinesVector[3] = new Point2D.Float(MathUtils.cos(radians + 2.8f), MathUtils.sin(radians + 2.8f));
	}

}
