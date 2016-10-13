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
		
		swap(new String[]{"a","b","c"},1,2);
		//swap(new int[]{1,2,3},1,2);===>�����������Ͳ����ǻ����������ͣ�ֻ����������������
		swap(new Integer[]{1,2,3},1,2);
		
		Object obj = "abc";
		String a = autoConvert(obj);
		
		GenericDao<ReflectionPoint> dao = new GenericDao<ReflectionPoint>();
		dao.add(new ReflectionPoint(3, 5));
		ReflectionPoint rp = dao.findById(1);
	}
	
	//��ӡ���⼯���е�Ԫ��
	public static void printCollection(Collection<?> collection){
		for(Object obj : collection){
			System.out.println(obj);
		}
	}
	
	//ʹ����һ�ַ�ʽʵ�ִ�ӡ�����е�Ԫ��
	public static <T> void printCollection2(Collection<T> collection){
		for(Object obj : collection){
			System.out.println(obj);
		}
	}
	
	//�Զ��巺�ͷ����������������͵������е�����Ԫ�ص�λ��
	//�Զ���������Ҫ���ڷ���ֵ֮ǰ
	public static <T> void swap(T[] a,int i,int j){
		T tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
	
	//�Զ��巺�ͷ������Զ���Object���͵Ķ���ת������������
	private static <T> T autoConvert(Object obj){
		return (T) obj;
	}
	
	//�Զ��巺�ͷ��������������͵������еĵ�����Ԫ�����Ϊ��Ӧ���͵�ĳ������
	private static <T> void fillArray(T[] arr,T obj){ 
		for(int i=0;i<arr.length;i++){
			arr[i] = obj;
		}
	}
}
