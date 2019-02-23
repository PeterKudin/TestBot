package testbot;

public class BotCommandLesson3 extends BotCommand {

    public void process(String text, Answer answer) {

        if (text.equalsIgnoreCase("урок 3") || (text.equalsIgnoreCase("привет"))) {
            answer.text = "Привет, выберите пример, который ";
            answer.Row1.add("123+534");
            answer.Row2.add("781+236");
        }
        if (text.equalsIgnoreCase("123+534")) {
            int i = 123 + 534;
            answer.text = "123+534="+ i;
        }
    }
}
