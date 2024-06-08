package com.example.medicalhealthcare;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DashboardPage {
    private Stage stage;
    private Scene scene;

    public DashboardPage(Stage stage) {
        this.stage = stage;
        createDashboardPage();
    }

    private void createDashboardPage() {

        VBox mainLayout = new VBox();
        mainLayout.setPadding(new Insets(20));
        mainLayout.setSpacing(10);
        mainLayout.setAlignment(Pos.TOP_CENTER);

        ImageView logoImageView = new ImageView(new Image("file:///C:/Users/Hp/OneDrive/Desktop/JAVA Project/MedicalHealthCare/th.jpeg"));
        logoImageView.setFitWidth(200);
        logoImageView.setFitHeight(100);
        logoImageView.setPreserveRatio(true);

        Text dashboardLabel = new Text("Dashboard");
        dashboardLabel.setFont(Font.font("Arial", 30));
        dashboardLabel.setFill(Color.BLUE);

        Line line = new Line(0, 0, 800, 0);
        line.setStroke(Color.BLUE);
        line.setStrokeWidth(2);
        line.setTranslateY(10);

        VBox buttonLayout = new VBox();
        buttonLayout.setAlignment(Pos.TOP_CENTER);
        buttonLayout.setSpacing(15);

        Button addPatientButton = createHighlightedButton("Add Patient");
        Button updatePatientButton = createHighlightedButton("Update Patient");
        Button deletePatientButton = createHighlightedButton("Delete Patient");
        Button addResultsButton = createHighlightedButton("Add Results");
        Button updateResultsButton = createHighlightedButton("Update Results");
        Button consultantAppointmentButton = createHighlightedButton("Consultant Appointment");
        Button viewPatientListButton = createHighlightedButton("View Patient List");
        Button viewAppointmentsButton = createHighlightedButton("View Appointments");
        Button viewResultButton = createHighlightedButton("View Result"); // New Button

        buttonLayout.getChildren().addAll(
                addPatientButton,
                updatePatientButton,
                deletePatientButton,
                addResultsButton,
                updateResultsButton,
                consultantAppointmentButton,
                viewPatientListButton,
                viewAppointmentsButton,
                viewResultButton // Add new button to layout
        );

        mainLayout.getChildren().addAll(logoImageView, dashboardLabel, line, buttonLayout);

        ImageView backgroundImageView = new ImageView(new Image("file:///C:/Users/Hp/Downloads/pexels-karolina-grabowska-5206978.jpg"));
        backgroundImageView.setPreserveRatio(false);
        backgroundImageView.setFitWidth(800);
        backgroundImageView.setFitHeight(550);

        backgroundImageView.fitWidthProperty().bind(stage.widthProperty());
        backgroundImageView.fitHeightProperty().bind(stage.heightProperty());

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundImageView, mainLayout);

        StackPane.setAlignment(mainLayout, Pos.TOP_CENTER);

        addPatientButton.setOnAction(e -> {
            AddPatientPage addPatientPage = new AddPatientPage(stage);
            stage.setScene(addPatientPage.getScene());
        });

        updatePatientButton.setOnAction(e -> {
            UpdatePatientPage updatePatientPage = new UpdatePatientPage(stage);
            stage.setScene(updatePatientPage.getScene());
        });

        deletePatientButton.setOnAction(e -> {
            DeletePatientPage deletePatientPage = new DeletePatientPage(stage);
            stage.setScene(deletePatientPage.getScene());
        });

        addResultsButton.setOnAction(e -> {
            AddResultsPage addResultsPage = new AddResultsPage(stage);
            stage.setScene(addResultsPage.getScene());
        });

        updateResultsButton.setOnAction(e -> {
            UpdateResultsPage updateResultsPage = new UpdateResultsPage(stage);
            stage.setScene(updateResultsPage.getScene());
        });

        consultantAppointmentButton.setOnAction(e -> {
            ConsultantAppointmentPage consultantAppointmentPage = new ConsultantAppointmentPage(stage);
            stage.setScene(consultantAppointmentPage.getScene());
        });

        viewPatientListButton.setOnAction(e -> {
            ViewPatientListPage viewPatientListPage = new ViewPatientListPage(stage);
            stage.setScene(viewPatientListPage.getScene());
        });

        viewAppointmentsButton.setOnAction(e -> {
            ViewAppointmentsPage viewAppointmentsPage = new ViewAppointmentsPage(stage);
            stage.setScene(viewAppointmentsPage.getScene());
        });

        viewResultButton.setOnAction(e -> {
            ViewResultPage viewResultPage = new ViewResultPage(stage);
            stage.setScene(viewResultPage.getScene());
        });

        scene = new Scene(stackPane, 800, 550);

        stage.setScene(scene);
    }

    private Button createHighlightedButton(String buttonText) {
        Button button = new Button(buttonText);
        button.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        button.setPrefSize(200, 40);

        DropShadow shadow = new DropShadow();
        button.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_ENTERED, e -> button.setEffect(shadow));
        button.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_EXITED, e -> button.setEffect(null));

        return button;
    }

    public Scene getScene() {
        return scene;
    }
}
