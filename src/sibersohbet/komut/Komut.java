/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sibersohbet.komut;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author skard
 */
public abstract class Komut implements Serializable {

    public String kimden;
    public String kime;
    public Date zaman;

    public Komut(String kimden, String kime) {
        this.kimden = kimden;
        this.kime = kime;
        zaman = new Date();
    }

    public abstract void calistir(KomutYorumla kmt);
}
