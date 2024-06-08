package com.example.medicalhealthcare;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConsultantAppointmentPage {
    private Stage stage;
    private Scene scene;
    private Map<String, String> patients;

    public ConsultantAppointmentPage(Stage stage) {
        this.stage = stage;
        this.patients = loadPatientsFromFile();
        createConsultantAppointmentPage();
    }

    private Map<String, String> loadPatientsFromFile() {
        Map<String, String> patients = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("patients.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] patientInfo = line.split(",");
                if (patientInfo.length >= 3) {
                    patients.put(patientInfo[2].trim(), line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return patients;
    }

    private void createConsultantAppointmentPage() {
        VBox mainLayout = new VBox();
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setSpacing(20);
        ImageView logoImageView = new ImageView(new Image("file:///C:/Users/Hp/OneDrive/Desktop/JAVA Project/MedicalHealthCare/th.jpeg"));
        logoImageView.setFitWidth(200);
        logoImageView.setFitHeight(100);
        logoImageView.setPreserveRatio(true);
        mainLayout.getChildren().add(logoImageView);

        Label titleLabel = new Label("Consultant Appointment");
        titleLabel.setFont(javafx.scene.text.Font.font("Arial", 20));
        mainLayout.getChildren().add(titleLabel);

        Label cnicLabel = new Label("Enter CNIC:");
        TextField cnicField = new TextField();
        mainLayout.getChildren().addAll(cnicLabel, cnicField);

        Button verifyButton = new Button("Verify");
        mainLayout.getChildren().add(verifyButton);


        verifyButton.setOnAction(e -> {
            String cnic = cnicField.getText();
            String patientInfo = patients.get(cnic);
            if (patientInfo != null) {
                displayAppointmentForm(patientInfo);
            } else {
                showAlert("Patient not found!");
            }
        });

        // Back button
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> stage.setScene(new DashboardPage(stage).getScene()));
        mainLayout.getChildren().add(backButton);

        // Create the background image view
        ImageView backgroundImageView = new ImageView(new Image("file:///C:\\Users\\Hp\\Downloads\\pexels-shvetsa-3845115.jpg"));
        backgroundImageView.setPreserveRatio(false);
        backgroundImageView.setFitWidth(800);
        backgroundImageView.setFitHeight(600);

        // Create a StackPane and add the background image and main layout
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundImageView, mainLayout);

        // Ensure the main layout is aligned to the top center
        StackPane.setAlignment(mainLayout, Pos.CENTER);

        scene = new Scene(stackPane, 800, 600);
    }

    private void displayAppointmentForm(String patientInfo) {
        VBox mainLayout = new VBox();
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setSpacing(7);

        ImageView logoImageView = new ImageView(new Image("file:///C:/Users/Hp/OneDrive/Desktop/JAVA Project/MedicalHealthCare/th.jpeg"));
        logoImageView.setFitWidth(200);
        logoImageView.setFitHeight(100);
        logoImageView.setPreserveRatio(true);
        mainLayout.getChildren().addAll(logoImageView);

        Label titleLabel = new Label("Consultant Appointment Form");
        titleLabel.setFont(javafx.scene.text.Font.font("Arial", 20));
        mainLayout.getChildren().add(titleLabel);

        Label patientInfoLabel = new Label("Patient Information: " + patientInfo);


        Label ConsultantNameLabel = new Label("Consultant Name:");
        ComboBox<String> consultantnameComboBox = new ComboBox<>();
        consultantnameComboBox.getItems().addAll(
                "DR.MAZHAR-UL-ISLAM", "DR.MEHMOOD AYAZ", "DR.ALI AKBAR", "DR.KHUSH BAKHT", "DR.MUBEEN AHMED QAZI ",
                "DR.SHAHID ALI NAQVI", "DR.BISMA YOUSAF", "DR.EMAAN FATIMA", "DR.MUNAEEM AKBAR NAEEM","DR.MOEZZ RASHID KHAN","DR HASSAN"
        );
        mainLayout.getChildren().addAll(ConsultantNameLabel,consultantnameComboBox);


        Label appointmentDateLabel = new Label("Appointment Date:");
        DatePicker appointmentDatePicker = new DatePicker();
        mainLayout.getChildren().addAll(appointmentDateLabel, appointmentDatePicker);

        Label appointmentTimeLabel = new Label("Appointment Time:");
        ComboBox<String> appointmentTimeComboBox = new ComboBox<>();
        appointmentTimeComboBox.getItems().addAll(
                "09:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "01:00 PM",
                "02:00 PM", "03:00 PM", "04:00 PM", "05:00 PM"
        );
        mainLayout.getChildren().addAll(appointmentTimeLabel, appointmentTimeComboBox);

        Label purposeLabel = new Label("Purpose of Visit:");
        TextField purposeField = new TextField();
        mainLayout.getChildren().addAll(purposeLabel, purposeField);

        Label notesLabel = new Label("Additional Notes:");
        TextArea notesArea = new TextArea();
        notesArea.setPrefRowCount(3);
        mainLayout.getChildren().addAll(notesLabel, notesArea);

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            String consultantName = consultantnameComboBox.getValue();
            String appointmentDate = appointmentDatePicker.getValue().toString();
            String appointmentTime = appointmentTimeComboBox.getValue();
            String purpose = purposeField.getText();
            String notes = notesArea.getText();
            saveAppointment(patientInfo, consultantName, appointmentDate, appointmentTime, purpose, notes);
            showAlert("Appointment saved successfully!");
            stage.setScene(new DashboardPage(stage).getScene());
        });
        mainLayout.getChildren().add(saveButton);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> stage.setScene(new DashboardPage(stage).getScene()));
        mainLayout.getChildren().add(backButton);


        ImageView backgroundImageView = new ImageView(new Image("file:///C:\\Users\\Hp\\Downloads\\pexels-shvetsa-3845115.jpg"));
        backgroundImageView.setPreserveRatio(false);
        backgroundImageView.setFitWidth(800);
        backgroundImageView.setFitHeight(600);


        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundImageView, mainLayout);


        StackPane.setAlignment(mainLayout, Pos.CENTER);

        Scene appointmentScene = new Scene(stackPane, 800, 600);
        stage.setScene(appointmentScene);
    }

    private void saveAppointment(String patientInfo, String consultantName, String appointmentDate, String appointmentTime, String purpose, String notes) {
        String appointmentData = patientInfo + "," + consultantName + "," + appointmentDate + "," + appointmentTime + "," + purpose + "," + notes;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("appointments.txt", true))) {
            writer.write(appointmentData);
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
