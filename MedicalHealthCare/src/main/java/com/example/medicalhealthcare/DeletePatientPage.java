package com.example.medicalhealthcare;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DeletePatientPage {
    private Stage stage;
    private Scene scene;
    private TextField cnicField;

    public DeletePatientPage(Stage stage) {
        this.stage = stage;
        createDeletePatientPage();
    }

    private void createDeletePatientPage() {
        VBox mainLayout = new VBox();
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setSpacing(20);

        // Logo at the top
        ImageView logo = new ImageView(new Image("file:///C:\\Users\\Hp\\OneDrive\\Desktop\\JAVA Project\\MedicalHealthCare\\th.jpeg"));
        logo.setFitWidth(100);
        logo.setFitHeight(100);
        mainLayout.getChildren().add(logo);

        // Title and line
        Label titleLabel = new Label("Delete Patient");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.WHITE);

        Line line = new Line(0, 0, 300, 0);
        line.setStrokeWidth(2);
        line.setStroke(Color.WHITE);

        VBox headingBox = new VBox();
        headingBox.setAlignment(Pos.CENTER);
        headingBox.getChildren().addAll(titleLabel, line);
        mainLayout.getChildren().add(headingBox);

        // Form layout
        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setPadding(new Insets(20));

        Label cnicLabel = new Label("CNIC:");
        cnicField = new TextField();
        layout.add(cnicLabel, 0, 0);
        layout.add(cnicField, 1, 0);

        Button deleteButton = new Button("Delete");
        Button backButton = new Button("Back");
        layout.add(deleteButton, 0, 1);
        layout.add(backButton, 1, 1);

        deleteButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this patient?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                deletePatientData(cnicField.getText());
                DashboardPage dashboardPage = new DashboardPage(stage);
                stage.setScene(dashboardPage.getScene());
            }
        });

        backButton.setOnAction(e -> {
            DashboardPage dashboardPage = new DashboardPage(stage);
            stage.setScene(dashboardPage.getScene());
        });

        mainLayout.getChildren().add(layout);

        // Background image
        ImageView backgroundImageView = new ImageView(new Image("file:///C:\\Users\\Hp\\Downloads\\pexels-shvetsa-3786158.jpg"));
        backgroundImageView.setPreserveRatio(false);
        backgroundImageView.setFitWidth(800);
        backgroundImageView.setFitHeight(600);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundImageView, mainLayout);
        StackPane.setAlignment(mainLayout, Pos.TOP_CENTER);

        scene = new Scene(stackPane, 800, 600);
    }

    private void deletePatientData(String cnic) {
        try (BufferedReader reader = new BufferedReader(new FileReader("patients.txt"))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length >= 6 && !details[2].equals(cnic)) {
                    lines.add(line);
                }
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("patients.txt"))) {
                for (String l : lines) {
                    writer.write(l);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Scene getScene() {
        return scene;
    }
}
