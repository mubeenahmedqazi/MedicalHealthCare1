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
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AddPatientPage {
    private Stage stage;
    private Scene scene;

    public AddPatientPage(Stage stage) {
        this.stage = stage;
        createAddPatientPage();
    }

    private void createAddPatientPage() {
        VBox mainLayout = new VBox();
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setSpacing(20);

        ImageView backgroundImageView = new ImageView(new Image("file:///C:\\Users\\Hp\\Downloads\\pexels-edward-jenner-4033148.jpg"));
        backgroundImageView.setPreserveRatio(false);
        backgroundImageView.setFitWidth(800);
        backgroundImageView.setFitHeight(550);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundImageView, mainLayout);

        ImageView logoImageView = new ImageView(new Image("file:///C:\\Users\\Hp\\OneDrive\\Desktop\\JAVA Project\\MedicalHealthCare\\th.jpeg"));
        logoImageView.setFitWidth(100);
        logoImageView.setPreserveRatio(true);
        mainLayout.getChildren().add(logoImageView);

        Label titleLabel = new Label("Add Patient");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        titleLabel.setTextFill(Color.BLUE);
        mainLayout.getChildren().add(titleLabel);

        Line line = new Line(0, 0, 300, 0);
        line.setStroke(Color.BLUE);
        line.setStrokeWidth(2);
        mainLayout.getChildren().add(line);

        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(10);
        layout.setVgap(10);

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        layout.add(nameLabel, 0, 0);
        layout.add(nameField, 1, 0);

        Label genderLabel = new Label("Gender:");
        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton maleRadio = new RadioButton("Male");
        RadioButton femaleRadio = new RadioButton("Female");
        maleRadio.setToggleGroup(genderGroup);
        femaleRadio.setToggleGroup(genderGroup);
        HBox genderBox = new HBox(10, maleRadio, femaleRadio);
        layout.add(genderLabel, 0, 1);
        layout.add(genderBox, 1, 1);

        Label cnicLabel = new Label("CNIC:");
        TextField cnicField = new TextField();
        layout.add(cnicLabel, 0, 2);
        layout.add(cnicField, 1, 2);

        Label bloodGroupLabel = new Label("Blood Group:");
        ComboBox<String> bloodGroupCombo = new ComboBox<>();
        bloodGroupCombo.getItems().addAll("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");
        layout.add(bloodGroupLabel, 0, 3);
        layout.add(bloodGroupCombo, 1, 3);

        Label mobileLabel = new Label("Mobile Number:");
        TextField mobileField = new TextField();
        layout.add(mobileLabel, 0, 4);
        layout.add(mobileField, 1, 4);

        Label ageLabel = new Label("Age:");
        TextField ageField = new TextField();
        layout.add(ageLabel, 0, 5);
        layout.add(ageField, 1, 5);

        Button saveButton = new Button("Save");
        Button clearButton = new Button("Clear");
        layout.add(saveButton, 0, 6);
        layout.add(clearButton, 1, 6);

        saveButton.setOnAction(e -> {
            String name = nameField.getText();
            String gender = maleRadio.isSelected() ? "Male" : "Female";
            String cnic = cnicField.getText();
            String bloodGroup = bloodGroupCombo.getValue();
            String mobile = mobileField.getText();
            String age = ageField.getText();

            showAlert("Is This Information is correct?");

            savePatientData(name, gender, cnic, bloodGroup, mobile, age);

            nameField.clear();
            genderGroup.selectToggle(null);
            cnicField.clear();
            bloodGroupCombo.getSelectionModel().clearSelection();
            mobileField.clear();
            ageField.clear();

            DashboardPage dashboardPage = new DashboardPage(stage);
            stage.setScene(dashboardPage.getScene());

        });

        clearButton.setOnAction(e -> {
            nameField.clear();
            genderGroup.selectToggle(null);
            cnicField.clear();
            bloodGroupCombo.getSelectionModel().clearSelection();
            mobileField.clear();
            ageField.clear();
        });

        mainLayout.getChildren().add(layout);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> stage.setScene(new DashboardPage(stage).getScene()));

        mainLayout.getChildren().add(backButton);

        scene = new Scene(stackPane, 800, 550);

    }

    private void savePatientData(String name, String gender, String cnic, String bloodGroup, String mobile, String age) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("patients.txt", true))) {
            writer.write(name + "," + gender + "," + cnic + "," + bloodGroup + "," + mobile + "," + age);
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

    public Scene getScene() {
        return scene;
    }
}
