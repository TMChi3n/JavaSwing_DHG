
package Model;

public class KhoHang{
    protected int id;
    protected String name;
    protected int soLuongTon;
    protected double donGia;

    public KhoHang(int id, String name, int soLuongTon, double donGia){
        this.id = id;
        this.name = name;
        this.soLuongTon = soLuongTon;
        this.donGia = donGia;
    }

    public double getDonGia() {
        return donGia;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }
}
