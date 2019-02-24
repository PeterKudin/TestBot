package testbot;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class BotCommandLesson4 extends BotCommand {

    public void process(String text, Answer answer) {

        if (text.equals("урок 4") || (text.equals("посчитай"))) {

            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");

            try {
                answer.text = engine.eval(text).toString();
            } catch (ScriptException e) {
                answer.text = "Не могу вычислить " + text + ", ошибка " + e.getMessage();
            }
        }
    }
}