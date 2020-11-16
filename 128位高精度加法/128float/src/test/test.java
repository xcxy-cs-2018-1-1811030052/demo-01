package test;

import java.util.Random;
import java.util.Scanner;

public class test {
	public static String addBigfloat(String sa,String sb) {
		if(!sa.contains(".")) {
			sa = sa +".0";
		}
		if(!sb.contains(".")) {
			sb = sb +".0";
		}
		String sc,sd;
		String result ="";
		int ia =sa.indexOf('.',0);
		int ib =sb.indexOf('.',0);
		sc=(ia>ib)?sa:sb;//�������ֳ��ĸ�ֵ��sc
		sd=(ia>ib)?sb:sa;//�������̵ֶĸ�ֵ��sd
		
		int n = (ia>ib)?(ia-ib):(ib-ia);//�������ֳ��ȵĲ�ֵ
		
		for(int i=0;i<n;i++) {
			sd="0"+sd;
		}
		int lenc = sc.length();//������������ֳ��ĵĳ���
		int lend = sd.length();//������������̵ֶĳ���
		sa=(lenc>lend)?sc:sd;
		sb=(lenc>lend)?sd:sc;
		n=(lenc>lend)?(lenc-lend):(lend-lenc);//С�����ֳ��ȵĲ�ֵ
		for(int i=0;i<n;i++) {
			sb=sb+"0";
		}//���˶Խ����
		
		int carry=0;//
		for(int i=sa.length()-1;i>=0;i--) {
			if(sa.charAt(i)=='.') {
				result = "."+result;
				continue;
			}
			char value = (char) ((sa.charAt(i)-'0')+(sb.charAt(i)-'0')+carry);//�õ��ַ���Ӻ�Ľ��
			result = (char)(value%10+'0')+result;//��������뵽����ַ�����
			carry=value/10;	
		}	//�����λ
		
		while(carry!=0) {		//����һ���ַ����н�λ
			result = (char)(carry%10+'0')+result;
			carry/=10;
		}
		return result;
	}
	public static String subBigfloat(String sa,String sb) {
		if(!sa.contains(".")) {
			sa = sa +".0";
		}
		if(!sb.contains(".")) {
			sb = sb +".0";
		}
		String sc,sd;
		String result ="";
		int ia =sa.indexOf('.',0);
		int ib =sb.indexOf('.',0);
		int flag=0;
		sc=(ia>ib)?sa:sb;//�������ֳ���
		sd=(ia>ib)?sb:sa;//�������̵ֶ�
		
		int n = (ia>ib)?(ia-ib):(ib-ia);//�������ֳ��ȵĲ�ֵ
		
		for(int i=0;i<n;i++) {
			sd="0"+sd;
		}
		int lenc = sc.length();//����������ĳ���
		int lend = sd.length();//����������ĳ���
		sa=(lenc>lend)?sc:sd;
		sb=(lenc>lend)?sd:sc;
		n=(lenc>lend)?(lenc-lend):(lend-lenc);
		for(int i=0;i<n;i++) {
			sb=sb+"0";
		}//���˶Խ����
		if(!compare(sa,sb)) {
			flag=1;
			String s3="";
			s3=sa;
			sa=sb;
			sb=s3;
		}
		if(sa==sb) {
			return "0";
		}else {
		int carry=10;//
		StringBuilder s1=new StringBuilder(sa);
		StringBuilder s2=new StringBuilder(sb);
		for(int i=s1.length()-1;i>=0;i--) {
			if(s1.charAt(i)=='.') {
				result = "."+result;
				continue;
			}
			int value = (int) ((s1.charAt(i)-'0')-(s2.charAt(i)-'0')+carry);//�õ��ַ������Ľ��
			result = (char)(value%10+'0')+result;//��������뵽����ַ�����
			if((s1.charAt(i)-'0')<(s2.charAt(i)-'0')) {
				if(s1.charAt(i-1)=='.') {
					s1.replace(i-2, i-1, (s1.charAt(i-2)-'0'-1)+"");
					
				}else {
					s1.replace(i-1, i, (s1.charAt(i-1)-'0'-1)+"");
				}
			}
			
			//carry=value/10;	
		}	//�����λ
		//��������0λ
//		for(int i=0;i<result.length();i++) {
//			if(result.charAt(i)=='0'&&result.charAt(i+1)!='.') {
//				result = result.substring(i+1);
//			}else if(result.charAt(i)=='0'&&result.charAt(i+1)=='.') {
//				break;
//			}else if(result.charAt(i)=='0'&&result.charAt(i+1)!='0') {
//				result =result.substring(i+1);
//			}
//		}
		if(flag==0) {
			return result;
		}else if(flag==1){
			return result;
		}
		return result;
		}
	}
	public static boolean compare(String a,String b) {//���a>=b,����true;���򷵻�false;
		if(!a.contains(".")) {
			a = a +".0";
		}
		if(!b.contains(".")) {
			b = b +".0";
		}
		int lena =a.indexOf('.',0);
		int lenb =b.indexOf('.',0);
		if(lena!=lenb)return lena>lenb; 
		for(int i=0;i<lena;i++) {
			if(a.charAt(i)!=b.charAt(i))return a.charAt(i)>b.charAt(i);
		}
		for(int i=lena+1;i<=a.length();i++) {
			if(a.charAt(i)!=b.charAt(i))return a.charAt(i)>b.charAt(i);
		}
		return true;
	}
	public static String getRandomString(int length) {
	    String str = "0123456789";
	    Random random = new Random();
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < length; i++) {
	        int number = random.nextInt(str.length());
	        sb.append(str.charAt(number));
	    }
	    return sb.toString();
	}
}
