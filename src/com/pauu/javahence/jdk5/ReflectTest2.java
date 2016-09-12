package com.pauu.javahence.jdk5;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;

/**
 * �Ƚ�ArrayList��HashSet
 * �ܽ᣺List==>����ģ������ظ��� Set:����ģ��������ظ���ͨ��equals()��hashCode()�����ж�Ԫ���Ƿ����
 * @author peng.xing
 *
 */
public class ReflectTest2 {
	public static void main(String[] args) throws Exception {
		//InputStream ips = new FileInputStream("config.properties");
		//ʹ������������������ļ�
		//����һ��InputStream ips = ReflectTest2.class.getClassLoader().getResourceAsStream("com/pauu/javahence/jdk5/config.properties");
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
		System.out.println("ReflectionPoint����δ����equals()��hashCode()ʱ��ArrayList�Ĵ�С��"+arrayList.size());//4
		System.out.println("ReflectionPoint����δ����equals()��hashCode()ʱ��HashSet�Ĵ�С��"+hashSet.size());//3
		System.out.println("ReflectionPoint���м���equals()��hashCode()��ArrayList�Ĵ�С��"+arrayList.size());//4
		System.out.println("ReflectionPoint���м���equals()��hashCode()��HashSet�Ĵ�С��"+hashSet.size());//2
		
		
	}
}
