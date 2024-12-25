package resources.controllers;

import graphics.collections.stack.StackDisplay;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StackController implements Initializable {
    private StackDisplay elements;

    @FXML
    private Label output;

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
        output.setText("");
        this.elements.create(new ArrayList<>());
    }

    @FXML
    private void create() {
        output.setText("");
        if (!createValues.getText().matches("\\s*-?\\d+\\s*(,\\s*-?\\d+\\s*)*")) {
            output.setText("Invalid input! You must entered integers separated by commas.");
            return;
        }
        String[] rawValues = createValues.getText().split(",");
        ArrayList<Integer> intValues = new ArrayList<>();
        for (String rawValue:rawValues) {
            intValues.add(Integer.parseInt(rawValue.trim()));
        }
        this.elements.create(intValues);
        createValues.setText("");
    }

    @FXML
    private void insert() {
        output.setText("");
        if (!StackDisplay.Existed) {
            output.setText("You must create the stack first.");
            return;
        }
        if(!insertValues.getText().matches("\\s*-?\\d+\\s*(,\\s*-?\\d+\\s*)*")) {
            output.setText("Invalid input! You must entered integers separated by commas.");
            return;
        }
        String[] rawValues = insertValues.getText().split(",");
        ArrayList<Integer> intValues = new ArrayList<>();
        for (String rawValue:rawValues) {
            intValues.add(Integer.parseInt(rawValue.trim()));
        }
        this.elements.push(intValues);
        insertValues.setText("");
    }

    @FXML
    private void remove() {
        output.setText("");
        if (!StackDisplay.Existed) {
            output.setText("You must create the stack first.");
            return;
        }
        this.elements.pop(1);
    }

    @FXML
    private void kxRemove() {
        output.setText("");
        if (!StackDisplay.Existed) {
            output.setText("You must create the stack first.");
            return;
        }
        if (!k.getText().matches("\\s*[1-9]\\d*\\s*")) {
            output.setText("Invalid input! You must entered a positive integer.");
            return;
        }
        int count = Integer.parseInt(k.getText().trim());
        this.elements.pop(count);
        k.setText("");
    }

    @FXML
    private void peek() {
        output.setText("");
        if (!StackDisplay.Existed) {
            output.setText("You must create the stack first.");
            return;
        }
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
