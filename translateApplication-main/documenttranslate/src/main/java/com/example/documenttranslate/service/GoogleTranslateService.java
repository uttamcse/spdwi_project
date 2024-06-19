package com.example.documenttranslate.service;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GoogleTranslateService {

    @Value("${google.api.key}")
    private String apiKey;

    public String translate(String text, String targetLanguage) throws IOException {

        Translate translate = TranslateOptions.newBuilder().setApiKey(apiKey).build().getService();
        Translation translation = translate.translate(text, Translate.TranslateOption.targetLanguage(targetLanguage));
        return translation.getTranslatedText();
    }
}
