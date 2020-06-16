/*
 * author: Nianhuan Chen
 * net ID: nxc180007
 */
package shoppingcartclient;
import bean.ShoppingCartBeanRemote;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.ejb.Remove;

public class Main extends Application {
    
    @javax.ejb.EJB
    private static ShoppingCartBeanRemote shoppingCartBean;
    
    @Override
    public void start(Stage primaryStage) {
        // creating a list of items to be selected       
        ObservableList<String> fruits = FXCollections.observableArrayList( 
         "Apple", "Banana", "Orange", "Peach", "Pear", "Plum", "Strawberry"); 
        ComboBox fruit = new ComboBox(fruits);
        
        final Label fruitLabel = new Label(); 
        fruit.getSelectionModel().selectedItemProperty().addListener( 
                new ChangeListener() { 
                 @Override
                 public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                 { fruitLabel.setText((String)newValue);
                    shoppingCartBean.addFruitItem((String)newValue);
                 } 
            }
        });
        // creating buttons
        Button placeOrder = new Button("Place Order");
        // creating labels
        Label orderList = new Label();
        placeOrder.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
                // displayting the entire order list and clear the cart
                orderList.setText(Arrays.toString(shoppingCartBean.getItemList().toArray()).replaceAll("[\\[\\]]", "")); 
                shoppingCartBean.clearCar();
            }
        });
        
        Button exit = new Button("Exit");
  
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
                // release the ejb bean and exit the application
                shoppingCartBean.remove();               
                Platform.exit();               
            }
        });
        
        // adding all components to the stage
        GridPane root = new GridPane();
        root.setPadding(new Insets(10,10,10,10));
        root.setHgap(10);
        root.setVgap(10);
        root.add(fruit, 0, 0);  
        root.add(placeOrder,0,1);
        root.add(orderList, 0, 2);
        root.add(exit, 2, 2);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Shopping Cart Combo Box");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
