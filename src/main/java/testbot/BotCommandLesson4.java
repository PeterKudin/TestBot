package testbot;

public class BotCommandLesson4 extends BotCommand {

    public void process(String text, Answer answer) {

        if (text.equals("урок 4") || (text.equals("привет"))) {
            answer.text = "Привет! Да здравствуют роботы!!!";
            answer.Row1.add("1.1");
        }
        if (text.equals("1.1"))
            answer.text = "Строка 1, столбец 1";
    }
}
