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
		//int i = (int) collection1.get(2);//会报java.lang.ClassCastException
		List<String> collection2 = new ArrayList<String>();
		//collection2.add(1);
		//collection2.add(1L);
		collection2.add("abc");
		String element = collection2.get(0);
		System.out.println(element);
		//泛型只在编译时使用，运行时会除去类型信息
		List<Integer> collection3 = new ArrayList<Integer>();
		collection3.add(1);
		System.out.println(collection1.getClass()==collection3.getClass());//true
		//使用反射绕过泛型
		collection3.getClass().getMethod("add",Object.class).invoke(collection3, "xyz");
		System.out.println(collection3.get(1));//xyz
		printCollection(collection3);
		
		//泛型综合运用==>对HashMap进行迭代
		Map<String,Integer> maps = new HashMap<String, Integer>();
		maps.put("zxx", 30);
		maps.put("hsp", 35);
		maps.put("bxd", 28);
		Set<Map.Entry<String,Integer>> entrySet= maps.entrySet();
		for(Map.Entry<String,Integer> entry : entrySet ){
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
		
		swap(new String[]{"a","b","c"},1,2);
		//swap(new int[]{1,2,3},1,2);===>报错，泛型类型不能是基本数据类型，只能是引用数据类型
		swap(new Integer[]{1,2,3},1,2);
		
		Object obj = "abc";
		String a = autoConvert(obj);
		
		GenericDao<ReflectionPoint> dao = new GenericDao<ReflectionPoint>();
		dao.add(new ReflectionPoint(3, 5));
		ReflectionPoint rp = dao.findById(1);
	}
	
	//打印任意集合中的元素
	public static void printCollection(Collection<?> collection){
		for(Object obj : collection){
			System.out.println(obj);
		}
	}
	
	//使用另一种方式实现打印集合中的元素
	public static <T> void printCollection2(Collection<T> collection){
		for(Object obj : collection){
			System.out.println(obj);
		}
	}
	
	//自定义泛型方法，交换任意类型的数组中的两个元素的位置
	//自定义类型需要放在返回值之前
	public static <T> void swap(T[] a,int i,int j){
		T tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
	
	//自定义泛型方法，自动将Object类型的对象转换成其他类型
	private static <T> T autoConvert(Object obj){
		return (T) obj;
	}
	
	//自定义泛型方法，将任意类型的数组中的的所有元素填充为相应类型的某个对象
	private static <T> void fillArray(T[] arr,T obj){ 
		for(int i=0;i<arr.length;i++){
			arr[i] = obj;
		}
	}
}
