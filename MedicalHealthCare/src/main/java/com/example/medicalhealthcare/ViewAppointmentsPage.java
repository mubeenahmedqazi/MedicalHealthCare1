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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ViewAppointmentsPage {
    private Stage stage;
    private Scene scene;

    public ViewAppointmentsPage(Stage stage) {
        this.stage = stage;
        createViewAppointmentsPage();
    }

    private void createViewAppointmentsPage() {
        VBox mainLayout = new VBox();
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setSpacing(20);

        ImageView backgroundImageView = new ImageView(new Image("file:///C:/Users/Hp/Downloads/pexels-edward-jenner-4033148.jpg"));
        backgroundImageView.setPreserveRatio(false);
        backgroundImageView.setFitWidth(800);
        backgroundImageView.setFitHeight(600);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundImageView, mainLayout);

        ImageView logoImageView = new ImageView(new Image("file:///C:/Users/Hp/OneDrive/Desktop/JAVA Project/MedicalHealthCare/th.jpeg"));
        logoImageView.setFitWidth(200);
        logoImageView.setFitHeight(100);
        logoImageView.setPreserveRatio(true);

        Label titleLabel = new Label("Appointments");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleLabel.setTextFill(Color.BLUE);

        mainLayout.getChildren().addAll(logoImageView, titleLabel);

        TableView<Appointment> appointmentsTable = new TableView<>();
        appointmentsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Appointment, Integer> numberColumn = new TableColumn<>("No.");
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

        TableColumn<Appointment, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Appointment, String> genderColumn = new TableColumn<>("Gender");
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

        TableColumn<Appointment, String> cnicColumn = new TableColumn<>("CNIC");
        cnicColumn.setCellValueFactory(new PropertyValueFactory<>("cnic"));

        TableColumn<Appointment, String> bloodGroupColumn = new TableColumn<>("Blood Group");
        bloodGroupColumn.setCellValueFactory(new PropertyValueFactory<>("bloodGroup"));

        TableColumn<Appointment, String> phoneNumberColumn = new TableColumn<>("Phone Number");
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        TableColumn<Appointment, String> ageColumn = new TableColumn<>("Age");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<Appointment, String> doctorColumn = new TableColumn<>("Dr Name");
        doctorColumn.setCellValueFactory(new PropertyValueFactory<>("doctor"));

        TableColumn<Appointment, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Appointment, String> timeColumn = new TableColumn<>("Time");
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        TableColumn<Appointment, String> purposeColumn = new TableColumn<>("Purpose");
        purposeColumn.setCellValueFactory(new PropertyValueFactory<>("purpose"));

        appointmentsTable.getColumns().addAll(numberColumn, nameColumn, genderColumn, cnicColumn, bloodGroupColumn, phoneNumberColumn, ageColumn, doctorColumn, dateColumn, timeColumn, purposeColumn);

        mainLayout.getChildren().add(appointmentsTable);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            DashboardPage dashboardPage = new DashboardPage(stage);
            stage.setScene(dashboardPage.getScene());
        });
        mainLayout.getChildren().add(backButton);

        scene = new Scene(stackPane, 800, 600);
        populateAppointmentsTable(appointmentsTable);
    }

    private void populateAppointmentsTable(TableView<Appointment> appointmentsTable) {
        appointmentsTable.getItems().clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("appointments.txt"))) {
            String line;
            int counter = 1;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length >= 11) {
                    appointmentsTable.getItems().add(new Appointment(
                            counter++,
                            details[0].trim(),
                            details[1].trim(),
                            details[2].trim(),
                            details[3].trim(),
                            details[4].trim(),
                            details[5].trim(),
                            details[6].trim(),
                            details[7].trim(),
                            details[8].trim(),
                            details[9].trim()

                    ));

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Scene getScene() {
        return scene;
    }

    public static class Appointment {
        private final int number;
        private final String name;
        private final String gender;
        private final String cnic;
        private final String bloodGroup;
        private final String phoneNumber;
        private final String age;
        private final String doctor;
        private final String date;
        private final String time;
        private final String purpose;

        public Appointment(int number, String name, String gender, String cnic, String bloodGroup, String phoneNumber, String age, String doctor, String date, String time, String purpose) {
            this.number = number;
            this.name = name;
            this.gender = gender;
            this.cnic = cnic;
            this.bloodGroup = bloodGroup;
            this.phoneNumber = phoneNumber;
            this.age = age;
            this.doctor = doctor;
            this.date = date;
            this.time = time;
            this.purpose = purpose;
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

        public String getDoctor() {
            return doctor;
        }

        public String getDate() {
            return date;
        }

        public String getTime() {
            return time;
        }

        public String getPurpose() {
            return purpose;
        }
    }
}
