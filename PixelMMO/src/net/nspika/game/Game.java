package net.nspika.game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import net.nspika.gfx.Assets;
import net.nspika.gfx.Camera;
import net.nspika.gfx.Display;
import net.nspika.handler.Handler;
import net.nspika.handler.KeyHandler;
import net.nspika.handler.MouseHandler;
import net.nspika.states.GameState;
import net.nspika.states.MenuState;
import net.nspika.states.State;

public class Game implements Runnable {

    private String title;
    private int width,height;

    private Graphics g;

    private boolean running;
    private int tickCount = 0;

    private Display display;

    //States declaration
    private State gameState;
    private State menuState;

    //Input
    private KeyHandler keyHandler;
    private MouseHandler mouseHandler;
    
    private Handler handler;
    
    private Camera camera;

    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        keyHandler = new KeyHandler();
        mouseHandler = new MouseHandler();
    }

    //Initialize classes etc.
    private void init() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyHandler);
        display.getFrame().addMouseListener(mouseHandler);
        Assets.init();
        
        camera = new Camera(this, 0, 0);
        handler = new Handler(this);

        //States initialization
        menuState = new MenuState(handler);
        gameState = new GameState(handler);
        State.setState(gameState);
    }

    public synchronized void start() {
        running = true;
        new Thread(this).start();
    }

    private synchronized void stop() {
        running = false;
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

    public KeyHandler getKeyHandler(){
        return keyHandler;
    }
    
    public Camera getCamera() {
    	return camera;
    }
    
    public int getWidth() {
    	return width;
    }
    
    public int getHeight() {
    	return height;
    }

    private void render() {
        //Buffer Strategy
        BufferStrategy bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        // Clear screen
        g.clearRect(0, 0, width, height);

        // Draw area
        if(State.getSate() != null){
            State.getSate().render(g);
        }

        // show
        bs.show();
        g.dispose();

    }

    private void tick() {
        keyHandler.tick();

        if(State.getSate() != null){
            State.getSate().tick();
        }
        tickCount++;
    }
}
