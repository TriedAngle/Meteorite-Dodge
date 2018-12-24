package net.strobl.game.states;

import java.awt.Color;
import java.awt.Graphics;

import net.strobl.game.Handler;
import net.strobl.game.managers.KeyManager;
import net.strobl.game.utils.Background;
import net.strobl.game.utils.Button;
import net.strobl.game.utils.Fonts;
import net.strobl.game.utils.InputField;


public class MenuState extends State {
	
	private Background background;
	
	private static final int startGameButtonYOffset = Handler.getHeight() * 3 / 4;
	private static final int startGameButtonWidth = Handler.getWidth() * 1/2;
	private static final int startGameButtonHeight = 120;

	private static final int inputFieldYOffset = Handler.getHeight() * 3 / 4;
	private static final int inputFieldWidth = Handler.getWidth() / 2;
	private static final int inputFieldHeight = 50;

	private static final int errorYOffset = inputFieldYOffset - 30;
	
	private Button startGameButton;
	private InputField nameField;

	public MenuState(Handler handler, KeyManager keyManager) {
		super(handler);
		
		background = new Background(handler);
		
		startGameButton = new Button(Handler.getWidth() * 1/4 , startGameButtonYOffset / 2, startGameButtonWidth, startGameButtonHeight, 15, 15);
		startGameButton.setColor(Color.ORANGE);
		startGameButton.setFont(Fonts.playButtonFont);
		startGameButton.setText("Start Game");
		startGameButton.setTextColor(Color.BLACK);

		nameField = new InputField(keyManager, Handler.getWidth() / 4, inputFieldYOffset, inputFieldWidth, inputFieldHeight, "Your Name", 10, 10);
	}

	@Override
	public void tick() {
		background.tick();
		startGameButton.tick();
		nameField.tick();
		if (startGameButton.isClicked()) {
			System.out.println("isclicked");
			setState(Handler.getGame().gameState);
		}
	}

	@Override
	public void render(Graphics g) {
		background.render(g);
		Fonts.drawTitle(g, "Meteorite", Color.RED, Fonts.titleFont, 50, true);
		Fonts.drawTitle(g, "Dodge", Color.RED, Fonts.titleFont, 115, true);
		startGameButton.render(g);
		g.setColor(Color.white);
		nameField.render(g);
	}

}
