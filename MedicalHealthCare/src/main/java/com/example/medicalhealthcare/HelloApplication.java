package com.example.medicalhealthcare;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class HelloApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        LoginPage loginPage = new LoginPage(stage);
        stage.setScene(loginPage.getScene());
        stage.initStyle(StageStyle.DECORATED);
        //stage.setMaximized(true);
        stage.setTitle("Medical Laboratory Management System");
        stage.setMinWidth(800);
        stage.setMinHeight(550);
        stage.getIcons().add(new Image("file:///C:\\Users\\Hp\\OneDrive\\Desktop\\JAVA Project\\MedicalHealthCare\\th.jpeg"));
        stage.show();
    }
}
