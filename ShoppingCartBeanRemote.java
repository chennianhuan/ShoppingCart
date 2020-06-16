/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.ArrayList;
import javax.ejb.Remote;
import javax.ejb.Remove;

/**
 *
 * @author Nianhuan Chen
 */
@Remote
public interface ShoppingCartBeanRemote {
    public void addFruitItem(String fruit);
    public void removeFruitItem(String Fruit);
    public ArrayList getItemList();
    public void clearCar();
    public void remove();
         
}
