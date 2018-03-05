/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sibersohbet.camera;

import sibersohbet.komut.Goruntu;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import sibersohbet.kullanici.KullaniciEkran;
import sibersohbet.kullanici.KullaniciKontrol;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author skard
 */
public class KameraPanel extends JPanel implements Runnable {

    private Webcam webcam = null;
    KullaniciEkran cf;
    KullaniciKontrol cm;
    public boolean isStopped = false;
    Goruntu goruntu;
    public KameraPanel(KullaniciEkran cf, KullaniciKontrol cm) {
        super(new BorderLayout());
        this.cf = cf;
        this.cm = cm;

    }

    @Override
    public void run() {
        goruntu = new Goruntu(cm.kullaniciAdi, "sunucu");
        webcam = Webcam.getDefault();
        if(webcam == null) return;
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        WebcamPanel panel = new WebcamPanel(webcam);
        panel.setFPSDisplayed(true);
        panel.setDisplayDebugInfo(true);
        panel.setImageSizeDisplayed(true);
        panel.setMirrored(true);
        add(panel, BorderLayout.CENTER);
        updateUI();
        while (!isStopped) {
            try {
                goruntu.add(createBytesFromImage(webcam.getImage()));
                if (goruntu.size() >= 5) {
                    cm.komutuGonder(goruntu);
                    goruntu.clear();
                    goruntu = new Goruntu(cm.kullaniciAdi, "sunucu");
                }
                //Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private byte[] createBytesFromImage(BufferedImage img) {
        if (img == null) {
            return null;
        }
        ByteArrayOutputStream bais = new ByteArrayOutputStream();
        try {
            ImageIO.write(img, "png", bais);
            return bais.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        isStopped = true;
        if (webcam != null) {
            webcam.close();
        }
    }

}
