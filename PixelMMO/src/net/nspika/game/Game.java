package net.nspika.game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JOptionPane;

import org.omg.Messaging.SyncScopeHelper;

import net.nspika.gfx.Assets;
import net.nspika.gfx.Camera;
import net.nspika.gfx.Display;
import net.nspika.handler.Handler;
import net.nspika.handler.KeyHandler;
import net.nspika.handler.MouseHandler;
import net.nspika.net.GameClient;
import net.nspika.net.GameServer;
import net.nspika.net.packets.Packet00Login;
import net.nspika.states.GameState;
import net.nspika.states.MenuState;
import net.nspika.states.State;
import net.nspika.utils.Fonts;

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
    
//    private GameServer gameServer;
//    private GameClient gameClient;

    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        keyHandler = new KeyHandler();
        mouseHandler = new MouseHandler();
    }

    //Initialize classes etc.
    private void init() {
    	Fonts.LoadFonts();
        display = new Display(title, width, height);
        display.getCanvas().addKeyListener(keyHandler);
        display.getCanvas().addMouseListener(mouseHandler);
        display.getCanvas().addMouseMotionListener(mouseHandler);
        display.getCanvas().requestFocus();
        Assets.init();
        
        handler = new Handler(this);
        camera = new Camera(handler, 0, 0);
       
        //States initialization
        menuState = new MenuState(handler, keyHandler);
        State.setState(menuState);
        //gameState = new GameState(handler);
        
//        Packet00Login loginPacket = new Packet00Login(JOptionPane.showInputDialog(this, "Please enter a name"));
//        loginPacket.writeData(gameClient);
        //gameClient.sendData("ping".getBytes());
        
    }
    

    public synchronized void start() {
        running = true;
        new Thread(this).start();
        
        //Initializing Multiplayer
//        int reply = JOptionPane.showConfirmDialog(null, "Do you want to Host the Server?", title, JOptionPane.YES_NO_OPTION);
//        if (reply == JOptionPane.YES_OPTION) {
//        	System.out.println("Starting the Server...");
//      		gameServer = new GameServer(this);
//      		gameServer.start();
//        }
//           	gameClient = new GameClient(this, "localhost");
//           	gameClient.start();
           	
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
        MouseHandler.tick();
        tickCount++;
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
}
