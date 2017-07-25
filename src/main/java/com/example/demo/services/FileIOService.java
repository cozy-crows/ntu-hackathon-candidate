package com.example.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javax.validation.constraints.NotNull;

/**
 * Created by jerry on 2017/7/11.
 */
@Slf4j
@Service
public class FileIOService {

    private static final String DEFAULT_DATA_PATH = "page-data/";
    private static final String UTF8 = "UTF-8";
    private static final String SUFFIX = ".json";

    /**
     * 將現在時間轉為 /yyyy/mm/dd 的檔案路徑
     *
     * @param fileName
     * @return
     */
    public String timeStampToFilePath(String fileName) {
        // 產生 Asia/Taipei 的 yyyy-mm-dd
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Taipei"));
        String dateTimeFormat = zonedDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE);
        dateTimeFormat = dateTimeFormat.replace('-', '/');

        return new StringBuilder(DEFAULT_DATA_PATH)
                .append(dateTimeFormat)
                .append("/" + fileName)
                .append(SUFFIX)
                .toString();
    }

    /**
     * 檢查並建立檔案
     */
    public File checkOrCreateFile(@NotNull String filePath) {
        final File file = new File(filePath);

        try {
            if (!file.exists()) {
                log.info("Create a file at : {}", filePath);
                FileUtils.touch(file);
            }

            return file;

        } catch (IOException e) {
            log.error("Touch a file IOException: {}", e.getMessage());
            return null;
        }
    }

    /**
     * 在某個檔案尾端添加 string
     *
     * @param file
     * @param appendString
     */
    public void appendToFile(@NotNull File file, @NotNull String appendString) {

        try {
            FileUtils.write(file, appendString + "\n", UTF8, true);

        } catch (IOException e) {
            log.error("Append File[{}] IOExceptions: {}", file.getName(), e.getMessage());
        }
    }

}
