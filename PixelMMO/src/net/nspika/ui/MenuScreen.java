package net.nspika.ui;

import java.awt.Color;
import java.awt.Graphics;

import net.nspika.handler.Handler;
import net.nspika.handler.KeyHandler;
import net.nspika.states.GameState;
import net.nspika.states.State;
import net.nspika.utils.Button;
import net.nspika.utils.Fonts;
import net.nspika.utils.InputField;

public class MenuScreen {

	private boolean isclicked = false;
	private GameState gameState;
	private Handler handler;
	private KeyHandler keyHandler;
	private Button start;

	// Buttons
	private static final int joinButtonYOffset = Handler.getHeight() * 3 / 4;
	private static final int joinButtonXDistance = 100;
	private static final int joinButtonWidth = 500;
	private static final int joinButtonHeight = 120;

	private static final int inputFieldYOffset = Handler.getHeight() * 3 / 5;
	private static final int inputFieldWidth = 490;
	private static final int inputFieldHeight = 50;

	private static final int errorYOffset = inputFieldYOffset - 30;

	private Button joinServerButton;
	private Button createServerButton;
	private InputField inputField;

	public MenuScreen(Handler handler, KeyHandler keyHandler) {
		this.handler = handler;
		this.keyHandler = keyHandler;
		gameState = new GameState(handler);

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

		inputField = new InputField(handler.getKeyHandler(), Handler.getWidth() / 2 + 205,
				Handler.getHeight() / 2 + 285, inputFieldWidth, inputFieldHeight, "username", 10, 10);
	}

	public void tick() {
		//State.setState(gameState);
		joinServerButton.tick();
		createServerButton.tick();
		inputField.tick();

		if (joinServerButton.isClicked() || createServerButton.isClicked()) {
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

}
