package testbot;

import java.util.Calendar;
import java.util.TimeZone;

public class BotCommandGoodMorning extends BotCommand {

    public void process(String text, Answer answer) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        boolean isMorning =(hour >= 5) && (hour <= 11);
        boolean isDea = ((hour >= 12) && (hour <= 15));


        if (text.equals("доброе утро")) {
            if (isMorning)  answer.text = "Доброе утро";
            if (isDea)  answer.text = "Ну какой же сейчас день, сейчас ведь утро. Доброе утро";

            }
        if (text.equals("привет")) {
            answer.withname = true;


            if (isMorning)  {
                answer.text = "Доброе утро!";
            }
            if ((hour >= 12) && (hour <= 15)) {
                answer.text = "Добрый день!";
            }
            if ((hour >= 16) && (hour <= 23)) {
                answer.text = "Добрый вечер!";
            }
            answer.text = "Сейчас " + hour + " часов" + answer.text;
        }
    }
}
