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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UpdatePatientPage {
    private Stage stage;
    private Scene scene;
    private TextField cnicField;
    private TextField nameField;
    private RadioButton maleRadio;
    private RadioButton femaleRadio;
    private ComboBox<String> bloodGroupCombo;
    private TextField mobileField;
    private TextField ageField;

    public UpdatePatientPage(Stage stage) {
        this.stage = stage;
        createUpdatePatientPage();
    }
    private void createUpdatePatientPage() {
        VBox mainLayout = new VBox();
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setSpacing(20);

        ImageView logo = new ImageView(new Image("file:///C:\\Users\\Hp\\OneDrive\\Desktop\\JAVA Project\\MedicalHealthCare\\th.jpeg"));
        logo.setFitWidth(100);
        logo.setFitHeight(100);
        mainLayout.getChildren().add(logo);

        Text heading = new Text("Update Patient");
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        heading.setFill(Color.BLUE);

        Line line = new Line(0, 0, 300, 0);
        line.setStrokeWidth(2);
        line.setStroke(Color.BLUE);

        VBox headingBox = new VBox();
        headingBox.setAlignment(Pos.CENTER);
        headingBox.getChildren().addAll(heading, line);
        mainLayout.getChildren().add(headingBox);

        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setPadding(new Insets(20));

        Label cnicLabel = new Label("CNIC:");
        cnicField = new TextField();
        layout.add(cnicLabel, 0, 0);
        layout.add(cnicField, 1, 0);

        Button searchButton = new Button("Search");
        layout.add(searchButton, 2, 0);

        Label nameLabel = new Label("Name:");
        nameField = new TextField();
        layout.add(nameLabel, 0, 1);
        layout.add(nameField, 1, 1);

        Label genderLabel = new Label("Gender:");
        ToggleGroup genderGroup = new ToggleGroup();
        maleRadio = new RadioButton("Male");
        femaleRadio = new RadioButton("Female");
        maleRadio.setToggleGroup(genderGroup);
        femaleRadio.setToggleGroup(genderGroup);
        HBox genderBox = new HBox(10, maleRadio, femaleRadio);
        layout.add(genderLabel, 0, 2);
        layout.add(genderBox, 1, 2);

        Label bloodGroupLabel = new Label("Blood Group:");
        bloodGroupCombo = new ComboBox<>();
        bloodGroupCombo.getItems().addAll("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");
        layout.add(bloodGroupLabel, 0, 3);
        layout.add(bloodGroupCombo, 1, 3);

        Label mobileLabel = new Label("Mobile Number:");
        mobileField = new TextField();
        layout.add(mobileLabel, 0, 4);
        layout.add(mobileField, 1, 4);

        Label ageLabel = new Label("Age:");
        ageField = new TextField();
        layout.add(ageLabel, 0, 5);
        layout.add(ageField, 1, 5);

        Button saveButton = new Button("Save");
        Button backButton = new Button("Back to Dashboard");
        layout.add(saveButton, 0, 6);
        layout.add(backButton, 1, 6);

        searchButton.setOnAction(e -> searchPatient());

        saveButton.setOnAction(e -> {
            updatePatientData(cnicField.getText());
            DashboardPage dashboardPage = new DashboardPage(stage);
            stage.setScene(dashboardPage.getScene());
        });

        backButton.setOnAction(e -> {
            DashboardPage dashboardPage = new DashboardPage(stage);
            stage.setScene(dashboardPage.getScene());
        });

        mainLayout.getChildren().add(layout);

        // Background image
        ImageView backgroundImageView = new ImageView(new Image("file:///C:\\Users\\Hp\\Downloads\\pexels-shvetsa-3786119.jpg"));
        backgroundImageView.setPreserveRatio(false);
        backgroundImageView.setFitWidth(800);
        backgroundImageView.setFitHeight(550);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundImageView, mainLayout);
        StackPane.setAlignment(mainLayout, Pos.TOP_CENTER);

        scene = new Scene(stackPane, 800, 600);
    }

    private void searchPatient() {
        try (BufferedReader reader = new BufferedReader(new FileReader("patients.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length >= 6 && details[2].equals(cnicField.getText())) {
                    nameField.setText(details[0]);
                    if (details[1].equals("Male")) {
                        maleRadio.setSelected(true);
                    } else {
                        femaleRadio.setSelected(true);
                    }
                    bloodGroupCombo.setValue(details[3]);
                    mobileField.setText(details[4]);
                    ageField.setText(details[5]);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updatePatientData(String cnic) {
        try (BufferedReader reader = new BufferedReader(new FileReader("patients.txt"))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length >= 6 && details[2].equals(cnic)) {
                    line = nameField.getText() + "," +
                            (maleRadio.isSelected() ? "Male" : "Female") + "," +
                            cnicField.getText() + "," +
                            bloodGroupCombo.getValue() + "," +
                            mobileField.getText() + "," +
                            ageField.getText();
                }
                lines.add(line);
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
