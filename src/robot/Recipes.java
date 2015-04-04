package robot;

public class Recipes {
	public static final Ingredient ROLL = new Ingredient(200, 380, 0);
	public static final Ingredient RICE = new Ingredient(90, 335, 10);
	public static final Ingredient NORI = new Ingredient(35, 390, 10);
	public static final Ingredient ROE = new Ingredient(90, 390, 20);
	public static final Ingredient SALMON = new Ingredient(30, 335, 30);

	public static final Ingredient[] RECEPI_ONIGIRI = { RICE, RICE, NORI, ROLL };
	public static final Ingredient[] RECEPI_CLIFORNIA_ROLL = { RICE, NORI, ROE, ROLL };
	public static final Ingredient[] RECEPI_GUNKAN_MAKI = { RICE, NORI, ROE, ROE, ROLL };
	public static final Ingredient[] RECEPI_SALMON_ROLL = { RICE, NORI, SALMON, SALMON, ROLL };

	public static final Dish ONIGIRI = new Dish("Sushi", RECEPI_ONIGIRI);
	public static final Dish CALIFORNIA_ROLL = new Dish("California roll", RECEPI_CLIFORNIA_ROLL);
	public static final Dish GUNKAN_MAKI = new Dish("Gunkan Maki", RECEPI_GUNKAN_MAKI);
	public static final Dish SALMON_ROLL = new Dish("Salmon roll", RECEPI_SALMON_ROLL);

}
