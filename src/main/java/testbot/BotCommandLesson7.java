/*
Задание 7: сделать класс BotCommandLesson7, решить примеры из тренажера по математике стр.55
*/
package testbot;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Arrays;
import java.util.List;

public class BotCommandLesson7 extends BotCommand {

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

        if (text.equals("урок 7") ) {
            List<String> expList = Arrays.asList("91+9","77+19","96-37","74-55",
                    "82-19","38-19","88+10","56-20",
                    "83+17","76-53","30-9","73-37",
                    "74-28","86-42","48+23","56+43",
                    "70-36","17+63","75-55","87-37",
                    "64-43","34+55","48-27","77+18",
                    "55+26","21+75","52+29","69-62",
                    "80-18","62-34","41-38","47+47",
                    "38+26","50-22","66-57","56-42"
                    );

            answer.text = "Привет! Я решаю примеры из тренажера.";
            for (String rowExp: expList) {
                answer.text = answer.text  + " " + eval(rowExp);
            }
        }
    }
}
