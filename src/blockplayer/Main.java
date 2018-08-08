/*
 * To change this lice,se header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockplayer;

import javax.swing.JFrame;

/**
 *
 * @author Meluleki
 */
public class Main {

    public static void main(String[] args) {
	JFrame frame = new JFrame("Block Breaker");
	BlockBreakerPanel panel = new BlockBreakerPanel();
	panel.repaint();
	frame.getContentPane().add(panel);
	frame.setVisible(true);
	frame.setSize(490, 600);
	frame.setResizable(false);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
