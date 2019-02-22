package testbot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class BotCommandTime extends BotCommand {

    public void process(String text, Answer answer) {
        if (text.equalsIgnoreCase("время") || text.equalsIgnoreCase("который час")) {
            Date date = new Date();
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            df.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
            answer.text = "Сейчас " + df.format(date);
        }
    }
}
