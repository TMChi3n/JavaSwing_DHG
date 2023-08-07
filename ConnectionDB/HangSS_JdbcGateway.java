package ConnectionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Model.SanhSu;

public class HangSS_JdbcGateway{
    private Connection connection;

    public HangSS_JdbcGateway() {
        String db = "jdbc:sqlserver://localhost:1433;databaseName=Goods;trustServerCertificate=true";
        String username = "sa";
        String password = "123456789";
        try {
            connection = DriverManager.getConnection(db, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addSS(SanhSu sanhSu) {
        String sql = "INSERT INTO SanhSu (id, name, quantityOnHand, unitPrice, manufacturerSS, importDate)" 
        + "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, sanhSu.getId());
            statement.setString(2, sanhSu.getName());
            statement.setInt(3, sanhSu.getSoLuongTon());
            statement.setInt(4, (int) sanhSu.getDonGia());
            statement.setString(5, sanhSu.getNhaSanXuat());
            statement.setDate(6, new java.sql.Date(sanhSu.getNgayNhapKho().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateSS(SanhSu sanhSu) {
        String sql = "UPDATE SanhSu SET id = ?, name = ?, quantityOnHand = ?, unitPrice = ?, manufacturerSS = ?, importDate = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, sanhSu.getId());
            statement.setString(2, sanhSu.getName());
            statement.setInt(3, sanhSu.getSoLuongTon());
            statement.setInt(4, (int) sanhSu.getDonGia());
            statement.setString(5, sanhSu.getNhaSanXuat());
            statement.setDate(6, new java.sql.Date(sanhSu.getNgayNhapKho().getTime()));
            statement.setInt(7, sanhSu.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSS(int idSS) {
        String sql = "DELETE FROM SanhSu WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idSS);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public SanhSu getSSById(int id) {
        String sql = "SELECT * FROM SanhSu WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
    
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    int soLuongTon = resultSet.getInt("quantityOnHand");
                    double donGia = resultSet.getDouble("unitPrice");
                    String nhaSanXuat = resultSet.getString("manufacturerSS");
                    Date ngayNhapKho = resultSet.getDate("importDate");
    
                    return new SanhSu(id, name, soLuongTon, donGia, nhaSanXuat, ngayNhapKho);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // If the item with the given ID is not found
    }

    public List<SanhSu> getAllSS() {
        List<SanhSu> sanhSu = new ArrayList<>();
        String sql = "SELECT * FROM SanhSu";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int soLuongTon = resultSet.getInt("quantityOnHand");
                double donGia = resultSet.getDouble("unitPrice");
                String nhaSanXuat = resultSet.getString("manufacturerSS");
                Date ngayNhapKho = resultSet.getDate("importDate");

                SanhSu sanh_su = new SanhSu(id, name, soLuongTon, donGia, nhaSanXuat, ngayNhapKho);
                sanhSu.add(sanh_su);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sanhSu;
    }

}
