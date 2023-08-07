package App;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ConnectionDB.HangDM_JdbcGateway;
import ConnectionDB.HangSS_JdbcGateway;
import ConnectionDB.HangTP_JdbcGateway;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;

public class ManagementApp extends JFrame{

    private JButton tpBtn;
    private JButton dmBtn;
    private JButton ssBtn;
    private JButton exitBtn;

    public ManagementApp() {

        setTitle("Kho hàng");
        setSize(600, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // TextField
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));

        // Button
        tpBtn = new JButton("Thực phẩm");
        dmBtn = new JButton("Điện máy");
        ssBtn = new JButton("Sành sứ");
        exitBtn = new JButton("Thoát chương trình");

        inputPanel.add(tpBtn);
        inputPanel.add(dmBtn);
        inputPanel.add(ssBtn);
        inputPanel.add(exitBtn);

        add(inputPanel, BorderLayout.SOUTH);

        tpBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pageViewTP();
            }
        });

        dmBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pageViewDM();
            }
        });

        ssBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pageViewSS();
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exitApplication();
            }
        });

    }

    private void exitApplication() {
        System.exit(0);
        //
    }

    private void pageViewSS() {
        JFrame viewSSFrame = new viewSS(new HangSS_JdbcGateway());
    
        viewSSFrame.setLocationRelativeTo(null); // Hiển thị cửa sổ ở giữa màn hình
        viewSSFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Đóng frame khi bấm nút X (không thoát toàn bộ ứng dụng)

        // Hiển thị cửa sổ viewTPFrame
        viewSSFrame.setVisible(true);
        dispose();
    }

    private void pageViewDM() {
        JFrame viewDMFrame = new viewDM(new HangDM_JdbcGateway());

        viewDMFrame.setLocationRelativeTo(null); // Hiển thị cửa sổ ở giữa màn hình
        viewDMFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Đóng frame khi bấm nút X (không thoát toàn bộ ứng dụng)

        // Hiển thị cửa sổ viewTPFrame
        viewDMFrame.setVisible(true);
        dispose();
    }

    private void pageViewTP() {
        JFrame viewTPFrame = new viewTP(new HangTP_JdbcGateway());
        viewTPFrame.setLocationRelativeTo(null); // Hiển thị cửa sổ ở giữa màn hình
        viewTPFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Đóng frame khi bấm nút X (không thoát toàn bộ ứng dụng)

        // Hiển thị cửa sổ viewTPFrame
        viewTPFrame.setVisible(true);
        dispose();
    }

    

}