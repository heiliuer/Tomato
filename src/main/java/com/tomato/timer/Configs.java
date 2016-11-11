package com.tomato.timer;


/**
 * Created by Heiliuer on 2016/11/11.
 */
public class Configs {


    /**
     * restTime : 300
     * workTime : 1200
     * restMsg : 休息中，听听歌，看看新闻！
     * workMsg : 工作中，认真思考，非零化！
     * workStart : true
     */

    private int restTime = 30;
    private float opacity = 1f;
    private int workTime = 1200;
    private String restMsg = "休息中，听听歌，看看新闻！";
    private String workMsg = "工作中，认真思考，非零化！";
    private boolean workStart = true;


    public float getOpacity() {
        return opacity;
    }

    public void setOpacity(float opacity) {
        this.opacity = opacity;
    }

    public int getRestTime() {
        return restTime;
    }

    public int getRestTimeMill() {
        return restTime * 1000 + 1000;
    }


    public int getWorkTimeMill() {
        return workTime * 1000 + 1000;
    }

    public void setRestTime(int restTime) {
        this.restTime = restTime;
    }

    public int getWorkTime() {
        return workTime;
    }

    public void setWorkTime(int workTime) {
        this.workTime = workTime;
    }

    public String getRestMsg() {
        return restMsg;
    }

    public void setRestMsg(String restMsg) {
        this.restMsg = restMsg;
    }

    public String getWorkMsg() {
        return workMsg;
    }

    public void setWorkMsg(String workMsg) {
        this.workMsg = workMsg;
    }

    public boolean isWorkStart() {
        return workStart;
    }

    public void setWorkStart(boolean workStart) {
        this.workStart = workStart;
    }
}
