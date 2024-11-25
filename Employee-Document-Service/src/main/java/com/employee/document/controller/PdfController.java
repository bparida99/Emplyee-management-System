package com.employee.document.controller;

import com.employee.document.service.HuggingFaceService;
import com.employee.document.service.LlamaService;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    private LlamaService llamaService;
    private HuggingFaceService huggingFaceService;

    @Autowired
    public PdfController(LlamaService llamaService,HuggingFaceService huggingFaceService) {
        this.llamaService = llamaService;
        this.huggingFaceService = huggingFaceService;
    }

    @GetMapping("/get/{msg}")
    public JsonNode get(@PathVariable String msg){
        return huggingFaceService.getCompletion(msg
                );
    }

    @PostMapping("/analyze")
    public ResponseEntity<JsonNode> analyzePdf() {

        try {
            String text = extractTextFromPdf(
                    getMultipartFileFromLocalFile("D:\\workspace\\India_Payslip_January_2024.pdf"));
            String instruction = "Convert the following payslip into a JSON object: ";
            JsonNode res = huggingFaceService.getCompletion(instruction+text);
            return ResponseEntity.ok(res);
        } catch (IOException e) {
            return null;
        }
    }

    private String extractTextFromPdf(MultipartFile file) throws IOException {
        PDDocument document = PDDocument.load(file.getInputStream());
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(document);
        document.close();
        return text;
    }

    public MultipartFile getMultipartFileFromLocalFile(String filePath) throws IOException {
        File file = new File(filePath);

        // Open a FileInputStream for the file
        FileInputStream inputStream = new FileInputStream(file);

        // Create a MockMultipartFile with the input stream and file details
        MultipartFile multipartFile = new MockMultipartFile(
                "file",                   // Name of the form parameter
                file.getName(),           // Original file name
                "application/octet-stream", // MIME type (can be set to specific types like "application/pdf" or "image/png")
                inputStream               // InputStream representing the file content
        );

        // Close the FileInputStream
        inputStream.close();

        return multipartFile;
    }
}
