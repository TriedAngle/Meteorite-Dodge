package net.strobl.game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import net.strobl.game.gfx.Display;
import net.strobl.game.managers.KeyManager;
import net.strobl.game.managers.MouseManager;
import net.strobl.game.states.DeathState;
import net.strobl.game.states.GameState;
import net.strobl.game.states.HighScoreState;
import net.strobl.game.states.MenuState;
import net.strobl.game.states.State;
import net.strobl.game.utils.Fonts;


public class Game implements Runnable{

    private Thread thread;
    private Graphics g;
	
    public static String title;
    public static int width,height;
    private boolean running;
    private int tickCount = 0;
    
    private Display display;
    private Handler handler;
    private KeyManager keyManager;
    public State gameState;
    public State deathState;
    public State menuState;
    public State highScoreState;
    private MouseManager mouseManager;
    
    public Game(String title, int width, int height) {
    	Game.title = title;
    	Game.width = width;
    	Game.height = height;
    }
    
    public void init() {
    	Fonts.init();
    	handler = new Handler(this);
    	keyManager = new KeyManager(handler);
    	mouseManager = new MouseManager();
        display = new Display(title, width, height);
        display.getCanvas().addKeyListener(keyManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        
        gameState = new GameState(handler);
        menuState = new MenuState(handler, keyManager);
        deathState = new DeathState(handler);
        highScoreState = new HighScoreState(handler);
        State.setState(menuState);
    }
    
	@Override
	public void run() {
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D / 60D;

        int ticks = 0;
        int frames = 0;

        long lastTimer = System.currentTimeMillis();
        double delta = 0;

        init();

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;
            boolean shouldRender = false;
            while (delta >= 1) {
                ticks++;
                tick();
                delta -= 1;
                shouldRender = true;
            }

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (shouldRender) {
                frames++;
                render();
            }
            //Resets ticks
            if (System.currentTimeMillis() - lastTimer >= 1000) {
            	display.tick(frames, ticks);
                lastTimer += 1000;
                frames = 0;
                ticks = 0;
            }
        }
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		running = false;
	}
	
	public void tick() {
		State.getState().tick();
		keyManager.tick();
		MouseManager.tick();
	}
	
	public void render() {
        BufferStrategy bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        
        // Clear screen
        g.clearRect(0, 0, width, height);

        // Draw area
        State.getState().render(g);
        
        // show
        bs.show();
        g.dispose();

	}
	

}
