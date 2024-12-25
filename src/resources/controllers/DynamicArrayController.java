package resources.controllers;

import graphics.collections.dynamic.array.DynamicArrayDisplay;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DynamicArrayController implements Initializable {
    private DynamicArrayDisplay elements;

    @FXML
    private Label output;

    @FXML
    private AnchorPane parent, theme;

    @FXML
    private Button createButton, insertButton, removeButton, updateButton, selectButton, sortButton;

    @FXML
    private HBox createField, insertField, removeField, updateField, selectField, sortField;

    @FXML
    private TextField createValues, insertValue, insertIndex, removeIndex, updateValue, updateIndex, selectIndex;

    @FXML
    private void createEmpty() {
        output.setText("");
        if (DynamicArrayDisplay.Existed) {
            parent.getChildren().removeIf(node -> node instanceof StackPane);
            this.elements = new DynamicArrayDisplay(parent);
        }
        this.elements.create(new ArrayList<>());
    }

    @FXML
    private void create() {
        output.setText("");
        if (!createValues.getText().matches("\\s*-?\\d+\\s*(,\\s*-?\\d+\\s*)*")) {
            output.setText("Invalid input! You must entered integers separated by commas.");
            return;
        }
        if (DynamicArrayDisplay.Existed) {
            parent.getChildren().removeIf(node -> node instanceof StackPane);
            this.elements = new DynamicArrayDisplay(parent);
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
    private void append() {
        output.setText("");
        if (!DynamicArrayDisplay.Existed) {
            output.setText("You must create the list first.");
            return;
        }
        if (!insertValue.getText().matches("\\s*-?\\d+\\s*")) {
            output.setText("Invalid input! You must entered an integer value.");
            return;
        }
        int value = Integer.parseInt(insertValue.getText().trim());
        this.elements.append(value);
        insertValue.setText("");
    }

    @FXML
    private void insert() {
        output.setText("");
        if (!DynamicArrayDisplay.Existed) {
            output.setText("You must create the list first.");
            return;
        }
        if (!insertValue.getText().matches("\\s*-?\\d+\\s*")) {
            output.setText("Invalid input! You must entered an integer value.");
            return;
        }
        if (!insertIndex.getText().matches("\\s*-?\\d+\\s*")) {
            output.setText("Invalid input! You must entered an integer index.");
            return;
        }
        int index = Integer.parseInt(insertIndex.getText().trim());
        int value = Integer.parseInt(insertValue.getText().trim());
        if (index < -this.elements.getSize() || index > this.elements.getSize()) {
            output.setText("Index " + index + " is out of bounds.");
            return;
        }
        this.elements.insert(index, value);
        insertIndex.setText("");
        insertValue.setText("");
    }

    @FXML
    private void remove() {
        output.setText("");
        if (!DynamicArrayDisplay.Existed) {
            output.setText("You must create the list first.");
            return;
        }
        if (!removeIndex.getText().matches("\\s*-?\\d+\\s*")) {
            output.setText("Invalid input! You must entered an integer index.");
            return;
        }
        int index = Integer.parseInt(removeIndex.getText().trim());
        if (index < -this.elements.getSize() || index >= this.elements.getSize()) {
            output.setText("Index " + index + " is out of bounds.");
            return;
        }
        this.elements.remove(index);
        removeIndex.setText("");
    }

    @FXML
    private void update() {
        output.setText("");
        if (!DynamicArrayDisplay.Existed) {
            output.setText("You must create the list first.");
            return;
        }
        if (!updateValue.getText().matches("\\s*-?\\d+\\s*")) {
            output.setText("Invalid input! You must entered an integer value.");
            return;
        }
        if (!updateIndex.getText().matches("\\s*-?\\d+\\s*")) {
            output.setText("Invalid input! You must entered an integer index.");
            return;
        }
        int index = Integer.parseInt(updateIndex.getText().trim());
        int value = Integer.parseInt(updateValue.getText().trim());
        if (index < -this.elements.getSize() || index >= this.elements.getSize()) {
            output.setText("Index " + index + " is out of bounds.");
            return;
        }
        this.elements.update(index, value);
        updateIndex.setText("");
        updateValue.setText("");
    }

    @FXML
    private void select() {
        output.setText("");
        if (!DynamicArrayDisplay.Existed) {
            output.setText("You must create the list first.");
            return;
        }
        if (!selectIndex.getText().matches("\\s*-?\\d+\\s*")) {
            output.setText("Invalid input! You must entered an integer index.");
            return;
        }
        int index = Integer.parseInt(selectIndex.getText().trim());
        if (index < -this.elements.getSize() || index >= this.elements.getSize()) {
            output.setText("Index " + index + " is out of bounds.");
            return;
        }
        this.elements.select(index);
        selectIndex.setText("");
    }

    @FXML
    private void min() {
        output.setText("");
        if (!DynamicArrayDisplay.Existed) {
            output.setText("You must create the list first.");
            return;
        }
        this.elements.min();
    }

    @FXML
    private void max() {
        output.setText("");
        if (!DynamicArrayDisplay.Existed) {
            output.setText("You must create the list first.");
            return;
        }
        this.elements.max();
    }

    @FXML
    private void sortAscending() {
        output.setText("");
        if (!DynamicArrayDisplay.Existed) {
            output.setText("You must create the list first.");
            return;
        }
        this.elements.sort(false);
    }

    @FXML
    private void sortDescending() {
        output.setText("");
        if (!DynamicArrayDisplay.Existed) {
            output.setText("You must create the list first.");
            return;
        }
        this.elements.sort(true);
    }

    public void disableFields() {
        createField.setVisible(false);
        insertField.setVisible(false);
        removeField.setVisible(false);
        updateField.setVisible(false);
        selectField.setVisible(false);
        sortField.setVisible(false);
        theme.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.elements = new DynamicArrayDisplay(parent);
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
        updateButton.setOnMouseClicked(event -> {
            disableFields();
            theme.setVisible(true);
            updateField.setVisible(true);
        });
        selectButton.setOnMouseClicked(event -> {
            disableFields();
            theme.setVisible(true);
            selectField.setVisible(true);
        });
        sortButton.setOnMouseClicked(event -> {
            disableFields();
            theme.setVisible(true);
            sortField.setVisible(true);
        });
        theme.setOnMouseClicked(event -> {
            disableFields();
        });
    }
}
