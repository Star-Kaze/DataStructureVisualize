package resources.controllers;

import graphics.collections.stack.StackDisplay;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StackController implements Initializable {
    private StackDisplay elements;

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
        this.elements.create(new ArrayList<>());
    }

    @FXML
    private void create() {
        String[] rawValues = createValues.getText().split(",");
        ArrayList<Integer> intValues = new ArrayList<>();
        for (String rawValue:rawValues) {
            intValues.add(Integer.parseInt(rawValue.trim()));
        }
        this.elements.create(intValues);
    }

    @FXML
    private void insert() {
        String[] rawValues = insertValues.getText().split(",");
        for (String rawValue:rawValues) {
            this.elements.push(Integer.parseInt(rawValue.trim()));
        }
    }

    @FXML
    private void remove() {
        this.elements.pop();
    }

    @FXML
    private void kxRemove() {
        int count = Integer.parseInt(k.getText().trim());
        for(int i = 0; i < count; i++) {
            this.elements.pop();
        }
    }

    @FXML
    private void peek() {
        this.elements.peek();
    }

    public void disableFields() {
        createField.setVisible(false);
        insertField.setVisible(false);
        removeField.setVisible(false);
        theme.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.elements = new StackDisplay(parent);
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
