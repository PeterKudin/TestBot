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

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class BotCommandLesson6 extends BotCommand {

    private  String eval(String expression) {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        try {
            return expression + "=" + engine.eval(expression).toString();
        } catch (ScriptException e) {
            return  "Не могу вычислить " + expression + ", ошибка " + e.getMessage();
        }

    }

    public void process(String text, Answer answer) {

        if (text.equals("урок 6") || (text.equals("привет"))) {
            answer.text = "Привет! Я решаю примеры из тренажера."
              + " " + eval("39+48")
              + " " + eval("48+36");
        }
    }
}
