package robot;

import java.awt.AWTException;
import java.io.IOException;
import java.net.URISyntaxException;


public class Main {
	public static void main(String[] arg) throws IOException, URISyntaxException, AWTException{
		FileUtils.openSuchi();
		Player player = new Player();
		player.skipInto();
		player.furstLevel();
		
	}
}
