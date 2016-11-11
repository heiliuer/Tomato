package com.tomato.timer;

import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Timer;
import java.util.TimerTask;

public class MainFrame extends JWindow {

    private static final int FRAME_HEIGHT = 34;

    private static final int FRAME_WIDTH = 350;

    private static final long serialVersionUID = 1L;

    private JLabel labelTimer;

    private static Configs configs;


    public static Configs getConfigs() {
        return configs;
    }

    static {
        try {
            configs = new Gson().fromJson(new FileReader("config.json"), Configs.class);
        } catch (FileNotFoundException e) {
            configs = new Configs();
        }
    }


    public MainFrame() {
        labelTimer = new JLabel("");
        labelTimer.setFont(new Font("微软雅黑", Font.BOLD, 19));
        Container container = getContentPane();
        container.setBackground(Color.BLACK);
        container.add(labelTimer);
        labelTimer.setForeground(Color.WHITE);
        setOpacity(configs.getOpacity());
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension ss = kit.getScreenSize();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setBounds((ss.width - FRAME_WIDTH) / 2, 0, FRAME_WIDTH, FRAME_HEIGHT);
        setAlwaysOnTop(true);
    }

    public void start() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                EventQueue.invokeLater(refreshLabelTimer);
            }
        }, 0, 500);
    }


    private Status status = new Status();

    private Runnable refreshLabelTimer = new Runnable() {

        public void run() {
            labelTimer.setText("  " + status.refreshCountTimeStr() + "  " + status.getStatus());
            labelTimer.setForeground(status.isResting() ? Color.GREEN : Color.RED);
        }
    };

    private static SystemTray st;
    private static PopupMenu pm;

    public static void createApp() {
        if (SystemTray.isSupported()) {// 判断当前平台是否支持系统托盘
            st = SystemTray.getSystemTray();
            createPopupMenu();
            Image image = Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("icon.png"));// 定义托盘图标的图片
            try {
                st.add(new TrayIcon(image, "番茄时间管理", pm));
            } catch (AWTException ex) {
                ex.printStackTrace();
            }
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    final MainFrame mf = new MainFrame();
                    mf.setVisible(true);
                    mf.start();
                }
            });
        }
    }

    public static void main(String[] args) {
        createApp();
    }

    public static void createPopupMenu() {
        pm = new PopupMenu();
        MenuItem exitMenu = new MenuItem("退出");
        exitMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        pm.add(exitMenu);
    }
}
