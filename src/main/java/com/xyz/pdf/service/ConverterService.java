package com.xyz.pdf.service;

import com.lowagie.text.DocumentException;
import freemarker.template.TemplateException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * html转PDF接口
 */
public interface ConverterService {

    /**
     * html转pdf
     *
     * @param templateName 模板名称
     * @param data         数据
     * @return ByteArrayOutputStream
     */
    ByteArrayOutputStream htmlToPdf(String templateName, Map<String, Object> data) throws IOException, TemplateException, DocumentException;

    /**
     * html转pdf
     *
     * @param templateName    模板名称
     * @param templateContent 模板内容
     * @param data            数据
     * @return ByteArrayOutputStream
     */
    ByteArrayOutputStream htmlToPdf(String templateName, String templateContent, Map<String, Object> data) throws IOException, TemplateException, DocumentException;

    /**
     * html转pdf添加水印
     *
     * @param templateName 模板名称
     * @param data         数据
     * @param watermark    水印
     * @return ByteArrayOutputStream
     */
    ByteArrayOutputStream htmlToPdfWithWatermark(String templateName, Map<String, Object> data, String watermark) throws IOException, TemplateException, DocumentException;

    /**
     * html转pdf添加水印
     *
     * @param templateName    模板名称
     * @param templateContent 模板内容
     * @param data            数据
     * @param watermark       水印
     * @return ByteArrayOutputStream
     */
    ByteArrayOutputStream htmlToPdfWithWatermark(String templateName, String templateContent, Map<String, Object> data, String watermark) throws IOException, TemplateException, DocumentException;
}
