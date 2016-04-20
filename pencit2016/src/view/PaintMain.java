/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import model.PadDraw;

/**
 *
 * @author Ryo
 */
public class PaintMain extends JFrame {

    public PaintMain() {
        super("Doodle Paint");
        final PadDraw drawingFrame = new PadDraw();
        Icon iconBlack = new ImageIcon("src/view/black.gif");
        Icon iconRed = new ImageIcon("src/view/red.gif");
        Icon iconGreen = new ImageIcon("src/view/green.gif");
        Icon iconBlue = new ImageIcon("src/view/blue.gif");
        Icon iconPink = new ImageIcon("src/view/pink.gif");
        Icon iconOrange = new ImageIcon("src/view/orange.gif");
        Icon erase = new ImageIcon("src/view/erase.png");
        Icon save = new ImageIcon("src/view/save.png");
        
        Container content = getContentPane();
        content.setLayout(new BorderLayout());
        content.add(drawingFrame, BorderLayout.CENTER);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(75, 100));
        panel.setMinimumSize(new Dimension(75, 100));
        panel.setMaximumSize(new Dimension(75, 100));
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingFrame.clear();
            }
        });
        JButton blackButton = new JButton(iconBlack);
        JButton redButton = new JButton(iconRed);
        JButton greenButton = new JButton(iconGreen);
        JButton blueButton = new JButton(iconBlue);
        JButton pinkButton = new JButton(iconPink);
        JButton orangeButton = new JButton(iconOrange);
        JButton eraser = new JButton(erase);
        JButton saveImg = new JButton(save);
        
        eraser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingFrame.erase();
            }
        });

        blackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingFrame.black();
            }
        });
        redButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingFrame.red();
            }
        });
        greenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingFrame.green();
            }
        });
        blueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingFrame.blue();
            }
        });
        pinkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingFrame.pink();
            }
        });
        orangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingFrame.orange();
            }
        });

        JLabel ticknessLabel = new JLabel("Tickness");
        JTextField tickness = new JTextField("1");
        tickness.setPreferredSize(new Dimension(70, 20));
        JButton setTickness = new JButton("Set");
        
        saveImg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedImage image = new BufferedImage(drawingFrame.getWidth(), drawingFrame.getHeight(), BufferedImage.TYPE_INT_RGB);
                    Graphics2D savingImage = image.createGraphics();
                    drawingFrame.paint(savingImage);
                    String filename = "noname";
                    filename = JOptionPane.showInputDialog(null,"Insert File name: ","Saving Image",1);
                    if(filename != null){
                        ImageIO.write(image,"jpeg", new File("src/savedImage/"+filename+".jpeg"));
                        JOptionPane.showMessageDialog(null, "Image Saved!", "Save Image",2,save);
                    }else{
                        JOptionPane.showMessageDialog(null, "IMAGE NOT SAVED!","Save Image",1);
                    }    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "IMAGE CANNOT BE SAVED!","Save Image",0);
                    System.out.println("FAILED TO SAVE IMAGE: "+ex);
                }
            }
        });
        
        setTickness.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingFrame.setTickness(Integer.parseInt(tickness.getText()));
            }
        });

        tickness.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (Integer.parseInt(tickness.getText()) > 100) {
                        tickness.setText("100");
                    } else if (tickness.getText().isEmpty() || Integer.parseInt(tickness.getText()) < 1) {
                        tickness.setText("1");
                    }
                } catch (Exception ex) {
                    tickness.setText("1");
                }
            }
        });

        blackButton.setPreferredSize(new Dimension(48, 48));
        redButton.setPreferredSize(new Dimension(48, 48));
        greenButton.setPreferredSize(new Dimension(48, 48));
        blueButton.setPreferredSize(new Dimension(48, 48));
        pinkButton.setPreferredSize(new Dimension(48, 48));
        orangeButton.setPreferredSize(new Dimension(48, 48));
        eraser.setPreferredSize(new Dimension(48, 48));
        saveImg.setPreferredSize(new Dimension(48,48));
        
        panel.add(blackButton);
        panel.add(blueButton);
        panel.add(redButton);
        panel.add(greenButton);
        panel.add(pinkButton);
        panel.add(orangeButton);
        panel.add(eraser);
        panel.add(clearButton);
        panel.add(ticknessLabel);
        panel.add(tickness);
        panel.add(setTickness);
        panel.add(saveImg);

        content.add(panel, BorderLayout.EAST);
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new PaintMain().setVisible(true);
    }

}
