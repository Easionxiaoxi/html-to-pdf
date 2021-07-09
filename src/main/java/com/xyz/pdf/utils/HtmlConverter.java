package com.xyz.pdf.utils;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.xyz.pdf.config.PDFProperties;
import org.springframework.core.io.ClassPathResource;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Html转PDF
 */
public class HtmlConverter {

    /**
     * html转pdf
     *
     * @param html     模板
     * @param pdfProperties pdf配置属性
     * @return ByteArrayOutputStream
     */
    public static ByteArrayOutputStream htmlToPdf(String html, PDFProperties pdfProperties) throws DocumentException, IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        // 设置 css中 的字体样式（宋体和黑体） 必须，不然中文不显示
        addFont(renderer.getFontResolver(),pdfProperties);
        // 把html代码传入渲染器中
        renderer.setDocumentFromString(html);
        // 设置模板中的图片路径（oss上的图片不用设置）模板中img标签src路径需要相对路径加图片名 如<img src="images/xh.jpg"/>
        renderer.getSharedContext().setBaseURL(getImgAbsolutePath(pdfProperties));
        renderer.layout();
        renderer.createPDF(outputStream, false);
        renderer.finishPDF();
        return outputStream;
    }

    /**
     * 添加字体
     *
     * @param fontResolver 字体解析器
     * @param pdfProperties pdf配置属性
     */
    private static void addFont(ITextFontResolver fontResolver, PDFProperties pdfProperties) throws IOException, DocumentException {
        for (String font : pdfProperties.getFontList()) {
            fontResolver.addFont(getFontAbsolutePath(pdfProperties) + File.separator + font, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        }
    }

    /**
     * 获取模板绝对路径
     *
     * @param pdfProperties pdf配置属性
     * @return String
     */
    private static String getImgAbsolutePath(PDFProperties pdfProperties) throws IOException {
        String rootDir = new ClassPathResource(pdfProperties.getTemplateFileDir()).getFile().getParent();
        return rootDir + File.separator + pdfProperties.getImageFileDir();
    }

    /**
     * 获取字体绝对路径
     *
     * @param pdfProperties pdf配置属性
     * @return String
     */
    private static String getFontAbsolutePath(PDFProperties pdfProperties) throws IOException {
        String rootDir = new ClassPathResource(pdfProperties.getTemplateFileDir()).getFile().getParent();
        return rootDir + File.separator + pdfProperties.getFontDir();
    }

}
