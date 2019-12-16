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
    private boolean firstPawnMove;
    
    public Piece(String type, String color) {
        this.size = 65;
        this.color = color;
        this.type = type;
        this.firstPawnMove = true;
    }
    
    public String getType() {
        return type;
    }
    
    public String getColor() {
        return color;
    }
    
    public boolean legalMove(Piece[][] pieces, int ni, int nii, int ci, int cii, boolean attack) {
        boolean legal = false;
        for (int i = -7; i < 8; i++) {
            if (ci + i < 0 || ci + i > 7) continue;
            for (int ii = -7; ii < 8; ii++) {
                if (cii + ii < 0 || cii + ii > 7) continue;
                if (type.equals("Pawn")) {
                    if (color.equals("blue")) {
                        if (cii - 1 == nii) {
                            if (attack) {
                                if (ci + 1 == ni || ci - 1 == ni) {
                                    legal = true;
                                }
                            }
                            else {
                                if (ci == ni) {
                                    legal = true;
                                }
                            }
                        }
                        else if (cii - 2 == nii && firstPawnMove) {
                            if (ci == ni) {
                                legal = true;
                                firstPawnMove = false;
                            }
                        }
                    }
                    else {
                        if (cii + 1 == nii) {
                            if (attack) {
                                if (ci + 1 == ni || ci - 1 == ni) {
                                    legal = true;
                                }
                            }
                            else {
                                if (ci == ni) {
                                    legal = true;
                                }
                            }
                        }
                        else if (cii + 2 == nii && firstPawnMove) {
                            legal = true;
                            firstPawnMove = false;
                        }
                    }
                }
                else if (type.equals("Rook")) {
                    if (ci == ni || cii == nii) {
                        legal = true;
                    }
                }
                else if (type.equals("Knight")) {
                    if (((ci - ni) / (cii - nii) == 1 / 2 || (ci - ni) / (cii - nii) == -1 / 2 || (ci - ni) / (cii - nii) == 2 || (ci - ni) / (cii - nii) == -2) && (ci - ni < 3 && ci - ni > -3 && cii - nii < 3 && cii - nii > -3)) {
                        legal = true;
                    }
                }
                else if (type.equals("Bishop")) {
                    if ((ci - ni) / (cii - nii) == 1 || (ci - ni) / (cii - nii) == -1) {
                        legal = true;
                    }
                }
                else if (type.equals("Queen")) {
                    if (ci == ni || cii == nii || (ci - ni) / (cii - nii) == 1 || (ci - ni) / (cii - nii) == -1) {
                        legal = true;
                    }
                }
                else if (type.equals("King")) {
                    if ((ci - ni < 2 && ci - ni > -2 && cii - nii < 2 && cii - nii > -2)) {
                        legal = true;
                    }
                }
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
