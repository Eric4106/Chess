/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

//@author 710568

import java.awt.Graphics;
import java.awt.Color;

public class Piece {
    private int size;
    private String type;
    private String color;
    
    public Piece(String type, String color) {
        this.size = 65;
        this.color = color;
        this.type = type;
    }
    
    public String getType() {
        return type;
    }
    
    public String getColor() {
        return color;
    }
    
    public boolean legalMove(Piece[][] pieces, int i, int ii) {
        boolean legal = false;
        for (int a = -7; a < 8; a++) {
            if (i + a < 0 || i + a > 8) continue;
            for (int b = -7; b < 8; b++) {
                if (ii + b < 0 || ii + b > 8) continue;
                legal = true;
            }
        }
        return legal;
    }
    
    public void draw(Graphics g, int x, int y, boolean selected) {
        if (selected) {
            g.setColor(Color.WHITE);
        }
        else if (color.equals("blue")) {
            g.setColor(Color.BLUE);
        }
        else {
            g.setColor(Color.GRAY);
        }
        g.fillOval((x * 75) + 30, (y * 75) + 30, size, size);
        g.setColor(Color.WHITE);
        g.drawString(type, (x * 75) + 48, (y * 75) + 64);
    }
}
