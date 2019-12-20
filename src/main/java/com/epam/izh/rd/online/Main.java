package com.epam.izh.rd.online;

import com.epam.izh.rd.online.repository.SimpleFileRepository;
import com.epam.izh.rd.online.service.SimpleBigNumbersService;
import com.epam.izh.rd.online.service.SimpleRegExpService;

public class Main {
    public static void main(String[] args) {

        SimpleBigNumbersService bigNumbersService = new SimpleBigNumbersService();
        System.out.println(bigNumbersService.getPrimaryNumber(100));
    }
}
