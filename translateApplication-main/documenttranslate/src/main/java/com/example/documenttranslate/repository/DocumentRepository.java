package com.example.documenttranslate.repository;

import com.example.documenttranslate.Entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document,Long> {
}
