package testbot;

class Weather {
    String getWeather(String city) {
        return  "Сегодня в городе " +  city + " +3 градуса. " +
                "Завтра утром 0...+2, днем +1..+2" ;

    }
    String getSeason(String city) {
        return  "В городе " +city+" весна.";
    }
}