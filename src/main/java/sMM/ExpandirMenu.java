/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sMM;

import java.awt.Component;
import java.awt.Dimension;

/**
 *
 * @author Eloy
 */
public class ExpandirMenu extends java.util.TimerTask {
    int pixeles = 50;
    javax.swing.JPanel Menu;
    javax.swing.JFrame Frame;
    public ExpandirMenu(javax.swing.JPanel m, javax.swing.JFrame f) {
        Menu = m;
        Frame = f;
    }
    @Override
    public void run() {
        Menu.setVisible(false);
        if (pixeles < 175) {
            pixeles += 5;
            Dimension dMenu = new Dimension(pixeles,Frame.getSize().height);
            Menu.setPreferredSize(dMenu);
            //System.out.println(pixeles);
            Menu.setVisible(true);
        } else {
            Dimension dMenu = new Dimension(175,Frame.getSize().height);
            Menu.setPreferredSize(dMenu);
            for (Component x : Menu.getComponents()) {
                x.setVisible(true);
            }
            Menu.setVisible(true);
            EstadoMenu.moviendose = false;
            cancel();
        }
        
    }
    
}