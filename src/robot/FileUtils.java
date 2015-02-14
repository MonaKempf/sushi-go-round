package robot;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class FileUtils {
	public static void openSuchi() throws IOException, URISyntaxException {
		Desktop desktop = Desktop.getDesktop();
	    desktop.browse(new
	    URI("http://www.miniclip.com/games/sushi-go-round/en/"));
		/*File test = new File("Sushi.swf");
		System.out.println(test.getAbsolutePath());
		desktop.open(test);
		*/
	}

}
