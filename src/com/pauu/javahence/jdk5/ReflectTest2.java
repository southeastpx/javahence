package com.pauu.javahence.jdk5;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;

/**
 * 比较ArrayList和HashSet
 * 总结：List==>有序的，可以重复。 Set:无序的，不可以重复，通过equals()和hashCode()方法判断元素是否相等
 * @author peng.xing
 *
 */
public class ReflectTest2 {
	public static void main(String[] args) throws Exception {
		//InputStream ips = new FileInputStream("config.properties");
		//使用类加载器加载配置文件
		//方法一：InputStream ips = ReflectTest2.class.getClassLoader().getResourceAsStream("com/pauu/javahence/jdk5/config.properties");
		InputStream ips = ReflectTest2.class.getResourceAsStream("config.properties");
		Properties props = new Properties();
		props.load(ips);
		ips.close();
		String className1 = props.getProperty("className1");
		String className2 = props.getProperty("className2");
		Collection arrayList = (Collection) Class.forName(className1).newInstance();
		Collection hashSet = (Collection) Class.forName(className2).newInstance();
		/*Collection arrayList = new ArrayList();
		Collection hashSet = new HashSet();*/
		ReflectionPoint pt1 = new ReflectionPoint(3, 3);
		ReflectionPoint pt2 = new ReflectionPoint(5, 5);
		ReflectionPoint pt3 = new ReflectionPoint(3, 3);
		arrayList.add(pt1);
		arrayList.add(pt2);
		arrayList.add(pt3);
		arrayList.add(pt1);
		hashSet.add(pt1);
		hashSet.add(pt2);
		hashSet.add(pt3);
		hashSet.add(pt1);
		System.out.println("ReflectionPoint类中未加入equals()和hashCode()时，ArrayList的大小："+arrayList.size());//4
		System.out.println("ReflectionPoint类中未加入equals()和hashCode()时，HashSet的大小："+hashSet.size());//3
		System.out.println("ReflectionPoint类中加入equals()和hashCode()后，ArrayList的大小："+arrayList.size());//4
		System.out.println("ReflectionPoint类中加入equals()和hashCode()后，HashSet的大小："+hashSet.size());//2
		
		
	}
}
