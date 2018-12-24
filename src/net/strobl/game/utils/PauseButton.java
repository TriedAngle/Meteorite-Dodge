package net.strobl.game.utils;

import java.awt.Color;
import java.awt.Graphics;

import net.strobl.game.Handler;
import net.strobl.game.states.GameState;

public class PauseButton {

	private Button button;

	private int x = Handler.getWidth() - 70, y = 20, width = 50, height = 50;

	private int width1 = 400;
	private int height1 = 500;
	private int y1 = Handler.getHeight() / 2 - height1 / 2 + 50;
	private int x1 = Handler.getWidth() / 2 - width1 / 2;
	private int y2;
	private int x2 = 0;
	private int counter = 0;

	public PauseButton(Handler handler) {
		button = new Button(x, y, width, height, 15, 15);
		button.setColor(Color.BLACK);
		button.setFont(Fonts.playButtonFont);
		button.setText("=");
		button.setTextColor(Color.WHITE);
	}

	public void tick() {
		button.tick();
		
		counter ++;

		if (GameState.displayMenu() == true && counter%10 == 0) {
			for(int i = y2; i <= y1; i++) {
				y2 = i;
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRoundRect(x - 5, y - 5, width + 10, height + 10, 15, 15);
		button.render(g);

		if (GameState.displayMenu() == true) {
			g.setColor(Color.BLACK);
			g.fillRect(x1 - 2, y2 - 2, width1 + 4, height1 + 4);
			g.setColor(Color.ORANGE);
			g.fillRect(x1, y2, width1, height1);
		}

	}

	public Button getButton() {
		return button;
	}

}
