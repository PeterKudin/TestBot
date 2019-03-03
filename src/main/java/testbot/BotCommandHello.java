package testbot;

public class BotCommandHello extends BotCommand {

    public void process(String text, Answer answer) {
        if (text.equalsIgnoreCase("привет")) {
            answer.withname = true;
            answer.text = "Привет";
            answer.Row1.add("Урок 2");
            answer.Row1.add("Урок 3");
            answer.Row2.add("Урок 4");
            answer.Row2.add("Урок 5");
            answer.Row3.add("Урок 6");
            answer.Row3.add("Урок 7");
        }
    }
}
