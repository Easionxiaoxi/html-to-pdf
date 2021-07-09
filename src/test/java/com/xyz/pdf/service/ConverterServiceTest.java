package com.xyz.pdf.service;

import com.lowagie.text.DocumentException;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
class ConverterServiceTest{

    @Resource
    private ConverterService converterService;

    @Test
    void htmlToPdf() throws DocumentException, TemplateException, IOException {
        String templateName = "template.ftl";
        ByteArrayOutputStream byteArrayOutputStream = converterService.htmlToPdf(templateName, new HashMap<>());
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\template.pdf");
        fileOutputStream.write(byteArrayOutputStream.toByteArray());
    }

    @Test
    void testHtmlToPdf() throws IOException, TemplateException, DocumentException {
        HashMap<String, Object> data = new HashMap<>();
        data.put("post","Java");
        String templateContent = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\"/>\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"/>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n" +
                "    <title>报名表</title>\n" +
                "    <style>\n" +
                "        * {\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            box-sizing: border-box;\n" +
                "        }\n" +
                "\n" +
                "        .a4 {\n" +
                "            width: 100%;\n" +
                "            margin: 0 auto;\n" +
                "            padding: 0 10px;\n" +
                "            font-size: 12px;\n" +
                "            font-weight: 700;\n" +
                "        }\n" +
                "\n" +
                "        em {\n" +
                "            font-style: normal;\n" +
                "        }\n" +
                "\n" +
                "        /*设置模板字体，对应配置的font字体*/\n" +
                "        body {\n" +
                "            font-family: SimSun;\n" +
                "        }\n" +
                "\n" +
                "        section {\n" +
                "            display: block;\n" +
                "        }\n" +
                "\n" +
                "        /*表格分页时，超出分页，在下一页显示*/\n" +
                "        table {\n" +
                "            page-break-inside: auto;\n" +
                "            -fs-table-paginate: paginate;\n" +
                "            word-break: break-word;\n" +
                "        }\n" +
                "\n" +
                "        /*表格行分页时，超出分页，在下一页显示*/\n" +
                "        tr {\n" +
                "            page-break-inside: avoid;\n" +
                "            page-break-after: auto;\n" +
                "        }\n" +
                "\n" +
                "        /*强制分页*/\n" +
                "        .pageNext {\n" +
                "            page-break-after: always;\n" +
                "        }\n" +
                "\n" +
                "        /*页码*/\n" +
                "        @page {\n" +
                "            /*size: 8.5in 11in;*/\n" +
                "            @bottom-right {\n" +
                "                content: \"page \" counter(page) \" of  \" counter(pages);\n" +
                "            }\n" +
                "        }\n" +
                "    </style>"+
                "</head>\n" +
                "<body>\n" +
                "    <section>\n" +
                "        <h4>应聘岗位：${post}</h4>\n" +
                "    </section>\n" +
                "</body>\n" +
                "</html>";
        ByteArrayOutputStream byteArrayOutputStream = converterService.htmlToPdf("test.ftl", templateContent, data);
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\html.pdf");
        fileOutputStream.write(byteArrayOutputStream.toByteArray());
    }

    @Test
    void htmlToPdfWithWatermark() throws DocumentException, TemplateException, IOException {
        String templateName = "template.ftl";
        ByteArrayOutputStream byteArrayOutputStream = converterService.htmlToPdfWithWatermark(templateName, new HashMap<>(),"水印水印水印");
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\template_watermark.pdf");
        fileOutputStream.write(byteArrayOutputStream.toByteArray());
    }

    @Test
    void testHtmlToPdfWithWatermark() throws IOException, TemplateException, DocumentException{
        HashMap<String, Object> data = new HashMap<>();
        data.put("post","Java");
        String templateContent = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\"/>\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"/>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n" +
                "    <title>报名表</title>\n" +
                "    <style>\n" +
                "        * {\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            box-sizing: border-box;\n" +
                "        }\n" +
                "\n" +
                "        .a4 {\n" +
                "            width: 100%;\n" +
                "            margin: 0 auto;\n" +
                "            padding: 0 10px;\n" +
                "            font-size: 12px;\n" +
                "            font-weight: 700;\n" +
                "        }\n" +
                "\n" +
                "        em {\n" +
                "            font-style: normal;\n" +
                "        }\n" +
                "\n" +
                "        /*设置模板字体，对应配置的font字体*/\n" +
                "        body {\n" +
                "            font-family: SimSun;\n" +
                "        }\n" +
                "\n" +
                "        section {\n" +
                "            display: block;\n" +
                "        }\n" +
                "\n" +
                "        /*表格分页时，超出分页，在下一页显示*/\n" +
                "        table {\n" +
                "            page-break-inside: auto;\n" +
                "            -fs-table-paginate: paginate;\n" +
                "            word-break: break-word;\n" +
                "        }\n" +
                "\n" +
                "        /*表格行分页时，超出分页，在下一页显示*/\n" +
                "        tr {\n" +
                "            page-break-inside: avoid;\n" +
                "            page-break-after: auto;\n" +
                "        }\n" +
                "\n" +
                "        /*强制分页*/\n" +
                "        .pageNext {\n" +
                "            page-break-after: always;\n" +
                "        }\n" +
                "\n" +
                "        /*页码*/\n" +
                "        @page {\n" +
                "            /*size: 8.5in 11in;*/\n" +
                "            @bottom-right {\n" +
                "                content: \"page \" counter(page) \" of  \" counter(pages);\n" +
                "            }\n" +
                "        }\n" +
                "    </style>"+
                "</head>\n" +
                "<body>\n" +
                "    <section>\n" +
                "        <h4>应聘岗位：${post}</h4>\n" +
                "    </section>\n" +
                "</body>\n" +
                "</html>";
        ByteArrayOutputStream byteArrayOutputStream = converterService.htmlToPdfWithWatermark("test.ftl", templateContent, data,"水印水印水印");
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\html_watermark.pdf");
        fileOutputStream.write(byteArrayOutputStream.toByteArray());
    }
}