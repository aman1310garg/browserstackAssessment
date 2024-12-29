package util;

import java.io.*;
import java.net.*;
import java.nio.file.*;

public class DownloadUtil {

    // Method to download image
    public static void downloadImage(String imageUrl) {
        try {
            // Create a URL object from the image URL
            URL url = new URL(imageUrl);

            // Open an InputStream to the URL
            try (InputStream inputStream = url.openStream()) {

                // Get the file name from the URL
                String fileName = getFileNameFromUrl(imageUrl);

                // Define the path where the image will be saved
                Path filePath = Paths.get("downloaded_images", fileName);

                // Create directories if they don't exist
                Files.createDirectories(filePath.getParent());

                // Copy the content of the InputStream to the local file
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

                System.out.println("Image downloaded: " + filePath.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to download image.");
        }
    }

    // Helper method to extract the file name from the URL
    private static String getFileNameFromUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            String path = url.getPath();
            return path.substring(path.lastIndexOf("/") + 1);
        } catch (Exception e) {
            e.printStackTrace();
            return "default_image.jpg"; // Default file name if URL parsing fails
        }
    }
}
