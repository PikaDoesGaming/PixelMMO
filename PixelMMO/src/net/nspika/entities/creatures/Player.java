package net.nspika.entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import net.nspika.gfx.Animation;
import net.nspika.gfx.Assets;
import net.nspika.handler.Handler;



public class Player extends Creature {

    private Animation anDown;
    private Animation anUp;
    private Animation anLeft;
    private Animation anRight;

    private int direction = 0;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.D_WIDTH * 4, Creature.D_HEIGHT * 4);

        //Animations
        anDown = new Animation(135, Assets.player_down);
        anLeft = new Animation(135, Assets.player_left);
        anUp = new Animation(135, Assets.player_up);
        anRight = new Animation(135, Assets.player_right);
    }

    @Override
    public void tick() {
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

    }
}