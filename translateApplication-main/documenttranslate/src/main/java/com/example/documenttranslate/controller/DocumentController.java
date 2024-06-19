package com.example.documenttranslate.controller;

import com.example.documenttranslate.Entity.Document;
import com.example.documenttranslate.repository.DocumentRepository;
import com.example.documenttranslate.service.GoogleTranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

@Controller
public class DocumentController {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private GoogleTranslateService googleTranslateService;

    @Value("${google.api.key}")
    private String apiKey;

    @GetMapping("/documents")
    public String listDocuments(Model model) {
        model.addAttribute("documents", documentRepository.findAll());
        return "document-list.html";
    }

    @GetMapping("/upload")
    public String uploadPage() {
        return "upload.html";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   @RequestParam("language") String language) throws IOException {
        String originalContent = new String(file.getBytes());
        String translatedContent = googleTranslateService.translate(originalContent, language);

        // Save the translated file to local directory
        Path path = Paths.get("uploads/" + file.getOriginalFilename());
        Files.write(path, translatedContent.getBytes());

        // Save the document information to the database
        Document document = new Document();
        document.setFileName(file.getOriginalFilename());
        document.setUploadDate(LocalDate.now());
        documentRepository.save(document);

        return "redirect:/documents";
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) {
        Document document = documentRepository.findById(id).orElseThrow(() -> new RuntimeException("Document not found"));
        Path path = Paths.get("uploads/" + document.getFileName());
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getFileName() + "\"")
                .body(resource);
    }
}
