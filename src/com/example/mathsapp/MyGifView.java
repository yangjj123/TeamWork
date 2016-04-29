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
		// ��ȡ�Զ�������isgifimage
		TypedArray array = context.obtainStyledAttributes(attrs,
				R.styleable.GifView);
		isGifImage = array.getBoolean(R.styleable.GifView_isgifimage, true);

		array.recycle();// ��ȡ�Զ���������Ϻ���Ҫrecycle����Ȼ����´λ�ȡ���Ӱ��
		// ��ȡImageView��Ĭ��src����
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
		//��ȡϵͳ��ǰʱ��
		long nowTime=android.os.SystemClock.currentThreadTimeMillis();
		if(movieState==0)
		{
			movieState=nowTime;
		}
			if(movie!=null)
			{
				int duration =movie.duration();//��ȡgif������ʱ��
				
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
		// ��һ�β���
		if (movieStart == 0) {
			movieStart = curTime;
		}
		if (movie != null) {
			int duraction = movie.duration();
			int relTime = (int) ((curTime - movieStart) % duraction);
			movie.setTime(relTime);
			movie.draw(canvas, 0, 0);
			// ǿ���ػ�
			invalidate();
		}
		super.onDraw(canvas);
	}*/
	
	
}
