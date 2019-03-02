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
              + " " + eval("39+48") + " " + eval("48+36")
              + " " + eval("81-47") + " " + eval("38+46")
              + " " + eval("67-29") + " " + eval("64+19")
              + " " + eval("90-74") + " " + eval("70+28")
              + " " + eval("58+34") + " " + eval("89-79")
              + " " + eval("76-29") + " " + eval("42+27")
              + " " + eval("46-18") + " " + eval("72+23")
              + " " + eval("78+20") + " " + eval("86-52")
              + " " + eval("90-37") + " " + eval("56-43")
              + " " + eval("80-19") + " " + eval("74-60")
              + " " + eval("57-45") + " " + eval("87+13")
              + " " + eval("46+23") + " " + eval("81-57")
              + " " + eval("65+23") + " " + eval("45+35")
              + " " + eval("65-53") + " " + eval("67+13")
              + " " + eval("70-58") + " " + eval("34+65")
              + " " + eval("67-37") + " " + eval("98-38");
        }
    }
}
