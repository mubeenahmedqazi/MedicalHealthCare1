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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginPage {
    private Stage stage;
    private Scene scene;

    public LoginPage(Stage stage) {
        this.stage = stage;
        createLoginPage();
    }

    private void createLoginPage() {

        ImageView logoImageView = new ImageView(new Image("file:///C:\\Users\\Hp\\OneDrive\\Desktop\\JAVA Project\\MedicalHealthCare\\th.jpeg"));
        logoImageView.setFitWidth(200);
        logoImageView.setFitHeight(100);
        logoImageView.setPreserveRatio(true);

        Image backgroundImage = new Image("file:///C:\\Users\\Hp\\Downloads\\pexels-karolina-grabowska-6627705.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(1500);
        backgroundImageView.setFitHeight(1020);


        VBox mainLayout = new VBox();
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setSpacing(20);
        Text welcomeText = new Text("Welcome to Medical Health Care Laboratory");
        welcomeText.setTextAlignment(TextAlignment.CENTER);
        welcomeText.setFill(Color.BLUEVIOLET);
        welcomeText.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));

        Label line = new Label("_________________________________________________________________________________________________________________________________________________________________");
        line.setAlignment(Pos.CENTER);
        line.setFont(Font.font("Arial", FontWeight.BOLD, 15)); // Make it bold


        mainLayout.getChildren().addAll(logoImageView,welcomeText, line);


        Pane spacer = new Pane();
        spacer.setMinHeight(50);


        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(20);
        layout.setVgap(20);

        Label nameLabel = new Label("Enter your username:");
        TextField nameField = new TextField();
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        nameField.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        layout.add(nameLabel, 0, 0);
        layout.add(nameField, 1, 0);

        Label passwordLabel = new Label("Enter your password:");
        PasswordField passwordField = new PasswordField();
        passwordLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        passwordField.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        layout.add(passwordLabel, 0, 1);
        layout.add(passwordField, 1, 1);

        Button loginButton = new Button("Login");
        loginButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        layout.add(loginButton, 1, 2);

        Label signupLabel = new Label("Don't have an account?");
        Button signupButton = new Button("Sign Up");
        signupLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        signupButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        layout.add(signupLabel, 0, 3);
        layout.add(signupButton, 1, 3);

        mainLayout.getChildren().addAll(spacer, layout);


        loginButton.setOnAction(e -> {
            String username = nameField.getText();
            String password = passwordField.getText();
            if (validateCredentials(username, password)) {
                DashboardPage dashboardPage = new DashboardPage(stage);
                stage.setScene(dashboardPage.getScene());


            } else {
                System.out.println("Invalid credentials!");
            }
        });


        signupButton.setOnAction(e -> {
            SignUpPage signUpPage = new SignUpPage(stage);
            stage.setScene(signUpPage.getScene());

        });


        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundImageView, mainLayout);


        scene = new Scene(stackPane, 800, 550);
        welcomeText.styleProperty().bind(mainLayout.widthProperty().divide(25).asString("-fx-font-size: %.2f;"));
    }

    private boolean validateCredentials(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials[0].equals(username) && credentials[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Scene getScene() {
        return scene;
    }
}
