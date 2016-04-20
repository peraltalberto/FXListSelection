package es.dimeque.fx.listselection;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Test extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        List<String> list = new ArrayList<String>();
        list.add("help");
        list.add("ayuda");
        list.add("aider");
        list.add("hilfe");
        ObservableList<String> observableList = FXCollections.observableList(list);

        FXListSelection<String> listSelection = new FXListSelection<>();
        listSelection.setItems(observableList);
        stage.setScene(new Scene(listSelection));
        stage.setTitle("Select List");
        stage.show();

        listSelection.getSearchText().textProperty().addListener(obs -> {
            String filter = listSelection.getSearchText().getText();
            if (filter == null || filter.length() == 0) {
                listSelection.getFilteredList().setPredicate(s -> true);
            } else {
                listSelection.getFilteredList().setPredicate(s -> s.toUpperCase().contains(filter.toUpperCase()));
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
