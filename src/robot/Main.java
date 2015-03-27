package robot;

import java.awt.AWTException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;

import org.omg.CORBA.SystemException;

public class Main {
	public static void main(String[] arg) throws IOException, URISyntaxException, AWTException {

		FileUtils.openSuchi();
		Player bot = new Player();
		bot.skipInto();
		final Ingredient roll = new Ingredient(200, 380, 0);
		final Ingredient rice = new Ingredient(90, 335, 10);
		final Ingredient nori = new Ingredient(35, 390, 10);
		final Ingredient roe = new Ingredient(90, 390, 20);
		final Ingredient salmon = new Ingredient(30, 335, 30);

		final Ingredient[] recepieSushi = { rice, rice, nori, roll };
		final Ingredient[] recepieCaliforniaRoll = { rice, nori, roe, roll };
		final Ingredient[] recepieGunkanMaki = { rice, nori, roe, roe, roll };
		final Ingredient[] recepieSalmonRoll = { rice, nori, salmon, salmon, roll };

		final Dish sushi = new Dish("Sushi", recepieSushi);
		final Dish californiaRoll = new Dish("California roll", recepieCaliforniaRoll);
		final Dish gunkanMaki = new Dish("Gunkan Maki", recepieGunkanMaki);
		final Dish salmonRoll = new Dish("Salmon roll", recepieSalmonRoll);

		bot.make(sushi);
		bot.make(californiaRoll);
		bot.make(gunkanMaki);
		bot.make(salmonRoll);

	}
}
