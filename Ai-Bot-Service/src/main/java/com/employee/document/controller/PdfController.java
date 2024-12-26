package com.employee.document.controller;

import com.employee.document.service.HuggingFaceService;
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

@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    private HuggingFaceService huggingFaceService;

    @Autowired
    public PdfController(HuggingFaceService huggingFaceService) {
        this.huggingFaceService = huggingFaceService;
    }

    @GetMapping("/get/{msg}")
    public JsonNode get(@PathVariable String msg){
        return huggingFaceService.getCompletion(msg);
    }


}
