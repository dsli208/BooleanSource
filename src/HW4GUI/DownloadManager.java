/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HW4GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author dsli
 */
public class DownloadManager extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        FlowPane pane = new FlowPane();
        pane.setHgap(5); pane.setVgap(5);
        pane.getChildren().add(new Label("Enter the information as requested below: "));
        GridPane information = new GridPane();
        TextField servers = new TextField();
        TextField mbps = new TextField();
        TextField timesteps = new TextField();
        TextField probPremium = new TextField();
        TextField probRegular = new TextField();
        information.add(new Label("Number of servers: "), 0, 0);
        information.add(servers, 1, 0);
        information.add(new Label("Amount of time: "), 0, 1);
        information.add(timesteps, 1, 1);
        information.add(new Label("Download speed (in mbps): "), 0, 2);
        information.add(mbps, 1, 2);
        information.add(new Label("Probability of a PREMIUM download (between 0 and 1): "), 0, 3);
        information.add(probPremium, 1, 3);
        information.add(new Label("Probability of a REGULAR download: "), 0, 4);
        information.add(probRegular, 1, 4);
        pane.getChildren().add(information);
        Button bt = new Button("Start DownloadManager");
        pane.getChildren().add(bt);
        /*Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });*/
        //root.getChildren().add(btn);
        
        Scene scene = new Scene(pane, 750, 250);
        
        primaryStage.setTitle("DownloadManager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
