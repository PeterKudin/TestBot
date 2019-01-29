package testbot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.TelegramBotsApi;

public class Main {

    public static void main(String[] args) {
       // Initialize Api Context
        System.out.println("before ApiContextInitializer.init");
        ApiContextInitializer.init();
        System.out.println("after ApiContextInitializer.init");

        // Instantiate Telegram Bots API
        System.out.println("before new TelegramBotsApi()");
        TelegramBotsApi botsApi = new TelegramBotsApi();
        System.out.println("after new TelegramBotsApi()");

        // Register our bot
        try {
            botsApi.registerBot(new TestBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}