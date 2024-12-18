package resources.controllers;

import graphics.collections.dynamic.array.DynamicArrayDisplay;
import graphics.collections.queue.QueueDisplay;
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
    private QueueDisplay elements;

    @FXML
    private AnchorPane parent, theme;

    @FXML
    private Button createButton, insertButton, removeButton, peekButton;

    @FXML
    private HBox createField, insertField, removeField, peekField;

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
            this.elements.enqueue(Integer.parseInt(rawValue.trim()));
        }
    }

    @FXML
    private void remove() {
        this.elements.dequeue();
    }

    @FXML
    private void kxRemove() {
        int count = Integer.parseInt(k.getText().trim());
        for(int i = 0; i < count; i++) {
            this.elements.dequeue();
        }
    }

    @FXML
    private void frontPeek() {
        this.elements.peekFront();
    }

    @FXML
    private void backPeek() {
        this.elements.peekBack();
    }

    public void disableFields() {
        createField.setVisible(false);
        insertField.setVisible(false);
        removeField.setVisible(false);
        peekField.setVisible(false);
        theme.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        elements = new QueueDisplay(parent);
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
        peekButton.setOnMouseClicked(event -> {
            disableFields();
            theme.setVisible(true);
            peekField.setVisible(true);
        });
        theme.setOnMouseClicked(event -> {
            disableFields();
        });
    }
}
