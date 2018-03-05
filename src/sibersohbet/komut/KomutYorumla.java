/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sibersohbet.komut;

import java.io.Serializable;

/**
 *
 * @author skard
 */
public interface KomutYorumla extends Serializable {

    public void loginKomutuYönet(Login login);

    public void sesKomutuYönet(Ses ses);

    public void goruntuKomutuYönet(Goruntu goruntu);

    public void loginKomutuYönet(Dosya dosya);

    public void mesajKomutuYönet(Mesaj mesaj);

    public void hamleKomutuYönet(Hamle hamle);

}
