package com.example.medicalhealthcare;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ViewResultPage {
    private Stage stage;
    private Scene scene;

    public ViewResultPage(Stage stage) {
        this.stage = stage;
        createViewResultPage();
    }

    private void createViewResultPage() {
        VBox mainLayout = new VBox();
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setSpacing(20);


        ImageView logoImageView = new ImageView(new Image("file:///C:/Users/Hp/OneDrive/Desktop/JAVA Project/MedicalHealthCare/th.jpeg"));
        logoImageView.setFitWidth(200);
        logoImageView.setFitHeight(100);
        logoImageView.setPreserveRatio(true);

        Label enterCnicLabel = new Label("Enter CNIC:");
        enterCnicLabel.setStyle("-fx-font-weight: bold");
        TextField cnicTextField = new TextField();
        Button submitButton = new Button("Submit");
        Button backButton = new Button("Back");

        submitButton.setOnAction(e -> {
            String cnic = cnicTextField.getText();
            displayResults(cnic, mainLayout);
        });

        backButton.setOnAction(e -> {
            DashboardPage dashboardPage = new DashboardPage(stage);
            stage.setScene(dashboardPage.getScene());
        });

        mainLayout.getChildren().addAll(logoImageView, enterCnicLabel, cnicTextField, submitButton, backButton);


        ImageView backgroundImageView = new ImageView(new Image("file:///C:\\Users\\Hp\\Downloads\\pexels-jan-van-der-wolf-11680885-20284823.jpg"));
        backgroundImageView.setPreserveRatio(false);
        backgroundImageView.setFitWidth(800);
        backgroundImageView.setFitHeight(600);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundImageView, mainLayout);

        scene = new Scene(stackPane, 800, 600);
    }

    private void displayResults(String cnic, VBox mainLayout) {
        mainLayout.getChildren().clear();


        ImageView logoImageView = new ImageView(new Image("file:///C:\\Users\\Hp\\OneDrive\\Desktop\\JAVA Project\\MedicalHealthCare\\th.jpeg"));
        logoImageView.setFitWidth(200);
        logoImageView.setFitHeight(100);
        logoImageView.setPreserveRatio(true);

        Label titleLabel = new Label("Result Details");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        GridPane resultGrid = new GridPane();
        resultGrid.setAlignment(Pos.CENTER);
        resultGrid.setHgap(10);
        resultGrid.setVgap(10);

        boolean found = false;
        try (BufferedReader reader = new BufferedReader(new FileReader("results.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 9 && details[2].trim().equals(cnic)) {
                    resultGrid.addRow(1, new Label("Name:"),new Label(details[0].trim()));
                    resultGrid.addRow(1, new Label("Gender:"), new Label(details[1].trim()));
                    resultGrid.addRow(2, new Label("CNIC:"), new Label(details[2].trim()));
                    resultGrid.addRow(3, new Label("Blood Group:"), new Label(details[3].trim()));
                    resultGrid.addRow(4, new Label("Phone Number:"), new Label(details[4].trim()));
                    resultGrid.addRow(5, new Label("Age:"), new Label(details[5].trim()));
                    resultGrid.addRow(6, new Label("Test:"), new Label(details[6].trim()));
                    resultGrid.addRow(7, new Label("Result:"), new Label(details[7].trim()));
                    resultGrid.addRow(8, new Label("Recommendations:"), new Label(details[8].trim()));
                    found = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!found) {
            resultGrid.addRow(0, new Label("No results found for the given CNIC."));
        }

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            DashboardPage dashboardPage = new DashboardPage(stage);
            stage.setScene(dashboardPage.getScene());
        });

        mainLayout.getChildren().addAll(logoImageView, titleLabel, resultGrid, backButton);
    }

    public Scene getScene() {
        return scene;
    }
}
