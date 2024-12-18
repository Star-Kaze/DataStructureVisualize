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

public class DynamicArrayController implements Initializable {
    private DynamicArrayDisplay elements;

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
        if (DynamicArrayDisplay.Existed) {
            parent.getChildren().removeIf(node -> node instanceof StackPane);
            this.elements = new DynamicArrayDisplay(parent);
        }
        this.elements.create(new ArrayList<>());
    }

    @FXML
    private void create() {
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
    }

    @FXML
    private void append() {
        if (!DynamicArrayDisplay.Existed) {
            return;
        }
        int value = Integer.parseInt(insertValue.getText().trim());
        this.elements.append(value);
    }

    @FXML
    private void insert() {
        if (!DynamicArrayDisplay.Existed) {
            return;
        }
        int index = Integer.parseInt(insertIndex.getText().trim());
        int value = Integer.parseInt(insertValue.getText().trim());
        this.elements.insert(index, value);
    }

    @FXML
    private void remove() {
        if (!DynamicArrayDisplay.Existed) {
            return;
        }
        int index = Integer.parseInt(removeIndex.getText().trim());
        this.elements.remove(index);
    }

    @FXML
    private void update() {
        int index = Integer.parseInt(updateIndex.getText().trim());
        int value = Integer.parseInt(updateValue.getText().trim());
        this.elements.update(index, value);
    }

    @FXML
    private void select() {
        int index = Integer.parseInt(selectIndex.getText().trim());
        this.elements.select(index);
    }

    @FXML
    private void min() {
        this.elements.min();
    }

    @FXML
    private void max() {
        this.elements.max();
    }

    @FXML
    private void sortAscending() {
        this.elements.sort(true);
    }

    @FXML
    private void sortDescending() {
        this.elements.sort(false);
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
