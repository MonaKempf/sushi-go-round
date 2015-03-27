package robot;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Order {
	
	private ArrayList<Dish> clientsOrder;
	private Player bot;
	
	public Order(ArrayList<Dish> L, Player bot){
		clientsOrder= L;
		this.bot = bot;
	}
	
	public void add(Dish d){
		clientsOrder.add(d);
	}
	
	
	
	
// public Order compareOrder(){	 
	 
// }
	
/* public class ClientOrder extends@ Order(){
  
  public void Orders(){
	  
  }
  public Order checkOrder(){
	  
  }
  public void make(){
	  
  }
    
 }
 */
	
/*public class OrderInGoing extends@ Order(){
	public void add(Dish d){
			super.add(d);
		}
	
}
*/
	
/*	public class OrderDone extends@ Order(){
		calculeBenefice();
		cleanDishes();
		public void add(Dish d){
			super.add(d);
		}
	}
*/

  
}
