package net.nspika.ui;

import java.awt.Color;
import java.awt.Graphics;

import net.nspika.handler.Handler;
import net.nspika.handler.KeyHandler;
import net.nspika.handler.MouseHandler;
import net.nspika.levels.Level;
import net.nspika.states.GameState;
import net.nspika.states.State;
import net.nspika.utils.Button;
import net.nspika.utils.Fonts;
import net.nspika.utils.InputField;

public class MenuScreen {

	private GameState gameState;
	// Buttons
	private static final int joinButtonYOffset = Handler.getHeight() * 3 / 4;
	private static final int joinButtonWidth = 500;
	private static final int joinButtonHeight = 120;

	private static final int inputFieldYOffset = Handler.getHeight() * 3 / 5;
	private static final int inputFieldWidth = 490;
	private static final int inputFieldHeight = 50;

	private static final int errorYOffset = inputFieldYOffset - 30;
	
	public static String name;

	private Button joinServerButton;
	private Button createServerButton;
	private InputField inputField;
	private Handler handler;
	private Level level;

	public MenuScreen(Handler handler, KeyHandler keyHandler) {
		this.handler = handler;
		joinServerButton = new Button(50, joinButtonYOffset / 2, joinButtonWidth, joinButtonHeight, 15, 15);
		joinServerButton.setColor(Color.GREEN);
		joinServerButton.setFont(Fonts.playButtonFont);
		joinServerButton.setText("Join a Server");
		joinServerButton.setTextColor(Color.WHITE);

		createServerButton = new Button(50, joinButtonYOffset / 2 + 150, joinButtonWidth, joinButtonHeight, 15, 15);
		createServerButton.setColor(Color.GREEN);
		createServerButton.setFont(Fonts.playButtonFont);
		createServerButton.setText("Create a Server");
		createServerButton.setTextColor(Color.WHITE);

		inputField = new InputField(keyHandler, Handler.getWidth() / 2 + 205,
				Handler.getHeight() / 2 + 285, inputFieldWidth, inputFieldHeight, "username", 10, 10);
	}

	public void tick() {
		//State.setState(gameState);
		joinServerButton.tick();
		createServerButton.tick();
		inputField.tick();
		
		name = getName();
		
		if (joinServerButton.isClicked() || createServerButton.isClicked()) {
			gameState = new GameState(handler);
			System.out.println("Buttons was pressed");
			State.setState(gameState);
		}
		
	}

	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Handler.getWidth(), Handler.getHeight());
		Fonts.drawLeftAllinedText(g, "PixelMMO", new Color(255, 255, 255), 100, 100, Fonts.titleFont);
		joinServerButton.render(g);
		createServerButton.render(g);
		g.drawRect(Handler.getWidth() / 2 + 200, Handler.getHeight() / 2 - 200, Handler.getWidth()* 1/4 + 20, Handler.getHeight() / 2);
		inputField.render(g);
	}
	
	public String getName() {
		return inputField.getText();
	}

}
