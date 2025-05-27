package com.cbltps.loveaiagent.tools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lichengxiang
 * Date: 2025-05-24
 * Time: 23:50
 */
class PDFGenerationToolTest {

    @Test
    void generatePDF() {
        PDFGenerationTool tool = new PDFGenerationTool();
        String fileName = "cbiltps博客主页.pdf";
        String content = "cbiltps博客主页 https://blog.csdn.net/Cbiltps";
        String result = tool.generatePDF(fileName, content);
        assertNotNull(result);
    }
}