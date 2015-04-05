package robot;

import static robot.Ingredient.NORI;
import static robot.Ingredient.RICE;
import static robot.Ingredient.ROE;
import static robot.Ingredient.SALMON;

import java.util.HashMap;
import java.util.Map;

public class StockManager {
	public Map<Ingredient, Integer> inventory = new HashMap<Ingredient, Integer>();

	public StockManager() {
		inventory.put(RICE, 10);
		inventory.put(NORI, 10);
		inventory.put(ROE, 10);
		inventory.put(SALMON, 5);
	}

	public void stockTake(Ingredient ingredient) {
		inventory.put(ingredient, inventory.get(ingredient) - 1);
	}

	public boolean canBeMade(Ingredient ingredient) {
		return inventory.get(ingredient) >= 1;
	}

	public void updateStock(Ingredient ingredient) {
		inventory.put(ingredient, inventory.get(ingredient) + 10);
	}

	public Map<Ingredient, Integer> getInventory() {
		return inventory;
	}
	
	
}
