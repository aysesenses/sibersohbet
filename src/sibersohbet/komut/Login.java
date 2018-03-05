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
public class Login extends Komut {

    public String kullaniciAdi;
    public String parola;

    public Login(String kullaniciAdi, String parola) {
        super(null, null);
        this.kullaniciAdi = kullaniciAdi;
        this.parola = parola;
    }

    @Override
    public void calistir(KomutYorumla kmt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
