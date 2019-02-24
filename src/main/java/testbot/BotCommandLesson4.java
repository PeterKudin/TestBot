package testbot;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class BotCommandLesson4 extends BotCommand {

    public void process(String text, Answer answer) {

        if (text.contains("+") || text.contains("-") || text.contains("\\*") || text.contains("/")) {
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");

            try {
                answer.text = "Я бот, я выполнил расчет, " + text + "=" + engine.eval(text).toString();
            } catch (ScriptException e) {
                answer.text = "Не могу вычислить " + text + ", ошибка " + e.getMessage();
            }
        }
    }
}