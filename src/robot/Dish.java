package robot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dish {
	private static SimpleLogger<Dish> LOGGER = new SimpleLogger<Dish>(Dish.class);

	private String name;
	private List<Ingredient> ingredients;

	public Dish(String name, Ingredient[] ingredients) {
		this.name = name;
		this.ingredients = Arrays.asList(ingredients);
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public String getName() {
		return name;
	}


}
