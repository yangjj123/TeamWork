package com.example.mathsapp;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

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
        //mPlayer.setLooping(true);//循环播放背景音乐

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

        //MainActivity.this.finish();
    }

}
