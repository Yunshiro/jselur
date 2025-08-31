package com.yunshiro.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunshiro.engine.model.Template;


import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Parser {

    /**
     * toTemplate transfer the rule file(.json) to a Template class.
     * @param filePath file path of the rule.json template.
     * @param charsetName file charset.
     * @return the rule template object.
     */
    public static Template toTemplate(String filePath, String charsetName) {
        String content = readFile(filePath, charsetName);
        ObjectMapper mapper = new ObjectMapper();

        try {
            Template template = mapper.readValue(content, Template.class);
            return template;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * read the rule template file(.json) to a String.
     * @param filePath file path of the rule.json template.
     * @param charsetName file charset.
     * @return json string result.
     */
    private static String readFile(String filePath, String charsetName) {

        InputStream inputStream = Parser.class.getClassLoader().getResourceAsStream(filePath);
        if (inputStream != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charsetName))) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append(System.lineSeparator());
                }
                return sb.toString();
            } catch (IOException e) {
                throw new RuntimeException("read file fail: " + filePath, e);
            }
        }
        throw new RuntimeException("getResourceAsStream fail.");
    }
}
