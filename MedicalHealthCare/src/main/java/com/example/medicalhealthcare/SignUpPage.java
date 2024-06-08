package com.example.medicalhealthcare;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SignUpPage {
    private Stage stage;
    private Scene scene;

    public SignUpPage(Stage stage) {
        this.stage = stage;
        createSignUpPage();
    }

    private void createSignUpPage() {
        VBox mainLayout = new VBox();
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setSpacing(20);


        ImageView logoImageView = new ImageView(new Image("file:///C:\\Users\\Hp\\OneDrive\\Desktop\\JAVA Project\\MedicalHealthCare\\th.jpeg"));
        logoImageView.setPreserveRatio(true);
        logoImageView.setFitHeight(100);
        mainLayout.getChildren().add(logoImageView);

        Text text = new Text("Sign Up for Medical Health Care Laboratory");
        text.setTextAlignment(TextAlignment.CENTER);
        text.setFill(Color.BLUEVIOLET);
        text.setFont(Font.font("Times New Roman", FontWeight.BOLD, 40));
        mainLayout.getChildren().add(text);

        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(20);
        layout.setVgap(20);

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        layout.add(usernameLabel, 0, 0);
        layout.add(usernameField, 1, 0);

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        layout.add(passwordLabel, 0, 1);
        layout.add(passwordField, 1, 1);

        Label confirmPasswordLabel = new Label("Confirm Password:");
        PasswordField confirmPasswordField = new PasswordField();
        layout.add(confirmPasswordLabel, 0, 2);
        layout.add(confirmPasswordField, 1, 2);

        Button signUpButton = new Button("Sign Up");
        layout.add(signUpButton, 1, 3);

        Button backButton = new Button("Back to Login");
        layout.add(backButton, 1, 4);

        mainLayout.getChildren().add(layout);

        signUpButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();
            if (password.equals(confirmPassword)) {
                saveCredentials(username, password);
                LoginPage loginPage = new LoginPage(stage);
                stage.setScene(loginPage.getScene());
            } else {
                System.out.println("Passwords do not match!");
            }
        });

        backButton.setOnAction(e -> {
            LoginPage loginPage = new LoginPage(stage);
            stage.setScene(loginPage.getScene());
        });

        ImageView backgroundImageView = new ImageView(new Image("file:///C:\\Users\\Hp\\Downloads\\pexels-shvetsa-3786158.jpg"));
        backgroundImageView.setPreserveRatio(false);
        backgroundImageView.setFitWidth(800);
        backgroundImageView.setFitHeight(550);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundImageView, mainLayout);
        StackPane.setAlignment(mainLayout, Pos.TOP_CENTER);

        scene = new Scene(stackPane, 800, 550);
        text.styleProperty().bind(mainLayout.widthProperty().divide(25).asString("-fx-font-size: %.2f;"));
    }

    private void saveCredentials(String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
            writer.write(username + "," + password);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Scene getScene() {
        return scene;
    }
}
