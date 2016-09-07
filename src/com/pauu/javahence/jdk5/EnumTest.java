package com.pauu.javahence.jdk5;

public class EnumTest {
	public static void main(String[] args) {
		MyWeekDay myWeekDay = MyWeekDay.SUN;
		System.out.println(myWeekDay.nextDay());
		WeekDay weekDay = WeekDay.FRI;
		System.out.println(weekDay);
		System.out.println(weekDay.name());
		System.out.println(weekDay.ordinal());
		System.out.println(WeekDay.valueOf("SUN"));
		System.out.println(WeekDay.values().length);
		TrafficLamp trafficLamp = TrafficLamp.RED;
		System.out.println(trafficLamp.nextLamp());
	}
	
	//使用枚举
	public enum WeekDay{
		SUN("星期日"),MON,TUE,WED,THI,FRI,SAT;
		private WeekDay(){
			System.out.println("first");
		}
		private WeekDay(String name){
			System.out.println("second:"+name);
		}
	}
	
	//枚举实现交通灯
	public enum TrafficLamp{
		RED(20){
			@Override
			public TrafficLamp nextLamp() {
				return GREEN;
			}
		},
		GREEN(30){
			@Override
			public TrafficLamp nextLamp() {
				return YELLOW;
			}
		},
		YELLOW(5){
			@Override
			public TrafficLamp nextLamp() {
				return RED;
			}
		};
		private TrafficLamp(){}
		private int time;
		private TrafficLamp(int time){
			this.time = time;
		}
		public abstract TrafficLamp nextLamp();
	}
}
