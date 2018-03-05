/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sibersohbet.camera;

import com.github.sarxos.webcam.WebcamPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.JPanel;

/**
 *
 * @author skard
 */
public final class OynatmaPaneli extends JPanel implements Runnable {

    private static final long serialVersionUID = 1L;

    private final Vector<BufferedImage> resimler;
    public boolean isStopped = false;
    WebcamPanel webcamPanel;

    public OynatmaPaneli(Vector<BufferedImage> images) {
        super();
        this.resimler = images;
    }

    public void add(BufferedImage resim) {
        synchronized (resimler) {
            resimler.add(resim);
        }
    }

    public BufferedImage getLast() {
        synchronized (resimler) {
            return resimler.remove(0);
        }
    }

    @Override
    public void run() {
        while (!isStopped) {
            if (resimler.size() > 0) {
                repaint();
            }

        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (resimler.size() > 0) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.drawImage(getLast(), 0, 0, null);
        }
    }
}
