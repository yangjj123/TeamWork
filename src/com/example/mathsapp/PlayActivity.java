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
	public static String s[] = new String[10];//��Ŀ����������10��
	public static int[] num1 = new int[5];//������
	static int num2;// �׳˵���
	static String data1;//����
	static String data2;
	static String data3;
	static String op1=new String();//�����������
	static String op2=new String();//�����������
	static String khdata=new String();
	public static String[] useranser = new String[10];// �û���
	static String[] staticanser = new String[10];// ��׼��
	static DecimalFormat decimal = new DecimalFormat("#.##");
	private TextView questionTextView;
	private ImageView passView,failView;
	private Button answer[]=new Button[3];
	private int whichtrue;
	private int count=0;//������Ŀ����
	private int total=0;
	private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // ���ô������������ô����ȺͲ������ȵĽ�����
        requestWindowFeature(Window.FEATURE_PROGRESS);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		
        setContentView(R.layout.activity_gameone);
        //��������
   //     mediaPlayer = MediaPlayer.create(this, R.raw.playmusic);
    //    mediaPlayer.setLooping(true);
  //      mediaPlayer.start();
        //��Ч
        soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC,100);
        final int right = soundPool.load(this, R.raw.right,1);
    	final int wrong=soundPool.load(this, R.raw.wrong,1);
    	final int result=soundPool.load(this, R.raw.yisell,1);
        	// ��ʾ���ֽ�����
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
     	
        
        final Drawable drawable = answer[whichtrue].getBackground();//���水ťԭ������ɫ
        final Animation translate=AnimationUtils.loadAnimation(PlayActivity.this, R.anim.translate);
        final Animation rotate=AnimationUtils.loadAnimation(PlayActivity.this, R.anim.rotate);     
        final Animation alpha=AnimationUtils.loadAnimation(PlayActivity.this, R.anim.a1);
        PlayActivity.timu();//����������
        
     whichtrue=(int) (Math.random()*3);//0-2֮��������
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
        			answer[i].setText(String.valueOf(decimal.format(staticString)));//���𰸼�һ���������̴����
        			answer[i].startAnimation(translate);
        		}catch(NumberFormatException e){
        			StringTokenizer fenxi = new StringTokenizer(string,"/");
        		      int data1_1 = Integer.parseInt(fenxi.nextToken())+(int) (Math.random()*5+1);//���𰸼�һ���������̴����
        		      int data1_2 = Integer.parseInt(fenxi.nextToken())+(int) (Math.random()*5+1);
        		      String strfenshui=String.valueOf(data1_1)+"/"+String.valueOf(data1_2);
        		      answer[i].setText(strfenshui);
        		      
        		      answer[i].startAnimation(translate);
        		}
        	}else{
        		total++;
        	}
        	
        }
   	 //staticanswerTextView.setText("��׼��"+staticanser[count]);
   	 
   	//���ж���ȷ���
        answer[0].setOnClickListener(new OnClickListener() {   	
			@Override
			public void onClick(View arg0) {
				progress.incrementProgressBy(10);
				progress.incrementSecondaryProgressBy(10);
				if(count<9){
				if(answer[0].getText().equals(staticanser[count]))
				{
					//answer[0].setText("�����");
					total++;
					answer[0].startAnimation(alpha);
					soundPool.play(right, 1, 1, 0, 0, 1); 
					
				}
				else {
					//answer[0].setText("�����");
					answer[0].startAnimation(translate);
					soundPool.play(wrong, 1, 1, 0, 0, 1); 
				}
						
					 count++;
					 whichtrue=(int) (Math.random()*3);//0-2�������
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
		            			answer[i].setText(String.valueOf(decimal.format(staticString)));//���𰸼�һ���������̴����
		            			answer[i].startAnimation(translate);
		            		}catch(NumberFormatException e){
		            			StringTokenizer fenxi = new StringTokenizer(string,"/");
		            		      int data1_1 = Integer.parseInt(fenxi.nextToken())+(int) (Math.random()*5+1);//���𰸼�һ���������̴����
		            		      int data1_2 = Integer.parseInt(fenxi.nextToken())+(int) (Math.random()*5+1);
		            		      String strfenshui=String.valueOf(data1_1)+"/"+String.valueOf(data1_2);
		            		      answer[i].setText(strfenshui);
		            		      
		            		      answer[i].startAnimation(translate);
		            		}
		             		
		             	}
		             	
		             }
		        	 //staticanswerTextView.setText("��׼��"+staticanser[count]);
				}
				else{					
					if(total>=6){
						 mediaPlayer.stop();
						 questionTextView.setText("��ϲ!��ϲ! "+"��Ե���"+total+"����Ŷ");
						 soundPool.play(result, 1, 1, 0, 0, 1); 
						 passView.setVisibility(View.VISIBLE);  
					}
					else if(total<6){
						mediaPlayer.stop();
						questionTextView.setText("���ź�!Ӵ~"+"ֻ��Ե���"+total+"����Ŷ");
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
					//answer[1].setText("�����");
 					total++;
					answer[1].startAnimation(alpha);
					soundPool.play(right, 1, 1, 0, 0, 1); 
 				}
				else {
					//answer[1].setText("�����");
					answer[1].startAnimation(translate);
					soundPool.play(wrong, 1, 1, 0, 0, 1);
				}
 				
					count++;
					 whichtrue=(int) (Math.random()*3);//0-2֮��������
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
		            			answer[i].setText(String.valueOf(decimal.format(staticString)));//���𰸼�һ���������̴����
		            			answer[i].startAnimation(translate);
		            		}catch(NumberFormatException e){
		            			StringTokenizer fenxi = new StringTokenizer(string,"/");
		            		      int data1_1 = Integer.parseInt(fenxi.nextToken())+(int) (Math.random()*5+1);//���𰸼�һ���������̴����
		            		      int data1_2 = Integer.parseInt(fenxi.nextToken())+(int) (Math.random()*5+1);
		            		      String strfenshui=String.valueOf(data1_1)+"/"+String.valueOf(data1_2);
		            		      answer[i].setText(strfenshui);
		            		      
		            		      answer[i].startAnimation(translate);
		            		}

		             	}
		             	
		             }
		        	 //staticanswerTextView.setText("��׼��"+staticanser[count]);
				}else {
					if(total>=6){
						mediaPlayer.stop();
						 questionTextView.setText("��ϲ!��ϲ! "+"��Ե���"+total+"����Ŷ");
						 soundPool.play(result, 1, 1, 0, 0, 1); 
						 passView.setVisibility(View.VISIBLE); 
					}
					else if(total<6){
						mediaPlayer.stop();
						questionTextView.setText("���ź�!Ӵ~"+"ֻ��Ե���"+total+"����Ŷ");
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
					//answer[2].setText("�����");
 					total++;
					answer[2].startAnimation(alpha);
					soundPool.play(right, 1, 1, 0, 0, 1); 
					}
				else {
					//answer[2].setText("�����");
					answer[2].startAnimation(translate);
					soundPool.play(wrong, 1, 1, 0, 0, 1); 
					
				}

					count++;
					
					 whichtrue=(int) (Math.random()*3);//0-2֮��������
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
		            			answer[i].setText(String.valueOf(decimal.format(staticString)));//���𰸼�һ���������̴����
		            			answer[i].startAnimation(translate);
		            		}catch(NumberFormatException e){
		            			StringTokenizer fenxi = new StringTokenizer(string,"/");
		            		      int data1_1 = Integer.parseInt(fenxi.nextToken())+(int) (Math.random()*5+1);//���𰸼�һ���������̴����
		            		      int data1_2 = Integer.parseInt(fenxi.nextToken())+(int) (Math.random()*5+1);
		            		      String strfenshui=String.valueOf(data1_1)+"/"+String.valueOf(data1_2);
		            		      answer[i].setText(strfenshui);
		            		      answer[i].startAnimation(translate);
		            		}

		             	}
		             	
		             }
		        	// staticanswerTextView.setText("��׼��"+staticanser[count]);
				}else{
					if(total>=6){
						mediaPlayer.stop();
						 questionTextView.setText("��ϲ!��ϲ! "+"��Ե���"+total+"����Ŷ");
						 soundPool.play(result, 1, 1, 0, 0, 1); 
						 passView.setVisibility(View.VISIBLE); 

					}
					else if(total<6){
						mediaPlayer.stop();
						questionTextView.setText("���ź�!Ӵ~"+"ֻ��Ե���"+total+"����Ŷ");
						soundPool.play(result, 1, 1, 0, 0, 1); 
						failView.setVisibility(View.VISIBLE); 
					}
				}
 			}  			
 		});
    }
    @Override
    protected void onDestroy() {
       // ���ٵ�ʱ���ͷ�SoundPool��Դ;
       if (soundPool!= null) {
    	   mediaPlayer.stop();
    	   mediaPlayer.release();
       	soundPool.release();
       	soundPool = null;
      }
      super.onDestroy();
  }
    public static void timu() {// ��Ŀ
		int n;
		char[] op = { '+', '-', '*', '��' };
		int[] no = new int[4];
		int cs;// ��������
		int ccs;// ���Ͳ���(�Ƿ��н׳�/������Ŀ/�����ŵ���Ŀ)
		
	
		String str = new String();

		for (int i = 0; i < 10; i++) {//10����
			ccs = (int) (Math.random() * 4);//0����ͨ��Ŀ��1�ǽ׳ˣ�2�Ƿ�����Ŀ��3�Ǵ����ŵ���Ŀ
			//System.out.println("����css:"+ccs);
			//s[i] = "(" + (i + 1) + ")";
			n = (int) (Math.random() * 4 + 2);// 2-5��������
			if (ccs == 0) {// һ��ʽ��
				for (int j = 0; j < n; j++) {
					cs = (int) (Math.random() * 2);

					if (cs == 0)// ����
						num1[j] = -(int) (Math.random() * 100);// �����������ֵ
					else
						// ����
						num1[j] = (int) (Math.random() * 100);// �����������ֵ
				}
				for (int k = 0; k < n - 1; k++) {
					no[k] = (int) (Math.random() * 4);// �������������
					if (no[k] == 3 && num1[k + 1] == 0) {
						do {
							num1[k + 1] = (int) (Math.random() * 100);// ������ź�Ϊ0��������ȡ����
						} while (num1[k + 1] == 0);
					}
				}
				for (int h = 0; h < n; h++) {//����������
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
			else if (ccs == 1) // �ײ�ʽ��
			{
				num2 = (int) (Math.random() * 5);
				s[i] =   String.valueOf(num2) + "!=?";

			}
			else if (ccs == 2) {//����
				int rand;
				int randop[]=new int[2];
				int num3[]=new int[6];
				for(int count=0;count<6;count++){
					rand=(int) (Math.random() * 10+1);//1-10֮�����
					num3[count]=rand;
				}
				for(int k=0;k<2;k++){
					randop[k] = (int) (Math.random() * 4);// �������������
				}
				op1=String.valueOf(op[randop[0]]);
				//System.out.println("����op1"+op1);
				op2=String.valueOf(op[randop[1]]);
				//System.out.println("����op2"+op2);
				data1=Integer.toString(num3[0])+"/"+Integer.toString(num3[1]);
				data2=Integer.toString(num3[2])+"/"+Integer.toString(num3[3]);
				data3=Integer.toString(num3[4])+"/"+Integer.toString(num3[5]);
		
					s[i] = data1+op1+data2+op2+data3+"=?";
				
			}
			else if (ccs == 3) {//������
				int rand;
				int num4[]=new int[4];
				for(int count=0;count<4;count++){
					rand=(int) (Math.random() * 10+1);//1-10֮�����
					num4[count]=rand;
				}
				for(int k=0;k<3;k++){
					no[k] = (int) (Math.random() * 4);// �������������
				} 
				
				int khcount=(int) (Math.random() *2+1);//���Ŷ������ĸ���������������
				if(khcount==1){//һ������
					int randposition=(int) (Math.random() *3+1);//���λ�ã�һ����������λ�ã�1+2��+3+4and1+(2+3)+4and1+2+(3+4)
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
				}else if(khcount==2){//��������  ��1+2��+��3+4��һ�����
					khdata="("+String.valueOf(num4[0])+String.valueOf(op[no[0]])+String.valueOf(num4[1])+")"
							+String.valueOf(op[no[1]])+"("+String.valueOf(num4[2])+String.valueOf(op[no[3]])+String.valueOf(num4[3])+")";
					s[i] =khdata+"=?";
				}
			}
			
			
			//����������
			if (ccs == 0) {//
				int sign; // �ۼ�����ʱ�ķ���
				float left, right;// �������
				decimal.setRoundingMode(RoundingMode.HALF_UP);//��������
				// �����׼��
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
					case '��':
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
			else if (ccs == 2) {//��������
				Calculator cal = new Calculator();
				String res=new String();
				String result=new String();
				if(op2.equals("+")||op2.equals("-")){
					//System.out.println("���ǳ����op1:"+op1);
					//System.out.println("���ǳ����op2:"+op2);
					res=cal.compute(data1, op1, data2);
					result=cal.compute(res, op2, data3);
				}else if(op2.equals("*")||op2.equals("��")){
					res=cal.compute(data2, op2, data3);
					result=cal.compute(data1, op1,res);
					
					//System.out.println("����2�����op1:"+op1);
					//System.out.println("����2�����op2:"+op2);
				}
				staticanser[i]=result;
				System.out.println((i + 1) + ":" + staticanser[i]);
			  //  System.out.println("������Ϊ:"+result);
			    
			}
			else if (ccs == 3) {//�����ŵ�ʽ�Ӽ���
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
		 mediaPlayer.setLooping(true);//ѭ�����ű�������
     	
    	super.onResume();
    }
    
     //�������Ǻ���ͣ��
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
		
		// ��ȡ��һ�������Ľ���
		int first = progress.getProgress();
		// ��ȡ�ڶ��������Ľ���
		int second = progress.getSecondaryProgress();
		// ��ȡ��������������
		int max = progress.getMax();
		
	}
}
