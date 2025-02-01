package com.example.UrlShortnerok.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity  // This tells Spring Boot this class will represent a database table
public class URL {

    @Id  // Marks this as the primary key (unique ID)
    private String shortUrl;  // Stores the shortened URL

    private String originalUrl;  // Stores the original URL

    // Default constructor (required by JPA)
    public URL() {}

    // Constructor to create a URL object
    public URL(String shortUrl, String originalUrl) {
        this.shortUrl = shortUrl;
        this.originalUrl = originalUrl;
    }

    // Getters and setters to access and modify the fields
    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}
