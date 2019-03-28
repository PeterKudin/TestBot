package testbot;

public class BotCommandHello extends BotCommand {

    public void process(String text, Answer answer) {
        if (text.equalsIgnoreCase("привет")) {
            answer.withname = true;
            answer.text = "Привет";
            answer.Row1.add("Урок 2");
            answer.Row1.add("Урок 3");
            answer.Row1.add("Урок 4");
            answer.Row2.add("Урок 5");
            answer.Row2.add("Урок 6");
            answer.Row2.add("Урок 7");
            answer.Row3.add("Урок 8");
            answer.Row3.add("Урок 9");
            answer.Row3.add("Урок 10");
            answer.Row4.add("Погода в Ногинске");
            answer.Row4.add("Погода в Москве");
            answer.Row4.add("Погода в Одинцово");
            answer.Row4.add("Погода");
        }
    }
}
