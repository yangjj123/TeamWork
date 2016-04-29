package com.example.mathsapp;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.StringTokenizer;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PlayActivity extends Activity {

	private static ProgressBar progress;
	private SoundPool soundPool;
	private Map<Integer, Integer> soundMap;
	public static String s[] = new String[10];//题目个数。。。10个
	public static int[] num1 = new int[5];//操作数
	static int num2;// 阶乘的数
	static String data1;//分数
	static String data2;
	static String data3;
	static String op1=new String();//分数的运算符
	static String op2=new String();//分数的运算符
	static String khdata=new String();
	public static String[] useranser = new String[10];// 用户答案
	static String[] staticanser = new String[10];// 标准答案
	static DecimalFormat decimal = new DecimalFormat("#.##");
	private TextView questionTextView;
	private ImageView passView,failView;
	private Button answer[]=new Button[3];
	private int whichtrue;
	private int count=0;//控制题目个数
	private int total=0;
	private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // 启用窗口特征，启用带进度和不带进度的进度条
        requestWindowFeature(Window.FEATURE_PROGRESS);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		
        setContentView(R.layout.activity_gameone);
        //背景音乐
   //     mediaPlayer = MediaPlayer.create(this, R.raw.playmusic);
    //    mediaPlayer.setLooping(true);
  //      mediaPlayer.start();
        //音效
        soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC,100);
        final int right = soundPool.load(this, R.raw.right,1);
    	final int wrong=soundPool.load(this, R.raw.wrong,1);
    	final int result=soundPool.load(this, R.raw.yisell,1);
        	// 显示两种进度条
     		setProgressBarVisibility(true);
     		setProgressBarIndeterminateVisibility(false);
     		// Max=10000
     		setProgress(9999);
     		init();
        
        questionTextView=(TextView)findViewById(R.id.textView1);
        passView=(ImageView)findViewById(R.id.imageView1);
        passView.setVisibility(View.INVISIBLE); 
        
        failView=(ImageView)findViewById(R.id.imageView2);
        failView.setVisibility(View.INVISIBLE);
        answer[0]=(Button)findViewById(R.id.button1);
        answer[1]=(Button)findViewById(R.id.button2);
        answer[2]=(Button)findViewById(R.id.button3);
     	
        
        final Drawable drawable = answer[whichtrue].getBackground();//保存按钮原背景颜色
        final Animation translate=AnimationUtils.loadAnimation(PlayActivity.this, R.anim.translate);
        final Animation rotate=AnimationUtils.loadAnimation(PlayActivity.this, R.anim.rotate);     
        final Animation alpha=AnimationUtils.loadAnimation(PlayActivity.this, R.anim.a1);
        PlayActivity.timu();//两个操作数
        
     whichtrue=(int) (Math.random()*3);//0-2之间的随机数
   	 questionTextView.setText(s[count]);
   	 answer[whichtrue].setText(staticanser[count]);
   	 decimal.setRoundingMode(RoundingMode.HALF_UP);
   	 answer[whichtrue].startAnimation(translate);
   	 
   	 for(int i=0;i<3;i++)
        {
        	
    		String string=staticanser[count];
    		
        	if(i!=whichtrue)
        	{
        		try{
        			float staticString =Float.parseFloat(string)+(int) (Math.random()*20+1);	
        			answer[i].setText(String.valueOf(decimal.format(staticString)));//将答案加一个随机数编程错误答案
        			answer[i].startAnimation(translate);
        		}catch(NumberFormatException e){
        			StringTokenizer fenxi = new StringTokenizer(string,"/");
        		      int data1_1 = Integer.parseInt(fenxi.nextToken())+(int) (Math.random()*5+1);//将答案加一个随机数编程错误答案
        		      int data1_2 = Integer.parseInt(fenxi.nextToken())+(int) (Math.random()*5+1);
        		      String strfenshui=String.valueOf(data1_1)+"/"+String.valueOf(data1_2);
        		      answer[i].setText(strfenshui);
        		      
        		      answer[i].startAnimation(translate);
        		}
        	}else{
        		total++;
        	}
        	
        }
   	 //staticanswerTextView.setText("标准答案"+staticanser[count]);
   	 
   	//答案判断正确与否
        answer[0].setOnClickListener(new OnClickListener() {   	
			@Override
			public void onClick(View arg0) {
				progress.incrementProgressBy(10);
				progress.incrementSecondaryProgressBy(10);
				if(count<9){
				if(answer[0].getText().equals(staticanser[count]))
				{
					//answer[0].setText("答对啦");
					total++;
					answer[0].startAnimation(alpha);
					soundPool.play(right, 1, 1, 0, 0, 1); 
					
				}
				else {
					//answer[0].setText("答错啦");
					answer[0].startAnimation(translate);
					soundPool.play(wrong, 1, 1, 0, 0, 1); 
				}
						
					 count++;
					 whichtrue=(int) (Math.random()*3);//0-2的随机数
		        	 questionTextView.setText(s[count]);
		        	 answer[whichtrue].setText(staticanser[count]);
		        	 for(int i=0;i<3;i++)
		             {
		             	
		         		String string=staticanser[count];
		         		//float staticanswer=Float.parseFloat(string);
		             	if(i!=whichtrue)
		             	{
		             		try{
		            			
		            			float staticString =Float.parseFloat(string)+(int) (Math.random()*20+1);	
		            			answer[i].setText(String.valueOf(decimal.format(staticString)));//将答案加一个随机数编程错误答案
		            			answer[i].startAnimation(translate);
		            		}catch(NumberFormatException e){
		            			StringTokenizer fenxi = new StringTokenizer(string,"/");
		            		      int data1_1 = Integer.parseInt(fenxi.nextToken())+(int) (Math.random()*5+1);//将答案加一个随机数编程错误答案
		            		      int data1_2 = Integer.parseInt(fenxi.nextToken())+(int) (Math.random()*5+1);
		            		      String strfenshui=String.valueOf(data1_1)+"/"+String.valueOf(data1_2);
		            		      answer[i].setText(strfenshui);
		            		      
		            		      answer[i].startAnimation(translate);
		            		}
		             		
		             	}
		             	
		             }
		        	 //staticanswerTextView.setText("标准答案"+staticanser[count]);
				}
				else{					
					if(total>=6){
						 mediaPlayer.stop();
						 questionTextView.setText("恭喜!恭喜! "+"答对的了"+total+"道题哦");
						 soundPool.play(result, 1, 1, 0, 0, 1); 
						 passView.setVisibility(View.VISIBLE);  
					}
					else if(total<6){
						mediaPlayer.stop();
						questionTextView.setText("很遗憾!哟~"+"只答对的了"+total+"道题哦");
						soundPool.play(result, 1, 1, 0, 0, 1); 
						failView.setVisibility(View.VISIBLE);
						
					}
					 
				}
			}  			
		});
        
        answer[1].setOnClickListener(new OnClickListener() {   	
 			@Override
 			public void onClick(View arg0) {
 				progress.incrementProgressBy(10);
				progress.incrementSecondaryProgressBy(10);
				if(count<9){
 				if(answer[1].getText().equals(staticanser[count]))
 				{
					//answer[1].setText("答对啦");
 					total++;
					answer[1].startAnimation(alpha);
					soundPool.play(right, 1, 1, 0, 0, 1); 
 				}
				else {
					//answer[1].setText("答错啦");
					answer[1].startAnimation(translate);
					soundPool.play(wrong, 1, 1, 0, 0, 1);
				}
 				
					count++;
					 whichtrue=(int) (Math.random()*3);//0-2之间的随机数
		        	 questionTextView.setText(s[count]);
		        	 answer[whichtrue].setText(staticanser[count]);
		        	 for(int i=0;i<3;i++)
		             {
		             	
		         		String string=staticanser[count];
		         		//float staticanswer=Float.parseFloat(string);
		             	if(i!=whichtrue)
		             	{
		             		try{
		            			
		            			float staticString =Float.parseFloat(string)+(int) (Math.random()*20+1);	
		            			answer[i].setText(String.valueOf(decimal.format(staticString)));//将答案加一个随机数编程错误答案
		            			answer[i].startAnimation(translate);
		            		}catch(NumberFormatException e){
		            			StringTokenizer fenxi = new StringTokenizer(string,"/");
		            		      int data1_1 = Integer.parseInt(fenxi.nextToken())+(int) (Math.random()*5+1);//将答案加一个随机数编程错误答案
		            		      int data1_2 = Integer.parseInt(fenxi.nextToken())+(int) (Math.random()*5+1);
		            		      String strfenshui=String.valueOf(data1_1)+"/"+String.valueOf(data1_2);
		            		      answer[i].setText(strfenshui);
		            		      
		            		      answer[i].startAnimation(translate);
		            		}

		             	}
		             	
		             }
		        	 //staticanswerTextView.setText("标准答案"+staticanser[count]);
				}else {
					if(total>=6){
						mediaPlayer.stop();
						 questionTextView.setText("恭喜!恭喜! "+"答对的了"+total+"道题哦");
						 soundPool.play(result, 1, 1, 0, 0, 1); 
						 passView.setVisibility(View.VISIBLE); 
					}
					else if(total<6){
						mediaPlayer.stop();
						questionTextView.setText("很遗憾!哟~"+"只答对的了"+total+"道题哦");
						soundPool.play(result, 1, 1, 0, 0, 1); 
						failView.setVisibility(View.VISIBLE); 
					}
					
				}
 			}  			
 		});
        
        answer[2].setOnClickListener(new OnClickListener() {   	
 			@Override
 			public void onClick(View arg0) {
 				progress.incrementProgressBy(10);
				progress.incrementSecondaryProgressBy(10);
				if(count<9){
 				if(answer[2].getText().equals(staticanser[count])){
					//answer[2].setText("答对啦");
 					total++;
					answer[2].startAnimation(alpha);
					soundPool.play(right, 1, 1, 0, 0, 1); 
					}
				else {
					//answer[2].setText("答错啦");
					answer[2].startAnimation(translate);
					soundPool.play(wrong, 1, 1, 0, 0, 1); 
					
				}

					count++;
					
					 whichtrue=(int) (Math.random()*3);//0-2之间的随机数
		        	 questionTextView.setText(s[count]);
		        	 answer[whichtrue].setText(staticanser[count]);
		        	 for(int i=0;i<3;i++)
		             {
		             	
		         		String string=staticanser[count];
		         		//float staticanswer=Float.parseFloat(string);
		             	if(i!=whichtrue)
		             	{
		             		
		             		try{
		            			
		            			float staticString =Float.parseFloat(string)+(int) (Math.random()*20+1);	
		            			answer[i].setText(String.valueOf(decimal.format(staticString)));//将答案加一个随机数编程错误答案
		            			answer[i].startAnimation(translate);
		            		}catch(NumberFormatException e){
		            			StringTokenizer fenxi = new StringTokenizer(string,"/");
		            		      int data1_1 = Integer.parseInt(fenxi.nextToken())+(int) (Math.random()*5+1);//将答案加一个随机数编程错误答案
		            		      int data1_2 = Integer.parseInt(fenxi.nextToken())+(int) (Math.random()*5+1);
		            		      String strfenshui=String.valueOf(data1_1)+"/"+String.valueOf(data1_2);
		            		      answer[i].setText(strfenshui);
		            		      answer[i].startAnimation(translate);
		            		}

		             	}
		             	
		             }
		        	// staticanswerTextView.setText("标准答案"+staticanser[count]);
				}else{
					if(total>=6){
						mediaPlayer.stop();
						 questionTextView.setText("恭喜!恭喜! "+"答对的了"+total+"道题哦");
						 soundPool.play(result, 1, 1, 0, 0, 1); 
						 passView.setVisibility(View.VISIBLE); 

					}
					else if(total<6){
						mediaPlayer.stop();
						questionTextView.setText("很遗憾!哟~"+"只答对的了"+total+"道题哦");
						soundPool.play(result, 1, 1, 0, 0, 1); 
						failView.setVisibility(View.VISIBLE); 
					}
				}
 			}  			
 		});
    }
    @Override
    protected void onDestroy() {
       // 销毁的时候释放SoundPool资源;
       if (soundPool!= null) {
    	   mediaPlayer.stop();
    	   mediaPlayer.release();
       	soundPool.release();
       	soundPool = null;
      }
      super.onDestroy();
  }
    public static void timu() {// 题目
		int n;
		char[] op = { '+', '-', '*', '÷' };
		int[] no = new int[4];
		int cs;// 正负参数
		int ccs;// 题型参数(是否有阶乘/分数题目/有括号的题目)
		
	
		String str = new String();

		for (int i = 0; i < 10; i++) {//10道题
			ccs = (int) (Math.random() * 4);//0是普通题目，1是阶乘，2是分数题目，3是带括号的题目
			//System.out.println("我是css:"+ccs);
			//s[i] = "(" + (i + 1) + ")";
			n = (int) (Math.random() * 4 + 2);// 2-5个操作数
			if (ccs == 0) {// 一般式子
				for (int j = 0; j < n; j++) {
					cs = (int) (Math.random() * 2);

					if (cs == 0)// 负数
						num1[j] = -(int) (Math.random() * 100);// 控制随机数数值
					else
						// 正数
						num1[j] = (int) (Math.random() * 100);// 控制随机数数值
				}
				for (int k = 0; k < n - 1; k++) {
					no[k] = (int) (Math.random() * 4);// 随机产生操作符
					if (no[k] == 3 && num1[k + 1] == 0) {
						do {
							num1[k + 1] = (int) (Math.random() * 100);// 如果除号后为0，则重新取数。
						} while (num1[k + 1] == 0);
					}
				}
				for (int h = 0; h < n; h++) {//负数加括号
					if (h != n - 1) {
						if (num1[h] < 0)
							str = str + "(" + String.valueOf(num1[h]) + ")"
									+ String.valueOf(op[no[h]]);
						else
							str = str + String.valueOf(num1[h])
									+ String.valueOf(op[no[h]]);
					} else {
						if (num1[h] < 0)
							str = str + "(" + String.valueOf(num1[h]) + ")=?";
						else
							str = str + String.valueOf(num1[h]) + "=?";

					}
				}
				s[i] = str;
				str = new String();
			}
			else if (ccs == 1) // 阶层式子
			{
				num2 = (int) (Math.random() * 5);
				s[i] =   String.valueOf(num2) + "!=?";

			}
			else if (ccs == 2) {//分数
				int rand;
				int randop[]=new int[2];
				int num3[]=new int[6];
				for(int count=0;count<6;count++){
					rand=(int) (Math.random() * 10+1);//1-10之间的数
					num3[count]=rand;
				}
				for(int k=0;k<2;k++){
					randop[k] = (int) (Math.random() * 4);// 随机产生操作符
				}
				op1=String.valueOf(op[randop[0]]);
				//System.out.println("我是op1"+op1);
				op2=String.valueOf(op[randop[1]]);
				//System.out.println("我是op2"+op2);
				data1=Integer.toString(num3[0])+"/"+Integer.toString(num3[1]);
				data2=Integer.toString(num3[2])+"/"+Integer.toString(num3[3]);
				data3=Integer.toString(num3[4])+"/"+Integer.toString(num3[5]);
		
					s[i] = data1+op1+data2+op2+data3+"=?";
				
			}
			else if (ccs == 3) {//带括号
				int rand;
				int num4[]=new int[4];
				for(int count=0;count<4;count++){
					rand=(int) (Math.random() * 10+1);//1-10之间的数
					num4[count]=rand;
				}
				for(int k=0;k<3;k++){
					no[k] = (int) (Math.random() * 4);// 随机产生操作符
				} 
				
				int khcount=(int) (Math.random() *2+1);//括号对数，四个随机最多两对括号
				if(khcount==1){//一对括号
					int randposition=(int) (Math.random() *3+1);//随机位置，一共三种括号位置（1+2）+3+4and1+(2+3)+4and1+2+(3+4)
					if(randposition==1){
						khdata="("+String.valueOf(num4[0])+String.valueOf(op[no[0]])+String.valueOf(num4[1])+")"
								+String.valueOf(op[no[1]])+String.valueOf(num4[2])+String.valueOf(op[no[3]])+String.valueOf(num4[3]);
						s[i] =khdata+"=?";
					}else if(randposition==2){
						khdata=String.valueOf(num4[0])+String.valueOf(op[no[0]])+"("+String.valueOf(num4[1])
								+String.valueOf(op[no[1]])+String.valueOf(num4[2])+")"+String.valueOf(op[no[3]])+String.valueOf(num4[3]);
						s[i] = khdata+"=?";
					}else if(randposition==3){
						khdata=String.valueOf(num4[0])+String.valueOf(op[no[0]])+String.valueOf(num4[1])
								+String.valueOf(op[no[1]])+"("+String.valueOf(num4[2])+String.valueOf(op[no[3]])+String.valueOf(num4[3])+")";
						s[i] =khdata+"=?";
					}
				}else if(khcount==2){//两对括号  （1+2）+（3+4）一种情况
					khdata="("+String.valueOf(num4[0])+String.valueOf(op[no[0]])+String.valueOf(num4[1])+")"
							+String.valueOf(op[no[1]])+"("+String.valueOf(num4[2])+String.valueOf(op[no[3]])+String.valueOf(num4[3])+")";
					s[i] =khdata+"=?";
				}
			}
			
			
			//计算计算计算
			if (ccs == 0) {//
				int sign; // 累加运算时的符号
				float left, right;// 保存蹭结果
				decimal.setRoundingMode(RoundingMode.HALF_UP);//四舍五入
				// 计算标准答案
				left = 0;
				right = num1[0];
				sign = 1;

				for (int g = 0; g < n - 1; g++) {
					switch (op[no[g]]) {
					case '+':
						left = left + sign * right;
						sign = 1;
						right = num1[g + 1];
						break;
					case '-':
						left = left + sign * right;
						sign = -1;
						right = num1[g + 1];
						break;
					case '*':
						right = right * num1[g + 1];
						break;
					case '÷':
						right = right / num1[g + 1];
						break;
					}
				}
				staticanser[i] = String.valueOf(decimal.format(left + sign
						* right));
				System.out.println((i + 1) + ":" + staticanser[i]);
			} 
			else if (ccs == 1)  {
				int ann = 1;
				if (num2 == 0) {
					staticanser[i] = "1";
				} else {
					for (int o = 1; o <= num2; o++) {
						ann = ann * o;
					}
					staticanser[i] = String.valueOf(ann);
				}
				System.out.println((i + 1) + ":" + staticanser[i]);

			}
			else if (ccs == 2) {//分数计算
				Calculator cal = new Calculator();
				String res=new String();
				String result=new String();
				if(op2.equals("+")||op2.equals("-")){
					//System.out.println("我是出错的op1:"+op1);
					//System.out.println("我是出错的op2:"+op2);
					res=cal.compute(data1, op1, data2);
					result=cal.compute(res, op2, data3);
				}else if(op2.equals("*")||op2.equals("÷")){
					res=cal.compute(data2, op2, data3);
					result=cal.compute(data1, op1,res);
					
					//System.out.println("我是2出错的op1:"+op1);
					//System.out.println("我是2出错的op2:"+op2);
				}
				staticanser[i]=result;
				System.out.println((i + 1) + ":" + staticanser[i]);
			  //  System.out.println("运算结果为:"+result);
			    
			}
			else if (ccs == 3) {//带括号的式子计算
				KuohaoCalc khcalcu = new KuohaoCalc();
				String khresult=khcalcu.interceResult(khdata);
				staticanser[i] =khresult;
				System.out.println((i + 1) + ":" + staticanser[i]);
			}
		}
			

	}
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	mediaPlayer=MediaPlayer.create(this, R.raw.playmusic);
    	mediaPlayer.start();
		 mediaPlayer.setLooping(true);//循环播放背景音乐
     	
    	super.onResume();
    }
    
     //被被覆盖后暂停后
     @Override
    protected void onPause() {
    	// TODO Auto-generated method stub
    	 mediaPlayer.stop();
    	 mediaPlayer.release();
    	super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private void init() {
		
		progress = (ProgressBar) findViewById(R.id.horiz);
		
		// 获取第一进度条的进度
		int first = progress.getProgress();
		// 获取第二进度条的进度
		int second = progress.getSecondaryProgress();
		// 获取进度条的最大进度
		int max = progress.getMax();
		
	}
}
