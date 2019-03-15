package cn.news.ui;

//import info.monitorenter.cpdetector.io.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: Clb
 * @Date: 2018/8/30 14:33
 * @Description:
 */
public class Test2 {
    public static void main(String[] args) {
//        String filePath1 = "H:\\pps\\pp\\pTest.txt";
//        File file = new File(filePath1);
//        File file1 = file.getParentFile();
//        String file2 = file.getParent();
//        if (file1.exists()) {
//            System.out.println(file2 + "存在");
//        } else {
//            System.out.println(file2 + "不存在");
//            if (file1.mkdirs()) {
//                System.out.println(file2 + "成功");
//            } else {
//                System.out.println(file2 + "失败");
//            }
//        }

        List list=new LinkedList();
        list.add(111);
        list.add(222);
        list.add(333);
        list.set(1,444);
        for (Object o:list){
            System.out.println(o);
        }
//        System.out.println(list.get(5));
//        list.set(3,555);

        System.out.println('a'+0);



//        int[] a={2,6,31,25,15,28};
//        a=name(a,46);
//        System.out.println(a[0]+"---"+a[1]);
    }
    public static int[] name(int[]a,int t){
        for (int i=0,len=a.length;i<len;i++){   //0-1-2
            for (int j=i+1;j<len;j++){
                if (a[i]+a[j]==t){
                    return new int[]{a[i],a[j]};
                }
            }
        }
        return null;
    }

    /**
     * <div>
     * 利用第三方开源包cpdetector获取文件编码格式.<br/>
     * --1、cpDetector内置了一些常用的探测实现类,这些探测实现类的实例可以通过add方法加进来,
     * 如:ParsingDetector、 JChardetFacade、ASCIIDetector、UnicodeDetector. <br/>
     * --2、detector按照“谁最先返回非空的探测结果,就以该结果为准”的原则. <br/>
     * --3、cpDetector是基于统计学原理的,不保证完全正确.<br/>
     * </div>
     *
     * @param filePath
     * @return 返回文件编码类型：GBK、UTF-8、UTF-16BE、ISO_8859_1
     * @throws Exception
     */
    public static String getFileCharset(String filePath) throws Exception {
//        CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
        /*ParsingDetector可用于检查HTML、XML等文件或字符流的编码,
         * 构造方法中的参数用于指示是否显示探测过程的详细信息，为false不显示。
         */
//        detector.add(new ParsingDetector(false));
        /*JChardetFacade封装了由Mozilla组织提供的JChardet，它可以完成大多数文件的编码测定。
         * 所以，一般有了这个探测器就可满足大多数项目的要求，如果你还不放心，可以再多加几个探测器，
         * 比如下面的ASCIIDetector、UnicodeDetector等。
         */
//        detector.add(JChardetFacade.getInstance());
//        detector.add(ASCIIDetector.getInstance());
//        detector.add(UnicodeDetector.getInstance());
        Charset charset = null;
        File file = new File(filePath);
        try {
            //charset = detector.detectCodepage(file.toURI().toURL());
            InputStream is = new BufferedInputStream(new FileInputStream(filePath));
//            charset = detector.detectCodepage(is, 8);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        String charsetName = "GBK";
        if (charset != null) {
            if (charset.name().equals("US-ASCII")) {
                charsetName = "ISO_8859_1";
            } else if (charset.name().startsWith("UTF")) {
                charsetName = charset.name();// 例如:UTF-8,UTF-16BE.
            }
        }
        return charsetName;
    }
}
