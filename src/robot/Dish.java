package robot;

import java.util.Arrays;
import java.util.List;
import static robot.Ingredient.RICE;
import static robot.Ingredient.NORI;
import static robot.Ingredient.ROE;
import static robot.Ingredient.SALMON;


public enum Dish {
	
	ONIGIRI(RICE, RICE, NORI),
	CALIFORNIA_ROLL(RICE, NORI, ROE),
	GUNKAN_MAKI(RICE, NORI, ROE, ROE),
	SALMON_ROLL(RICE, NORI, SALMON, SALMON);

	private List<Ingredient> ingredients;

	private Dish(Ingredient...ingredients) {
		this.ingredients = Arrays.asList(ingredients);
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	
}
