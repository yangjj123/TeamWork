package com.example.mathsapp;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
public class KuohaoCalc {
	 private static int intercePosition = 0; // ��¼�����������ݵĳ���
	 static DecimalFormat decimal = new DecimalFormat("#.##");

	 // �ж��Ƿ��д����ŵ������ַ�������
	 public String interceResult(String str) {
	  String finalresult;
	  String result = str;
	  char[] numberString = str.toCharArray(); 
	  int IndexStart = 0; // ��¼��������ʵ������
	  int EndStart = 0; // ��¼��������ʵ������
	  for (int i = 0; i < numberString.length; i++) {
	   if ('(' == numberString[i]) {
	    // ��¼���һ�������ŵ�λ��
	    IndexStart = i;
	   }
	   if (')' == numberString[i]) {
	    // ��¼�����ŵ����ʼ�±��λ��
	    EndStart = i;

	    // ��ȡ������һ��������������ַ���
	    result = result.substring(IndexStart + 1, EndStart);

	    // ��ȡ���ŵ������ַ�����������,�����µ������ַ���
	    result = str.substring(0, IndexStart)
	      + interceptOperation(result, '*', '��')
	      + str.substring(EndStart + 1, str.length());

	    // �ص�ִ�У�����С���ŵ������ַ���
	    return interceResult(result);
	   }
	   if (i == numberString.length - 1)
	    if (EndStart == 0)
	     break;
	  }
	  // �����������ˣ��ٽ��л������
	  result = interceptOperation(str, '*', '��');
	  double res=Double.parseDouble(result);
	  decimal.setRoundingMode(RoundingMode.HALF_UP);//��������
	  finalresult=String.valueOf(decimal.format(res));
	  return finalresult;
	 }

	 // �������ŵ���������
	 private static String interceptOperation(String operationNumber, char a,
	   char b) {
	  String mess = operationNumber;
	  char[] stringOperation = mess.toCharArray();

	  // ѭ�����������ַ�����������Ӧ������
	  for (int i = 0; i < stringOperation.length; i++) {

	   // �ж���������ڵ�����
	   if (stringOperation[i] == a || stringOperation[i] == b) {
	    if (i != 0) {
	     // �����ǰ�ĵ�һ����
	     double num1 = interceptNumIndex(mess.substring(0, i));

	     // ��¼��һ�����ݵĳ���
	     int frontPosition = intercePosition;

	     // �����ǰ�ĵڶ�����
	     double num2 = interceptNumEnd(mess.substring(i + 1,
	       stringOperation.length));

	     // ��¼�ڶ������ݵĳ���
	     int backPosition = intercePosition;

	     // ����˳���������滻��ԭ�������λ�ã��õ��µ������ַ���
	     String IndexMess = mess.substring(0, i - frontPosition + 1);
	     String IndexResult = "";

	     // �ж��Ƿ����㵽���Ľ����
	     if (IndexMess.indexOf('+') == -1
	       && IndexMess.indexOf('*') == -1
	       && IndexMess.indexOf('��') == -1
	       && IndexMess.lastIndexOf('-') == -1)
	      IndexMess = "";
	     if (IndexMess != "")
	      IndexResult = IndexMess.lastIndexOf('-') == IndexMess
	        .length() - 1 ? IndexMess.substring(0, i
	        - frontPosition) : IndexMess;

	     // ��װ�µ������ַ���
	     mess = IndexResult// mess.substring(0,i-frontPosition+1)
	       + reslutString("" + stringOperation[i], num1, num2)
	       + mess.substring(i + backPosition + 1);
	     // 0.111/1212/2/2/2/2/2/2/2
	     if (mess.lastIndexOf('-') == 0 && mess.indexOf('+') == -1
	       && mess.indexOf('*') == -1
	       && mess.indexOf('��') == -1) {
	      break;
	     }
	     // �ص�����������
	     return interceptOperation(mess, a, b);// 1+7-5+89/3+4-6*8/2+4-6
	    } else
	     continue;
	   }
	   if (i == stringOperation.length - 1) {
	    // �ݹ���ڣ��ж��Ƿ��������ַ�����
	    if (mess.indexOf('+') != -1 || mess.indexOf('-') != -1)
	     return interceptOperation(mess, '+', '-');
	    break;
	   }
	  }
	  return mess;
	 }

	 // ��ȡ�ڶ�����
	 private static double interceptNumEnd(String str) {
	  double a = 0;
	  int InrerceIndex = 0;
	  char[] stringOperation = str.toCharArray();
	  boolean ispas = false; // ��¼�Ƿ�Ϊ����
	  for (int i = 0; i < stringOperation.length; i++) {
	   switch (stringOperation[i]) {
	   case '*':
	   case '��':
	   case '+':
	   case '-':
	    InrerceIndex = i;
	    if (i != 0) // �жϸ����Ƿ�Ϊ����
	     ispas = true;
	    break;
	   default:
	    break;
	   }
	   if (ispas)
	    break;
	  }
	  // �жϴ������Ƿ��������ַ��������һλ
	  if (InrerceIndex == 0) {
	   a = Double.parseDouble(str);
	   intercePosition = str.length();
	   if (ispas)
	    intercePosition++;

	  } else {
	   a = Double.parseDouble(str.substring(0, InrerceIndex));
	   // ��¼���ݵ���ʵ����
	   intercePosition = str.substring(0, InrerceIndex).length();
	  }
	  return a;
	 }

	 // ��ȡ��һ����
	 private static double interceptNumIndex(String str) {
	  double a = 0; // ��¼����
	  int InrerceIndex = 0; // ��¼�������λ��
	  boolean temp = false; // ��¼����ǰ�������״̬
	  char[] stringOperation = str.toCharArray();
	  for (int i = stringOperation.length - 1; i >= 0; i--) {
	   switch (stringOperation[i]) {
	   case '*':
	   case '��':

	   case '+':
	   case '-':
	    InrerceIndex = i;
	    temp = true;
	    break;
	   default:
	    break;
	   }
	   if (temp)
	    break;
	  }
	  // �жϴ������Ƿ��������ַ����ĵ�һλ
	  if (InrerceIndex == 0) {
	   a = Double.parseDouble(str);
	   intercePosition = str.length();
	   // if(temp)
	   // intercePosition++;
	  } else {
	   a = Double.parseDouble(str.substring(InrerceIndex, str.length()));
	   // ��¼���ݵ���ʵ����
	   intercePosition = str.substring(InrerceIndex, str.length())
	     .length();
	  }
	  return a;
	 }

	 // ������
	 private static double reslutString(String operation, double num1,
	   double num2) {
	  double sumResult = 0;
	  if (operation.equals("*"))
	   sumResult = num1 * num2;
	  if (operation.equals("-"))
	   sumResult = num1 - num2;
	  if (operation.equals("��"))
	   sumResult = num1 / num2;
	  if (operation.equals("+"))
	   sumResult = num1 + num2;
	  return sumResult;
	 }

	
	}