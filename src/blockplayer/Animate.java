/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockplayer;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Meluleki
 */
public class Animate implements Runnable {

    BlockBreakerPanel bp;
    boolean pause = false;
    boolean started=false;
    public Animate(BlockBreakerPanel b) {
        this.bp = b;
    }

    @Override
    public void run() {
        while (true) {
            if (!pause) {
                bp.update();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Animate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
