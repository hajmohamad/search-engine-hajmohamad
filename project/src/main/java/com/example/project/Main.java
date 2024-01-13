package com.example.project;


import Controller.ReadFromDataBase;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.TransportOptions;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import co.elastic.clients.transport.rest_client.RestClientOptions;
import co.elastic.clients.transport.rest_client.RestClientOptions;

import java.io.IOException;

public class Main extends Application {
    public static  Stage orginalStage;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainPage.fxml"));
        orginalStage=stage;
        Scene scene = new Scene(fxmlLoader.load(), 1150, 658);

        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.show();
    }

    public static void main(String[] args) {
        try {
            ReadFromDataBase rd=new ReadFromDataBase();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        launch();



    }
}