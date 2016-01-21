/**
 * Created by NTT docomo on 2015/11/16.
 * ログを出力するためのクラス
 * permission追加すること
 *     <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"></uses-permission>

 */

package com.sio.timestamp;

import android.os.Environment;
import android.util.Log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 測位結果をログに出力するためのクラス
 */
public class MyLog
{
    private final SimpleDateFormat FILENAME_SDF = new SimpleDateFormat("yyyyMMdd-kkmmssSSS");
    private File LogFile;
    private String PkgName = "com.sio.timestamp";
    private BufferedWriter bw;
    private final String char_code = "Shift-JIS";
    private final String extention = ".csv";
    private String filename;
    private FileOutputStream fos;
    private OutputStreamWriter osw;

    /**
     * ログに追記を行う
     * @param paramString
     * 追記する文字列
     */
    public void addLog(String paramString) {
        try {
            this.fos = new FileOutputStream(this.LogFile, true);
            this.osw = new OutputStreamWriter(this.fos, "Shift-JIS");
            this.bw = new BufferedWriter(this.osw);
            this.bw.write(paramString + "\n");
            this.bw.flush();
            this.bw.close();
            this.fos.close();
            return;
        } catch (IOException e) {
            Log.d(this.PkgName, "addlog error");
            e.printStackTrace();
        }
    }

    /**
     * ログファイルを作成するクラス
     */
    public void initLog() {
        this.filename = this.FILENAME_SDF.format(new Date());
        this.LogFile = new File("/sdcard/" + this.PkgName + "/" + this.filename + this.extention);
        this.LogFile.getParentFile().mkdir();
    }

    /**
     * ログファイルの作成とファイルの書き込みを同時に行う
     * @param paramString
     * 書き込む文字列
     */
    public void initLog(String paramString) {
        this.filename = this.FILENAME_SDF.format(new Date());
        this.LogFile = new File("/sdcard/" + this.PkgName + "/" + this.filename + this.extention);
        this.LogFile.getParentFile().mkdir();
        try {
            this.fos = new FileOutputStream(this.LogFile, true);
            this.osw = new OutputStreamWriter(this.fos, "Shift-JIS");
            this.bw = new BufferedWriter(this.osw);
            this.bw.write(paramString + "\n");
            this.bw.flush();
            this.bw.close();
            this.fos.close();
            return;
        }
        catch (IOException e) {
            Log.d(this.PkgName,"initLog Error");
            e.printStackTrace();
        }
    }
}