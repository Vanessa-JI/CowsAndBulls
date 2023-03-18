package org.example;

import org.example.controller.Controller;
import org.example.dao.GameDAO;
import org.example.dto.Game;
import org.example.view.GameView;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    public static void main(String ... args) {

        SpringApplication.run(App.class, args);


//        GameDAO dao = new GameDAO();
//        GameView view = new GameView();
//        Controller controller = new Controller(dao, view);
//        controller.startApp();
    }

}
