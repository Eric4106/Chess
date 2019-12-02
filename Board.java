/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

//@author 710568

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import java.util.Timer;
import java.util.TimerTask;

public class Board extends JPanel {
    Piece[][] spaces = new Piece[10][10];
    Timer timer = new Timer();
    
    public Board() {
        timer.scheduleAtFixedRate(new Task(), 100, 1000/6);
        for (int i = 0; i < 8; i++) {
            for (int ii = 0; ii < 8; ii++) {
                Piece newPuck = new Piece();
                spaces[i][ii] = newPuck;
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.DARK_GRAY);
    }
    
    private class Task extends TimerTask {
        public void run() {
            repaint();
        }
    }
    
    public void keyPressed(KeyEvent e) {
        
    }

    public void keyReleased(KeyEvent e) {
        
    }
}
