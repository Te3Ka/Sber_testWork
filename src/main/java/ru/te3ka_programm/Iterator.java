//***********************//
//****Program author*****//
//******Te3Ka_PaynE******//
//*79811131773@yandex.ru*//
//***********************//

package ru.te3ka_programm;


import java.io.Serial;
import java.io.Serializable;

/**
 * Класс счётчика, с которым ведётся основная работа программы.
 */
class Iterator implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L; // Серийная версия интерфейса для сохранения файла
    private int iterator = 0;

    void setIterator(int iterator) {
        this.iterator = iterator;
    }

    int getIterator() {
        return iterator;
    }
}
