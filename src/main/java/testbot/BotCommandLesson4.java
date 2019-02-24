package testbot;

public class BotCommandLesson4 extends BotCommand {

    public void process(String text, Answer answer) {

        if (text.equals("урок 4") || (text.equals("привет"))) {
            answer.text = "Привет! Да здравствуют роботы!!!";
            answer.Row1.add("1+1");
            answer.Row2.add("2+2");
            answer.Row3.add("3+3");
            answer.Row4.add("4+4");
            answer.Row5.add("5+5");
            answer.Row6.add("6+6");
        }
    }
}
