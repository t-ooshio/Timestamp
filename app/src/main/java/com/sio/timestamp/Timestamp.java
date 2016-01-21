package com.sio.timestamp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by NTT docomo on 2016/01/20.
 * タイムスタンプを取得するクラス
 * エポックかhh:mm:ss.000の形式を選べる
 * EPO_HMSで両方をカンマ区切りで出力
 * 基準は日本時間で取得する
 */
public class Timestamp {
    public final int EPOCH = 0;
    public final int HMS = 1;
    public final int EPO_HMS =2;

    private final SimpleDateFormat LOGTIME_FORMAT = new SimpleDateFormat("kk:mm:ss.SSS");
    Locale locale = Locale.JAPAN;
    TimeZone timeZone = TimeZone.getTimeZone("Asia/Tokyo");
    long currentTimeMillis;
    /**
     *
     * @param timeTYPE
     * タイムスタンプの形式 定数EPOCH 0:エポック形式 定数HMS 1:hh:mm:ss.000
     * @return
     */
    String getTime(int timeTYPE){
        String rTime = null;
        currentTimeMillis = System.currentTimeMillis();
        rTime = Long.toString(currentTimeMillis);

        switch (timeTYPE) {
            case EPOCH:
                break;
            case HMS:
                rTime = this.LOGTIME_FORMAT.format(Long.valueOf(currentTimeMillis));
                break;
            case EPO_HMS:
                rTime = this.LOGTIME_FORMAT.format(Long.valueOf(currentTimeMillis)) + "," + Long.valueOf(currentTimeMillis);
        }
        return rTime;
    }
}