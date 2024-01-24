//***********************//
//****Program author*****//
//******Te3Ka_PaynE******//
//*79811131773@yandex.ru*//
//***********************//

package ru.te3ka_programm;

import java.io.*;
import java.util.Scanner;

/**
 * Класс логики программы.
 * Содержит в себе основные элементы управления программой
 * Методы Загрузки и Сохранение счётчика.
 * Метод основного меню программы
 */
class Logic {

    final String FILE_PATH = "iter.ser"; // Путь к файлу

    // Константы команд для управления программой через консоль.
    static final String PLUS_ITERATOR = "/inc";
    static final String RESET_ITERATOR = "/reset";
    static final String STOP_ITERATOR = "/stop";

    Decorator decorator = new Decorator();
    Iterator iterator = new Iterator();

    /**
     * Основной метод управления программой.
     * Показывает меню управления.
     * Проверяет вводимые пользователем команды.
     * Изменяет и отображает в консоли значение итератора.
     */
    void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        decorator.startProgram();
        try {
            loadIterator(iterator);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            System.out.println("Нажмите Enter для продолжения.");
            scanner.nextLine();
            decorator.showCommandsMenu();
            userInput = scanner.nextLine();

            switch (userInput) {
                case PLUS_ITERATOR -> plusIterator(iterator);
                case RESET_ITERATOR -> resetIterator(iterator);
                case STOP_ITERATOR -> {
                    decorator.showIterator(iterator);
                    try {
                        saveIterator(iterator);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(decorator.ANSI_GREEN + "Завершаю работу" + decorator.ANSI_RESET);
                    decorator.author();
                    return;
                }
                default -> System.out.println(decorator.ANSI_RED + "Введена неверная команда!" + decorator.ANSI_RESET);
            }
            decorator.showIterator(iterator);
        }
    }

    /**
     * Метод для загрузки значения итератора из файла.
     * @param iterator - счётчик
     */
    void loadIterator(Iterator iterator) throws FileNotFoundException {
        try {
            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            ObjectInputStream objectInputStream = null;
            try {
                objectInputStream = new ObjectInputStream(fileInputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                iterator.setIterator((Integer) objectInputStream.readObject());
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Счётчик загружен, значение: " + decorator.ANSI_RED + iterator.getIterator() + decorator.ANSI_RESET);
        } catch (FileNotFoundException ex) {
            System.out.println(decorator.ANSI_RED + "Файл с счётчиком не найден." + decorator.ANSI_RESET);
            iterator.setIterator(0);
            saveIterator(iterator);
            System.out.println(decorator.ANSI_GREEN + "Создан новый файл." + decorator.ANSI_RESET);
            System.out.println("Текущее значение счётчика: " + decorator.ANSI_RED + iterator.getIterator() + decorator.ANSI_RESET);
        }
    }

    /**
     * Метод сохранения значения счётчика в файл.
     * @param iterator - счётчик
     */
    void saveIterator(Iterator iterator) throws FileNotFoundException {
        FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            objectOutputStream.writeObject(iterator.getIterator());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            objectOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод для увеличения счётчика на единицу
     * @param iterator - счётчик
     */
    void plusIterator(Iterator iterator) {
        System.out.println(decorator.ANSI_GREEN + "Счётчик увеличен!" + decorator.ANSI_RESET);
        iterator.setIterator(iterator.getIterator() + 1);
    }

    /**
     * Метод для сброса счётчика до нуля.
     * @param iterator - счётчик
     */
    void resetIterator(Iterator iterator) {
        System.out.println(decorator.ANSI_GREEN + "Счётчик сброшен!" + decorator.ANSI_RESET);
        iterator.setIterator(0);
    }
}