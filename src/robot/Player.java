package robot;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class Player {

	private Robot robot;
	private static final int INITIAL_WIDTH = 640;
	private static final int INITIAL_HEIGHT = 480;
	private static final int CURRENT_WIDTH = 640;
	private static final int CURRENT_HEIGHT = 480;
	private static final int OFFSET_X = 156;
	private static final int OFFSET_Y = 213;

	public Player() throws AWTException {
		robot = new Robot();

	}

	public void skipInto() {
		robot.delay(18000);
		click(310, 200); //PLAY
		robot.delay(1000);
		click(320, 390); //CONTINUE1
		robot.delay(1000);
		click(320, 400); //CONTINUE2
		robot.delay(1000);
		click(585, 450); //SKIP
		robot.delay(1000);
		click(320, 375); //CONTINUE3
		
	}
	public void mouseMove(int x, int y){
		robot.mouseMove(OFFSET_X+x,OFFSET_Y+y);
	}
	
	public void click(int x, int y){
		mouseMove(x,y);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
}
