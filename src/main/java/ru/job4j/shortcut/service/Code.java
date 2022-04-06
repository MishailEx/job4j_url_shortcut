package ru.job4j.shortcut.service;

import ru.job4j.shortcut.entity.UrlLink;
import ru.job4j.shortcut.repositories.UrlRepository;

import java.util.Random;

public class Code {

    public static String generate(int num) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder strGen = new StringBuilder();
        for (int i = 0; i < num; i++) {
            int numStr = random.nextInt(62);
            strGen.append(str.charAt(numStr));
        }
        return strGen.toString();
    }
}
