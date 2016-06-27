/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import app.view.MainMenu;
import javax.swing.SwingUtilities;

/**
 *
 * @author dimmaryanto
 */
public class MainApplication {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainMenu mainMenu = new MainMenu();
                mainMenu.setTitle("SIPMI - pada Zalfa Miracle Skin Care Bandung");
                mainMenu.setVisible(true);
            }
        });
    }
}
