package com.pauu.javahence.nio;

import java.nio.IntBuffer;

public class IntBufferTest {
	public static void main(String[] args) {
		IntBuffer buf = IntBuffer.allocate(10);//分配10个大小的缓冲区
		System.out.println("1.写入数据之前的position、limit、capacity:");
		System.out.println("缓冲区操作位置position="+buf.position()+",缓冲区的限制limit="+buf.limit()
		+",缓冲区的容量capacity="+buf.capacity());
		int[] temp={5,7,9};
		buf.put(3);
		buf.put(temp);
		System.out.println("2.写入数据之后的position、limit、capacity:");
		System.out.println("缓冲区操作位置position="+buf.position()+",缓冲区的限制limit="+buf.limit()
		+",缓冲区的容量capacity="+buf.capacity());
		buf.flip();
		System.out.println("3.缓冲区重设之后的position、limit、capacity:");
		System.out.println("缓冲区操作位置position="+buf.position()+",缓冲区的限制limit="+buf.limit()
		+",缓冲区的容量capacity="+buf.capacity());
		while(buf.hasRemaining()){
			int x = buf.get();
			System.out.print(x+",");
		}
	}
}
