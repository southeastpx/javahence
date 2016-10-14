package com.pauu.javahence.jdk5;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MyClassLoader extends ClassLoader {
	private String classDir;
	
	public static void main(String[] args) throws Exception {
		String srcPath = args[0];
		String destDir = args[1];
		String destFileName = srcPath.substring(srcPath.lastIndexOf("\\")+1);
		String destPath = destDir+"\\"+destFileName;
		FileInputStream fis = new FileInputStream(srcPath);
		FileOutputStream fos = new FileOutputStream(destPath);
		cypher(fis, fos);
		fis.close();
		fos.close();
	}
	
	private static void cypher(InputStream ips,OutputStream ops) throws Exception{
		int b = -1;
		while((b=ips.read())!=-1){
			ops.write(b^0xff);
		}
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		String classFilePath = classDir + "\\"+name.substring(name.lastIndexOf(".")+1)+".class";
		FileInputStream fis = null;
		ByteArrayOutputStream bos = null;
		try {
			fis = new FileInputStream(classFilePath);
			bos = new ByteArrayOutputStream();
			cypher(fis, bos);
			System.out.println("myclassloader!!!");
			byte[] bytes = bos.toByteArray();
			fis.close();
			bos.close();
			return defineClass(bytes, 0, bytes.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.findClass(name);
	}

	
	public MyClassLoader() {
		super();
	}

	public MyClassLoader(String classDir) {
		this.classDir = classDir;
	}
	
	
}
