package ConnectionDB;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.DienMay;

public class HangDM_JdbcGateway{

    private Connection connection;


    public HangDM_JdbcGateway() {
        String db = "jdbc:sqlserver://localhost:1433;databaseName=Goods;trustServerCertificate=true";
        String username = "sa";
        String password = "123456789";
        try {
            connection = DriverManager.getConnection(db, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addDM(DienMay dienMay) {
        String sql = "INSERT INTO DienMay (id, name, quantityOnHand, unitPrice, warrantyMonthsDM, powerKwDM)" 
        + "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, dienMay.getId());
            statement.setString(2, dienMay.getName());
            statement.setInt(3, dienMay.getSoLuongTon());
            statement.setInt(4, (int) dienMay.getDonGia());
            statement.setInt(5, dienMay.getBaoHanh());
            statement.setInt(6, (int) dienMay.getCongSuat());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDM(DienMay dienMay) {
        String sql = "UPDATE DienMay SET id = ?, name = ?, quantityOnHand = ?, unitPrice = ?, warrantyMonthsDM = ?, powerKwDM = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, dienMay.getId());
            statement.setString(2, dienMay.getName());
            statement.setInt(3, dienMay.getSoLuongTon());
            statement.setInt(4, (int) dienMay.getDonGia());
            statement.setInt(5, dienMay.getBaoHanh());
            statement.setInt(6, (int) dienMay.getCongSuat());
            statement.setInt(7, dienMay.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDM(int idDM) {
        String sql = "DELETE FROM DienMay WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idDM);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DienMay getDMById(int id) {
        String sql = "SELECT * FROM DienMay WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
    
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    int soLuongTon = resultSet.getInt("quantityOnHand");
                    double donGia = resultSet.getDouble("unitPrice");
                    int baoHanh = resultSet.getInt("warrantyMonthsDM");
                    double congSuat = resultSet.getDouble("powerKwDM");
    
                    return new DienMay(id, name, soLuongTon, donGia, baoHanh, congSuat);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // If the item with the given ID is not found
    }

    public List<DienMay> getAllDM() {
        List<DienMay> dienMay = new ArrayList<>();
        String sql = "SELECT * FROM DienMay";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int soLuongTon = resultSet.getInt("quantityOnHand");
                double donGia = resultSet.getDouble("unitPrice");
                int baoHanh = resultSet.getInt("warrantyMonthsDM");
                double congSuat = resultSet.getDouble("powerKwDM");

                DienMay dien_may = new DienMay(id, name, soLuongTon, donGia, baoHanh, congSuat);
                dienMay.add(dien_may);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dienMay;
    }
    
}
