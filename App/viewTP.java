package App;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import ConnectionDB.HangTP_JdbcGateway;
import Model.ThucPham;

public class viewTP extends JFrame {

    private DefaultTableModel tableModel;
    private JTable table;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton findButton;
    private JButton backButton;
    private JButton sumOfTP;
    private JTextField idTextField;
    private JTextField tenHangTextField;
    private JTextField soLuongTonTextField;
    private JTextField donGiaTextField;
    private JTextField ngaySanXuatTextField;
    private JTextField ngayHetHanTextField;
    private JTextField nhaCungCapTextField;

    private HangTP_JdbcGateway jdbcRemote;

    public viewTP(HangTP_JdbcGateway jdbcRemote) {

        this.jdbcRemote = jdbcRemote;

        setTitle("Thực phẩm");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Table
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Tên hàng");
        tableModel.addColumn("Số lượng tồn");
        tableModel.addColumn("Đơn giá");
        tableModel.addColumn("Nhà cung cấp");
        tableModel.addColumn("Ngày sản xuất");
        tableModel.addColumn("Ngày hết hạn");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // TextField
        JPanel inputPanel = new JPanel(new GridLayout(15, 2));
        idTextField = new JTextField();
        tenHangTextField = new JTextField();
        soLuongTonTextField = new JTextField();
        donGiaTextField = new JTextField();
        nhaCungCapTextField = new JTextField();
        ngaySanXuatTextField = new JTextField();
        ngayHetHanTextField = new JTextField();

        // Button
        addButton = new JButton("Thêm");
        updateButton = new JButton("Sửa");
        deleteButton = new JButton("Xóa");
        findButton = new JButton("Tìm kiếm");
        backButton = new JButton("<< Quay trở lại trang chủ");
        sumOfTP = new JButton("Tổng số lượng hàng hóa của thực phẩm");

        // Add
        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idTextField);
        inputPanel.add(new JLabel("Tên hàng:"));
        inputPanel.add(tenHangTextField);
        inputPanel.add(new JLabel("Số lượng tồn:"));
        inputPanel.add(soLuongTonTextField);
        inputPanel.add(new JLabel("Đơn giá:"));
        inputPanel.add(donGiaTextField);
        inputPanel.add(new JLabel("Ngày sản xuất (Thực phẩm):"));
        inputPanel.add(ngaySanXuatTextField);
        inputPanel.add(new JLabel("Ngày hết hạn (Thực phẩm):"));
        inputPanel.add(ngayHetHanTextField);
        inputPanel.add(new JLabel("Nhà cung cấp (Thực phẩm):"));
        inputPanel.add(nhaCungCapTextField);

        inputPanel.add(addButton);
        inputPanel.add(updateButton);
        inputPanel.add(deleteButton);
        inputPanel.add(findButton);
        inputPanel.add(backButton);
        inputPanel.add(sumOfTP);

        add(inputPanel, BorderLayout.SOUTH);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                backPage();
            }
        });

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addItems();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateItems();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteItems();
            }
        });

        findButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                findItems();
            }
        });

        sumOfTP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sumTP();
            }
        });

        loadItems();
    }

    // LOAD ITEMS
    private void loadItems() {
        List<ThucPham> thucPhamRemote = jdbcRemote.getAllTP();
        tableModel.setRowCount(0);
        for (ThucPham thuc_pham : thucPhamRemote) {
            Object[] rowData = { thuc_pham.getId(), thuc_pham.getName(), thuc_pham.getSoLuongTon(),
                    thuc_pham.getDonGia(), thuc_pham.getNhaCungCap(), thuc_pham.getNgaySanXuat(),
                    thuc_pham.getNgayHetHan() };
            tableModel.addRow(rowData);

        }
    }

    // ADD ITEMS
    private void addItems() {
        int id = Integer.parseInt(idTextField.getText());
        String name = tenHangTextField.getText();
        int soLuongTon = Integer.parseInt(soLuongTonTextField.getText());
        double donGia = Double.parseDouble(donGiaTextField.getText());
        Date ngaySanXuat = parseDate(ngaySanXuatTextField.getText());
        Date ngayHetHan = parseDate(ngayHetHanTextField.getText());
        String nhaCungCap = nhaCungCapTextField.getText();

        if (soLuongTon < 0 || donGia <= 0) {
            JOptionPane.showMessageDialog(this, "Lỗi. Vui lòng nhập lại cho đúng thông tin");
            clearFieldS();
        } else {
            ThucPham thucPhamRemote = new ThucPham(id, name, soLuongTon, donGia, ngaySanXuat, ngayHetHan, nhaCungCap);
            jdbcRemote.addTP(thucPhamRemote);

            loadItems();
            clearFieldS();
        }

    }

    // UPDATE ITEMS
    private void updateItems() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn thực phẩm trên bảng để cập nhật");
        }

        int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn cập nhật hàng?", "Xác nhận cập nhật hàng", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            int id = Integer.parseInt(idTextField.getText());
            String name = tenHangTextField.getText();
            int soLuongTon = Integer.parseInt(soLuongTonTextField.getText());
            double donGia = Double.parseDouble(donGiaTextField.getText());
            Date ngaySanXuat = parseDate(ngaySanXuatTextField.getText());
            Date ngayHetHan = parseDate(ngayHetHanTextField.getText());
            String nhaCungCap = nhaCungCapTextField.getText();

            if (soLuongTon < 0 || donGia <= 0) {
                JOptionPane.showMessageDialog(this, "Lỗi. Vui lòng nhập lại cho đúng thông tin");
                clearFieldS();
            } else {
                ThucPham thucPhamRemote = new ThucPham(id, name, soLuongTon, donGia, ngaySanXuat, ngayHetHan, nhaCungCap);
                jdbcRemote.updateTP(thucPhamRemote);

                loadItems();
                clearFieldS();
            }
            clearFieldS();
        }    

    }

    // DELETE ITEMS
    private void deleteItems() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn thực phẩm trên bảng để xóa");
            return;
        }

        int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa hàng?", "Xác nhận xóa hàng", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            int id = (int) tableModel.getValueAt(row, 0);
            jdbcRemote.deleteTP(id);
            loadItems();
        }

    }

    // FIND ITEMS BY WEEK
    private void findItems() {
        String input = JOptionPane.showInputDialog(this, "Nhập ID sản phẩm cần tìm:");
        if (input == null) {
            return; // User canceled the input
        }

        try {
            int id = Integer.parseInt(input);
            ThucPham foundItem = jdbcRemote.getTPById(id);

            if (foundItem != null) {
                // Display the details of the found item
                JOptionPane.showMessageDialog(this,
                        "Sản phẩm ID: " + foundItem.getId() +
                        "\nTên hàng: " + foundItem.getName() +
                        "\nSố lượng tồn: " + foundItem.getSoLuongTon() +
                        "\nĐơn giá: " + foundItem.getDonGia() +
                        "\nNhà cung cấp: " + foundItem.getNhaCungCap() +
                        "\nNgày sản xuất: " + foundItem.getNgaySanXuat() +
                        "\nNgày hết hạn: " + foundItem.getNgayHetHan());
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm với ID: " + id);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số ID hợp lệ.");
        }

    }

    // CONVERT DATE
    private Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        try {
            // Chuyển đổi đối tượng chuỗi thành ngày
            Date date = dateFormat.parse(dateString);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Vui lòng nhập cho đúng định dạng yyyy/MM/dd");
        }

        return null; // Return null if the parsing fails
    }

    // CLEAR FIELDS WHEN ADD SUCCESS
    private void clearFieldS() {
        idTextField.setText("");
        tenHangTextField.setText("");
        soLuongTonTextField.setText("");
        donGiaTextField.setText("");
        ngaySanXuatTextField.setText("");
        ngayHetHanTextField.setText("");
        nhaCungCapTextField.setText("");
    }

    // BACK VIEW PAGE
    private void backPage() {
        JFrame viewPage = new ManagementApp();
        viewPage.setLocationRelativeTo(null);
        viewPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        viewPage.setVisible(true);
        dispose();
    }

    private void sumTP() {
        Map<String, Integer> typeQuantityMap = new HashMap<>();

        // Lặp lại danh sách các đối tượng KhoHang và tính tổng số lượng cho từng loại
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String type = (String) tableModel.getValueAt(i, 1); // Assuming the type is in the second column (index 1)
            int quantity = (int) tableModel.getValueAt(i, 2); // Assuming the quantity is in the third column (index 2)

            // Kiểm tra xem loại đã có chưa the map, if yes, update the total quantity, nếu
            // không thì, add it to the map
            typeQuantityMap.put(type, typeQuantityMap.getOrDefault(type, 0) + quantity);
        }

        // Tạo một chuỗi để hiển thị kết quả trong hộp thoại
        StringBuilder result = new StringBuilder();
        result.append("Tổng số lượng hàng hóa của thực phẩm:\n");
        for (Map.Entry<String, Integer> entry : typeQuantityMap.entrySet()) {
            result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        // Hiển thị kết quả trong hộp thoại
        JOptionPane.showMessageDialog(this, result.toString());
    }

}
