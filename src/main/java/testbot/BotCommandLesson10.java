/*
Задание 10: проверка правильности решения примеров из тренажера
Нужно написать программу, которая проверит правильно ли решены примеры в тренажера

1. Бот должен реагировать на команды "Урок 10" и "Тренажер 59"
2. Бот должен проверить все решения, и показать те примеры, которые решены неправильно. Либо ответить
"Молодец, все решено верно"
3. Для этого нужно записать все примеры с предпологаемыми ответами в массив строк examples в виде "2*3==6", "12/4==3":
List<String> examples = Arrays.asList("2*3==6", "12/4==3");

4. Далее бот должен перебрать все примеры по одному из массива в цикле, в переменную example будет занесен текущий элемент массива examples:
for (String example: examples) {

}

5.В цикле нужно вычислить значение примера example и проверить, правильно ли решен пример.
Для решения примера нужно вызвать функцию eval(example).
String res = eval(example);
Чтобы проверить правильно ли решен пример, нужно посмотреть содержится ли в ответе строка false. Если строка false есть, то пример решен неверно
if (res.contains("false"))
Если пример решен неверно, то пример нужно записать в ответную строку
    answer.text = answer.text + ", " + example;
6. После окончания цикла у нас в answer.text будут неправильно решенные примеры. Если answer.text не пустой, то нужно добавить вначале строку "Есть ошибочки, вот эти примеры решены неверно", иначе записать в answer.text строку "Молодец, все решено верно"
if (answer.text.isEmpty)
  answer.text = "Молодец, все решено верно"
else answer.text = "Есть ошибочки, вот эти примеры решены неверно " + answer.text;

*/
package testbot;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Arrays;
import java.util.List;

public class BotCommandLesson10 extends BotCommand {

    private String eval(String expression) {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        try {
            return expression + "=" + engine.eval(expression).toString();
        } catch (ScriptException e) {
            return "Не могу вычислить " + expression + ", ошибка " + e.getMessage();
        }

    }

    public void process(String text, Answer answer) {

        if (text.equals("урок 10") || text.equals("тренажер 59")) {
            List<String> examples = Arrays.asList("3*3==9", "12/3==5");

            for (String example : examples) {
                String res = eval(example);
                if (res.contains("false"))
                    answer.text = answer.text + ", " + example;
            }

            if (answer.text.isEmpty())
                answer.text = "Молодец, все решено верно";
            else answer.text = "Есть ошибочки, вот эти примеры решены неверно " + answer.text;
        }
    }
}
