/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sibersohbet.sunucu;

import sibersohbet.komut.*;
 
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author skardas
 */
public class SunucuKontrol extends Thread {

    boolean isStopped = false;
    KomutYorumla yorumlayici;
    int sunucuPort;
    protected ServerSocket serverSocket = null;
    protected ExecutorService threadHavuzu = Executors.newFixedThreadPool(10);
    SunucuEkran sunucuEkran;
    HashMap<String, Kullanici> clients = new HashMap<>();

    public SunucuKontrol(SunucuEkran sunucuEkran, int sunucuPort) {
        this.sunucuEkran = sunucuEkran;
        this.sunucuPort = sunucuPort;
        yorumlayici = new SunucuKomutYorumla(this);
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(sunucuPort);
            sunucuEkran.addMessage("Sunucu dinlemeye başladı...");
            sunucuEkran.addMessage("Hostname:" + serverSocket.getInetAddress().getHostName());

        } catch (Exception e) {
            sunucuEkran.addMessage(e.getMessage());

        }
        while (!isStopped()) {
            try {
                Socket socket = serverSocket.accept();
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                String username = ((Login) in.readObject()).kullaniciAdi;
                sunucuEkran.addMessage(username + " is connected...");
                Kullanici client = new Kullanici(username, out, in);
                clients.put(username, client);
                this.threadHavuzu.execute(client);
            } catch (Exception e) {
                sunucuEkran.addMessage(e.getMessage());
            }
        }
        this.threadHavuzu.shutdown();
        sunucuEkran.addMessage("Sunucu durduruldu.");
    }

    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public void stopServer() {
        this.isStopped = true;
        try {
            threadHavuzu.shutdownNow();
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    synchronized void komutuHerkeseGonder(Komut komut) throws IOException {
        for (Iterator<Kullanici> it = clients.values().iterator(); it.hasNext();) {
            Kullanici client = it.next();
            client.komutuGonder(komut);
        }
    }

    synchronized void komutuBenHaricHerkeseGonder(Komut komut) throws IOException {
        for (Iterator<Kullanici> it = clients.values().iterator(); it.hasNext();) {
            Kullanici client = it.next();
            if (!client.kullaniciAdi.equalsIgnoreCase(komut.kimden)) {
                client.komutuGonder(komut);
            }
        }
    }

    void goruntuEkle(Goruntu goruntu) {
        sunucuEkran.addGoruntu(goruntu);
    }

    private class Kullanici implements Runnable {

        boolean isClosed = false;
        ObjectOutputStream cikis;
        ObjectInputStream giris;
        String kullaniciAdi;

        public Kullanici(String kullaniciAdi, ObjectOutputStream cikis, ObjectInputStream giris) throws IOException {
            this.giris = giris;
            this.cikis = cikis;
            this.kullaniciAdi = kullaniciAdi;
        }

        @Override
        public void run() {
            Object komut = null;
            try {
                while ((komut = giris.readObject()) != null) {
                    ((Komut) komut).calistir(yorumlayici);
                }
            } catch (Exception e) {

            }
        }

        public void close() {
            try {
                giris.close();
                cikis.close();
            } catch (Exception e) {
            }
        }

        public void komutuGonder(Komut komut) throws IOException {
            cikis.writeObject(komut);
            cikis.flush();
        }

    }

}
