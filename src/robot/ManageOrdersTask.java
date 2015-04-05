package robot;

import static robot.Dish.CALIFORNIA_ROLL;
import static robot.Dish.GUNKAN_MAKI;
import static robot.Dish.ONIGIRI;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.TimerTask;

import javax.imageio.ImageIO;

public class ManageOrdersTask extends TimerTask {

	public enum SeatingState {
		IDLE, TAKING_ORDER
	}

	private static SimpleLogger<ManageOrdersTask> LOGGER = new SimpleLogger<ManageOrdersTask>(ManageOrdersTask.class);
	private Robot robot;
	private Queue<Dish> orders;
	private SeatingState[] previousSeatingState = new SeatingState[6];

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

	public ManageOrdersTask(Queue<Dish> orders) throws AWTException {
		this.robot = new Robot();
		this.orders = orders;
		Arrays.fill(previousSeatingState, SeatingState.IDLE);
	}

	public Dish searchPerfectMatch(BufferedImage img) {
		for (Entry<BufferedImage, Dish> entry : IMAGE_DISH_MAPPING.entrySet()) {
			if (compareImage(img, entry.getKey())) {
				return entry.getValue();
			}
		}

		return null;
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

	@Override
	public void run() {
		List<Dish> newOrders = new ArrayList<Dish>();
		for (int i = 0; i < 6; i++) {
			Rectangle rec = new Rectangle(GUIInterface.OFFSET_X + i * GUIInterface.CLIENT_SPACING + 35, GUIInterface.OFFSET_Y + 50, 40, 35);
			BufferedImage img = robot.createScreenCapture(rec);

			Dish orderedDish = searchPerfectMatch(img);
			if (orderedDish != null && previousSeatingState[i] == SeatingState.IDLE) {
				orders.offer(orderedDish);
				newOrders.add(orderedDish);
			}

			if (orderedDish != null) {
				previousSeatingState[i] = SeatingState.TAKING_ORDER;
			} else {
				previousSeatingState[i] = SeatingState.IDLE;
			}
		}
		LOGGER.info("Checking for orders : " + newOrders);
	}

}
