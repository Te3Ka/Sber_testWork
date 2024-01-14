package ru.Te3Ka_ProgramM;

import java.io.*;
import java.util.Scanner;

//***********************//
//******Te3Ka_PaynE******//
//*79811131773@yandex.ru*//
//***********************//

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Decorator decorator = new Decorator();
        Iterator iterator = new Iterator();
        Logic logic = new Logic();

        decorator.startProgram();

        logic.loadIterator(iterator);
        logic.mainMenu(iterator, decorator);

        decorator.author();
    }
}

class Decorator {
    // Цвет консольных команд
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_GREEN = "\u001B[32m";


    /**
     * Метод для показа консольного текста с описанием работы программы.
     */
    void startProgram() {
        System.out.println("Программа показывает инкрементируемый счётчик.");
        System.out.println("При выходе из приложения показатель счётчика сохраняется.");
        System.out.println("При повторном запуске счётчик загружается с последнего сохранённого положения.");
        System.out.println();
    }

    /**
     * Метод для показа консаольного текста с описанием команд для работы программы.
     */
    void showCommandsMenu() {
        System.out.println("Команды управления программой:");
        System.out.println("-- " + ANSI_YELLOW + "/inc" + ANSI_RESET + " - увеличение счётчика на 1;");
        System.out.println("-- " + ANSI_YELLOW + "/reset" + ANSI_RESET + " - сброс счётчика до 0.");
        System.out.println("-- " + ANSI_YELLOW + "/stop" + ANSI_RESET + " - завершить работу программы.");
        System.out.println("Примечание: команды управления нужно вводить на английской раскладке маленькими буквами и указанием знака \"/\" в начале команды.");
        System.out.print("Введите команду >>: ");
    }

    /**
     * Метод для показа консольного текста автора программы.
     */
    void author() {
        System.out.println();
        System.out.println(ANSI_YELLOW + "/" + "*".repeat(23) + "/");
        System.out.println("/******" + ANSI_PURPLE + "Te3Ka_PaynE" + ANSI_YELLOW + "******/");
        System.out.println("/*" + ANSI_PURPLE + "79811131773@yandex.ru" + ANSI_YELLOW + "*/");
        System.out.println("/" + "*".repeat(23) + "/" + ANSI_RESET);
    }

    /**
     * Метод для отображения текущего значения итератора в консоли.
     * @param iterator - счётчик
     */
    void showIterator(Iterator iterator) {
        System.out.println("Текущее значение счётчика: " + Decorator.ANSI_RED + iterator.getIterator() + Decorator.ANSI_RESET);
    }
}

class Logic implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L; // Серийная версия интерфейса для сохранения файла

    final String FILE_PATH = "iter.ser"; // Путь к файлу

    // Константы команд для управления программой через консоль.
    final String PLUS_ITERATOR = "/inc";
    final String RESET_ITERATOR = "/reset";
    final String STOP_ITERATOR = "/stop";

    /**
     * Основной метод управления программой.
     * Показывает меню управления.
     * Проверяет вводимые пользователем команды.
     * Изменяет и отображает в консоли значение итератора.
     * @param iterator - счётчик
     * @param decorator - класс декоратора, для отображения текста в консоли.
     */
    void mainMenu(Iterator iterator, Decorator decorator) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";

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
                    saveIterator(iterator);
                    System.out.println(Decorator.ANSI_GREEN + "Завершаю работу" + Decorator.ANSI_RESET);
                    return;
                }
                default -> System.out.println(Decorator.ANSI_RED + "Введена неверная команда!" + Decorator.ANSI_RESET);
            }
            decorator.showIterator(iterator);
        }

    }

    /**
     * Метод для загрузки значения итератора из файла.
     * @param iterator - счётчик
     */
    void loadIterator(Iterator iterator) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        iterator.setIterator((Integer) objectInputStream.readObject());

        System.out.println("Счётчик загружен, значение: " + Decorator.ANSI_RED + iterator.getIterator() + Decorator.ANSI_RESET);
    }

    /**
     * Метод сохранения значения счётчика в файл.
     * @param iterator - счётчик
     */
    void saveIterator(Iterator iterator) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(iterator.getIterator());
        objectOutputStream.close();
    }

    /**
     * Метод для увеличения счётчика на единицу
     * @param iterator - счётчик
     */
    void plusIterator(Iterator iterator) {
        iterator.setIterator(iterator.getIterator() + 1);
    }

    /**
     * Метод для сброса счётчика до нуля.
     * @param iterator - счётчик
     */
    void resetIterator(Iterator iterator) {
        iterator.setIterator(0);
    }
}

/**
 * Класс счётчика, с которым ведётся основная работа программы.
 */
class Iterator {
    private int iterator = 0;

    void setIterator(int iterator) {
        this.iterator = iterator;
    }

    int getIterator() {
        return iterator;
    }
}