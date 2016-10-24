package com.pauu.javahence.nio;

import java.nio.IntBuffer;

public class IntBufferTest {
	public static void main(String[] args) {
		IntBuffer buf = IntBuffer.allocate(10);//����10����С�Ļ�����
		System.out.println("1.д������֮ǰ��position��limit��capacity:");
		System.out.println("����������λ��position="+buf.position()+",������������limit="+buf.limit()
		+",������������capacity="+buf.capacity());
		int[] temp={5,7,9};
		buf.put(3);
		buf.put(temp);
		System.out.println("2.д������֮���position��limit��capacity:");
		System.out.println("����������λ��position="+buf.position()+",������������limit="+buf.limit()
		+",������������capacity="+buf.capacity());
		buf.flip();
		System.out.println("3.����������֮���position��limit��capacity:");
		System.out.println("����������λ��position="+buf.position()+",������������limit="+buf.limit()
		+",������������capacity="+buf.capacity());
		while(buf.hasRemaining()){
			int x = buf.get();
			System.out.print(x+",");
		}
	}
}
