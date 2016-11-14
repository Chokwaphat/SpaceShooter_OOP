package com.chokwapeem.game;

public abstract class GameState {
	protected GameStateManager gsm;
	
	protected GameState(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
	
	public abstract void init();
	public abstract void update(float dt);
	public abstract void draw();
	public abstract void handleInputPlayer1();
	public abstract void handleInputPlayer2();
	public abstract void dispose();
	
}
