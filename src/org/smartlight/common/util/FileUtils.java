package org.smartlight.common.util;

import it.sauronsoftware.base64.Base64;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;


public class FileUtils {
public static Log log = LogFactory.getLog(FileUtils.class);
	
	public static boolean uploadFile(InputStream is, String filePath) {

		boolean retCode = false;
		byte[] buffer = new byte[1024];
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream(new File(filePath));
			
			int n = -1;
			while ((n = is.read(buffer, 0, buffer.length)) != -1) {
				fos.write(buffer, 0, n);
			}

			retCode = true;
			System.out.println("upload file success...");
		} catch (FileNotFoundException fnfe) {
			System.out.println("fnfe:" + fnfe);
		} catch (IOException ioe) {
			System.out.println("ioe:" + ioe);
		} finally {
			if (fos != null) {
				try {
					fos.close();
					fos = null;
				} catch (IOException e) {
					log.error(e);
				}
			}
			if (is != null) {
				try {
					is.close();
					is = null;
				} catch (IOException e) {
					log.error(e);
				}

			}
		}

		return retCode;
	}
	
	public static String getXmlContent(File xmlFile) {
		try {
			Document document = new SAXReader().read(xmlFile);
			return document.asXML();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public static String getFileContent(String fileName) {
		
		BufferedReader reader = null;
		StringBuilder fileContent = new StringBuilder();
		try {
			File f = new File(fileName);

			reader = new BufferedReader(new FileReader(f));
			String line = "";
			while ((line = reader.readLine()) != null) {
				fileContent.append(line);
				fileContent.append("\n"); 
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
					reader = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileContent.toString();
	}

	public static String getFileContent(InputStream is) {

		BufferedReader reader = null;
		StringBuilder fileContent = new StringBuilder();
		try {
			reader = new BufferedReader(new InputStreamReader(is));
			String line = "";
			while ((line = reader.readLine()) != null) {
				fileContent.append(line);
				fileContent.append("\n"); 
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
					reader = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileContent.toString();

	}
	
	public static boolean setFileContent(String path, String content) {
		boolean flag = false;
		DataOutputStream dos = null;
		try {
			if (content != null && content.length() >= 0) {
				byte abyte[] = content.getBytes();
				dos = new DataOutputStream(new FileOutputStream(path));
				dos.write(abyte, 0, abyte.length);
				dos.flush();

				flag = true;
			}
		} catch (FileNotFoundException e) {
			log.error("fnfe:" + e);
		} catch (IOException e) {
			log.error("ioe:" + e);
		} finally {
			if (dos != null) {
				try {
					dos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				dos = null;
			}
		}
		return flag;
	}
	
	public static String file2Base64String(String filePath, String tmpDir) {
		File file = new File(filePath);

		String tmpFilePath = tmpDir + "/" + UUID.randomUUID().toString()
				+ ".base64";
		File tmpFile = new File(tmpFilePath);

		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {
			Base64.encode(file, tmpFile);

			// Read All Base 64 File
			br = new BufferedReader(new FileReader(tmpFile));
			String tmpStr = null;
			while ((tmpStr = br.readLine()) != null) {
				sb.append(tmpStr);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
					br = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		tmpFile.delete();
		return sb.toString();

	}

    public static String getFileExt(String fileName){
    	if (fileName == null) return "";
    	
    	String ext = "";
		int lastIndex = fileName.lastIndexOf(".");
		if (lastIndex >= 0) {
			ext = fileName.substring(lastIndex + 1).toLowerCase();
		}
		
		return ext;
    }
    
	private static String hexStr =  "0123456789ABCDEF";
	
    private static String[] binaryArray =   
    {"0000","0001","0010","0011",  
    "0100","0101","0110","0111",  
    "1000","1001","1010","1011",  
    "1100","1101","1110","1111"};  
    
    public static byte[] ReversEndian(byte str[],int len,boolean big){
    	byte b;
        byte res[]=new byte[len];
        for(int i=0;i<len;i++)
        {
            res[i]=str[i];
        }
        if(big==false)
        {
            for(int i=0;i<len;i++)
            {
               b=str[i];
               res[len-i-1]=b;             
            }
        }      
        return res;
    	
    }	
    
    public static byte[] getBytes(String filename) throws IOException{
    	File f=new File(filename);
    	FileInputStream in=new FileInputStream(f);
    	int s=in.available();
    	byte[] tempBytes=new byte[s];
    	int i=0,b;
    	while((b=in.read())!=-1) {
    		tempBytes[i]=(byte) b;
    		i++;
    	}
    	in.close();
    	return tempBytes;
    }
    
    public static String getString(String filename) throws IOException{
        File file=new File(filename);
        if(!file.exists()||file.isDirectory())
            throw new FileNotFoundException();
        BufferedReader br=new BufferedReader(new FileReader(file));
        String temp=null;
        StringBuffer sb=new StringBuffer();
        temp=br.readLine();
        while(temp!=null){
            sb.append(temp+" ");
            temp=br.readLine();
        }
        return sb.toString();
    }   
    
    public void createDir(String path){
        File dir=new File(path);
        if(!dir.exists())
            dir.mkdir();
    }
    
    public void createFile(String path,String filename) throws IOException{
        File file=new File(path+"/"+filename);
        if(!file.exists())
            file.createNewFile();
    }
    
    public void delFile(String path,String filename){
        File file=new File(path+"/"+filename);
        if(file.exists()&&file.isFile())
            file.delete();
    }
    
    public void delDir(String path){
        File dir=new File(path);
        if(dir.exists()){
            File[] tmp=dir.listFiles();
            for(int i=0;i<tmp.length;i++){
                if(tmp[i].isDirectory()){
                    delDir(path+"/"+tmp[i].getName());
                }
                else{
                    tmp[i].delete();
                }
            }
            dir.delete();
        }
    }
    
    public void writeString(String filename,String content) throws IOException{
        File file=new File(filename);
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file,false);        
 
            StringBuffer sb=new StringBuffer();
            sb.append(content);
            out.write(sb.toString().getBytes("utf-8"));
        
        out.close();
    }
    
    public void appendString(String filename,String content) throws IOException{
        File file=new File(filename);
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file,true);        
  
            StringBuffer sb=new StringBuffer();
            sb.append(content);
            out.write(sb.toString().getBytes("utf-8"));
        
        out.flush();
        out.close();
    }    
    
    public void writeString(String filename,StringBuffer content) throws IOException{
        File file=new File(filename);
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file,false);        
      
            out.write(content.toString().getBytes("utf-8"));
            
        out.close();
    }  
    
    public void appendString(String filename,StringBuffer content) throws IOException{
        File file=new File(filename);
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file,true);        
   
            out.write(content.toString().getBytes("utf-8"));
             
        out.close();
    }      
    
    //复制文件
    public void copyFile(String src,String dest) throws IOException{
        FileInputStream in=new FileInputStream(src);
        File file=new File(dest);
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file);
        int c;
        byte buffer[]=new byte[1024];
        while((c=in.read(buffer))!=-1){
            for(int i=0;i<c;i++)
                out.write(buffer[i]);        
        }
        in.close();
        out.close();
    }
    //获得控制台用户输入的信息
    public String getInputMessage() throws IOException{
        System.out.println("请输入您的命令∶");
        byte buffer[]=new byte[1024];
        int count=System.in.read(buffer);
        char[] ch=new char[count-2];//最后两位为结束符，删去不要
        for(int i=0;i<count-2;i++)
            ch[i]=(char)buffer[i];
        String str=new String(ch);
        return str;
    }
    
    public static int getFileSize(String filename) throws FileNotFoundException, IOException{
        File file=new File(filename);
        if(!file.exists()){
        	return new FileInputStream(file).available();
        }
        else
        	return -1;
    }
    
    //文件重命名
    public void renameFile(String path,String oldname,String newname){
        if(!oldname.equals(newname)){//新的文件名和以前文件名不同时,才有必要进行重命名
            File oldfile=new File(path+"/"+oldname);
            File newfile=new File(path+"/"+newname);
            if(newfile.exists())//若在该目录下已经有一个文件和新文件名相同，则不允许重命名
                System.out.println(newname+"已经存在！");
            else{
                oldfile.renameTo(newfile);
            }
        }         
    } 
    //转移文件目录
    public void changeDirectory(String filename,String oldpath,String newpath,boolean cover){
        if(!oldpath.equals(newpath)){
            File oldfile=new File(oldpath+"/"+filename);
            File newfile=new File(newpath+"/"+filename);
            if(newfile.exists()){//若在待转移目录下，已经存在待转移文件
                if(cover)//覆盖
                    oldfile.renameTo(newfile);
                else
                    System.out.println("在新目录下已经存在："+filename);
            }
            else{
                oldfile.renameTo(newfile);
            }
        }       
    } 
    
    /** 
     *  
     * @param bytes 
     * @return 将二进制转换为十六进制字符输出 
     */  
    public static String BinaryToHexString(byte[] bytes){  
          
        String result = "";  
        String hex = "";  
        for(int i=0;i<bytes.length;i++){  
            //字节高4位  
            hex = String.valueOf(hexStr.charAt((bytes[i]&0xF0)>>4));  
            //字节低4位  
            hex += String.valueOf(hexStr.charAt(bytes[i]&0x0F));  
            result +=hex;  
        }  
        return result;  
    }    
    
    /** 
     *  
     * @param hexString 
     * @return 将十六进制转换为字节数组 
     */  
    public static byte[] HexStringToBinary(String hexString){  
        //hexString的长度对2取整，作为bytes的长度  
        int len = hexString.length()/2;  
        byte[] bytes = new byte[len];  
        byte high = 0;//字节高四位  
        byte low = 0;//字节低四位  
  
        for(int i=0;i<len;i++){  
             //右移四位得到高位  
             high = (byte)((hexStr.indexOf(hexString.charAt(2*i)))<<4);  
             low = (byte)hexStr.indexOf(hexString.charAt(2*i+1));  
             bytes[i] = (byte) (high|low);//高地位做或运算  
        }  
        return bytes;  
    }  
    
    /** 
     *  
     * @param str 
     * @return 转换为二进制字符串 
     */  
    public static String bytes2BinaryStr(byte[] bArray){  
          
        String outStr = "";  
        int pos = 0;  
        for(byte b:bArray){  
            //高四位  
            pos = (b&0xF0)>>4;  
            outStr+=binaryArray[pos];  
            //低四位  
            pos=b&0x0F;  
            outStr+=binaryArray[pos];  
        }  
        return outStr;  
          
    }     
    
    /** 
    * 将两个ASCII字符合成一个字节； 
    * 如："EF"--> 0xEF 
    * @param src0 byte 
    * @param src1 byte 
    * @return byte 
    */ 
    public static byte uniteBytes(byte src0, byte src1) { 
    byte _b0 = Byte.decode("0x" + new String(new byte[]{src0})).byteValue(); 
    _b0 = (byte)(_b0 << 4); 
    byte _b1 = Byte.decode("0x" + new String(new byte[]{src1})).byteValue(); 
    byte ret = (byte)(_b0 ^ _b1); 
    return ret; 
    }     
    
    //读取properties的全部信息
    public static Properties readProperties(String filePath) {
    	Properties props = new Properties();
        try {
	         InputStream in = new BufferedInputStream (new FileInputStream(filePath));
	         props.load(in);
        } catch (Exception e) {
         e.printStackTrace();
        }
        return props;
    }
    
}
