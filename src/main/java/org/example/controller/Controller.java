package org.example.controller;

import org.example.dao.GameDAO;
import org.example.view.GameView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import java.sql.MysqlDataSource;
import java.util.Scanner;

//@RestController
//@RequestMapping("/api")
public class Controller {
    private GameView view;
    private GameDAO dao;

//    @Autowired
    public Controller(GameDAO dao, GameView view) {
        this.dao = dao;
        this.view = view;
    }

//    @GetMapping
//    public String[] helloWorld() {
//        String[] result = {"Hello", "World", "!"};
//        return result;
//    }

//    @GetMapping
    public void startApp() {

        while (true) {

            int selection = view.displayMainMenu();

            switch (selection) {
                case 1 -> System.out.println("playing");
                case 2 -> System.out.println("displaying all");
            }

        }


    }



    // generate the array of random numbers to be guessed
    public static void generateNumArr() {


    }
}
