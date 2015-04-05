package robot;

import static robot.Dish.CALIFORNIA_ROLL;
import static robot.Dish.GUNKAN_MAKI;
import static robot.Dish.ONIGIRI;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

public class Player {

	private static SimpleLogger<Player> LOGGER = new SimpleLogger<Player>(Player.class);
	public static final int OFFSET_X = 9;
	public static final int OFFSET_Y = 69;
	private static final int CLICK_DELAY = 0;
	public static final int CLIENT_SPACING = 101;

	public static Map<BufferedImage, Dish> IMAGE_DISH_MAPPING = new HashMap<BufferedImage, Dish>();
	static {
		try {
			IMAGE_DISH_MAPPING.put(ImageIO.read(new File("order_database/onigiri.png")), ONIGIRI);
			IMAGE_DISH_MAPPING.put(ImageIO.read(new File("order_database/california_roll.png")), CALIFORNIA_ROLL);
			IMAGE_DISH_MAPPING.put(ImageIO.read(new File("order_database/gunkan_maki.png")), GUNKAN_MAKI);
		} catch (IOException e) {
			System.exit(-1);
		}
	}

	private Robot robot;
	private StockManager stockManager;
	public boolean[] onGoingOrders = { false, false, false, false, false, false };

	public Player() throws AWTException {
		robot = new Robot();
		stockManager = new StockManager();
	}

	public void skipInto() {
		robot.delay(10000);
		click(GUIInterface.PLAY);
		robot.delay(1000);
		click(GUIInterface.CONTINUE1);
		robot.delay(1000);
		click(GUIInterface.CONTINUE2);
		robot.delay(1000);
		click(GUIInterface.SKIP);
		robot.delay(1000);
		click(GUIInterface.CONTINUE3);
	}

	public void mouseMove(Coordinate coordinate) {
		robot.mouseMove(OFFSET_X + coordinate.getX(), OFFSET_Y + coordinate.getY());
	}

	public void click(Coordinate coordinate) {
		mouseMove(coordinate);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.delay(100);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		robot.mouseMove(OFFSET_X, OFFSET_Y);// (bug windows) reset mouse for
											// next click
	}

	public void chooseIngredient(Ingredient ingredient) {
		LOGGER.info("click on: " + ingredient);

		stockManager.stockTake(ingredient);
		click(GUIInterface.INGREDIENS_COORDINATES.get(ingredient));
	}

	public void buyStock(Ingredient ingredient) {
		LOGGER.info("Buying stock for " + ingredient);
		for (Coordinate coordinate : GUIInterface.BUY_INGREDIENS_COORDINATES.get(ingredient)) {
			click(coordinate);
			robot.delay(100);
		}
		robot.delay(8000);
		stockManager.updateStock(ingredient);
	}

	public void make(Dish dish) {
		LOGGER.info("Making " + dish);
		for (Ingredient ingredient : dish.getIngredients()) {
			if (!stockManager.canBeMade(ingredient)) {
				buyStock(ingredient);
			}
			chooseIngredient(ingredient);
			robot.delay(CLICK_DELAY);
		}
		click(GUIInterface.ROLL);
		robot.delay(1000);
	}

	public void startPlaying() throws IOException, InterruptedException {
		int i = 0;
		while (i++ < 100) {
			LOGGER.info("Playing, current stock : " + stockManager.getInventory());
			handleOrders();
			cleanUpPlates();
			Thread.sleep(100);
		}
	}

	public void cleanUpPlates() {
		for (Coordinate platePosition : GUIInterface.PLATES) {
			click(platePosition);
		}
	}

	public void handleOrders() {
		for (int i = 0; i < 6; i++) {
			Rectangle rec = new Rectangle(OFFSET_X + i * CLIENT_SPACING + 35, OFFSET_Y + 50, 40, 35);
			BufferedImage img = robot.createScreenCapture(rec);

			if (!onGoingOrders[i]) {
				Dish orderdedDish = searchPerfectMatch(img);
				if (orderdedDish != null) {
					make(orderdedDish);
					made(i);
				}
			} else if (searchPerfectMatch(img) == null) {
				served(i);
			}
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

	public boolean made(int i) {
		onGoingOrders[i] = true;
		return onGoingOrders[i];
	}

	public boolean served(int i) {
		onGoingOrders[i] = false;
		return onGoingOrders[i];
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
