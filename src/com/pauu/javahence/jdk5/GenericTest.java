package com.pauu.javahence.jdk5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GenericTest {
	public static void main(String[] args) throws Exception{
		List collection1 = new ArrayList();
		collection1.add(1);
		collection1.add(1L);
		collection1.add("abc");
		//int i = (int) collection1.get(2);//�ᱨjava.lang.ClassCastException
		List<String> collection2 = new ArrayList<String>();
		//collection2.add(1);
		//collection2.add(1L);
		collection2.add("abc");
		String element = collection2.get(0);
		System.out.println(element);
		//����ֻ�ڱ���ʱʹ�ã�����ʱ���ȥ������Ϣ
		List<Integer> collection3 = new ArrayList<Integer>();
		collection3.add(1);
		System.out.println(collection1.getClass()==collection3.getClass());//true
		//ʹ�÷����ƹ�����
		collection3.getClass().getMethod("add",Object.class).invoke(collection3, "xyz");
		System.out.println(collection3.get(1));//xyz
		printCollection(collection3);
		
		//�����ۺ�����==>��HashMap���е���
		Map<String,Integer> maps = new HashMap<String, Integer>();
		maps.put("zxx", 30);
		maps.put("hsp", 35);
		maps.put("bxd", 28);
		Set<Map.Entry<String,Integer>> entrySet= maps.entrySet();
		for(Map.Entry<String,Integer> entry : entrySet ){
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
	}
	
	public static void printCollection(Collection<?> collection){
		for(Object obj : collection){
			System.out.println(obj);
		}
	}
}
