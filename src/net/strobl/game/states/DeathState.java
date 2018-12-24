package net.strobl.game.states;

import java.awt.Color;
import java.awt.Graphics;

import net.strobl.game.Handler;
import net.strobl.game.utils.Background;
import net.strobl.game.utils.Button;
import net.strobl.game.utils.Fonts;

public class DeathState extends State {

	private GameState gameState;
	private Background background;
	
	private Button restartButton;
	private Button highscoreButton;
	private Button menuButton;
	private static final int restartButtonYOffset = Handler.getHeight() / 2 - 35;
	private static final int restartButtonWidth = 400;
	private static final int restartButtonHeight = 80;
	
	private static final int highscoreYOffset = restartButtonYOffset + restartButtonHeight + 20;
	private static final int highscoreButtonWidth = 400;
	private static final int highscoreButtonHeight = 80;
	
	private static final int menuYOffset = highscoreYOffset + highscoreButtonHeight+ 20;
	private static final int menuButtonWidth = 400;
	private static final int menuButtonHeight = 80;
	

	public DeathState(Handler handler) {
		super(handler);
		gameState = (GameState) Handler.getGame().gameState;
		background = new Background(handler);
		
		restartButton = new Button(Handler.getWidth() / 2 - restartButtonWidth/2, restartButtonYOffset, restartButtonWidth, restartButtonHeight, 15, 15);
		restartButton.setColor(Color.ORANGE);
		restartButton.setFont(Fonts.playButtonFont);
		restartButton.setText("Restart Game");
		restartButton.setTextColor(Color.BLACK);
		
		highscoreButton = new Button(Handler.getWidth() / 2 - highscoreButtonWidth/2, highscoreYOffset, highscoreButtonWidth, highscoreButtonHeight, 15, 15);
		highscoreButton.setColor(Color.ORANGE);
		highscoreButton.setFont(Fonts.playButtonFont);
		highscoreButton.setText("Highscores");
		highscoreButton.setTextColor(Color.BLACK);
		
		menuButton = new Button(Handler.getWidth() / 2 - menuButtonWidth/2, menuYOffset, menuButtonWidth, menuButtonHeight, 15, 15);
		menuButton.setColor(Color.ORANGE);
		menuButton.setFont(Fonts.playButtonFont);
		menuButton.setText("Menu");
		menuButton.setTextColor(Color.BLACK);
	}

	@Override
	public void tick() {
		background.tick();
		restartButton.tick();
		menuButton.tick();
		highscoreButton.tick();
		
		if(restartButton.isClicked()) {
			GameState.restart();
			setState(Handler.getGame().gameState);
		}
		
		if(menuButton.isClicked()) {
			GameState.restart();
			setState(Handler.getGame().menuState);
		}
		
		if(highscoreButton.isClicked()) {
			GameState.restart();
			setState(Handler.getGame().highScoreState);
		}
	}

	@Override
	public void render(Graphics g) {
		background.render(g);
		g.setColor(Color.ORANGE);
		Fonts.drawCenteredText(g, "You Died", Handler.getWidth() / 2, Handler.getHeight() / 8, Fonts.titleFont);
		
		Fonts.drawCenteredText(g, Integer.toString(gameState.getScore()), Handler.getWidth() / 2, Handler.getHeight() / 4, Fonts.scoreFont);
		
		restartButton.render(g);
		highscoreButton.render(g);
		menuButton.render(g);
	}

}
