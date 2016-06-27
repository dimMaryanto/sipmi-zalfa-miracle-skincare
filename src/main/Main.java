/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import penjualan.view.LoginView;

/**
 *
 * @author Lani
 */
public class Main {

    public static void main(String[] args) {
        LoginView loginform = new LoginView();
        loginform.setVisible(true);
        loginform.setDefaultCloseOperation(FMenu.EXIT_ON_CLOSE);
    }

}
