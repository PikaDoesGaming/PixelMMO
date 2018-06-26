package net.nspika.entities.creatures;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import net.nspika.gfx.Animation;
import net.nspika.gfx.Assets;
import net.nspika.handler.Handler;
import net.nspika.tiles.Tile;

public class Player extends Creature {

    private Animation anDown;
    private Animation anUp;
    private Animation anLeft;
    private Animation anRight;
    
    private String username;

    private int direction = 0;

    public Player(Handler handler, float x, float y, String username) {
        super(handler, x, y, Creature.D_WIDTH * 4, Creature.D_HEIGHT * 4);
        this.username = username;
        
        bounds.x = 48;
        bounds.y = 68;
        bounds.width = 32;
        bounds.height = 48;
        
        //Animations
        anDown = new Animation(135, Assets.player_down);
        anLeft = new Animation(135, Assets.player_left);
        anUp = new Animation(135, Assets.player_up);
        anRight = new Animation(135, Assets.player_right);
    }

    @Override
    public void tick() {
    	if(x < 0) {
    		x += speed;
    	}
    	if(x > handler.getLevel().getWidth() * Tile.TILEWIDTH - handler.getLevel().getHeight()) {
    		x -= speed;
    	}
    	
    	if(y < 0) {
    		y += speed;
    	}
    	
    	if(y > handler.getLevel().getHeight() * Tile.TILEHEIGHT - handler.getLevel().getHeight()) {
    		y -= speed;
    	}
    	
        anDown.tick();
        anUp.tick();
        anLeft.tick();
        anRight.tick();
        getInput();
        move();
        handler.getCamera().centerOnEntity(this);
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        if (handler.getKeyHandler().up)
            yMove = -speed;
        if (handler.getKeyHandler().down)
            yMove = speed;
        if (handler.getKeyHandler().left)
            xMove = -speed;
        if (handler.getKeyHandler().right)
            xMove = speed;
    }

    public BufferedImage getCurrentAnimation() {
        if (xMove != 0 || yMove != 0) {
            if (xMove < 0) {
                direction = 1;
                return anLeft.getCurrent();
            } else if (xMove > 0) {
                direction = 3;
                return anRight.getCurrent();
            } else if (yMove < 0) {
                direction = 2;
                return anUp.getCurrent();
            } else {
                direction = 0;
                return anDown.getCurrent();
            }
        } else return Assets.player_standing[direction];
    }


    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimation(), (int)(x - handler.getCamera().getxOffset()), (int)(y - handler.getCamera().getyOffset()), width, height, null);
//        g.setColor(Color.gray);
//        g.fillRect((int) (x + bounds.x - handler.getCamera().getxOffset()), (int)(y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
        g.setColor(Color.WHITE);
        Font myFont = new Font("Serif", Font.BOLD, 50);
        g.setFont(myFont);
        g.drawString(username, (int) (x + bounds.x - handler.getCamera().getxOffset()) - 32, (int) (y + bounds.y - handler.getCamera().getyOffset()) - 70);
    }
}