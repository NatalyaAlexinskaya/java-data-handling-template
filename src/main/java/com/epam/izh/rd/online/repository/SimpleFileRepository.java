package com.epam.izh.rd.online.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class SimpleFileRepository implements FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {
        long countFiles = 0;
        File file = new File(path);
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File value : listFiles) {
                if (value.isFile()) {
                    countFiles++;
                } else {
                    countFilesInDirectory(value.getPath());
                }
            }
        }
        return countFiles;
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {
        long countDirs = 1;
        File file = new File(path);
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File files : listFiles) {
                if (files.isDirectory()) {
                    countDirs++;
                } else {
                    countDirsInDirectory(files.getPath());
                }
            }
        }
        return countDirs;
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) {
        File file1 = new File(from);
        File[] files = file1.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getPath().endsWith(".txt")) {
                    try {
                        Files.copy(file.toPath(), Paths.get(to + "/" + file.getName()), REPLACE_EXISTING);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Метод создает файл на диске с расширением txt
     *
     * @param path путь до нового файла
     * @param name имя файла
     * @return был ли создан файл
     */
    @Override
    public boolean createFile(String path, String name) {
        try {
            Files.createFile(Paths.get(path + "/" + name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Files.exists(Paths.get(path + "/" + name));
    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {
        String code = "";
        StringBuilder stringBuilder = new StringBuilder(code);
        File file = new File("D:/Java/JavaEpamTasks/Java-homework/java-data-handling-template/src/main/resources");
        File[] array = file.listFiles();
        if (array != null) {
            for (File files : array) {
                if (files.getName().equals(fileName)) {
                    try {
                        List<String> lines = Files.readAllLines(files.toPath());
                        for (String string : lines) {
                            stringBuilder.append(string);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return stringBuilder.toString();
    }
}
