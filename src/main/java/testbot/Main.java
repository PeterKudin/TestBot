package testbot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.TelegramBotsApi;

public class Main {

    public static void main(String[] args) {
       // Initialize Api Context
        ApiContextInitializer.init();

        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // Register our bot
        try {
            System.out.println("before botsApi.registerBot");
            botsApi.registerBot(new TestBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}