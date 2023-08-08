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
        JFrame viewSSFrameRemote = new viewSS(new HangSS_JdbcGateway());
    
        viewSSFrameRemote.setLocationRelativeTo(null); // Hiển thị cửa sổ ở giữa màn hình
        viewSSFrameRemote.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Đóng frame khi bấm nút X (không thoát toàn bộ ứng dụng)

        // Hiển thị cửa sổ viewTPFrame
        viewSSFrameRemote.setVisible(true);
        dispose();
    }

    private void pageViewDM() {
        JFrame viewDMFrameRemote = new viewDM(new HangDM_JdbcGateway());

        viewDMFrameRemote.setLocationRelativeTo(null); // Hiển thị cửa sổ ở giữa màn hình
        viewDMFrameRemote.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Đóng frame khi bấm nút X (không thoát toàn bộ ứng dụng)

        // Hiển thị cửa sổ viewTPFrame
        viewDMFrameRemote.setVisible(true);
        dispose();
    }

    private void pageViewTP() {
        JFrame viewTPFrameRemote = new viewTP(new HangTP_JdbcGateway());
        viewTPFrameRemote.setLocationRelativeTo(null); // Hiển thị cửa sổ ở giữa màn hình
        viewTPFrameRemote.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Đóng frame khi bấm nút X (không thoát toàn bộ ứng dụng)

        // Hiển thị cửa sổ viewTPFrame
        viewTPFrameRemote.setVisible(true);
        dispose();
    }

    

}