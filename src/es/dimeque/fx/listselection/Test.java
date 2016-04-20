

package es.dimeque.fx.listselection;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Test extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXListSelection customControl = new FXListSelection();
       
        
        stage.setScene(new Scene(customControl));
        stage.setTitle("Custom Control");
       
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
