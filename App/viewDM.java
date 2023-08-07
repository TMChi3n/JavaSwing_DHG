package App;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import ConnectionDB.HangDM_JdbcGateway;
import Model.DienMay;
import Model.ThucPham;


public class viewDM extends JFrame{

    private DefaultTableModel tableModel;
    private JTable table;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton backButton;
    private JButton findButton;
    private JButton sumOfDM;
    private JTextField idTextField;
    private JTextField tenHangTextField;
    private JTextField soLuongTonTextField;
    private JTextField donGiaTextField;
    private JTextField baoHanhTextField;
    private JTextField congSuatTextField;

    private HangDM_JdbcGateway jdbcRemote;

    public viewDM(HangDM_JdbcGateway jdbcRemote) {

        this.jdbcRemote = jdbcRemote;

        setTitle("Điện máy");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Table
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Tên hàng");
        tableModel.addColumn("Số lượng tồn");
        tableModel.addColumn("Đơn giá");
        tableModel.addColumn("Bảo hành");
        tableModel.addColumn("Công suất");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // TextField
        JPanel inputPanel = new JPanel(new GridLayout(15, 2));
        idTextField = new JTextField();
        tenHangTextField = new JTextField();
        soLuongTonTextField = new JTextField();
        donGiaTextField = new JTextField();
        baoHanhTextField = new JTextField();
        congSuatTextField = new JTextField();

        // Button
        addButton = new JButton("Thêm");
        updateButton = new JButton("Sửa");
        deleteButton = new JButton("Xóa");
        findButton = new JButton("Tìm kiếm");
        backButton = new JButton("<< Quay trở lại trang chủ");
        sumOfDM = new JButton("Tổng số lượng hàng hóa của điện máy");

        // Add
        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idTextField);
        inputPanel.add(new JLabel("Tên hàng:"));
        inputPanel.add(tenHangTextField);
        inputPanel.add(new JLabel("Số lượng tồn:"));
        inputPanel.add(soLuongTonTextField);
        inputPanel.add(new JLabel("Đơn giá:"));
        inputPanel.add(donGiaTextField);
        inputPanel.add(new JLabel("Bảo hành:"));
        inputPanel.add(baoHanhTextField);
        inputPanel.add(new JLabel("Công suất:"));
        inputPanel.add(congSuatTextField);

        inputPanel.add(addButton);
        inputPanel.add(updateButton);
        inputPanel.add(deleteButton);
        inputPanel.add(findButton);
        inputPanel.add(backButton);
        inputPanel.add(sumOfDM);

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


        sumOfDM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sumDM();
            }
        });

        loadItems();

    }

    // LOAD ITEMS   
    private void loadItems() {
        List<DienMay> dienMayRemote = jdbcRemote.getAllDM();
        tableModel.setRowCount(0);
        for(DienMay dien_may : dienMayRemote) {
            Object[] rowData = {dien_may.getId(), dien_may.getName(), dien_may.getSoLuongTon(), dien_may.getDonGia(), dien_may.getBaoHanh(), dien_may.getCongSuat()};
            tableModel.addRow(rowData);
        }
    }

    private void addItems() {
        int id = Integer.parseInt(idTextField.getText());
        String name = tenHangTextField.getText();
        int soLuongTon = Integer.parseInt(soLuongTonTextField.getText());
        double donGia = Double.parseDouble(donGiaTextField.getText());
        int baoHanh = Integer.parseInt(baoHanhTextField.getText());
        double congSuat = Double.parseDouble(congSuatTextField.getText());

        if (soLuongTon < 0 || donGia <= 0 || baoHanh <= 0 || congSuat < 0) {
            JOptionPane.showMessageDialog(this, "Lỗi. Vui lòng nhập lại cho đúng thông tin");
            clearFieldS();
        } else {
            DienMay dienMayRemote = new DienMay(id, name, soLuongTon, donGia, baoHanh, congSuat);
            jdbcRemote.addDM(dienMayRemote);

            loadItems();
            clearFieldS();
        }      
            
    }
    

    private void updateItems() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn hàng trên bảng để cập nhật");
            return;
        }
    
        int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn cập nhật hàng?", "Xác nhận cập nhật hàng", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            int id = Integer.parseInt(idTextField.getText());
            String name = tenHangTextField.getText();
            int soLuongTon = Integer.parseInt(soLuongTonTextField.getText());
            double donGia = Double.parseDouble(donGiaTextField.getText());
            int baoHanh = Integer.parseInt(baoHanhTextField.getText());
            double congSuat = Double.parseDouble(congSuatTextField.getText());
    
            if (soLuongTon < 0 || donGia <= 0 || baoHanh <= 0 || congSuat < 0) {
                JOptionPane.showMessageDialog(this, "Lỗi. Vui lòng nhập lại cho đúng thông tin");
                clearFieldS();
            } else {
                DienMay dienMay = new DienMay(id, name, soLuongTon, donGia, baoHanh, congSuat);
                jdbcRemote.updateDM(dienMay);
    
                loadItems();
                clearFieldS();
                
            }
            clearFieldS();
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
            DienMay foundItem = jdbcRemote.getDMById(id);

            if (foundItem != null) {
                // Display the details of the found item
                JOptionPane.showMessageDialog(this,
                        "Sản phẩm ID: " + foundItem.getId() +
                        "\nTên hàng: " + foundItem.getName() +
                        "\nSố lượng tồn: " + foundItem.getSoLuongTon() +
                        "\nĐơn giá: " + foundItem.getDonGia() +
                        "\nBảo hành: " + foundItem.getBaoHanh() +
                        "\nCông suất: " + foundItem.getCongSuat());
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm với ID: " + id);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số ID hợp lệ.");
        }

    }
    

    private void deleteItems() {
        int row = table.getSelectedRow();
        if(row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn hàng trên bảng để xóa");
            return;
        }

        int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa hàng?", "Xác nhận xóa hàng", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            int id = (int) tableModel.getValueAt(row, 0);
            jdbcRemote.deleteDM(id);
            loadItems();
        }
    }

// CLEAR FIELDS    
    private void clearFieldS() {
        idTextField.setText("");
        tenHangTextField.setText("");
        soLuongTonTextField.setText("");
        donGiaTextField.setText("");
        baoHanhTextField.setText("");
        congSuatTextField.setText("");
    }

    protected void backPage() {
        JFrame viewPage = new ManagementApp();
        viewPage.setLocationRelativeTo(null);
        viewPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        viewPage.setVisible(true);
        dispose();
    }

    private void sumDM(){
        Map<String, Integer> typeQuantityMap = new HashMap<>();

        // Iterate through the list of KhoHang objects and calculate the total quantity for each type
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String type = (String) tableModel.getValueAt(i, 1); // Assuming the type is in the second column (index 1)
            int quantity = (int) tableModel.getValueAt(i, 2); // Assuming the quantity is in the third column (index 2)

            // Check if the type is already in the map, if yes, update the total quantity, otherwise, add it to the map
            typeQuantityMap.put(type, typeQuantityMap.getOrDefault(type, 0) + quantity);
        }

        // Create a string to display the results in the dialog box
        StringBuilder result = new StringBuilder();
        result.append("Tổng số lượng hàng hóa của điện máy:\n");
        for (Map.Entry<String, Integer> entry : typeQuantityMap.entrySet()) {
            result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        // Show the results in a dialog box
        JOptionPane.showMessageDialog(this, result.toString());
    }

}
