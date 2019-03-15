package cn.news.ui;

import cn.news.dao.impl.EnglishDaoImpl;
import cn.news.entity.English;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @Author: Clb
 * @Date: 2018/8/15 08:40
 * @Description:
 */
public class Test {
    public static void main(String[] args){
        /*

         */
        String filePath1 = "H:\\pTest.xls";
        String filePath2 = "H:\\oTest.txt";
        String filePath3 = "H:\\pp\\oTest.txt";
        String filePath4 = "H:\\pp";
        String filePath5 = "H:";
        String filePath6 = "H:\\pps\\l.txt";
        File file = new File(filePath4);
        file = file.getParentFile();
        String[] fs = file.list();
        File[] fz = file.listFiles();
        for (File f : fz) {
            if (f.isFile()) {
                System.out.println("文件名:" + f.getName());
            }
            if (f.isDirectory()) {
                System.out.println("文件夹:" + f.getName());
            }
            if (f.isHidden()) {
                System.out.println("yz文件夹:" + f.getName());
            }
        }
//        if (file.delete()){
//            System.out.println("文件夹或文件删除成功");
//        }
//        if (file.createNewFile()) {
//            System.out.println("成功");
//        }
//        if (file.delete()){
//            System.out.println("删除成功");
//        }
//        if (file.isFile()){
//            System.out.println("isfileyes");
//        }else {
//            System.out.println("isfileno");
//        }
//        if (file.exists()){
//            System.out.println(666888);
//        }
//        if (file.isDirectory()){
//            System.out.println("isdyes");
//        }else {
//            System.out.println("isdno");
//        }


    }


    private void initial() {
        Iterator<English> iterator = new TreeSet<English>().iterator();
        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add(null);
        System.out.println((int) Math.round(Math.random() * (0 - (0 - 1)) + (0 - 1)));
        Scanner input = new Scanner(System.in);
        List<English> list = new EnglishDaoImpl().eList("E:\\test.txt");
        List<English> readList = new ArrayList<>();
        while (list.size() > 0) {
            int i = (int) Math.round(Math.random() * (0 - (list.size() - 1)) + (list.size() - 1));
            int chinese = (int) Math.round(Math.random() * (0 - (list.get(i).getChinese().size() - 1)) + (list.get(i).getChinese().size() - 1));
            System.out.println("还有" + list.size() + "题");
            System.out.print(list.get(i).getChinese().get(chinese) + " 的英语: ");
            if (input.next().trim().equals(list.get(i).getEnglish())) {
                readList.add(list.get(i));
                list.remove(i);
                System.out.println("yes");
            } else {
                System.out.println("no 答案: " + list.get(i).getEnglish());
                list.get(i).setErrorNumberIncrement();
            }
            System.out.println();
        }
        for (English e : readList) {
            if (e.getErrorNumber() > 0) {
                StringBuffer sb = new StringBuffer(e.getEnglish());
                for (String chinese : e.getChinese()) {
                    sb.append(" " + chinese);
                }
                System.out.println(sb + "  错误次数:" + e.getErrorNumber());
            }
        }
    }
}
