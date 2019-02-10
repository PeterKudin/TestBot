package testbot;

class Weather {
    String getWeather(String city) {
        return  "Сегодня в городе " +  city + " -4 градуса. " +
                "Завтра утром -4...-2, днем -2..0" ;

    }
    String getSeason(String city) {
        return  "В городе " +city+" весна.";
    }
}