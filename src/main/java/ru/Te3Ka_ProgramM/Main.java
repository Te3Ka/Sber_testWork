package ru.Te3Ka_ProgramM;

import java.util.Scanner;

public class Main {
    // Константы команд для управления программой через консоль.
    String PLUS_ITERATOR = "/inc";
    String RESET_ITERATOR = "/reset";
    String STOP_ITERATOR = "/stop";

    Scanner scanner = new Scanner(System.in);
    // Счётчик и методы для его изменения и получения данных
    private int iterator;

    void setIterator(int iterator) {
        this.iterator = iterator;
    }

    int getIterator() {
        return iterator;
    }


    public static void main(String[] args) {
        System.out.println("Begin program");
    }
}