package com.chokwapeem.game;

public class GameStateManager {

	private GameState gameState;
	
	public static final int MENU = 0;
	public static final int PLAY = 1234;
	
	public GameStateManager() {
		setState(PLAY);
	}
	
	public void setState(int state) {
		if(gameState != null) {
			gameState.dispose();
		}
		
		if(state == MENU) {
			
		}
		
		if(state == PLAY) {
			gameState = new PlayState(this);
		}
	}
	
	public void update(float dt) {
		gameState.update(dt);
//		System.out.println(dt);
	}
	
	public void draw() {
		gameState.draw();
	}
	
}
