package testbot;

public class BotCommandWeather extends BotCommand {

    public void process(String text, Answer answer) {
        if (text.equalsIgnoreCase("какая погода")) {
            answer.text = getWeather("Ногинск") + " " + getSeason("Ногинск");
        }
    }

    private String getWeather(String city) {
        return "Сегодня в городе " + city + " +3 градуса. " +
                "Завтра утром 0...+2, днем +1..+2";

    }

    private String getSeason(String city) {
        return "В городе " + city + " весна.";
    }
}