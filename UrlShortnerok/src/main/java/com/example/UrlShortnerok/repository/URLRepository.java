package com.example.UrlShortnerok.repository;

import com.example.UrlShortnerok.model.URL;
import org.springframework.data.jpa.repository.JpaRepository;

// Interface that connects to the database
public interface URLRepository extends JpaRepository<URL, String> {
}
