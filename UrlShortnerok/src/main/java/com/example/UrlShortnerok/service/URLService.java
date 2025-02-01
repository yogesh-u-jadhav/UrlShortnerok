package com.example.UrlShortnerok.service;

import com.example.UrlShortnerok.model.URL;
import com.example.UrlShortnerok.repository.URLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service  // Marks this as a service class
public class URLService {

    @Autowired  // Automatically injects URLRepository to interact with the database
    private URLRepository urlRepository;

    // Method to generate a short URL
    public String generateShortUrl(String originalUrl) {
        // Generate a random 6-character string for the short URL
        String shortUrl = generateRandomString();

        // Create a new URL object and store it in the database
        URL url = new URL(shortUrl, originalUrl);
        urlRepository.save(url);

        return shortUrl;
    }

    // Method to get the original URL from a short URL
    public String getOriginalUrl(String shortUrl) {
        URL url = urlRepository.findById(shortUrl).orElse(null);
        return url != null ? url.getOriginalUrl() : null;
    }

    // Helper method to generate a random string
    private String generateRandomString() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder shortUrl = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 6; i++) {
            shortUrl.append(characters.charAt(rand.nextInt(characters.length())));
        }
        return shortUrl.toString();
    }
}
