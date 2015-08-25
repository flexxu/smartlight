package org.smartlight.common.util;

import java.io.*;
import java.net.*;

/**
 * Http Client
 */

public class HttpClient {
	
	public static String getHtmlCode(String url,String charsets){
		String content="";
		try{
			URL httpurl = new URL(url);  
			String protocol = httpurl.getProtocol(); 
			if (!protocol.equals("http"))   
				throw new IllegalArgumentException("Must use 'http:' protocol");
			if(charsets==null || charsets.equals(""))
				charsets="UTF-8";
			BufferedReader ii = new BufferedReader(new InputStreamReader(httpurl.openStream(),charsets)); // //使用openStream得到一输入流并由此构造一个BufferedReader对象 
			String input; 
			while ((input = ii.readLine()) != null) { 
				// 建立读取循环，并判断是否有读取值 
				content += input; 
			} 
			ii.close();			
			
		}catch(Exception e){
			System.err.println(e);
		}
		
		return content;
	}
	
	public static void main(String[] args) { 
		try {  
			// 检查命令行参数 
			if ((args.length != 1) && (args.length != 2))  
				throw new IllegalArgumentException("Wrong number of args");   
			OutputStream to_file; 
			if (args.length == 2)   
				to_file = new FileOutputStream(args[1]);//输出到文件 
			else   
				to_file = System.out;//输出到控制台    
			URL url = new URL(args[0]);  
			String protocol = url.getProtocol(); 
			if (!protocol.equals("http"))   
				throw new IllegalArgumentException("Must use 'http:' protocol"); 
			String host = url.getHost(); 
			int port = url.getPort(); 
			if (port == -1) port = 80;   
			String filename = url.getFile();  
			Socket socket = new Socket(host, port);//打开一个socket连接 
			InputStream from_server = socket.getInputStream();//获取输入流  
			PrintWriter to_server = new PrintWriter(socket.getOutputStream());//获取输出流   
			to_server.print("GET " + filename + "\n\n");//请求服务器上的文件 
			to_server.flush(); 
			// Send it right now!   
			byte[] buffer = new byte[4096]; 
			int bytes_read;  //读服务器上的响应，并写入文件。  
			while((bytes_read = from_server.read(buffer)) != -1) 
				to_file.write(buffer, 0, bytes_read);   
			socket.close(); 
			to_file.close(); 
		}  catch (Exception e) {  
			System.err.println(e);  
			System.err.println("Usage: java HttpClient <URL> [<filename>]"); 
		} 
	}

}