package net.strobl.game.states;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import net.strobl.game.Handler;
import net.strobl.game.entities.Bullet;
import net.strobl.game.entities.Meteorite;
import net.strobl.game.entities.Player;
import net.strobl.game.utils.Background;
import net.strobl.game.utils.CollisionDetection;
import net.strobl.game.utils.Files;
import net.strobl.game.utils.Fonts;
import net.strobl.game.utils.PauseButton;

public class GameState extends State {

	private Handler handler;
	private Player player;
	private ArrayList<Bullet> bullets;
	private ArrayList<Meteorite> meteors;
	private Background background;
	private Random random;
	public static boolean shootable = true;
	private int secs = 0;
	private int secs2 = 0;
	private int secs3 = 0;
	private int counter = 0;

	private int score = 0;

	private boolean shipHitted = false;
	private static boolean paused = false;
	private int ammo;

	private PauseButton pauseButton;

	private static boolean restart = false;

	private char gear;

	public GameState(Handler handler) {
		super(handler);

		player = new Player(handler);
		background = new Background(handler);
		bullets = new ArrayList<Bullet>();
		meteors = new ArrayList<Meteorite>();
		random = new Random();
		ammo = 4;
		
		pauseButton = new PauseButton(handler);
	}

	@Override
	public void tick() {

		if (restart) {
			player.setHP(3);
			player.setX(Handler.getWidth() - (Handler.getWidth() + player.getWidth()) / 2);
			player.setY(Handler.getHeight() - Handler.getWidth() / 5);
			ammo = 4;
			score = 0;

			meteors.clear();
			bullets.clear();

			restart = false;
		}

		pauseButton.tick();
		counter++;

		if (pauseButton.getButton().isClicked() && paused == false && counter > 5) {
			paused = true;
			counter = 0;
		}

		if (pauseButton.getButton().isClicked() && paused == true && counter > 5) {
			paused = false;
			counter = 0;
		}

		if (player.getAlive() && paused == false) {

			player.tick();

			background.tick();
			secs++;
			secs2++;
			secs3++;

			if (Handler.getKeyManager().space) {
				if (shootable && ammo > 1) {
					generateBullet();
					shootable = false;
				}
			}

			for (int i = 0; i < bullets.size(); i++) {
				bullets.get(i).tick();
				if (bullets.get(i).getY() + bullets.get(i).getHeight() < 0) {
					bullets.remove(i);
				}
			}

			for (int i = 0; i < meteors.size(); i++) {
				for (int j = 0; j < bullets.size(); j++) {
					if (CollisionDetection.testMeteoriteBulletCollision(meteors.get(i), bullets.get(j))) {
						meteors.remove(i);
						bullets.remove(j);
						score++;
						break;
					}
				}
			}

			for (int i = 0; i < meteors.size(); i++) {
				meteors.get(i).tick();
				if (meteors.get(i).getY() > Handler.getHeight()) {
					meteors.remove(i);
					score++;
				}
			}

			for (int i = 0; i < meteors.size(); i++) {
				if (CollisionDetection.testMeteoriteShipCollision(meteors.get(i), player)) {
					meteors.remove(i);
					shipHitted = true;
				}
			}

			if (secs - 50 >= 0) {
				secs = 0;
				shootable = true;
			}

			if (secs3 - 200 >= 0 && ammo <= 10) {
				secs3 = 0;
				ammo++;
			}

			if (shipHitted) {
				player.reduceHP(1);
				shipHitted = false;
			}

			if (secs2 - 20 >= 0) {
				generateMetoer();
				secs2 = 0;
			}
		}

		if (player.getHP() == 0) {
			Files.writeNumber("Highscores.txt", score);
			setState(Handler.getGame().deathState);
		}
	}

	@Override
	public void render(Graphics g) {

		background.render(g);
		player.render(g);

		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(g);
		}

		for (int i = 0; i < meteors.size(); i++) {
			meteors.get(i).render(g);
		}

		g.setColor(Color.WHITE);
		Fonts.drawLeftAllinedText(g, "Ammo", 10, 200, Fonts.playerNameFont);

		for (int i = 0; i < player.getHP(); i++) {
			g.setColor(Color.ORANGE);
			g.fillRect(9 + 80 * i, 29, 72, 72);
			g.setColor(Color.RED);
			g.fillRect(10 + 80 * i, 30, 70, 70);
		}

		for (int i = 1; i < ammo; i++) {
			g.setColor(Color.ORANGE);
			g.fillRect(17, 200 + 20 * i, 42, 12);
			g.setColor(Color.BLACK);
			g.fillRect(18, 201 + 20 * i, 40, 10);
		}

		if (ammo == 1) {
			g.setColor(Color.RED);
			Fonts.drawCenteredText(g, "Empty", 42, 220, Fonts.playerNameFont);
		}

		g.setColor(Color.RED);
		Fonts.drawCenteredText(g, Integer.toString(score), Handler.getWidth() / 2, Handler.getHeight() / 8,
				Fonts.scoreFont);
		pauseButton.render(g);
	}

	private void generateBullet() {
		ammo--;
		bullets.add(new Bullet(handler, player.getX() + player.getWidth() / 2 - 32 / 2, player.getY()));

	}

	private void generateMetoer() {
		int randX = random.nextInt(Handler.getWidth() - 20);
		int randSize = 1 + random.nextInt(3);
		int randSpeed = 1 + random.nextInt(5);
		meteors.add(new Meteorite(handler, randX, 0, randSpeed, randSize));
	}

	public static void restart() {
		restart = true;
	}

	public int getScore() {
		return score;
	}
	
	public static boolean displayMenu() {
		return paused;
	}
	
}
