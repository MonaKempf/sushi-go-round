package robot;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.util.Queue;
import java.util.Timer;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Player {

	private static SimpleLogger<Player> LOGGER = new SimpleLogger<Player>(Player.class);
	private static final int CLICK_DELAY = 0;

	private Timer timer;
	private Robot robot;
	private Queue<Dish> orders;
	private StockManager stockManager;
	public boolean[] onGoingOrders = { false, false, false, false, false, false };

	public Player() throws AWTException {
		timer = new Timer();
		robot = new Robot();
		stockManager = new StockManager();
		orders = new ConcurrentLinkedQueue<Dish>();
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
		robot.mouseMove(GUIInterface.OFFSET_X + coordinate.getX(), GUIInterface.OFFSET_Y + coordinate.getY());
	}

	public void click(Coordinate coordinate) {
		mouseMove(coordinate);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.delay(100);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		// (bug windows) reset mouse for next click
		robot.mouseMove(GUIInterface.OFFSET_X, GUIInterface.OFFSET_Y);
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

	public void startPlaying() throws IOException, InterruptedException, AWTException {
		timer.scheduleAtFixedRate(new ManageOrdersTask(orders), 0, 50);
		int i = 0;
		while (i++ < 100) {
			LOGGER.info("Playing, current stock : " + stockManager.getInventory());
			prepareOrders(orders);
			cleanUpPlates();
			Thread.sleep(100);
		}

		timer.cancel();
	}

	public void cleanUpPlates() {
		for (Coordinate platePosition : GUIInterface.PLATES) {
			click(platePosition);
		}
	}

	public void prepareOrders(Queue<Dish> orders) {
		Dish order = orders.poll();
		while (order != null) {
			make(order);
			order = orders.poll();
		}
	}

}
