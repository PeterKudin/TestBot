/*
Задание, урок 9: учим бота сообщать какой сегодня день недели
1. Бот должен уметь отвечать на фразу 'какой сегодня день недели', со всевозможными перестановками слов (например: сегодня какой день недели, день недели сегодня какой, какой день недели сегодня)
2. Для получения текущего дня недели нужно вписать следующий код (в переменной dayOfWeek будет название дня недели):
  Format dateFormat = new SimpleDateFormat("EEE");
  String dayOfWeek = dateFormat.format(new Date());

*/
package testbot;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BotCommandLesson9 extends BotCommand {

    public void process(String text, Answer answer) {
        Format dateFormat = new SimpleDateFormat("EEE");
        String dayOfWeek = dateFormat.format(new Date());

        if (text.equals("урок 9")) {
            answer.text = "Сегодня " + dayOfWeek;
        }
        if (text.contains("какой") && text.contains("сегодня") && text.contains("день") && text.contains("недели"))
            answer.text = "Сегодня " + dayOfWeek;
    }
}
