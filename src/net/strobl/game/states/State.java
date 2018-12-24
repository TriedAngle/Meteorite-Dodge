package net.strobl.game.states;

import java.awt.Graphics;

import net.strobl.game.Handler;

public abstract class State {

	protected Handler handler;
	
	private static State currentState;
	
	public State(Handler handler) {
		this.handler = handler;
	}
	
	public static State getState() {
		return currentState;
	}
	
	public static void setState(State state) {
		currentState = state;
	}
	
	public abstract void tick();

	public abstract void render(Graphics g);
}
