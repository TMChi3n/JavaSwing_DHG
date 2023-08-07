package Model;

import java.util.Date;

public class ThucPham extends KhoHang{
    private Date ngaySanXuat;
    private Date ngayHetHan;
    private String nhaCungCap;

    public ThucPham(int id, String name, int soLuongTon, double donGia, Date ngaySanXuat, Date ngayHetHan, String nhaCungCap) {
        super(id, name, soLuongTon, donGia);
        this.ngaySanXuat = ngaySanXuat;
        this.ngayHetHan = ngayHetHan;
        this.nhaCungCap = nhaCungCap;
    }

    public Date getNgayHetHan() {
        return ngayHetHan;
    }

    public Date getNgaySanXuat() {
        return ngaySanXuat;
    }

    public String getNhaCungCap() {
        return nhaCungCap;
    }


    public void setNgayHetHan(Date ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public void setNgaySanXuat(Date ngaySanXuat) {
        this.ngaySanXuat = ngaySanXuat;
    }

    public void setNhaCungCap(String nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }
    
}
