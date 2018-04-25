/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockplayer;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author Meluleki
 */
class BlockBreakerPanel extends JPanel implements KeyListener {

    ArrayList<Block> blocks = new ArrayList<>();
    ArrayList<Block> ball = new ArrayList<>();
    ArrayList<Block> powerup = new ArrayList<>();
    Block paddle;
    Thread thread;
    Animate animate;

    public BlockBreakerPanel() {
        paddle = new Block(175, 480, 150, 25, "paddle.png");
        animate = new Animate(this);
        thread = new Thread(animate);
        for (int i = 0; i < 8; i++) {
            blocks.add(new Block(i * 60 + 2, 0, 60, 25, "blue.png"));
        }
        for (int i = 0; i < 8; i++) {
            blocks.add(new Block(i * 60 + 2, 25, 60, 25, "red.png"));
        }
        for (int i = 0; i < 8; i++) {
            blocks.add(new Block(i * 60 + 2, 50, 60, 25, "green.png"));
        }
        for (int i = 0; i < 8; i++) {
            blocks.add(new Block(i * 60 + 2, 75, 60, 25, "yellow.png"));
        }
        ball.add(new Block(237, 437, 25, 25, "ball.png"));
        Random r = new Random();
        blocks.get(r.nextInt(32)).powerup = true;
        blocks.get(r.nextInt(32)).powerup = true;
        blocks.get(r.nextInt(32)).powerup = true;
        blocks.get(r.nextInt(32)).powerup = true;
        blocks.get(r.nextInt(32)).powerup = true;
        addKeyListener(this);
        setFocusable(true);
        requestFocus();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Block b : blocks) {
            b.draw(g, this);
        }
        for (Block b : ball) {
            b.draw(g, this);
        }
        for (Block b : powerup) {
            b.draw(g, this);
        }
        paddle.draw(g, this);
    }

    void update() {
        for (Block p : powerup) {
            p.y += 1;
            if (p.intersects(paddle) && !p.destroyed) {
                p.destroyed = true;
                ball.add(new Block(paddle.dx + 75, 437, 25, 25, "ball.png"));
            }
        }
        for (Block ba : ball) {
            for (Block bal : ball) {
                if (bal != ba) {
                    if (ba.intersects(bal)) {
                        ba.dx *= -1;
                        bal.dx *= -1;
                        ba.dy *= -1;
                        bal.dy *= -1;
                    }
                }
            }
            ba.x += ba.dx;
            if (ba.x >= (getWidth() - ba.width) || (ba.x <= 0)) {
                ba.dx *= -1;
            }
            if (ba.intersects(paddle) || (ba.y <= 0)) {
                ba.dy *= -1;
            }
            ba.y += ba.dy;
            for (Block b : blocks) {
                if ((b.left.intersects(ba) || b.right.intersects(ba)) && b.destroyed == false) {
                    ba.dx *= -1;
                    b.destroyed = true;
                    if (b.powerup) {
                        powerup.add(new Block(b.x, b.y, 25, 19, "extra.png"));
                        b.powerup = false;
                    }
                } else if (ba.intersects(b) && b.destroyed == false) {
                    b.destroyed = true;
                    ba.dy *= -1;
                    if (b.powerup) {
                        powerup.add(new Block(b.x, b.y, 25, 19, "extra.png"));
                    }
                }

            }
            if(ba.y>paddle.y&&(!ba.destroyed)){
                if(ball.size()==1){
                    ba.x=237;
                    ba.y=437;
                    animate.pause=true;
                    ba.dx*=-1;
                    ba.dy*=-1;
                }else{
                    ba.destroyed=true;
                    ba.dx=0;
                    ba.dy=0;
                }
            }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (KeyEvent.VK_ENTER==e.getKeyCode()) {
            if (!animate.started) {
                animate.started = true;
                thread.start();
            } else {
                animate.pause = !animate.pause;
            }
        }

        if (e.getKeyCode()
                == KeyEvent.VK_LEFT) {
            paddle.x -= (paddle.x > 0) ? 20 : 0;

        }

        if (e.getKeyCode()
                == KeyEvent.VK_RIGHT) {

            paddle.x += (paddle.x < (getWidth() - paddle.width)) ? 20 : 0;
        }
    }

    @Override
    public void keyReleased(KeyEvent e
    ) {

    }
}
