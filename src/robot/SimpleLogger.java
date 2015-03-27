package robot;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleLogger<T> {
	
	private static SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("HH:mm:ss.SSS");
	
	private Class<T> clazz;
	
	public SimpleLogger(Class<T> clazz) {
		this.clazz = clazz;
	}


	public void info (String info) {
		String formattedDate = DATE_FORMATTER.format(new Date());
		System.out.println("[" + clazz.getName() + "] " + formattedDate + " : " + info);
	}

}
