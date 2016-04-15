package com.tomato.timer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Status {

	private static int TIME_WORK;
	private static int TIME_REST;
	private boolean resting = true;

	public boolean isResting() {
		return resting;
	}

	private long lastSwitchTime;

	public long refreshCountTime() {
		long currentTime = System.currentTimeMillis();
		long remainTime = (resting ? TIME_REST : TIME_WORK) - (currentTime - lastSwitchTime);
		if (remainTime <= 0) {
			resting = !resting;
			remainTime = 0;
			lastSwitchTime = currentTime;
		}
		return remainTime;
	}

	public Status() {
		Properties p = new Properties();
		try {
			p.load(new FileInputStream(new File("config.ini")));
			TIME_REST = Integer.parseInt(p.getProperty("restTime")) * 1000 + 1000;
			TIME_WORK = Integer.parseInt(p.getProperty("workTime")) * 1000 + 1000;
			return;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		TIME_WORK = 1000 * 1200+ 1000;
		TIME_REST = 1000 * 300 + 1000;
	}

	public String refreshCountTimeStr() {
		long remainTime = refreshCountTime() / 1000;
		int minutes = (int) (remainTime / 60);
		int seconds = (int) (remainTime % 60);
		return minutes + ":" + seconds;
	}

	public String getStatus() {
		return resting ? "休息中，听听歌，看看新闻！" : "工作中，认真思考，非零化！";
	}
}
