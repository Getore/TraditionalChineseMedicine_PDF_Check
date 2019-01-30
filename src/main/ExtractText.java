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
        doc.loadFromFile("F:\\Trainee\\ideaIU-2018.2\\readpdf\\src\\pdf\\test.pdf");
        StringBuilder sb = new StringBuilder();
        PdfPageBase page;
        //遍历PDF页面，获取文本
        for (int i = 0; i < doc.getPages().getCount(); i++) {
            page = doc.getPages().get(i);
            sb.append(page.extractText(true));
        }
        FileWriter writer;
        try {
            //将文本写入文本文件
            writer = new FileWriter("F:\\Trainee\\ideaIU-2018.2\\readpdf\\src\\txt\\test.txt");
            writer.write(sb.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        doc.close();
    }
}