package com.example.mathsapp;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
public class KuohaoCalc {
	 private static int intercePosition = 0; // 记录单个运算数据的长度
	 static DecimalFormat decimal = new DecimalFormat("#.##");

	 // 判断是否有带括号的运算字符串存在
	 public String interceResult(String str) {
	  String finalresult;
	  String result = str;
	  char[] numberString = str.toCharArray(); 
	  int IndexStart = 0; // 记录“（”的实际数量
	  int EndStart = 0; // 记录“）”的实际数量
	  for (int i = 0; i < numberString.length; i++) {
	   if ('(' == numberString[i]) {
	    // 记录最后一个正括号的位置
	    IndexStart = i;
	   }
	   if (')' == numberString[i]) {
	    // 记录反括号的最初始下标的位置
	    EndStart = i;

	    // 截取最里面一个括号里的运算字符串
	    result = result.substring(IndexStart + 1, EndStart);

	    // 截取括号的运算字符串进行运算,生成新的运算字符串
	    result = str.substring(0, IndexStart)
	      + interceptOperation(result, '*', '÷')
	      + str.substring(EndStart + 1, str.length());

	    // 回调执行，其它小括号的运算字符串
	    return interceResult(result);
	   }
	   if (i == numberString.length - 1)
	    if (EndStart == 0)
	     break;
	  }
	  // 不存在括号了，再进行混合运算
	  result = interceptOperation(str, '*', '÷');
	  double res=Double.parseDouble(result);
	  decimal.setRoundingMode(RoundingMode.HALF_UP);//四舍五入
	  finalresult=String.valueOf(decimal.format(res));
	  return finalresult;
	 }

	 // 不带括号的四则运算
	 private static String interceptOperation(String operationNumber, char a,
	   char b) {
	  String mess = operationNumber;
	  char[] stringOperation = mess.toCharArray();

	  // 循环遍历运算字符串，并做相应的运算
	  for (int i = 0; i < stringOperation.length; i++) {

	   // 判断运算符所在的索引
	   if (stringOperation[i] == a || stringOperation[i] == b) {
	    if (i != 0) {
	     // 运算符前的第一个数
	     double num1 = interceptNumIndex(mess.substring(0, i));

	     // 记录第一个数据的长度
	     int frontPosition = intercePosition;

	     // 运算符前的第二个数
	     double num2 = interceptNumEnd(mess.substring(i + 1,
	       stringOperation.length));

	     // 记录第二个数据的长度
	     int backPosition = intercePosition;

	     // 算完乘除，将结果替换到原来运算的位置，得到新的运算字符串
	     String IndexMess = mess.substring(0, i - frontPosition + 1);
	     String IndexResult = "";

	     // 判断是否运算到最后的结果了
	     if (IndexMess.indexOf('+') == -1
	       && IndexMess.indexOf('*') == -1
	       && IndexMess.indexOf('÷') == -1
	       && IndexMess.lastIndexOf('-') == -1)
	      IndexMess = "";
	     if (IndexMess != "")
	      IndexResult = IndexMess.lastIndexOf('-') == IndexMess
	        .length() - 1 ? IndexMess.substring(0, i
	        - frontPosition) : IndexMess;

	     // 组装新的运算字符串
	     mess = IndexResult// mess.substring(0,i-frontPosition+1)
	       + reslutString("" + stringOperation[i], num1, num2)
	       + mess.substring(i + backPosition + 1);
	     // 0.111/1212/2/2/2/2/2/2/2
	     if (mess.lastIndexOf('-') == 0 && mess.indexOf('+') == -1
	       && mess.indexOf('*') == -1
	       && mess.indexOf('÷') == -1) {
	      break;
	     }
	     // 回调，继续运算
	     return interceptOperation(mess, a, b);// 1+7-5+89/3+4-6*8/2+4-6
	    } else
	     continue;
	   }
	   if (i == stringOperation.length - 1) {
	    // 递归出口，判断是否还有运算字符串在
	    if (mess.indexOf('+') != -1 || mess.indexOf('-') != -1)
	     return interceptOperation(mess, '+', '-');
	    break;
	   }
	  }
	  return mess;
	 }

	 // 截取第二个数
	 private static double interceptNumEnd(String str) {
	  double a = 0;
	  int InrerceIndex = 0;
	  char[] stringOperation = str.toCharArray();
	  boolean ispas = false; // 记录是否为负数
	  for (int i = 0; i < stringOperation.length; i++) {
	   switch (stringOperation[i]) {
	   case '*':
	   case '÷':
	   case '+':
	   case '-':
	    InrerceIndex = i;
	    if (i != 0) // 判断该数是否为负数
	     ispas = true;
	    break;
	   default:
	    break;
	   }
	   if (ispas)
	    break;
	  }
	  // 判断此数据是否在运算字符串的最后一位
	  if (InrerceIndex == 0) {
	   a = Double.parseDouble(str);
	   intercePosition = str.length();
	   if (ispas)
	    intercePosition++;

	  } else {
	   a = Double.parseDouble(str.substring(0, InrerceIndex));
	   // 记录数据的真实长度
	   intercePosition = str.substring(0, InrerceIndex).length();
	  }
	  return a;
	 }

	 // 截取第一个数
	 private static double interceptNumIndex(String str) {
	  double a = 0; // 记录数据
	  int InrerceIndex = 0; // 记录运算符的位置
	  boolean temp = false; // 记录数据前运算符的状态
	  char[] stringOperation = str.toCharArray();
	  for (int i = stringOperation.length - 1; i >= 0; i--) {
	   switch (stringOperation[i]) {
	   case '*':
	   case '÷':

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
	  // 判断此数据是否在运算字符串的第一位
	  if (InrerceIndex == 0) {
	   a = Double.parseDouble(str);
	   intercePosition = str.length();
	   // if(temp)
	   // intercePosition++;
	  } else {
	   a = Double.parseDouble(str.substring(InrerceIndex, str.length()));
	   // 记录数据的真实长度
	   intercePosition = str.substring(InrerceIndex, str.length())
	     .length();
	  }
	  return a;
	 }

	 // 计算结果
	 private static double reslutString(String operation, double num1,
	   double num2) {
	  double sumResult = 0;
	  if (operation.equals("*"))
	   sumResult = num1 * num2;
	  if (operation.equals("-"))
	   sumResult = num1 - num2;
	  if (operation.equals("÷"))
	   sumResult = num1 / num2;
	  if (operation.equals("+"))
	   sumResult = num1 + num2;
	  return sumResult;
	 }

	
	}