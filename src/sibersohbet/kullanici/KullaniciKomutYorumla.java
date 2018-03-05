/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sibersohbet.kullanici;

import sibersohbet.komut.Dosya;
import sibersohbet.komut.Goruntu;
import sibersohbet.komut.Hamle;
import sibersohbet.komut.Login;
import sibersohbet.komut.Mesaj;
import sibersohbet.komut.Ses;
import sibersohbet.komut.KomutYorumla;

/**
 *
 * @author skard
 */
public class KullaniciKomutYorumla implements KomutYorumla {

    KullaniciKontrol manager;

    public KullaniciKomutYorumla(KullaniciKontrol manager) {
        this.manager = manager;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loginKomutuYönet(Dosya dosya) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mesajKomutuYönet(Mesaj mesaj) {
        manager.ekran.addMessage(mesaj);
    }

    @Override
    public void hamleKomutuYönet(Hamle hamle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
