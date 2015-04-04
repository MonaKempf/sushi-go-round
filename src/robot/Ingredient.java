package robot;

import java.io.IOException;

public class Ingredient {
	private int x;
	private int y;
	//private int nbRise;
	private int price;
	
	
	public Ingredient(int coorX, int coorY, int price){
		this.x = coorX;
		this.y = coorY;
		this.price = price;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	
	
	/*public void buyIngredient(){
		bot.click(this.x, this.y);
		this.price = this.price - 10;
	}
	*/
	
}
