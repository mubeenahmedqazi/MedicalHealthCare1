package com.example.medicalhealthcare;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AddResultsPage {
    private Stage stage;
    private Scene scene;

    public AddResultsPage(Stage stage) {
        this.stage = stage;
        createAddResultsPage();
    }

    private void createAddResultsPage() {
        VBox mainLayout = new VBox();
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setSpacing(20);

        ImageView logoImageView = new ImageView(new Image("file:///C:/Users/Hp/OneDrive/Desktop/JAVA Project/MedicalHealthCare/th.jpeg"));
        logoImageView.setFitWidth(200);
        logoImageView.setFitHeight(100);
        logoImageView.setPreserveRatio(true);

        Label titleLabel = new Label("Add Results");
        titleLabel.setFont(Font.font("Arial", 30));
        titleLabel.setTextFill(Color.BLUE);

        Line line = new Line(0, 0, 800, 0);
        line.setStroke(Color.BLUE);
        line.setStrokeWidth(2);
        line.setTranslateY(10);

        Label cnicLabel = new Label("Enter CNIC:");
        TextField cnicField = new TextField();
        Button searchButton = new Button("Search");

        VBox searchLayout = new VBox(10, cnicLabel, cnicField, searchButton);
        searchLayout.setAlignment(Pos.CENTER);

        searchButton.setOnAction(e -> {
            String cnic = cnicField.getText();
            String patientInfo = getPatientInfo(cnic);
            if (patientInfo != null) {
                openResultFormPage(patientInfo);
            } else {
                showAlert("Patient not found!");
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> stage.setScene(new DashboardPage(stage).getScene()));

        mainLayout.getChildren().addAll(logoImageView, titleLabel, line, searchLayout, backButton);

        ImageView backgroundImageView = new ImageView(new Image("file:///C:/Users/Hp/Downloads/pexels-karolina-grabowska-4230623.jpg"));
        backgroundImageView.setPreserveRatio(false);
        backgroundImageView.setFitWidth(800);
        backgroundImageView.setFitHeight(600);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundImageView, mainLayout);
        StackPane.setAlignment(mainLayout, Pos.TOP_CENTER);

        scene = new Scene(stackPane, 800, 600);
    }

    private void openResultFormPage(String patientInfo) {
        VBox mainLayout = new VBox();
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setSpacing(20);

        Label titleLabel = new Label("Patient Information and Add Results");
        titleLabel.setFont(Font.font("Arial", 30));
        titleLabel.setTextFill(Color.BLUE);

        Line line = new Line(0, 0, 800, 0);
        line.setStroke(Color.BLUE);
        line.setStrokeWidth(2);
        line.setTranslateY(10);

        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(10);
        layout.setVgap(10);

        String[] patientDetails = patientInfo.split(",");

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField(patientDetails[0]);
        nameField.setEditable(false);
        layout.add(nameLabel, 0, 0);
        layout.add(nameField, 1, 0);

        Label genderLabel = new Label("Gender:");
        TextField genderField = new TextField(patientDetails[1]);
        genderField.setEditable(false);
        layout.add(genderLabel, 0, 1);
        layout.add(genderField, 1, 1);

        Label cnicLabel = new Label("CNIC:");
        TextField cnicField = new TextField(patientDetails[2]);
        cnicField.setEditable(false);
        layout.add(cnicLabel, 0, 2);
        layout.add(cnicField, 1, 2);

        Label bloodGroupLabel = new Label("Blood Group:");
        TextField bloodGroupField = new TextField(patientDetails[3]);
        bloodGroupField.setEditable(false);
        layout.add(bloodGroupLabel, 0, 3);
        layout.add(bloodGroupField, 1, 3);

        Label mobileLabel = new Label("Mobile Number:");
        TextField mobileField = new TextField(patientDetails[4]);
        mobileField.setEditable(false);
        layout.add(mobileLabel, 0, 4);
        layout.add(mobileField, 1, 4);

        Label ageLabel = new Label("Age:");
        TextField ageField = new TextField(patientDetails[5]);
        ageField.setEditable(false);
        layout.add(ageLabel, 0, 5);
        layout.add(ageField, 1, 5);

        Label testsLabel = new Label("Select Medical Test:");
        ComboBox<String> testsComboBox = new ComboBox<>();
        testsComboBox.getItems().addAll(
                "Blood Test", "Urine Test", "X-ray", "MRI", "CT Scan", "Ultrasound", "ECG", "EEG",
                "Liver Function Test", "Kidney Function Test", "Thyroid Function Test", "Cholesterol Test",
                "Glucose Test", "Complete Blood Count (CBC)", "Prostate-Specific Antigen (PSA) Test",
                "HIV Test", "Hepatitis Test", "Vitamin D Test", "Calcium Test", "Iron Test",
                "Bone Density Test", "Allergy Test", "Pregnancy Test", "Stool Test", "Sputum Test",
                "Pulmonary Function Test", "ABG (Arterial Blood Gas) Test", "Lipid Profile", "CRP Test",
                "ESR (Erythrocyte Sedimentation Rate) Test"
        );
        layout.add(testsLabel, 0, 6);
        layout.add(testsComboBox, 1, 6);

        Label resultLabel = new Label("Result:");
        ToggleGroup resultGroup = new ToggleGroup();
        RadioButton positiveRadio = new RadioButton("Positive");
        RadioButton negativeRadio = new RadioButton("Negative");
        positiveRadio.setToggleGroup(resultGroup);
        negativeRadio.setToggleGroup(resultGroup);
        layout.add(resultLabel, 0, 7);
        layout.add(positiveRadio, 1, 7);
        layout.add(negativeRadio, 2, 7);

        Label recommendationsLabel = new Label("Recommendations:");
        TextField recommendationsField = new TextField();
        layout.add(recommendationsLabel, 0, 8);
        layout.add(recommendationsField, 1, 8);

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            String test = testsComboBox.getValue();
            String result = positiveRadio.isSelected() ? "Positive" : "Negative";
            String recommendations = recommendationsField.getText();
            saveResult(patientInfo, test, result, recommendations);
            showAlert("Result saved successfully!");
            stage.setScene(new DashboardPage(stage).getScene());
        });
        layout.add(saveButton, 1, 9);

        ImageView logoImageView = new ImageView(new Image("file:///C:/Users/Hp/OneDrive/Desktop/JAVA Project/MedicalHealthCare/th.jpeg"));
        logoImageView.setFitWidth(200);
        logoImageView.setFitHeight(100);
        logoImageView.setPreserveRatio(true);

        mainLayout.getChildren().addAll(logoImageView, titleLabel, line, layout);

        ImageView backgroundImageView = new ImageView(new Image("file:///C:/Users/Hp/Downloads/pexels-shvetsa-3845115.jpg"));
        backgroundImageView.setPreserveRatio(false);
        backgroundImageView.setFitWidth(800);
        backgroundImageView.setFitHeight(600);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundImageView, mainLayout);
        StackPane.setAlignment(mainLayout, Pos.TOP_CENTER);

        Scene resultScene = new Scene(stackPane, 800, 600);
        stage.setScene(resultScene);
    }

    private void saveResult(String patientInfo, String test, String result, String recommendations) {
        String resultData = patientInfo + "," + test + "," + result + "," + recommendations;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("results.txt", true))) {
            writer.write(resultData);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private String getPatientInfo(String cnic) {
        try (BufferedReader reader = new BufferedReader(new FileReader("patients.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] patientInfo = line.split(",");
                if (patientInfo.length >= 3 && patientInfo[2].trim().equals(cnic.trim())) {
                    return line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Scene getScene() {
        return scene;
    }
}
