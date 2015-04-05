package robot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GUIInterface {
	public static final Coordinate PLAY = new Coordinate(310, 200);
	public static final Coordinate CONTINUE1 = new Coordinate(320, 390);
	public static final Coordinate CONTINUE2 = new Coordinate(320, 400);
	public static final Coordinate CONTINUE3 = new Coordinate(320, 375);
	public static final Coordinate SKIP = new Coordinate(585, 450);

	public static final Coordinate ROLL = new Coordinate(200, 380);
	public static final Coordinate CALL = new Coordinate(555, 380);
	public static final Coordinate TOPPING = new Coordinate(540, 272);
	public static final Coordinate FREE = new Coordinate(490, 294);

	public static final List<Coordinate> PLATES = new ArrayList<Coordinate>();
	static {
		for (int i = 0; i < 6; i++) {
			PLATES.add(new Coordinate(i * Player.CLIENT_SPACING + 80, 203));
		}
	}

	public static final Map<Ingredient, Coordinate> INGREDIENS_COORDINATES = new HashMap<Ingredient, Coordinate>();
	static {
		INGREDIENS_COORDINATES.put(Ingredient.RICE, new Coordinate(90, 335));
		INGREDIENS_COORDINATES.put(Ingredient.NORI, new Coordinate(35, 390));
		INGREDIENS_COORDINATES.put(Ingredient.ROE, new Coordinate(90, 390));
		INGREDIENS_COORDINATES.put(Ingredient.SALMON, new Coordinate(30, 335));
	}

	public static final Map<Ingredient, List<Coordinate>> BUY_INGREDIENS_COORDINATES = new HashMap<Ingredient, List<Coordinate>>();
	static {
		BUY_INGREDIENS_COORDINATES.put(Ingredient.RICE, Arrays.asList(CALL, new Coordinate(550, 292), new Coordinate(550, 292), FREE));
		BUY_INGREDIENS_COORDINATES.put(Ingredient.NORI, Arrays.asList(CALL, TOPPING, new Coordinate(500, 280), FREE));
		BUY_INGREDIENS_COORDINATES.put(Ingredient.ROE, Arrays.asList(CALL, TOPPING, new Coordinate(580, 280), FREE));
		BUY_INGREDIENS_COORDINATES.put(Ingredient.SALMON, Arrays.asList(CALL, TOPPING, new Coordinate(500, 333), FREE));
	}
}
