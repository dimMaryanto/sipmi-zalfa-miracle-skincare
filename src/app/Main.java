/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import penjualan.view.login;

/**
 *
 * @author Lani
 */
public class Main {

    public static void main(String[] args) {
        login loginform = new login();
        loginform.setVisible(true);
        loginform.setDefaultCloseOperation(FMenu.EXIT_ON_CLOSE);
    }

}
