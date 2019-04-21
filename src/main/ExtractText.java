package main;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Description: 将pdf文件转换成txt文件
 *
 * @author shenli3
 * @createDate 2019/1/20 19:02
 */
public class ExtractText {
    public static void main(String[] args) {
        //创建PdfDocument实例
        PdfDocument doc = new PdfDocument();
        //加载PDF文件
        doc.loadFromFile("F:\\Trainee\\ideaIU-2018.2\\TraditionalChineseMedicine_PDF_Check\\src\\pdf\\test.pdf");
        StringBuilder sb = new StringBuilder();
        PdfPageBase page;
        //遍历PDF页面，获取文本
        for (int i = 0; i < doc.getPages().getCount(); i++) {
            page = doc.getPages().get(i);
            sb.append(page.extractText(true));
        }
        FileWriter writer;
        String temp_string = null;
        try {
            //将文本写入文本文件
            writer = new FileWriter("F:\\Trainee\\ideaIU-2018.2\\TraditionalChineseMedicine_PDF_Check\\src\\txt\\test.txt");
            temp_string = sb.toString();    // 将sb变成字符串
            // 搞完这三个以后，temp_string就变成了一段话...
            temp_string = temp_string.replaceAll(" ", ""); // 实现无空格存入数据库
//            temp_string = temp_string.replaceAll("\n", "");
            temp_string = temp_string.replaceAll("\r", "");

            writer.write(temp_string);

            writer.flush();

            System.out.print("----PDF转换成TXT完成----");
        } catch (IOException e) {
            e.printStackTrace();
        }

        doc.close();
    }
}