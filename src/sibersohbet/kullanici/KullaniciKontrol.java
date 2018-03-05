/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sibersohbet.kullanici;

import sibersohbet.komut.Komut;
import sibersohbet.komut.Login;
import sibersohbet.komut.Mesaj;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import sibersohbet.komut.KomutYorumla;

/**
 *
 * @author skardas
 */
public class KullaniciKontrol extends Thread {

    String sunucuIP;
    int sunucuPort;
    ObjectOutputStream cikis;
    ObjectInputStream giris;
    boolean isStopped = false;
    KullaniciEkran ekran;
    public String kullaniciAdi;

    KomutYorumla yorumlayici;

    public KullaniciKontrol(KullaniciEkran frame, String sunucuIP, int sunucuPort, String username) throws IOException {
        this.ekran = frame;
        this.sunucuIP = sunucuIP;
        this.sunucuPort = sunucuPort;
        this.kullaniciAdi = username;
        yorumlayici = new KullaniciKomutYorumla(this);

        Socket socket = new Socket(sunucuIP, sunucuPort);
        this.cikis
                = new ObjectOutputStream(socket.getOutputStream());
        this.giris = new ObjectInputStream(socket.getInputStream());
        komutuGonder(new Login(username, "test"));
        frame.addMessage(new Mesaj("Bağlantı", null, "Sunucuya bağlanıldı"));
        frame.startCamera(this);
    }

    @Override
    public void run() {
        Komut komut;
        try {
            while (!isStopped) {
                komut = (Komut) giris.readObject();
                komut.calistir(yorumlayici);

            }
        } catch (Exception e) {
        }
    }

    public void komutuGonder(Komut komut) throws IOException {
        cikis.writeObject(komut);
        cikis.flush();
    }

}
