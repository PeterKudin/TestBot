package testbot;

public class BotCommandLesson3 extends BotCommand {

    public void process(String text, Answer answer) {

        if (text.equalsIgnoreCase("урок 3") || (text.equalsIgnoreCase("привет"))) {
            answer.text = "Привет";
            answer.Row1.add("123+534");
        }
        if (text.equalsIgnoreCase("123+534")) {
            int i = 123+534;
            answer.text = Integer.toString(i);
        }
    }
}
