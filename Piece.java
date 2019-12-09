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
    }
    
    public void update() {
        
    }
    
    public void draw(Graphics g, int x, int y, boolean selected) {
        if (selected) {
            g.setColor(Color.WHITE);
        }
        else if (color.equals("White")) {
            g.setColor(Color.BLUE);
        }
        else {
            g.setColor(Color.GRAY);
        }
        g.fillOval((x * 75) + 30, (y * 75) + 30, size, size);
    }
}
