package net.nspika.states;

import java.awt.Graphics;

import net.nspika.entities.creatures.Player;
import net.nspika.game.Game;
import net.nspika.levels.Level;


public class GameState extends State {

    private Player player;
    private Level level;

    public GameState(Game game){
        super(game);
        player = new Player(game,100, 100);
        level = new Level("");
    }

    @Override
    public void tick() {
    	level.tick();
        player.tick();
        
    }

    @Override
    public void render(Graphics g) {
        
    	player.render(g);
    	level.render(g);
    }

}
