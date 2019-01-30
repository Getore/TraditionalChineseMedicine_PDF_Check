package test_file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Description: 获取 txt 中的内容，获取每一行
 *
 * @author shenli3
 * @createDate 2019/1/20 19:41
 */
public class TestReadFiledata {

    public static void main(String[] args) {

        File file = new File("F:\\Trainee\\ideaIU-2018.2\\readpdf\\test.txt");//我的txt文本存放目录，根据自己的路径修改即可
        System.out.println(txt2String(file));

    }

    public static String txt2String(File file) {

        StringBuilder result = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                if (s.contains("[治法名称]")){  // String.contains 进行模糊匹配
                    result.append(System.lineSeparator() + s);
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result.toString();
    }

}