/*
Задание 5: сделать класс BotCommandLesson5, создать при помощи него клавиатуру в форме ёлочки

        1. Скопировать класс BotCommandLesson4 в BotCommandLesson5
        (Открыть дерево проекта, нажать правой кнопкой на BotCommandLesson4, выбрать Copy, затем выбрать Paste.
        Откроется диалог копирования класса, ввести новое название класса)
        2. Подключить новый класс к боту
        В модуле TestBot.java в конструкторе TestBot() вписать строчку по аналогии с BotCommandLesson4
        botCommands.add(new BotCommandLesson5());
        3. Запрограммировать в BotCommandLesson5 следующий диалог:
        - Пользователь пишет "Привет" или "Урок 5"
        - Бот отвечает: "Привет! Я умею рисовать клавиатуры в форме ёлочки" и создает следующую клавиатуру:
        Кнопка
        Кнопка Кнопка
        Кнопка Кнопка Кнопка
        Кнопка Кнопка Кнопка Кнопка
*/
package testbot;

public class BotCommandLesson5 extends BotCommand {

    public void process(String text, Answer answer) {

        if (text.equals("урок 5") || (text.equals("привет"))) {
            answer.text = "Привет! Я умею рисовать клавиатуры в форме ёлочки.";
            answer.Row1.add("Кнопка");
            answer.Row2.add("Кнопка" );
            answer.Row2.add("Кнопка" );
            answer.Row3.add("Кнопка");
            answer.Row3.add("Кнопка");
            answer.Row3.add("Кнопка");
            answer.Row4.add("Кнопка");
            answer.Row4.add("Кнопка");
            answer.Row4.add("Кнопка");
            answer.Row4.add("Кнопка");
        }
    }
}
