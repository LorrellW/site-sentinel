package com.sitesentinel.backend.service;

import com.microsoft.playwright.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class PlayWrightService {

    // Helper Record
    public record CheckResult(int status, long responseTime, String screenshotFileName) {}

    public CheckResult performCheck(String url) {
        // 1. 🛠 FIX URL PROTOCOL (Auto-correct "linkedin.com" -> "https://linkedin.com")
        if (!url.startsWith("http")) {
            url = "https://" + url;
        }

        System.out.println("🚀 Starting check for: " + url);

        // 2. Prepare Screenshot Folder
        String storageDir = "screenshots";
        new File(storageDir).mkdirs(); // Ensure folder exists

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
            Page page = browser.newPage();

            // Set viewport for a nice desktop screenshot
            page.setViewportSize(1280, 720);

            long start = System.currentTimeMillis();

            // Navigate and wait for network to be idle (ensures page is fully loaded)
            Response response = page.navigate(url, new Page.NavigateOptions().setTimeout(30000));

            long duration = System.currentTimeMillis() - start;

            // 3. 📸 Save Screenshot with unique name
            String filename = UUID.randomUUID().toString() + ".png";
            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get(storageDir, filename)));

            System.out.println("✅ Check complete! Status: " + response.status());

            return new CheckResult(response.status(), duration, filename);
        } catch (Exception e) {
            System.err.println("❌ Check failed: " + e.getMessage());
            // Return 0 status if failed, and null screenshot
            return new CheckResult(0, 0, null);
        }
    }
}