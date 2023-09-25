/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.neology.tolling.dev.mantis;

/**
 *
 * @author pkumar
 */
public class MantisResponseDimension {
    
    private int x0;
    
    private int y0;
    
    private int width;
    
    private int height;

    public MantisResponseDimension() {
    }

    public MantisResponseDimension(int x0, int y0, int width, int height) {
        this.x0 = x0;
        this.y0 = y0;
        this.width = width;
        this.height = height;
    }

    public int getX0() {
        return x0;
    }

    public void setX0(int x0) {
        this.x0 = x0;
    }

    public int getY0() {
        return y0;
    }

    public void setY0(int y0) {
        this.y0 = y0;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "MantisResponseDimension{" + "x0=" + x0 + ", y0=" + y0 + ", width=" + width + ", height=" + height + '}';
    }
    
}
