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
        System.out.println("-- /inc - увеличение счётчика на 1;");
        System.out.println("-- /reset - сброс счётчика до 0.");
        System.out.println("-- /stop - завершить работу программы.");
        System.out.println("Примечание: команды управления нужно вводить на английской раскладке маленькими буквами и указанием знака \"/\" в начале команды.");
        System.out.print("Введите команду >>: ");
    }

    /**
     * Метод для показа консольного текста автора программы.
     */
    void author() {
        System.out.println();
        System.out.println("/" + "*".repeat(23) + "/");
        System.out.println("/******Te3Ka_PaynE******/");
        System.out.println("/*79811131773@yandex.ru*/");
        System.out.println("/" + "*".repeat(23) + "/");
    }

    void showIterator(Iterator iterator) {
        System.out.println("Текущее значение счётчика: " + iterator.getIterator());
    }
}

class Logic implements Serializable {
    private final long serialVersionUID = 1L;
    final String FILE_PATH = "iter.ser";

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


            if (userInput.equals(PLUS_ITERATOR))
                plusIterator(iterator);
            else if (userInput.equals(RESET_ITERATOR))
                resetIterator(iterator);
            else if (userInput.equals(STOP_ITERATOR)) {
                decorator.showIterator(iterator);
                saveIterator(iterator);
                System.out.println("Завершаю работу");
                return;
            } else
                System.out.println("Введена неверная команда.");
            decorator.showIterator(iterator);
        }

    }

    /**
     * Метод для загрузки значения итератора из файла.
     * Пока заглушка.
     * @param iterator - счётчик
     */
    void loadIterator(Iterator iterator) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        iterator.setIterator((Integer) objectInputStream.readObject());

        System.out.println("Счётчик загружен, значение: " + iterator.getIterator());
    }

    /**
     * Метод сохранения значения счётчика в файл.
     * Пока заглушка.
     * @param iterator - счётчик
     */
    void saveIterator(Iterator iterator) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(iterator.getIterator());
        objectOutputStream.close();
    }

    void plusIterator(Iterator iterator) {
        iterator.setIterator(iterator.getIterator() + 1);
    }

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