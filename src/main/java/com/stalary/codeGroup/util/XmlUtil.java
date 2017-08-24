package com.stalary.codeGroup.util;

import com.stalary.codeGroup.service.BaseService;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by BianXunye on 2017/4/19.
 */
public class XmlUtil {
    static String parameterConfigUrl = BaseService.class.getClassLoader().getResource("/").getPath() + "ParameterConfig.xml";
    static String tollConfigUrl = BaseService.class.getClassLoader().getResource("/").getPath() + "TollConfig.xml";

    public static String findByName(String name){
        Element root = null;
        try {
            File f = new File(parameterConfigUrl);
            SAXReader reader = new SAXReader();
            Document doc = reader.read(f);
            root = doc.getRootElement();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return root.element(name).getText();
    }

    /**
     *
     * @param name 标签名
     * @param content 修改的内容
     * @return 是否修改成功
     */
    public static boolean setParameter(String name, String content){
        try {
            InputStream in = new FileInputStream(parameterConfigUrl);
            SAXReader reader = new SAXReader();
            Document doc = reader.read(in);
            System.out.println(doc.getRootElement().element(name).getText());
            doc.getRootElement().element(name).setText(content);
            System.out.println(doc.getRootElement().element(name).getText());

            FileOutputStream out =new FileOutputStream(parameterConfigUrl);
            OutputFormat format=OutputFormat.createPrettyPrint(); //漂亮格式：有空格换行
            format.setEncoding("UTF-8");
            XMLWriter writer=new XMLWriter(out,format);
            writer.write(doc);
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static String getTollByName(String name, String subName){
        Element root = null;
        try {
            File f = new File(tollConfigUrl);
            SAXReader reader = new SAXReader();
            Document doc = reader.read(f);
            root = doc.getRootElement();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        if (null != root.element(name) && null != root.element(name).element(subName))
            return root.element(name).element(subName).getText();
        else
            return null;
    }
}
