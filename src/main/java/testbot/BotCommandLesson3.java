package testbot;

public class BotCommandLesson3 extends BotCommand {

    public void process(String text, Answer answer) {

        if (text.equals("урок 3") || (text.equals("привет"))) {
            answer.text = "Привет, выберите пример, который ";
            answer.Row1.add("123+534");
            answer.Row2.add("781+236");
            answer.Row2.add("809+149");
        }
        if (text.equals("123+534"))
            answer.text = text + "=" + (123 + 534);
        if (text.equals("781+236"))
            answer.text = "781+236=" + (781 + 236);
        if (text.equals("809+149"))
            answer.text = "809+149=" + (809 + 149);
    }
}
