package com.xyz.pdf.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * html-to-pdf文件导出相关配置项
 */
@Component
@ConfigurationProperties(prefix = "com.xyz.pdf.export")
public class PDFProperties {
    /**
     * 模板文件目录
     */
    private String templateFileDir;
    /**
     * 图片文件目录
     */
    private String imageFileDir;
    /**
     * 字体目录，linux和windows的目录不同
     */
    private String fontDir;

    /**
     * 字体目录，linux和windows的目录不同
     */
    private List<String> fontList;

    public String getFontDir() {
        return fontDir;
    }

    public void setFontDir(String fontDir) {
        this.fontDir = fontDir;
    }

    public String getTemplateFileDir() {
        return templateFileDir;
    }

    public void setTemplateFileDir(String templateFileDir) {
        this.templateFileDir = templateFileDir;
    }

    public String getImageFileDir() {
        return imageFileDir;
    }

    public void setImageFileDir(String imageFileDir) {
        this.imageFileDir = imageFileDir;
    }

    public List<String> getFontList() {
        return fontList;
    }

    public void setFontList(List<String> fontList) {
        this.fontList = fontList;
    }
}
