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
    boolean[][] selected = new boolean[8][8];
    boolean sSelected;
    int iSelected;
    int iiSelected;
    String turn = "blue";
    boolean blueWin = false;
    boolean blackWin = false;
    
    public Board() {
        timer.scheduleAtFixedRate(new Task(), 100, 1000/6);
        generatePieces();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.DARK_GRAY);
        drawBoard(g);
        g.setColor(Color.WHITE);
        for (int i = 0; i < 8; i++) {
            for (int ii = 0; ii < 8; ii++) {
                if (pieces[i][ii] == null) continue;
                pieces[i][ii].draw(g, ii, i, selected[ii][i]);
            }
        }
        if (blueWin) {
            g.drawString("Congradulations, the blue player wins!", 800, 300);
        }
        else if (blackWin) {
            g.drawString("Congradulations, the black player wins!", 800, 300);
        }
        else {
            g.drawString("It is the " + turn + " player's turn", 800, 300);
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
                bounds[i][ii] = new Rectangle((i * 75) + 33, (ii * 75) + 53, 75, 75);
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
        pieces[0][0] = new Piece("Rook", "black");
        pieces[0][1] = new Piece("Knight", "black");
        pieces[0][2] = new Piece("Bishop", "black");
        pieces[0][3] = new Piece("Queen", "black");
        pieces[0][4] = new Piece("King", "black");
        pieces[0][5] = new Piece("Bishop", "black");
        pieces[0][6] = new Piece("Knight", "black");
        pieces[0][7] = new Piece("Rook", "black");
        
        pieces[7][0] = new Piece("Rook", "blue");
        pieces[7][1] = new Piece("Knight", "blue");
        pieces[7][2] = new Piece("Bishop", "blue");
        pieces[7][3] = new Piece("King", "blue");
        pieces[7][4] = new Piece("Queen", "blue");
        pieces[7][5] = new Piece("Bishop", "blue");
        pieces[7][6] = new Piece("Knight", "blue");
        pieces[7][7] = new Piece("Rook", "blue");
        
        for (int i = 0; i < 8; i++) {
            pieces[1][i] = new Piece("Pawn", "black");
            pieces[6][i] = new Piece("Pawn", "blue");
        }
    }
    
    public void mouseClicked(MouseEvent e) {
        if (!(blueWin || blackWin)) {
            Rectangle mousePos = new Rectangle(e.getX(), e.getY(), 1, 1);
            for (int i = 0; i < 8; i++) {
                for (int ii = 0; ii < 8; ii++) {
                    if (mousePos.intersects(bounds[i][ii])) {
                        if (pieces[ii][i] == null) {
                            if (sSelected) {
                                if (pieces[iiSelected][iSelected].legalMove(pieces, i , ii, iSelected, iiSelected, false)) {
                                    pieces[ii][i] = pieces[iiSelected][iSelected];
                                    pieces[iiSelected][iSelected] = null;
                                    if (turn.equals("blue")) {
                                        turn = "black";
                                    }
                                    else {
                                        turn = "blue";
                                    }
                                    selected[i][ii] = false;
                                    sSelected = false;
                                }
                            }
                        }
                        else {
                            if (pieces[ii][i].getColor().equals(turn)) {
                                if (sSelected) {
                                    if (selected[i][ii]) {
                                        selected[i][ii] = false;
                                        sSelected = false;
                                    }
                                    else {
                                        selected[iSelected][iiSelected] = false;
                                        selected[i][ii] = true;
                                        iSelected = i;
                                        iiSelected = ii;
                                    }
                                }
                                else {
                                    selected[i][ii] = true;
                                    sSelected = true;
                                    iSelected = i;
                                    iiSelected = ii;
                                }
                            }
                            else if (sSelected) {
                                if (pieces[iiSelected][iSelected].legalMove(pieces, i, ii, iSelected, iiSelected, true)) {
                                    if (pieces[ii][i].getType().equals("King")) {
                                        if (pieces[ii][i].getColor().equals("blue")) {
                                            blackWin = true;
                                        }
                                        else {
                                            blueWin = true;
                                        }
                                    }
                                    pieces[ii][i] = pieces[iiSelected][iSelected];
                                    pieces[iiSelected][iSelected] = null;
                                    if (turn.equals("blue")) {
                                        turn = "black";
                                    }
                                    else {
                                        turn = "blue";
                                    }
                                    selected[i][ii] = false;
                                    sSelected = false;
                                }
                            }
                        }
                    }
                }
            }
        }
        else {
            System.exit(0);
        }
    }
}
