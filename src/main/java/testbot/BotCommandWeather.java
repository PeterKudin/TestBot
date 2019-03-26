/*
Задание: научить бота сообщать текущую погоду для города Ногинска

1. Нужно переписать реализацию функции getWeather в модуле BotCommandWeather.java
2. Для того, чтобы получить текущую погоду, нужно вызвать метод getWeatherByCityName,
и передать в качестве параметр название города, для которого нужно получить погоду.
getWeatherByCityName("Ногинск");
3. Данные о погоде будет возвращены в виде объекта Weather, в котором будет описание текущей погоды.
Нужно объявить переменную с типом Weather и названием weather и записать в нее результат работы функции getWeatherByCityName:
Weather weather;  // Объявление переменной
weather = getWeatherByCityName("Ногинск"); // Вызов функции getWeatherByCityName и занесение результата в переменную weather

4. Чтобы узнать текущую температуру, нужно обратиться к свойству temp объекта weather: вызвать weather.temp.
О погоде известные следующие данные:
weather.temp - текущая температура
weather.temp_min - минимальная температура
weather.temp_max - максимальная температура
weather.description - описание погоды (облачно, солнце, дождь и т.п.)

5. Нужно сформировать строку с данными о погоде и записать ее в ответ бота (в answer.text),
answer.text = "Сегодня в Ногинске " + weather.temp + " градусов, "  + weather.description;

6. Также можно добавить в сообщение ссылку на картинку с описанием погоды,
для этого нужно добавить в ответ answer.text следующий текст:
"http://openweathermap.org/img/w/"+weather.icon_id+".png

answer.text = answer.text +"http://openweathermap.org/img/w/"+weather.icon_id+".png;

http://api.openweathermap.org/data/2.5/weather?q=ГОРОД,ru&units=МЕТРИЧЕСКАЯ_СИСТЕМА&lang=РУССКИЙЯЗЫК&appid=КЛЮЧ_ДОСТУПА
http://api.openweathermap.org/data/2.5/weather?q=Ногинск,ru&units=metric&lang=ru&appid=33d3288733508a5997641d20aa5de2ae

 */
package testbot;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class Weather {
    String city; // Город
    String temp; // Температура
    String temp_min; // Минимальная температура
    String temp_max; // Максимальная температура
    String description; // Описание погоды
    String icon_id; // Картинка с погодой

    public String toString() {
        return temp + " градусов, " + description +
                " http://openweathermap.org/img/w/" + icon_id + ".png";
    }
}

public class BotCommandWeather extends BotCommand {

    private static final String OWN_API_KEY =
            "33d3288733508a5997641d20aa5de2ae";

    public void process(String text, Answer answer) {
        if (text.equals("какая погода")) {
            answer.text = getWeather("Ногинск");
        }

        if (text.contains("погода") && text.contains("ногинск")) {
            Weather noginsk = getWeatherByCityName("Ногинск");
            answer.text = "Погода в городе Ногинске " + noginsk.toString();
        }
        if (text.contains("погода") && text.contains("москв")) {
            Weather moscow = getWeatherByCityName("Москва");
            answer.text = "Погода в городе Москва " + moscow.toString();
        }
        if (text.contains("погода") && text.contains("одинцово")) {
            Weather odintsovo = getWeatherByCityName("Одинцово");
            answer.text = "Погода в городе Одинцово " + odintsovo.toString();
        }
        if (text.equals("погода")) {
            Weather noginsk;
            Weather moscow;
            Weather odintsovo;
            noginsk = getWeatherByCityName("ногинск");
            moscow = getWeatherByCityName("Москва");
            odintsovo = getWeatherByCityName("Одинцово");
            answer.text = String.format("Погода в Ногинске %s \\u00b0, в Москве %s \\u00b0, в Одинцово %s \\u00b0",
                    noginsk.temp, moscow.temp, odintsovo.temp);

        }

    }

    private String getWeather(String city) {
        Weather weather = getWeatherByCityName("Ногинск");
        return "Сегодня в Ногинске " + weather.toString();
    }

    private Weather getWeatherByCityName(String city) {
        Weather weather = new Weather();

        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + ",ru&lang=ru&units=metric&appid=" + OWN_API_KEY;
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;

            StringBuilder response;
            response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String json = response.toString();
            JSONObject jsonObject = new JSONObject(json);
            JSONObject main = (JSONObject) jsonObject.get("main");
            JSONArray jsonWeather = (JSONArray) jsonObject.get("weather");

            weather.city = jsonObject.get("name").toString();
            weather.temp = main.get("temp").toString();
            weather.temp_min = main.get("temp_min").toString();
            weather.temp_max = main.get("temp_max").toString();

            JSONObject first = (JSONObject) jsonWeather.get(0);
            weather.description = first.get("description").toString();
            weather.icon_id = first.get("icon").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return weather;
    }
}