/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockplayer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Meluleki
 */
public class Welcome implements KeyListener, ActionListener {

    JFrame frame;
    JPanel panel;
    JLabel l;

    Welcome() {

        frame = new JFrame("Block Breaker");
        panel = new JPanel();
        l = new JLabel(new ImageIcon("Welcome.png"));
        l.addKeyListener(this);
        l.setFocusable(true);
        setUpGUI();
    }

    void setUpGUI() {;
        panel.setBackground(new Color(0, 162, 232));
        panel.add(l);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        frame.setSize(490, 600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Welcome();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            Main m = new Main();
           
            frame.setVisible(false);
            l.setFocusable(false);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
