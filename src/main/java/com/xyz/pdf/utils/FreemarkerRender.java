package com.xyz.pdf.utils;

import com.xyz.pdf.config.PDFProperties;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;

/**
 * Freemarker模板引擎渲染
 */
public class FreemarkerRender {
    /**
     * Freemarker模板引擎渲染
     *
     * @param pdfProperties pdf配置属性
     * @param templateName  模板名称
     * @param data          数据
     * @return String 模板html
     */
    public static String renderFromDir(PDFProperties pdfProperties, String templateName, Map<String, Object> data) throws IOException, TemplateException {
        // 创建FreeMarker模板的Configuration配置实例，指定默认版本号
        Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        // 指定FreeMarker模板文件的位置
        configuration.setDirectoryForTemplateLoading(getTemplateAbsolutePath(pdfProperties));
        // 设置模板的编码格式
        configuration.setEncoding(Locale.CHINA, "UTF-8");
        // 获取模板文件
        Template template = configuration.getTemplate(templateName, "UTF-8");
        // 将数据输出到html中
        StringWriter writer = new StringWriter();
        // 渲染
        template.process(data, writer);
        writer.flush();
        return writer.toString();
    }

    /**
     * 模板渲染
     *
     * @param templateName    模板名称
     * @param templateContent 模板内容
     * @param data            数据
     * @return String 渲染后的html
     */
    public static String renderFromWeb(String templateName, String templateContent, Map<String, Object> data) throws IOException, TemplateException {
        StringTemplateLoader stringLoader = new StringTemplateLoader();
        stringLoader.putTemplate(templateName, templateContent);
        Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setTemplateLoader(stringLoader);
        // 设置模板的编码格式
        configuration.setEncoding(Locale.CHINA, "UTF-8");
        // 获取模板文件
        Template template = configuration.getTemplate(templateName, "UTF-8");
        // 将数据输出到html中
        StringWriter writer = new StringWriter();
        // 渲染
        template.process(data, writer);
        writer.flush();
        return writer.toString();
    }

    /**
     * 获取模板绝对路径
     *
     * @param pdfProperties pdf配置属性
     * @return String
     */
    private static File getTemplateAbsolutePath(PDFProperties pdfProperties) throws IOException {
        String rootDir = new ClassPathResource(pdfProperties.getTemplateFileDir()).getFile().getParent();
        String templateAbsolutePath = rootDir + File.separator + pdfProperties.getTemplateFileDir();
        return new File(templateAbsolutePath);
    }
}
