package ru.Te3Ka_ProgramM;

import java.util.Scanner;

//***********************//
//******Te3Ka_PaynE******//
//*79811131773@yandex.ru*//
//***********************//

public class Main {
    // Константы команд для управления программой через консоль.
    String PLUS_ITERATOR = "/inc";
    String RESET_ITERATOR = "/reset";
    String STOP_ITERATOR = "/stop";

    Scanner scanner = new Scanner(System.in);
    // Счётчик и методы для его изменения и получения данных

    public static void main(String[] args) {
        Decorator decorator = new Decorator();
        decorator.startProgram();
        decorator.showIterator();
        decorator.author();
    }
}

class Decorator {
    void startProgram() {
        System.out.println("Программа показывает инкрементируемый счётчик.");
        System.out.println("При выходе из приложения показатель счётчика сохраняется.");
        System.out.println("При повторном запуске счётчик загружается с последнего сохранённого положения.");
        System.out.println();
    }
    void author() {
        System.out.println();
        System.out.println("/" + "*".repeat(23) + "/");
        System.out.println("/******Te3Ka_PaynE******/");
        System.out.println("/*79811131773@yandex.ru*/");
        System.out.println("/" + "*".repeat(23) + "/");
    }

    void showIterator() {
        Iterator iter = new Iterator();
        System.out.println("Текущее значение счётчика: " + iter.getIterator());
    }
}

class Iterator {
    private int iterator = 0;

    void setIterator(int iterator) {
        this.iterator = iterator;
    }

    int getIterator() {
        return iterator;
    }
}