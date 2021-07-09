package com.xyz.pdf.service.impl;

import com.lowagie.text.DocumentException;
import com.xyz.pdf.config.PDFProperties;
import com.xyz.pdf.service.ConverterService;
import com.xyz.pdf.utils.FreemarkerRender;
import com.xyz.pdf.utils.HtmlConverter;
import com.xyz.pdf.utils.PDFWatermark;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * html转PDF服务
 */
@Service
public class ConverterServiceImpl implements ConverterService {
    @Resource
    private PDFProperties pdfProperties;

    /**
     * html转pdf
     *
     * @param templateName 模板名称
     * @param data         数据
     * @return ByteArrayOutputStream
     */
    @Override
    public ByteArrayOutputStream htmlToPdf(String templateName, Map<String, Object> data) throws IOException, TemplateException, DocumentException {
        String html = FreemarkerRender.renderFromDir(pdfProperties, templateName, data);
        return HtmlConverter.htmlToPdf(html, pdfProperties);
    }

    /**
     * html转pdf
     *
     * @param templateName    模板名称
     * @param templateContent 模板内容
     * @param data            数据
     * @return ByteArrayOutputStream
     */
    @Override
    public ByteArrayOutputStream htmlToPdf(String templateName, String templateContent, Map<String, Object> data) throws IOException, TemplateException, DocumentException {
        String html = FreemarkerRender.renderFromWeb(templateName, templateContent, data);
        return HtmlConverter.htmlToPdf(html, pdfProperties);
    }

    /**
     * html转pdf添加水印
     *
     * @param templateName 模板名称
     * @param data         数据
     * @param watermark    水印
     * @return ByteArrayOutputStream
     */
    @Override
    public ByteArrayOutputStream htmlToPdfWithWatermark(String templateName, Map<String, Object> data, String watermark) throws IOException, TemplateException, DocumentException {
        String html = FreemarkerRender.renderFromDir(pdfProperties, templateName, data);
        ByteArrayOutputStream byteArrayOutputStream = HtmlConverter.htmlToPdf(html, pdfProperties);
        return PDFWatermark.addWatermark(byteArrayOutputStream, pdfProperties, watermark);
    }

    /**
     * html转pdf添加水印
     *
     * @param templateName    模板名称
     * @param templateContent 模板内容
     * @param data            数据
     * @param watermark       水印
     * @return ByteArrayOutputStream
     */
    @Override
    public ByteArrayOutputStream htmlToPdfWithWatermark(String templateName, String templateContent, Map<String, Object> data, String watermark) throws IOException, TemplateException, DocumentException {
        String html = FreemarkerRender.renderFromWeb(templateName, templateContent, data);
        ByteArrayOutputStream byteArrayOutputStream = HtmlConverter.htmlToPdf(html, pdfProperties);
        return PDFWatermark.addWatermark(byteArrayOutputStream, pdfProperties, watermark);
    }
}
