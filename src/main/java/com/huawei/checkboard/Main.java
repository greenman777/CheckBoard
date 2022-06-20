package com.huawei.;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.stream.Collectors;

public class MainApplication extends Application {
    final FileChooser fileChooser = new FileChooser();

    @Override
    public void start(Stage stage) throws IOException {
        // Create MenuBar
        MenuBar menuBar = new MenuBar();

        // Create menus
        Menu fileMenu = new Menu("File");
        Menu taskMenu = new Menu("Tasks");
        Menu helpMenu = new Menu("Help");

        // Create MenuItems
        MenuItem openCellItem = new MenuItem("Load DSP CELL...");
        MenuItem openBrdItem = new MenuItem("Load DSP BRD...");
        MenuItem openBppItem = new MenuItem("Load LST BPP...");
        MenuItem exitItem = new MenuItem("Exit");

        MenuItem startBrdCheck = new MenuItem("Start BRD free check...");

        // Add menuItems to the Menus
        fileMenu.getItems().addAll(openCellItem, openBrdItem, openBppItem, exitItem);
        taskMenu.getItems().addAll(startBrdCheck);

        // Add Menus to the MenuBar
        menuBar.getMenus().addAll(fileMenu, taskMenu, helpMenu);


        BorderPane root = new BorderPane();
        root.setTop(menuBar);


        Scene scene = new Scene(root, 350, 200);


        stage.setTitle("BRD LTE check");
        stage.setScene(scene);
        stage.show();

        exitItem.setOnAction(event -> System.exit(0));

        openCellItem.setOnAction(event -> {
            ParseCell.startParse(openFileToString(stage));
        });

        openBrdItem.setOnAction(event -> {
            ParseBRD.startParse(openFileToString(stage));
        });

        openBppItem.setOnAction(event -> {
            ParseBBP.startParse(openFileToString(stage));
        });
    }
    private String openFileToString(Stage stage){
        File file = fileChooser.showOpenDialog(stage);
        String stringAll = null;
        if (file != null) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file.getAbsoluteFile()));
                stringAll = br.lines().collect(Collectors.joining("\n"));
                System.out.println(stringAll);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return stringAll;
    }

    public static void main(String[] args) {
        launch();
    }
}