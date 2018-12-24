package net.strobl.game;

public class Launcher {

	private final static String TITLE = "Meteorite Dodge";
	private final static int WIDTH = 600;
	private final static int HEIGHT = 700;
	public static void main (String[] args) {
		Game game = new Game(TITLE, WIDTH, HEIGHT);
		game.start();
	}
	
}
