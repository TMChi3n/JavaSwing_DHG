package Model;

import java.util.Date;

public class SanhSu extends KhoHang{
    private String nhaSanXuat;
    private Date ngayNhapKho;

    public SanhSu(int id, String name, int soLuongTon, double donGia, String nhaSanXuat, Date ngayNhapKho){
        super(id, name, soLuongTon, donGia);
        this.nhaSanXuat = nhaSanXuat;
        this.ngayNhapKho = ngayNhapKho;
    }

    public Date getNgayNhapKho() {
        return ngayNhapKho;
    }

    public String getNhaSanXuat() {
        return nhaSanXuat;
    }
    

    public void setNgayNhapKho(Date ngayNhapKho) {
        this.ngayNhapKho = ngayNhapKho;
    }

    public void setNhaSanXuat(String nhaSanXuat) {
        this.nhaSanXuat = nhaSanXuat;
    }

}
