/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sibersohbet.komut;

/**
 *
 * @author skardas
 */
public class Hamle extends Komut {

    int fromX, fromY;
    int toX = -1, toY = -1;

    public Hamle(String kimden, String kime, int fromX, int fromY) {
        super(kimden, kime);
        this.fromX = fromX;
        this.fromY = fromY;
    }

    public Hamle(String kimden, String kime, int fromX, int fromY, int toX, int toY) {
        this(kimden, kime, fromX, fromY);
        this.toX = toX;
        this.toY = toY;
    }

    @Override
    public void calistir(KomutYorumla kmt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
