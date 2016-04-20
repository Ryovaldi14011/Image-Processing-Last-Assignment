/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JComponent;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author Ryo
 */
public class PadDraw extends JComponent {

    Image image;
    Graphics2D graphics2D;
    int currentX, currentY, oldX, oldY;
    BasicStroke stroke = new BasicStroke(1);
    
    public PadDraw() {
        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
            }

//            @Override
//            public void mouseClicked(MouseEvent e) {
//                currentX = e.getX();
//                currentY = e.getY();
//                if (graphics2D != null) {
//                    graphics2D.fill(new Rectangle(currentX, currentY, 100, 100));
//                }
//            }
            
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                currentX = e.getX();
                currentY = e.getY();
                if (graphics2D != null) {
                    graphics2D.drawLine(oldX, oldY, currentX, currentY);
                }
                repaint();
                oldX = currentX;
                oldY = currentY;
            }

        });
        
        
        
    }

    @Override
    public void paintComponent(Graphics g) {
        if (image == null) {
            image = createImage(getSize().width, getSize().height);
            graphics2D = (Graphics2D) image.getGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clear();

        }
        g.drawImage(image, 0, 0, null);
    }

    public void setTickness(int newThickness){
        graphics2D.setStroke(new BasicStroke(newThickness));
    }
    
    public void clear() {
        graphics2D.setPaint(Color.white);
        graphics2D.fillRect(0, 0, getSize().width, getSize().height);
        graphics2D.setPaint(Color.black);
        repaint();
    }
    
    public void erase(){
        graphics2D.setPaint(Color.white);
        repaint();
    }
    
    public void red() {
        graphics2D.setPaint(Color.red);
        repaint();
    }

    public void black() {
        graphics2D.setPaint(Color.black);
        repaint();
    }

    public void pink() {
        graphics2D.setPaint(Color.magenta);
        repaint();
    }

    public void blue() {
        graphics2D.setPaint(Color.blue);
        repaint();
    }

    public void green() {
        graphics2D.setPaint(Color.green);
        repaint();
    }

    public void orange() {
        graphics2D.setPaint(Color.orange);
        repaint();
    }
}
