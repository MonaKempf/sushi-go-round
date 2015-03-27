package robot;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.imageio.ImageIO;

public class Player {

	private static SimpleLogger<Player> LOGGER = new SimpleLogger<Player>(
			Player.class);
	private Robot robot;

	private static final int OFFSET_X = 9;
	private static final int OFFSET_Y = 69;
	private static final int TIME = 1000;

	public Player() throws AWTException {
		robot = new Robot();
	}

	public void skipInto() {
		robot.delay(10000);
		click(310, 200); // PLAY
		robot.delay(1000);
		click(320, 390); // CONTINUE1
		robot.delay(1000);
		click(320, 400); // CONTINUE2
		robot.delay(1000);
		click(585, 450); // SKIP
		robot.delay(1000);
		click(320, 375); // CONTINUE3

	}

	public void mouseMove(int x, int y) {
		robot.mouseMove(OFFSET_X + x, OFFSET_Y + y);
	}

	public void click(int x, int y) {
		LOGGER.info("click on (" + x + "," + y + ")");
		mouseMove(x, y);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		robot.delay(TIME);
		mouseMove(0, 0);//(bug windows) reset mouse for next click
	}
	
	public void make(Dish dish){
		for ( Ingredient ingredient: dish.getIngredients() ) {
			click(ingredient.getX(), ingredient.getY());
		}
	}


}
