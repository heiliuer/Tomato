package com.tomato.timer;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JWindow;

public class MainFrame extends JWindow {

	private static final int FRAME_HEIGHT = 34;

	private static final int FRAME_WIDTH = 350;

	private static final long serialVersionUID = 1L;

	private JLabel labelTimer;

	public MainFrame() {
		labelTimer = new JLabel("  00:00   工作中，认真思考，不要讲话！");
		labelTimer.setFont(new Font("微软雅黑", Font.BOLD, 19));
		Container container = getContentPane();
		container.setBackground(Color.BLACK);
		container.add(labelTimer);
		labelTimer.setForeground(Color.WHITE);
		setOpacity(0.3f);
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

		@Override
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
				@Override
				public void run() {
					final MainFrame mf = new MainFrame();
					mf.setVisible(true);
					mf.start();
				}
			});
		}
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
