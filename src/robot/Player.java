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
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.imageio.ImageIO;

public class Player {

	private static SimpleLogger<Player> LOGGER = new SimpleLogger<Player>(Player.class);
	private Robot robot;

	public static final int OFFSET_X = 9;
	public static final int OFFSET_Y = 69;
	private static final int CLICK_DELAY = 1000;
	public static final int CLIENT_SPACING = 101;

	public static final File ONIGIRI = new File("order_database/onigiri.png");
	public static final File CALIFORNIA_ROLL = new File("order_database/california_roll.png");
	public static final File GUNKAN_MAKI = new File("order_database/gunkan_maki.png");

	public static Map<BufferedImage, Dish> IMAGE_DISH_MAPPING = new HashMap<BufferedImage, Dish>();
	static {
		try {
			IMAGE_DISH_MAPPING.put(ImageIO.read(ONIGIRI), Recipes.ONIGIRI);
			IMAGE_DISH_MAPPING.put(ImageIO.read(CALIFORNIA_ROLL), Recipes.CALIFORNIA_ROLL);
			IMAGE_DISH_MAPPING.put(ImageIO.read(GUNKAN_MAKI), Recipes.GUNKAN_MAKI);
		} catch (IOException e) {
			System.exit(-1);
		}
	}

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
		robot.delay(CLICK_DELAY);
		mouseMove(0, 0);// (bug windows) reset mouse for next click
	}

	public void make(Dish dish) {
		LOGGER.info("Making " + dish.getName());
		for (Ingredient ingredient : dish.getIngredients()) {
			click(ingredient.getX(), ingredient.getY());
		}
	}

	public void monitorOrders() throws IOException, InterruptedException {
		while (true) {
			LOGGER.info("Monitor orders:");
			for (int i = 0; i < 6; i++) {
				Rectangle rec = new Rectangle(OFFSET_X + i * CLIENT_SPACING + 35, OFFSET_Y + 50, 40, 35);
				BufferedImage img = robot.createScreenCapture(rec);

				if (!onGoing[i]) {
					Dish orderdedDish = searchPerfectMatch(img);
					if (orderdedDish != null) {
						make(orderdedDish);
						made(i);
					}
				}
				else if (searchPerfectMatch(img) == null) {
					served(i);
				}
			}
			Thread.sleep(100);
		}
	}

	public Dish searchPerfectMatch(BufferedImage img) {
		for (Entry<BufferedImage, Dish> entry : IMAGE_DISH_MAPPING.entrySet()) {
			if (compareImage(img, entry.getKey())) {
				return entry.getValue();
			}
		}

		return null;
	}

	public boolean[] onGoing = { false, false, false, false, false, false };

	public boolean made(int i) {
		onGoing[i] = true;
		return onGoing[i];
	}

	public boolean served(int i) {
		onGoing[i] = false;
		return onGoing[i];
	}

	public boolean compareImage(BufferedImage img, BufferedImage imgD) {
		for (int i = 0; i < img.getWidth(); i++) {
			for (int j = 0; j < img.getHeight(); j++) {
				if (img.getRGB(i, j) != imgD.getRGB(i, j)) {
					return false;
				}
			}
		}
		return true;
	}

}
