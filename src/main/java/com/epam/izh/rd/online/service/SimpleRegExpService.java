package com.epam.izh.rd.online.service;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleRegExpService implements RegExpService {

    /**
     * Метод должен читать файл sensitive_data.txt (из директории resources) и маскировать в нем конфиденциальную информацию.
     * Номер счета должен содержать только первые 4 и последние 4 цифры (1234 **** **** 5678). Метод должен содержать регулярное
     * выражение для поиска счета.
     *
     * @return обработанный текст
     */
    @Override
    public String maskSensitiveData() {
        String code = "";
        StringBuilder stringBuilder = new StringBuilder(code);
        BufferedReader file;
        try {
            file = new BufferedReader(new FileReader(".\\src\\main\\resources\\sensitive_data.txt"));
            while ((code = file.readLine()) != null) {
                stringBuilder.append(code);
            }
            file.close();
            Pattern patter = Pattern.compile("\\d{4}\\s\\d{4}\\s\\d{4}\\s\\d{4}");
            Matcher matcher = patter.matcher(stringBuilder.toString());
            while (matcher.find()) {
                String string = matcher.group();
                String[] strings = string.split(" ");
                String rezult = strings[0] + " **** **** " + strings[3];
                stringBuilder.replace(matcher.start(), matcher.end(), rezult);
            }
            code = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        String code = "";
        StringBuilder stringBuilder = new StringBuilder(code);
        BufferedReader file;
        try {
            file = new BufferedReader(new FileReader(".\\src\\main\\resources\\sensitive_data.txt"));
            while ((code = file.readLine()) != null) {
                stringBuilder.append(code);
            }
            file.close();
            Pattern patter = Pattern.compile("\\$\\{.*?\\}");
            Matcher matcher = patter.matcher(stringBuilder.toString());
            if (matcher.find()) {
                stringBuilder.replace(matcher.start(), matcher.end(), String.valueOf(((int) paymentAmount)));
                matcher = patter.matcher(stringBuilder.toString());
                if (matcher.find()) {
                    int start = matcher.start();
                    stringBuilder.replace(start, matcher.end(), String.valueOf(((int) balance)));
                }
            }
            code = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;
    }
}
