/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.dimeque.fx.listselection;

import java.io.IOException;
import java.net.URL;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cursos
 */
public class FXListSelection<T>  extends VBox implements Initializable{

    @FXML
    private ListView<T> destinationList;
    @FXML
    private ListView<T> originList;

    @FXML
    private TextField searchText;

    @FXML
    private ResourceBundle resourceBundle ;
    
    private ObservableList<T> lista;

    private FilteredList<T> filteredData;

    public FXListSelection() {
        try {
            resourceBundle = new PropertyResourceBundle(getClass().getResourceAsStream("/ES_es.properties"));            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXListSelection.fxml"),resourceBundle);
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            
                fxmlLoader.load();
            
        } catch (IOException ex) {
            Logger.getLogger(FXListSelection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

   
   

    public void setItems(ObservableList<T> lista) {
        this.lista = FXCollections.observableArrayList(lista);
        filteredData = new FilteredList<>(this.lista, s -> true);
        this.originList.setItems(filteredData);
        this.originList.refresh();

    }

    public ObservableList<T> getSelectedItems() {
        return this.destinationList.getItems();
    }

    @FXML
    void remove(ActionEvent event) {

        for (T item : this.destinationList.getSelectionModel().getSelectedItems()) {
            this.destinationList.getItems().remove(item);
            lista.add(item);
        }

    }

    @FXML
    void addAll(ActionEvent event) {
        ObservableList<T> temp = FXCollections.observableArrayList(this.originList.getItems());
        for (T item : temp) {
            System.out.println(item.toString());
            this.destinationList.getItems().add(item);
            lista.remove(item);
        }

    }

    @FXML
    void add(ActionEvent event) {

        for (T item : this.originList.getSelectionModel().getSelectedItems()) {
            this.destinationList.getItems().add(item);
            lista.remove(item);
            // this.originList.getItems().remove(item);
        }
    }

    @FXML
    void removeAll(ActionEvent event) {
        ObservableList<T> temp = FXCollections.observableArrayList(this.destinationList.getItems());

        for (T item : temp) {
            this.destinationList.getItems().remove(item);
            lista.add(item);
        }

    }

    public TextField getSearchText() {
        return this.searchText;
    }

    public FilteredList<T> getFilteredList() {
        return this.filteredData;
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        
    }

   

    
}
