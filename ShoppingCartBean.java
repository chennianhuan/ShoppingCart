package bean;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 *
 * @author Nianhuan Chen
 */
@Stateful
public class ShoppingCartBean implements ShoppingCartBeanRemote {
    public ArrayList cartItems;
   
    
    public ShoppingCartBean(){
        cartItems = new ArrayList<String>();
    }
    
    @Override
    public void addFruitItem(String fruit) {
        cartItems.add(fruit);
    }
    
    @Override
    public void removeFruitItem(String Fruit) {
        cartItems.remove(Fruit);
    }
    
    @Override
    public ArrayList getItemList(){
        return cartItems;
    }
    
    public void clearCar(){
        cartItems.clear();
    }
    @Remove
    @Override
    public void remove(){
        cartItems = null;         
    }
    
}
