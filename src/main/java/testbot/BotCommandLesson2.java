package testbot;

public class BotCommandLesson2 extends BotCommand {

    public void process(String text, Answer answer) {

        if (text.equalsIgnoreCase("лекция 2")) {
            answer.text = "Привет, выберите действие 1,2,3";
            answer.Row1.add("1");
            answer.Row1.add("2");
            answer.Row1.add("3");
        }
        if (text.equalsIgnoreCase("1")) {
            answer.text = "Вы нажали кнопку 1";
        }
        if (text.equalsIgnoreCase("2")) {
            answer.text = "Вы нажали кнопку 2";
        }
        if (text.equalsIgnoreCase("3")) {
            answer.text = "Вы нажали кнопку 3";
        }
    }
}
