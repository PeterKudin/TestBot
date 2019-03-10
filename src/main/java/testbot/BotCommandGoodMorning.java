package testbot;

import java.util.Calendar;
import java.util.TimeZone;

public class BotCommandGoodMorning extends BotCommand {

    public void process(String text, Answer answer) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        boolean isMorning = (hour >= 5) && (hour <= 11);
        boolean isDay = ((hour >= 12) && (hour <= 15));
        boolean isEvening=(hour >= 16) && (hour <= 23);

        if (text.equals("доброе утро")) {
            if (isMorning) answer.text = "Доброе утро";
            if (isDay) answer.text = "Ну какое же сейчас утро, сейчас ведь день. Добрый день";
            if (isEvening) answer.text = "Ну какое же сейчас утро, сейчас ведь вечер. Добрый вечер";
        }
        if (text.equals("добрый день")) {
            if (isMorning) answer.text = "Ну какой же сейчас день, сейчас ведь утро. Доброе утро";
            if (isDay) answer.text = "Добрый день";
            if (isEvening) answer.text = "Ну какой же сейчас день, сейчас ведь вечер. Добрый вечер";
        }
        if (text.equals("привет")) {
            answer.withname = true;

            if (isMorning) answer.text = "Доброе утро!";
            if (isDay) answer.text = "Добрый день!";
            if (isEvening) answer.text = "Добрый вечер!";

            answer.text = "Сейчас " + hour + " часов" + answer.text;
        }
    }
}
