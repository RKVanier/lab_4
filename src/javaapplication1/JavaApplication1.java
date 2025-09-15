/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class JavaApplication1 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(20));
        root.setCenter(gridPane);

        gridPane.setAlignment(Pos.CENTER);

        Label fNameLabel = new Label("First Name: ");
        TextField fNameField = new TextField();
        gridPane.add(fNameLabel, 0, 0);
        gridPane.add(fNameField, 1, 0);

        Label lNameLabel = new Label("Last Name: ");
        TextField lNameField = new TextField();
        gridPane.add(lNameLabel, 0, 1);
        gridPane.add(lNameField, 1, 1);

        Label emailLabel = new Label("Email: ");
        TextField emailField = new TextField();
        gridPane.add(emailLabel, 0, 2);
        gridPane.add(emailField, 1, 2);

        Label passLabel = new Label("Password: ");
        PasswordField passField = new PasswordField();
        gridPane.add(passLabel, 0, 3);
        gridPane.add(passField, 1, 3);

        Button registerBtn = new Button("Register");
        registerBtn.setDisable(true);

        Button clearBtn = new Button("Clear");
        gridPane.add(registerBtn, 0, 4);
        gridPane.add(clearBtn, 1, 4);

        Label message = new Label();
        gridPane.add(message, 0, 5, 2, 1);

        fNameField.setOnKeyTyped(e -> updateButtonState(registerBtn, fNameField, lNameField, emailField, passField));
        lNameField.setOnKeyTyped(e -> updateButtonState(registerBtn, fNameField, lNameField, emailField, passField));
        emailField.setOnKeyTyped(e -> updateButtonState(registerBtn, fNameField, lNameField, emailField, passField));
        passField.setOnKeyTyped(e -> updateButtonState(registerBtn, fNameField, lNameField, emailField, passField));

        clearBtn.setOnAction(e -> {
            fNameField.clear();
            lNameField.clear();
            emailField.clear();
            passField.clear();
            registerBtn.setDisable(true);
            message.setText("");
        });

        registerBtn.setOnAction(e -> {
            if (areFieldsValid(fNameField, lNameField, emailField, passField)) {
                if (!emailField.getText().contains("@vaniercollege.qc.ca")) {
                    message.setText("The email entered is not a valid one. Try again.");
                } else if (!isPasswordValid(passField.getText())) {
                    message.setText("The password entered is not a valid one. Try again.");
                } else {
                    message.setText("Welcome to the page!");
                }
            } else {
                message.setText("One or multiple fields are empty. Try again.");
            }
        });

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Registration Form");
        stage.show();
    }

    private void updateButtonState(Button registerBtn, TextField fNameField, TextField lNameField, TextField emailField, PasswordField passField) {
        boolean allFieldsFilled = !fNameField.getText().isEmpty() && !lNameField.getText().isEmpty()
                && !emailField.getText().isEmpty() && !passField.getText().isEmpty();
        registerBtn.setDisable(!allFieldsFilled);
    }

    private boolean isPasswordValid(String password) {
        boolean hasLetter = false;
        boolean hasDigit = false;

        for (int i = 0; i < password.length(); i++) {
            if (Character.isLetter(password.charAt(i))) {
                hasLetter = true;
            }
            if (Character.isDigit(password.charAt(i))) {
                hasDigit = true;
            }
        }
        return hasLetter && hasDigit;
    }

    private boolean areFieldsValid(TextField fNameField, TextField lNameField, TextField emailField, PasswordField passField) {
        return !fNameField.getText().isEmpty() && !lNameField.getText().isEmpty()
                && !emailField.getText().isEmpty() && !passField.getText().isEmpty();
    }
}
