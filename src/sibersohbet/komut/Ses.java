/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sibersohbet.komut;

/**
 *
 * @author skard
 */
public class Ses extends Komut {

    byte[] data;

    public Ses(String kimden, String kime, byte[] data) {
        super(kimden, kime);
        this.data = data;
    }

    @Override
    public void calistir(KomutYorumla kmt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
