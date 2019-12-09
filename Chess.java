/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

//@author 710568

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class Chess {
    public static void main(String[] args) {
        JFrame j = new JFrame("Chess");
        Board b = new Board();
        j.add(b);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setSize(1200, 675);
        j.setVisible(true);
        j.setLocationRelativeTo(null);
        j.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                b.mouseClicked(e);
            }
        });
    }
    
}