package com.hb.util.java2xml.xstream;

import org.apache.log4j.Logger;

import com.hb.models.StudentInfo;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * 输出xml和解析xml的工具类
 *@XstreamXmlUtil
 *@author: chenyoulong  Email: chen.youlong@payeco.com
 *@date :2012-9-29 上午9:51:28
 *@Description:依赖jar包 ：xstream-1.4.7.jar；xmlpull-1.1.3.1.jar；=xpp3_min-1.1.4c.jar ；
 */
public class XstreamXmlUtil{
	/**
	 * Logger for this class
	 */
	private static final Logger log = Logger.getLogger(XstreamXmlUtil.class);
	
	public static final String UTF8 = "utf-8";

    /**
     * java 转换成xml
     * @Title: toXml 
     * @Description: TODO 
     * @param obj 对象实例
     * @return String xml字符串
     */
    public static String toXml(Object obj){
//        XStream xstream=new XStream();
//      XStream xstream=new XStream(new DomDriver()); //直接用jaxp dom来解释
      XStream xstream=new XStream(new DomDriver(UTF8)); //指定编码解析器,直接用jaxp dom来解释
         
        ////如果没有这句，xml中的根元素会是<包.类名>；或者说：注解根本就没生效，所以的元素名就是类的属性
        xstream.processAnnotations(obj.getClass()); //通过注解方式的，一定要有这句话
        return xstream.toXML(obj);
    }
     
    /**
     *  将传入xml文本转换成Java对象
     * @Title: toBean 
     * @Description: TODO 
     * @param xmlStr
     * @param cls  xml对应的class类
     * @return T   xml对应的class类的实例对象
     * 
     * 调用的方法实例：PersonBean person=XmlUtil.toBean(xmlStr, PersonBean.class);
     */
    public static <T> T  toBean(String xmlStr,Class<T> cls){
        //注意：不是new Xstream(); 否则报错：java.lang.NoClassDefFoundError: org/xmlpull/v1/XmlPullParserFactory
        XStream xstream=new XStream(new DomDriver(UTF8));
        xstream.processAnnotations(cls);
        T obj=(T)xstream.fromXML(xmlStr);
        return obj;         
    } 

   /**
     * 写到xml文件中去
     * @Title: writeXMLFile 
     * @Description: TODO 
     * @param obj 对象
     * @param absPath 绝对路径
     * @param fileName  文件名
     * @return boolean
     */
   
    public static boolean toXMLFile(Object obj, String absPath, String fileName ){
    
        String filePath = absPath + fileName;
        return toXMLFile(obj,filePath);
    }
    
    /**
     * 写到xml文件中去
     * @Title: writeXMLFile 
     * @Description: TODO 
     * @param obj 对象
     * @param absPath 绝对路径
     * @param fileName  文件名
     * @return boolean
     */
   
    public static boolean toXMLFile(Object obj,String fileName ){
        return toXMLFile(obj,new File(fileName));
    }
    
    /**
     * 写到xml文件中去
     * @Title: writeXMLFile 
     * @Description: TODO 
     * @param obj 对象
     * @param absPath 绝对路径
     * @param fileName  文件名
     * @return boolean
     */
   
    public static boolean toXMLFile(Object obj,File file){
        
        if(file == null)
        {
        	 log.error("创建{}文件失败!!!");
             return false ;
        }
        
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                log.error("创建{"+ file.toURI().toString() +"}文件失败!!!" + e.getMessage());
                return false ;
            }
        }// end if 
        
        OutputStream ous = null ;
        try {
        	String strXml = toXml(obj);
            ous = new FileOutputStream(file);
            ous.write(strXml.getBytes());
            ous.flush();
        } catch (Exception e1) {
            log.error("写{"+ file.toURI().toString() +"}文件失败!!!" + e1.getMessage());
            return false;
        }finally{
            if(ous != null )
                try {
                    ous.close();
                } catch (IOException e) {
                    log.error("写{"+ file.toURI().toString() +"}文件关闭输出流异常!!!" + e.getMessage());
                }
        }
        return true ;
    }

     
    /**
     * 从xml文件读取报文
     * @Title: toBeanFromFile 
     * @Description: TODO 
     * @param absPath 绝对路径
     * @param fileName 文件名
     * @param cls
     * @throws Exception 
     * @return T
     */
    public static <T> T  toBeanFromFile(String absPath, String fileName,Class<T> cls) throws Exception{
        String filePath = absPath +fileName;
        return toBeanFromFile(filePath,cls);       
    } 
    
    /**
     * 从xml文件读取报文
     * @Title: toBeanFromFile 
     * @Description: TODO 
     * @param absPath 绝对路径
     * @param fileName 文件名
     * @param cls
     * @throws Exception 
     * @return T
     */
    public static <T> T  toBeanFromFile(String fileName,Class<T> cls) throws Exception{
        return toBeanFromFile(new File(fileName),cls);         
    } 
    
    /**
     * 从xml文件读取报文
     * @Title: toBeanFromFile 
     * @Description: TODO 
     * @param absPath 绝对路径
     * @param fileName 文件名
     * @param cls
     * @throws Exception 
     * @return T
     */
    public static <T> T  toBeanFromFile(File file,Class<T> cls) throws Exception{
        InputStream ins = null ;
        try {
            ins = new FileInputStream(file);
        } catch (Exception e) {
            throw new Exception("读{"+ file.toURI().toString() +"}文件失败！", e);
        }
         
        XStream xstream=new XStream(new DomDriver(UTF8));
//        XStream xstream=new XStream(new DomDriver());
        xstream.processAnnotations(cls);
        T obj =null;
        try {
            obj = (T)xstream.fromXML(ins);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw new Exception("解析{"+ file.toURI().toString() +"}文件失败！",e);
        }
        if(ins != null)
            ins.close();
        return obj;         
    } 
    
    
    public static void main(String[] args) {  
    	StudentInfo student=new StudentInfo();
		 student.setId(1);
		 student.setName("Hello World!");
		 student.setAge(26);
		 student.setAddress("深圳市福田区新洲街湖北大厦");
		 student.setBirthday(new Date());
		 student.setCreateTime(new Date());
		 student.setEmail("sss@126.com");
		 student.setPassword("123465");
		 student.setSex(1);
		 
		 
		  File file=new File("D:\\HelloWorld_xstream.xml");
          
		 System.out.println("xml 是否生成成功："+toXMLFile(student,file));
		 
		 
		  try {
			StudentInfo studentFromFile = toBeanFromFile(file,StudentInfo.class);
			System.out.println("读取xml 转为java "+studentFromFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  

    }  
    
     
}