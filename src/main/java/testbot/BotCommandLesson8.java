/*
Задание 7: сделать класс BotCommandLesson7, решить примеры из тренажера по математике стр.55
*/
package testbot;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Arrays;
import java.util.List;

public class BotCommandLesson8 extends BotCommand {

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

        if (text.equals("урок 8") ) {
            List<String> expList = Arrays.asList("14/2", "6/2", "2*2", "10/2",
                    "2/2", "4*2", "7*2", "3*2",
                    "8/2", "6*2", "4/2", "10*2",
                    "9/2", "20/2", "5*2", "0*2",
                    "16/2", "8*2", "18/2", "1*2",
                    "12/2", "6*2", "20/2", "0*2"

                    );

            answer.text = "Привет! Я решаю примеры из тренажера.";
            for (String rowExp: expList) {
                answer.text = answer.text  + " " + eval(rowExp);
            }
        }
    }
}
