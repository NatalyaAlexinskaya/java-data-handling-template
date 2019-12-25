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
        String s = new File("").getAbsolutePath();
        long countFiles = 0;
        File file = new File(s + "/src/main/resources/" + path);
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File value : listFiles) {
                if (value.isFile()) {
                    countFiles++;
                } else {
                    String string = value.getName();
                    countFiles += countFilesInDirectory(path + "/" + string);
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
        String s = new File("").getAbsolutePath();
        long countDirs = 0;
        File file = new File(s + "/src/main/resources/" + path);
        if (file.getPath().endsWith(path)) {
            countDirs++;
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File files : listFiles) {
                if (files.isDirectory()) {
                    countDirs++;
                    String string = files.getName();
                    countDirs += countDirsInDirectory(path + "/" + string);
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
        String s = new File("").getAbsolutePath();
        File file1 = new File(s + "/src/main/resources/" + from);
        File[] files = file1.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getPath().endsWith(".txt")) {
                    try {
                        Files.copy(file.toPath(), Paths.get((s + "/src/main/resources/" + to) + "/" + file.getName()), REPLACE_EXISTING);
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
        String s = new File("").getAbsolutePath();
        File dir = new File(s + "/src/main/resources/" + path);
        File file = new File(s + "/src/main/resources/" + path + "/" + name);
        boolean createdFile = file.exists();

        if (!dir.exists()) {
            boolean createdDir = dir.mkdir();
            if (createdDir) {
                try {
                    createdFile = file.createNewFile();
                    return createdFile;
                } catch (IOException e) {
                    return createdFile;
                }
            }
        } else {
            if (!file.exists()) {
                try {
                    createdFile = file.createNewFile();
                    return createdFile;
                } catch (IOException e) {
                    return createdFile;
                }
            }
        }
        return createdFile;
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
        File file = new File("./src/main/resources");
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
