package com.example.medicalhealthcare;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ViewPatientListPage {
    private Stage stage;
    private Scene scene;

    public ViewPatientListPage(Stage stage) {
        this.stage = stage;
        createViewPatientListPage();
    }

    private void createViewPatientListPage() {
        VBox mainLayout = new VBox();
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setSpacing(20);
        mainLayout.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        ImageView logoImageView = new ImageView(new Image("file:///C:\\Users\\Hp\\OneDrive\\Desktop\\JAVA Project\\MedicalHealthCare\\th.jpeg"));
        logoImageView.setFitWidth(200);
        logoImageView.setFitHeight(100);
        logoImageView.setPreserveRatio(true);

        Label titleLabel = new Label("Patient List");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleLabel.setTextFill(Color.BLUE);

        mainLayout.getChildren().addAll(logoImageView, titleLabel);

        TableView<Patient> patientTable = new TableView<>();
        patientTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Patient, Integer> numberColumn = new TableColumn<>("No.");
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

        TableColumn<Patient, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Patient, String> genderColumn = new TableColumn<>("Gender");
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

        TableColumn<Patient, String> cnicColumn = new TableColumn<>("CNIC");
        cnicColumn.setCellValueFactory(new PropertyValueFactory<>("cnic"));

        TableColumn<Patient, String> bloodGroupColumn = new TableColumn<>("Blood Group");
        bloodGroupColumn.setCellValueFactory(new PropertyValueFactory<>("bloodGroup"));

        TableColumn<Patient, String> phoneNumberColumn = new TableColumn<>("Phone Number");
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        TableColumn<Patient, String> ageColumn = new TableColumn<>("Age");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        patientTable.getColumns().addAll(numberColumn, nameColumn, genderColumn, cnicColumn, bloodGroupColumn, phoneNumberColumn, ageColumn);

        try (BufferedReader reader = new BufferedReader(new FileReader("patients.txt"))) {
            String line;
            int counter = 1;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length >= 6) { // Ensure there are enough details in each line
                    patientTable.getItems().add(new Patient(counter++, details[0], details[1], details[2], details[3], details[4], details[5]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainLayout.getChildren().add(patientTable);

        Button backButton = new Button("Back to Dashboard");
        backButton.setOnAction(e -> {
            DashboardPage dashboardPage = new DashboardPage(stage);
            stage.setScene(dashboardPage.getScene());
        });
        mainLayout.getChildren().add(backButton);

        scene = new Scene(mainLayout, 800, 600);
    }

    public Scene getScene() {
        return scene;
    }

    public static class Patient {
        private final int number;
        private final String name;
        private final String gender;
        private final String cnic;
        private final String bloodGroup;
        private final String phoneNumber;
        private final String age;

        public Patient(int number, String name, String gender, String cnic, String bloodGroup, String phoneNumber, String age) {
            this.number = number;
            this.name = name;
            this.gender = gender;
            this.cnic = cnic;
            this.bloodGroup = bloodGroup;
            this.phoneNumber = phoneNumber;
            this.age = age;
        }

        public int getNumber() {
            return number;
        }

        public String getName() {
            return name;
        }

        public String getGender() {
            return gender;
        }

        public String getCnic() {
            return cnic;
        }

        public String getBloodGroup() {
            return bloodGroup;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getAge() {
            return age;
        }
    }
}
