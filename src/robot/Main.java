package robot;

import java.awt.AWTException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;

import org.omg.CORBA.SystemException;

public class Main {
	public static void main(String[] arg) throws IOException, URISyntaxException, AWTException, InterruptedException {

		FileUtils.openSuchi();
		Player bot = new Player();
		bot.skipInto();
		
		
		bot.monitorOrders();

	}
}
