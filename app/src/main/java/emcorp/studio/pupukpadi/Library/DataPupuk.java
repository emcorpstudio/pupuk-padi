package emcorp.studio.pupukpadi.Library;

/**
 * Created by ASUS on 27/01/2016.
 */
public class DataPupuk {

    private String DATA_PANJANG;
    private String DATA_LEBAR;
    private String DATA_LUAS;
    private String DATA_HEKTAR;
    private String DATA_FUZZY;
    private String DATA_PUPUK;
    private String DATA_TANGGAL;

    public DataPupuk(String DATA_PANJANG,String DATA_LEBAR,String DATA_LUAS,String DATA_HEKTAR,String DATA_FUZZY,String DATA_PUPUK,String DATA_TANGGAL) {
        this.DATA_PANJANG = DATA_PANJANG;
        this.DATA_LEBAR = DATA_LEBAR;
        this.DATA_LUAS = DATA_LUAS;
        this.DATA_HEKTAR = DATA_HEKTAR;
        this.DATA_FUZZY = DATA_FUZZY;
        this.DATA_PUPUK = DATA_PUPUK;
        this.DATA_TANGGAL = DATA_TANGGAL;
    }

    public void setDATA_PANJANG(String DATA_PANJANG){
        this.DATA_PANJANG = DATA_PANJANG;
    }
    public void setDATA_LEBAR(String DATA_LEBAR){
        this.DATA_LEBAR = DATA_LEBAR;
    }
    public void setDATA_LUAS(String DATA_LUAS){
        this.DATA_LUAS = DATA_LUAS;
    }
    public void setDATA_HEKTAR(String DATA_HEKTAR){
        this.DATA_HEKTAR = DATA_HEKTAR;
    }
    public void setDATA_FUZZY(String DATA_FUZZY){
        this.DATA_FUZZY = DATA_FUZZY;
    }
    public void setDATA_PUPUK(String DATA_PUPUK){
        this.DATA_PUPUK = DATA_PUPUK;
    }
    public void setDATA_TANGGAL(String DATA_TANGGAL){
        this.DATA_TANGGAL = DATA_TANGGAL;
    }

    public String getDATA_PANJANG(){
        return this.DATA_PANJANG;
    }
    public String getDATA_LEBAR(){
        return this.DATA_LEBAR;
    }
    public String getDATA_LUAS(){
        return this.DATA_LUAS;
    }
    public String getDATA_HEKTAR(){
        return this.DATA_HEKTAR;
    }
    public String getDATA_FUZZY(){
        return this.DATA_FUZZY;
    }
    public String getDATA_PUPUK(){
        return this.DATA_PUPUK;
    }
    public String getDATA_TANGGAL(){
        return this.DATA_TANGGAL;
    }
}
