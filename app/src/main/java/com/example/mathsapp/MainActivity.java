package com.example.mathsapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
    private MediaPlayer mPlayer;//背景音乐


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        mPlayer=MediaPlayer.create(this,R.raw.bg_mic);
        mPlayer.start();
        mPlayer.setLooping(true);//循环播放背景音乐

        super.onResume();
    }

    //被被覆盖后暂停后
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        mPlayer.stop();
        mPlayer.release();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub

        mPlayer.stop();
        mPlayer.release();
        super.onDestroy();
    }
    public void Start(View view)
    {


        mPlayer=MediaPlayer.create(this, R.raw.click);
        mPlayer.start();
        Intent intent=new Intent(this,PlayActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.rotate,R.anim.rotate);
       // MainActivity.this.finish();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item ){
        switch(item .getItemId() ) {
            case R.id.about:
                aboutAlert(this.getResources().getString(R.string .game_introduce ) ) ;
                break;
            case R.id.exit:
                exitAlert(this.getResources() .getString(R.string .exit ) );
                break;

        }
        return true;
    }
    private  void exitAlert(String msg){
        AlertDialog.Builder  builder=new AlertDialog.Builder(this);
        builder .setMessage(msg).setCancelable(false).setPositiveButton("确定",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish() ;
            }
        }) .setNegativeButton("取消",null);
        builder .create() .show() ;
    }
    private void aboutAlert(String msg){
        AlertDialog .Builder  builder=new AlertDialog.Builder(this);
        builder .setMessage(msg).setCancelable(false).setPositiveButton("确定",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }) .create().show();

    }


}
