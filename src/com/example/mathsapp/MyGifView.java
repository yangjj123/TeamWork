package com.example.mathsapp;

import android.R.animator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.util.AttributeSet;
import android.widget.ImageView;

public class MyGifView extends ImageView {

	private long movieState;
	private Movie movie;
	boolean isGifImage;

	public MyGifView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// 获取自定义属性isgifimage
		TypedArray array = context.obtainStyledAttributes(attrs,
				R.styleable.GifView);
		isGifImage = array.getBoolean(R.styleable.GifView_isgifimage, true);

		array.recycle();// 获取自定义属性完毕后需要recycle，不然会对下次获取造成影响
		// 获取ImageView的默认src属性
		int image = attrs.getAttributeResourceValue(
				"http://schemas.android.com/apk/res/android", "src", 0);

		movie = Movie.decodeStream(getResources().openRawResource(image));
	}

	@Override
	protected void onDraw(Canvas canvas) {

		
		
		if (isGifImage) {
			DrawGifImage(canvas);
		}
		
	}

	private void DrawGifImage(Canvas canvas)
	{
		//获取系统当前时间
		long nowTime=android.os.SystemClock.currentThreadTimeMillis();
		if(movieState==0)
		{
			movieState=nowTime;
		}
			if(movie!=null)
			{
				int duration =movie.duration();//获取gif持续的时间
				
				if(duration!=0)
				{
					int relTime=(int)((nowTime-movieState)%duration);
					movie.setTime(relTime);
					movie.draw(canvas, 0, 0);
					invalidate();
				}
				
			}
		
	}
/*	@Override
	protected void onDraw(Canvas canvas) {
		long curTime = android.os.SystemClock.uptimeMillis();
		// 第一次播放
		if (movieStart == 0) {
			movieStart = curTime;
		}
		if (movie != null) {
			int duraction = movie.duration();
			int relTime = (int) ((curTime - movieStart) % duraction);
			movie.setTime(relTime);
			movie.draw(canvas, 0, 0);
			// 强制重绘
			invalidate();
		}
		super.onDraw(canvas);
	}*/
	
	
}
