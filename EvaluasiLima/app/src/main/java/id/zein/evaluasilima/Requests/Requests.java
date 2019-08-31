package id.zein.evaluasilima.Requests;

import java.io.Serializable;

public class Requests implements Serializable {

    private String nama;
    private String nohp;
    private String divisi;

    private String key;

    public Requests(){

    }

    public Requests(String nm, String nohape, String divici){
        nama = nm;
        nohp = nohape;
        divisi = divici;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getDivisi() {
        return divisi;
    }

    public void setDivisi(String divisi) {
        this.divisi = divisi;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString(){
        return " "+ nama + "\n" +
                " "+ nohp + "\n" +
                 " "+ divisi;

    }

}
