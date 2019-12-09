/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

//@author 710568

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.util.Timer;
import java.util.TimerTask;

public class Board extends JPanel {
    Piece[][] pieces = new Piece[8][8];
    Timer timer = new Timer();
    Rectangle[][] bounds = new Rectangle[8][8];
    boolean[][] aSelected = new boolean[8][8];
    boolean selected = false;
    
    public Board() {
        timer.scheduleAtFixedRate(new Task(), 100, 1000/6);
        generatePieces();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.DARK_GRAY);
        drawBoard(g);
        for (int i = 0; i < 8; i++) {
            for (int ii = 0; ii < 8; ii++) {
                if (pieces[i][ii] == null) continue;
                pieces[i][ii].draw(g, ii, i, aSelected[ii][i]);
            }
        }
    }
    
    private class Task extends TimerTask {
        public void run() {
            repaint();
        }
    }
    
    public void drawBoard(Graphics g) {
        boolean[][] isWhite = new boolean[8][8];
        for (int i = 0; i < 8; i++) {
            for (int ii = 0; ii< 8; ii++) {
                bounds[i][ii] = new Rectangle((i * 75) + 25, (ii * 75) + 25, 75, 75);
                if (isEven(i + ii)) {
                    isWhite[i][ii] = true;
                }
            }
        }
        
        for (int i = 0; i < 8; i++) {
            for (int ii = 0; ii < 8; ii++) {
                if (isWhite[i][ii]) {
                    g.setColor(Color.CYAN);
                }
                else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect((i * 75) + 25, (ii * 75) + 25, 75, 75);
            }
        }
    }
    
    public boolean isEven(int x) {
        boolean isEven = false;
        int y = x / 2;
        y = y * 2;
        if (x == y) {
            isEven = true;
        }
        return isEven;
    }
    
    public void generatePieces() {
        pieces[0][0] = new Piece("Rook", "Black");
        pieces[0][1] = new Piece("Knight", "Black");
        pieces[0][2] = new Piece("Bishop", "Black");
        pieces[0][3] = new Piece("Queen", "Black");
        pieces[0][4] = new Piece("King", "Black");
        pieces[0][5] = new Piece("Bishop", "Black");
        pieces[0][6] = new Piece("Knight", "Black");
        pieces[0][7] = new Piece("Rook", "Black");
        
        pieces[7][0] = new Piece("Rook", "White");
        pieces[7][1] = new Piece("Knight", "White");
        pieces[7][2] = new Piece("Bishop", "White");
        pieces[7][3] = new Piece("King", "White");
        pieces[7][4] = new Piece("Queen", "White");
        pieces[7][5] = new Piece("Bishop", "White");
        pieces[7][6] = new Piece("Knight", "White");
        pieces[7][7] = new Piece("Rook", "White");
        
        for (int i = 0; i < 8; i++) {
            pieces[1][i] = new Piece("Pawn", "Black");
            pieces[6][i] = new Piece("Pawn", "White");
        }
    }
    
    public void mouseClicked(MouseEvent e) {
        Rectangle mousePos = new Rectangle(e.getX(), e.getY(), 1, 1);
        for (int i = 0; i < 8; i++) {
            for (int ii = 0; ii < 8; ii++) {
                if (mousePos.intersects(bounds[i][ii])) {
                    if (pieces[i][ii] == null && selected) {
                        
                    }
                    else if (1 == 1) {
                        aSelected[i][ii] = true;
                        selected = true;
                    }
                    else if (1 == 1) {
                        
                    }
                }
            }
        }
    }
}
