package testbot;

import java.util.Calendar;

public class BotCommandGoodMorning extends BotCommand {

    public void process(String text, Answer answer) {
        if (text.equals("привет")) {
            answer.withname = true;

            Calendar cal = Calendar.getInstance();
            int hour = cal.get(Calendar.HOUR_OF_DAY);

            if ((hour >= 5) && (hour <= 11)) {
                answer.text = "Доброе утро";
            }
            if ((hour >= 12) && (hour <= 15)) {
                answer.text = "Добрый день";
            }
            if ((hour >= 16) && (hour <= 23)) {
                answer.text = "Добрый вечер";
            }
            answer.text = "Сейчас " + hour + " часов" + answer.text;
        }
    }
}
