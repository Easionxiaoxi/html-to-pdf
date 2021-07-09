package com.xyz.pdf.utils;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.xyz.pdf.config.PDFProperties;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * PDF生成水印
 */
public class PDFWatermark {

    /**
     * pdf添加水印
     *
     * @param pdfOutputStream 输入流
     * @param watermarkText   水印文字
     * @return ByteArrayOutputStream
     */
    public static ByteArrayOutputStream addWatermark(ByteArrayOutputStream pdfOutputStream, PDFProperties pdfProperties, String watermarkText) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(new ByteArrayInputStream(pdfOutputStream.toByteArray())), new PdfWriter(outputStream));
        pdfDoc.addEventHandler(PdfDocumentEvent.END_PAGE, event -> {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            PdfDocument newPdfDoc = docEvent.getDocument();
            PdfPage page = docEvent.getPage();
            // 要显示中文水印的话，需要设置中文字体，这里可以动态判断
            PdfFont font = null;
            try {
                font = PdfFontFactory.createFont(getFontAbsolutePath(pdfProperties), PdfEncodings.IDENTITY_H,false);
            } catch (IOException e) {
                e.printStackTrace();
            }
            PdfCanvas canvas = new PdfCanvas(page);
            PdfExtGState pdfExtGState = new PdfExtGState();
            // 水印透明度
            pdfExtGState.setFillOpacity(0.5f);
            canvas.setExtGState(pdfExtGState);
            new Canvas(canvas, newPdfDoc, page.getPageSize())
                    .setFontColor(ColorConstants.LIGHT_GRAY)
                    .setFontSize(60)
                    .setFont(font)
                    .showTextAligned(new Paragraph(watermarkText), 298, 421, newPdfDoc.getPageNumber(page), TextAlignment.CENTER, VerticalAlignment.MIDDLE, 45);
        });
        pdfDoc.close();
        return outputStream;
    }

    /**
     * 获取字体绝对路径
     *
     * @param pdfProperties pdf配置属性
     * @return String
     */
    private static String getFontAbsolutePath(PDFProperties pdfProperties) throws IOException {
        String rootDir = new ClassPathResource(pdfProperties.getTemplateFileDir()).getFile().getParent();
        return rootDir + File.separator + pdfProperties.getFontDir()+ File.separator+pdfProperties.getFontList().get(1);
    }
}
