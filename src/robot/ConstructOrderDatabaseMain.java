package robot;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

public class ConstructOrderDatabaseMain {
	public static void main(String[] arg) throws IOException, URISyntaxException, AWTException {

		Robot robot = new Robot();
		FileUtils.openSuchi();
		Player bot = new Player();
		bot.skipInto();

		for (int j = 0; j < 10; j++) {
			for (int i = 0; i < 6; i++) {
				Rectangle rec = new Rectangle(GUIInterface.OFFSET_X + i * GUIInterface.CLIENT_SPACING + 35, GUIInterface.OFFSET_Y + 50, 40, 35);
				BufferedImage img = robot.createScreenCapture(rec);
				ImageIO.write(img, "png", new File("tmp/" + j + "_" + i + ".png"));

			}
			robot.delay(1000);
		}

	}
}
