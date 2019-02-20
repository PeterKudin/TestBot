package testbot;

public class BotCommandHello extends BotCommand {

    public Boolean process(String text, Answer answer) {
        if (text.equalsIgnoreCase("привет")) {
            answer.withname = true;
            answer.text = "Привет";
            answer.Row1.add("1+1");
            answer.Row1.add("2+2");
            return true;
        }
        return false;
    }
}
