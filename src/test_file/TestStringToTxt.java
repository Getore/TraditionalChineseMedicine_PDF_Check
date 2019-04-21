package test_file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author shenli - XD02551
 * @email
 * @createDate 2019/4/15 21:50
 */
public class TestStringToTxt {

    public static String strFilename = "F:\\Trainee\\ideaIU-2018.2\\TraditionalChineseMedicine_PDF_Check\\src\\pdf\\deleteSpace.txt";
    public static String strBuffer001 = "你好，MJ";
    public static String strBuffer002 = "你好，SL";

    public static void TextToFile(String strFilename, String strBuffer) throws Exception {
        // 创建文件对象
        File fileText = new File(strFilename);
        // 向文件写入对象写入信息
        FileWriter fileWriter = new FileWriter(fileText);

        // 写文件
        fileWriter.write(strBuffer);
        // 关闭
        fileWriter.close();
    }

    public static void main(String[] args) throws Exception{
        String string = strBuffer001 + "\n" + strBuffer002;
        TextToFile(strFilename, string);
//        TextToFile(strFilename, strBuffer002);
    }
}