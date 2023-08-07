package Model;

public class DienMay extends KhoHang{
    private int baoHanh;
    private double congSuat;

    public DienMay(int id, String name, int soLuongTon, double donGia, int baoHanh, double congSuat){
        super(id, name, soLuongTon, donGia);
        this.baoHanh = baoHanh;
        this.congSuat = congSuat;
    }

    public int getBaoHanh() {
        return baoHanh;
    }

    public double getCongSuat() {
        return congSuat;
    }

    public void setBaoHanh(int baoHanh) {
        this.baoHanh = baoHanh;
    }

    public void setCongSuat(double congSuat) {
        this.congSuat = congSuat;
    }

}
