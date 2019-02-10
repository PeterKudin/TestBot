package testbot;

public class Weather {
    public String getWeather(String city) {
        return  "Сегодня в городе " +  city + " -4 градуса. " +
                "Завтра утром -4...-2, днем -2..0" ;

    }
    public String getSeason(String city) {
        return  "В городе " +city+" весна.";
    }
}