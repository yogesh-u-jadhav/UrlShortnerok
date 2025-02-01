package com.example.UrlShortnerok.controller;

import com.example.UrlShortnerok.service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller  // This tells Spring Boot that this is a controller for rendering HTML views
public class URLController {

    @Autowired
    private URLService urlService;

    // Show the main form (home page)
    @GetMapping("/")
    public String showForm(Model model) {
        // In case you want to pass initial data or empty attributes
        model.addAttribute("shortUrl", null);
        model.addAttribute("error", null);
        return "index";  // This will load the index.html template
    }

    // Handle form submission to shorten the URL
    @PostMapping("/shorten")
    public String shortenUrl(@RequestParam String originalUrl, Model model) {
        String shortUrl = urlService.generateShortUrl(originalUrl);
        
        // If the URL shortening was successful, pass the short URL to the view
        if (shortUrl != null && !shortUrl.isEmpty()) {
            model.addAttribute("shortUrl", shortUrl);  // Add the short URL to the model to show it in the view
        } else {
            model.addAttribute("error", "There was an error shortening the URL.");
        }
        return "index";  // Return to the index page with the result
    }

    // Redirect the user to the original URL based on the short URL
    @GetMapping("/api/url/{shortUrl}")
    public String redirectToOriginalUrl(@PathVariable String shortUrl, Model model) {
        String originalUrl = urlService.getOriginalUrl(shortUrl);
        if (originalUrl != null) {
            return "redirect:" + originalUrl;  // Redirect to the original URL
        } else {
            model.addAttribute("error", "URL not found!");
            return "index";  // If the URL is not found, show an error message on the same page
        }
    }
}
