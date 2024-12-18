package resources.controllers;

import graphics.collections.dynamic.array.DynamicArrayDisplay;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class QueueController implements Initializable {

    @FXML
    private AnchorPane parent, theme;

    @FXML
    private Button createButton, insertButton, removeButton, updateButton;

    @FXML
    private HBox createField, insertField, removeField;

    @FXML
    private TextField createValues, insertValues, k;

    @FXML
    private void createEmpty() {

    }

    @FXML
    private void create() {

    }

    @FXML
    private void insert() {

    }

    @FXML
    private void remove() {

    }

    @FXML
    private void kxRemove() {

    }

    @FXML
    private void peek() {

    }

    public void disableFields() {
        createField.setVisible(false);
        insertField.setVisible(false);
        removeField.setVisible(false);
        theme.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        disableFields();
        createButton.setOnMouseClicked(event -> {
            disableFields();
            theme.setVisible(true);
            createField.setVisible(true);
        });
        insertButton.setOnMouseClicked(event -> {
            disableFields();
            theme.setVisible(true);
            insertField.setVisible(true);
        });
        removeButton.setOnMouseClicked(event -> {
            disableFields();
            theme.setVisible(true);
            removeField.setVisible(true);
        });
        theme.setOnMouseClicked(event -> {
            disableFields();
        });
    }
}
