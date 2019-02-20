package testbot;

import java.util.ArrayList;
import java.util.List;

class Answer {
    Boolean withname = false;
    String text = "";
    List<String> Row1 = new ArrayList<>();
    List<String> Row2 = new ArrayList<>();
}
abstract public class BotCommand {

    public abstract Boolean process(String text, Answer answer);


}
