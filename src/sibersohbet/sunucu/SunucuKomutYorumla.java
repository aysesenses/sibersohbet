/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sibersohbet.sunucu;

import sibersohbet.komut.*;
import sibersohbet.komut.Goruntu;

import java.io.IOException;

/**
 *
 * @author skard
 */
public class SunucuKomutYorumla implements KomutYorumla {

    SunucuKontrol sunucu;

    public SunucuKomutYorumla(SunucuKontrol sunucu) {
        this.sunucu = sunucu;
    }

    @Override
    public void loginKomutuYönet(Login login) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sesKomutuYönet(Ses ses) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void goruntuKomutuYönet(Goruntu goruntu) {
        sunucu.goruntuEkle(goruntu);

    }

    @Override
    public void loginKomutuYönet(Dosya dosya) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mesajKomutuYönet(Mesaj mesaj) {
        try {
            sunucu.komutuBenHaricHerkeseGonder(mesaj);
            sunucu.sunucuEkran.addMessage(mesaj.toString());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void hamleKomutuYönet(Hamle hamle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
