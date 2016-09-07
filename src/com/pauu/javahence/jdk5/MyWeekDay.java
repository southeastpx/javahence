package com.pauu.javahence.jdk5;
/**
 * ʹ����ͨ��ģ��ö��
 * @author peng.xing
 *
 */
public abstract class MyWeekDay {
	private MyWeekDay(){}
	public static final MyWeekDay SUN = new MyWeekDay(){
		@Override
		public MyWeekDay nextDay() {
			return MON;
		}
	}; 
	public static final MyWeekDay MON = new MyWeekDay(){
		@Override
		public MyWeekDay nextDay() {
			return SUN;
		}
	}; 
	public abstract MyWeekDay nextDay(); 
	/*public WeekDay nextDay(){
		if(this==SUN){
			return MON;
		}else{
			return SUN;
		}
	}*/
	public String toString(){
		return this==SUN?"SUN":"MON";
	}
}
