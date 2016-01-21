/**
 * タイムスタンプを取得するアプリ
 * エポック形式（1970/1/1 00:00:00.000からのミリ秒）
 * hh:mm:ss.000形式（時分秒ミリ秒）
 * 両方を出力する
 * の、3通りで取得できる
 * 開始ボタンを押すと「開始」タグのついたタイムスタンプ
 * 停止ボタンを押すと「停止」タグのついたタイムスタンプ
 * を、取得する
 * タイムスタンプの「削除ボタン」を押すとその1項目のみを削除する
 * 全削除ボタンを「長押し」することでタイムスタンプをすべて削除する
 */
package com.sio.timestamp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MyLog myLog = new MyLog();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //UIパーツ
        final Button button_start = (Button)findViewById(R.id.button_start);
        final Button button_stop = (Button)findViewById(R.id.button_stop);
        final Button button_delete_all = (Button)findViewById(R.id.button_delete_all);
        final Button button_save = (Button)findViewById(R.id.button_save);
        final Button button_save_delete = (Button)findViewById(R.id.button_save_delete);

        final RadioButton radio_epoch = (RadioButton)findViewById(R.id.radio_epoch);
        final RadioButton radio_hms = (RadioButton)findViewById(R.id.radio_hms);
        final RadioButton radio_epo_hms = (RadioButton)findViewById(R.id.radio_epo_hms);
        final TextView text_result = (TextView)findViewById(R.id.text_result);

        final Timestamp timestamp = new Timestamp();

        button_start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //開始ボタンを押したときの処理
                Log.d("MyTimestamp","start_click");
                if(radio_epoch.isChecked()) {
                    text_result.append(getString(R.string.start) + "," + timestamp.getTime(timestamp.EPOCH)+"\n");
                }
                if(radio_hms.isChecked()){
                    text_result.append(getString(R.string.start) + "," + timestamp.getTime(timestamp.HMS)+"\n");
                }
                if(radio_epo_hms.isChecked()){
                    text_result.append(getString(R.string.start) + "," + timestamp.getTime(timestamp.EPO_HMS)+"\n");
                }
            }
        });
        button_stop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //停止ボタンを押したときの処理
                Log.d("MyTimestamp", "stop_click");
                if(radio_epoch.isChecked()) {
                    text_result.append(getString(R.string.stop) + "," + timestamp.getTime(timestamp.EPOCH)+"\n");
                }
                if(radio_hms.isChecked()){
                    text_result.append(getString(R.string.stop) + "," + timestamp.getTime(timestamp.HMS)+"\n");
                }
                if(radio_epo_hms.isChecked()){
                    text_result.append(getString(R.string.stop) + "," + timestamp.getTime(timestamp.EPO_HMS)+"\n");
                }
            }
        });
        button_delete_all.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("MyTimestamp", "delete_all_long_click");
                text_result.setText("");
                return false;
            }
        });
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myLog.initLog(text_result.getText().toString());
                //myLog.addLog(text_result.getText().toString());
            }
        });
        button_save_delete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                myLog.initLog(text_result.getText().toString());
                text_result.setText("");
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
