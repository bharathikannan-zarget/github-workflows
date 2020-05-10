package com.freshworks.githubflows;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class FileUtil {

    public static final String[] ALPHA = {"a", "b", "c","d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p","q",
    "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    public static final Random RANDOM = new Random();

    public static final long MAX_SIZE = 5 * 1000 * 1000; //10 MB

    public static String getFileNameFromFilePath(String filePath) {
        return filePath.substring(filePath.lastIndexOf("/") + 1);
    }

    public static boolean isValidImageFile(String contentType) {
        if (!(contentType.equals("image/pjpeg") || contentType.equals("image/jpeg") || contentType.equals("image/png")
                || contentType.equals("image/bmp")
                || contentType.equals("image/x-png") || contentType.equals("image/x-icon"))) {
            return false;
        }
        return true;
    }


    public static List<ValidationError> validateFile(MultipartFile file) {
        List<ValidationError> errors = new ArrayList<>();

       if(file.getSize() > MAX_SIZE) {
           errors.add(new ValidationError("File size should be less than : "+ MAX_SIZE));
       }

       if(!FileUtil.isValidImageFile(file.getContentType())) {
            errors.add(new ValidationError("Invalid file format, only images are supported. File: " + file.getContentType()));
       }

       return errors;
    }


    @AllArgsConstructor
    public static class ValidationError {
        String msg;
    }


    public static String getRandomName(String fileExt) {
        return _random() + "." + fileExt;
    }

    private static final String _random() {
        String name = "photo-" + System.currentTimeMillis() + "-";

        for(int i=0; i < 10; i++) {
            name += ALPHA[RANDOM.nextInt(ALPHA.length-1)];
        }
        return name;
    }

    public static String getRandomName() {
        return _random();
    }


    public static String readInputStream(InputStream io) throws IOException {

        byte[] buffer = new byte[8092];
        int bytesRead = -1;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
                while((bytesRead = io.read(buffer)) > 0) {
                    out.write(buffer, 0, bytesRead);
                }
        } finally {
            io.close();
        }
        return new String(out.toByteArray(), "utf-8");
    }

}

