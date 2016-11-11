package com.tomato.timer;


public class Status {

    private boolean resting = true;

    public boolean isResting() {
        return resting;
    }

    private long lastSwitchTime;

    public long refreshCountTime() {
        long currentTime = System.currentTimeMillis();
        long remainTime = (resting ? configs.getRestTimeMill() : configs.getWorkTimeMill()) - (currentTime - lastSwitchTime);
        if (remainTime <= 0) {
            resting = !resting;
            remainTime = 0;
            lastSwitchTime = currentTime;
        }
        return remainTime;
    }

    private Configs configs;


    public Status() {
        configs = MainFrame.getConfigs();
    }

    public String refreshCountTimeStr() {
        long remainTime = refreshCountTime() / 1000;
        int minutes = (int) (remainTime / 60);
        int seconds = (int) (remainTime % 60);
        return minutes + ":" + seconds;
    }

    public String getStatus() {
        return resting ? configs.getRestMsg() : configs.getWorkMsg();
    }
}
