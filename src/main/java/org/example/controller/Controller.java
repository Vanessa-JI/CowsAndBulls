package org.example.controller;

import org.example.view.MainMenu;

import javax.sql.DataSource;
import java.sql.SQLException;
//import java.sql.MysqlDataSource;
import java.util.Scanner;

public class Controller {
    private MainMenu menu;

    public Controller(MainMenu menu) {
        this.menu = menu;
    }

    public void startApp() {
        Scanner scanner = new Scanner(System.in);

        while (true) {

            int selection = scanner.nextInt();
            menu.displayMainMenu();


        }


    }


//    private static DataSource getDataSource() throws SQLException {
//        MysqlDataSource ds = new MysqlDataSource();
//        ds.setServerName("localhost");
//        ds.setDatabaseName("todoDB");
//        ds.setUser("root");
//        ds.setPassword("rootroot");
//        ds.setServerTimezone("America/Chicago");
//        ds.setUseSSL(false);
//        ds.setAllowPublicKeyRetrieval(true);
//
//        return ds;
//    }


    // generate the array of random numbers to be guessed
    public static void generateNumArr() {




    }


}
