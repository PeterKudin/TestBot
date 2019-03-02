package testbot;

public class BotCommandWeather extends BotCommand {

    public void process(String text, Answer answer) {
        if (text.equalsIgnoreCase("какая погода")) {
            answer.text = getWeather("Ногинск") + " " + getSeason("Ногинск");
        }
    }

    private String getWeather(String city) {
        return "Сегодня в городе " + city + " +1 градус. " +
                "Завтра утром -5...-2, днем -2..0";

    }

    private String getSeason(String city) {
        return "В городе " + city + " весна.";
    }
}