package com.inventor.management.inventor_management.core.utils;

import com.inventor.management.core.exceptions.ImageErrorException;
import lombok.experimental.UtilityClass;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class FileUploadUtil {
    public static final long MAX_FILE_SIZE = 2 * 1024 * 1024;
    public static final String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";
    public static final String VIDEO_PATTERN = "([^\\s]+(\\.(?i)(mp4|avi|mov|wmv))$)";
    public static final String AUDIO_PATTERN = "([^\\s]+(\\.(?i)(mp3|wav|ogg))$)";
    public static final String DOCUMENT_PATTERN = "([^\\s]+(\\.(?i)(pdf|doc|docx|xls|xlsx|txt))$)";
    public static final String DATE_FORMAT = "yyyyMMddHHmmss";
    public static final String FILE_NAME_FORMAT = "%s_%s";

    public static boolean isAllowedExtension(final String fileName, final String pattern) {
        final Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(fileName);
        return matcher.matches();
    }

    public static void assertAllowed(MultipartFile file, String pattern) {
        final long size = file.getSize();
        if(size > MAX_FILE_SIZE) {
            throw new ImageErrorException("Max file size is 20MB");
        }

        final String fileName = file.getOriginalFilename();
        FilenameUtils.getExtension(fileName);
        if(!isAllowedExtension(fileName, pattern)) {
            throw new ImageErrorException("Invalid file format. Only " + pattern + " are allowed");
        }
    }

    public static String generateFileName(final String name) {
        final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        final String date = dateFormat.format(System.currentTimeMillis());

        return String.format(FILE_NAME_FORMAT, name, date);
    }
}
