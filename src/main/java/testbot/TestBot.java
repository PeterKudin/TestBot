package testbot;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.*;

public class TestBot extends TelegramLongPollingBot {
    private static final String BOT_NAME = "Misha2010TestBot";
    private static final String BOT_TOKEN = "722649547:AAFobeolFSuWDdTU3PBiRqEDdJVkF_Wnc40";
    private List<BotCommand> botCommands = new ArrayList<>();

    TestBot() {
        botCommands.add(new BotCommandWeather());
        botCommands.add(new BotCommandTime());
        botCommands.add(new BotCommandLesson2());
        botCommands.add(new BotCommandLesson3());
        botCommands.add(new BotCommandLesson4());
        botCommands.add(new BotCommandLesson5());
        botCommands.add(new BotCommandLesson6());
        botCommands.add(new BotCommandCalc());
        botCommands.add(new BotCommandHello());
    }

    @Override
    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();
        if (message == null || !message.hasText()) return;
        String text = message.getText().toLowerCase();

        Answer answer = new Answer();
        for (BotCommand bot : botCommands) {
            bot.process(text, answer);
            if (answer.text.length() != 0) {
                sendMsg(message, answer);
                return;
            }
        }

        oldOnUpdateReceived(update);
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }


    private void oldOnUpdateReceived(Update update) {

        Message message = update.getMessage();

        if (message != null && message.hasText()) {
            switch (message.getText().toLowerCase()) {
                case "здравствуй":
                    sendMsg(message, "Здравствуй", true);
                    break;
                case "пока":
                    sendMsg(message, "Пока", true);
                    break;
                case "до свидания":
                    sendMsg(message, "До свидания", true);
                    break;
                case "как дела":
                    sendMsg(message, "Отлично", false, Arrays.asList("Сколько тебе лет", "который час"));
                    break;
                case "сколько тебе лет":
                    sendMsg(message, "Роботам такие вопросы не задают.", true,
                            Arrays.asList("Привет", "Пока", "Распечатать чек"));
                    break;
                default:
                    sendMsg(message, "Я не знаю что ответить на это", false);
                    break;
            }
        }
    }

    private void sendMsg(Message message, Answer answer) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
//        sendMessage.setReplyToMessageId(message.getMessageId());
        if (answer.withname) answer.text = answer.text + ", " + message.getChat().getFirstName() + "!";
        sendMessage.setText(answer.text);

        AddButtons(sendMessage, Arrays.asList(answer.Row1, answer.Row2, answer.Row3, answer.Row4, answer.Row5, answer.Row6));

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendMsg(Message message, String text, Boolean needname, List<String> FirstRowCaptions) {
        Answer answer = new Answer();
        answer.text = text;
        answer.withname = needname;
        answer.Row1 = FirstRowCaptions;
        sendMsg(message, answer);
    }

    private void sendMsg(Message message, String text, Boolean needname) {
        sendMsg(message, text, needname, Collections.emptyList());
    }

    private void AddButtons(SendMessage sendMessage, List<List<String>> RowList) {

        // Создаем клавиуатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        for (List<String> Row : RowList) {
            if (Row.size() == 0) continue;

            KeyboardRow keyboardRow = new KeyboardRow();
            for (String temp : Row)
                keyboardRow.add(new KeyboardButton(temp));
            keyboard.add(keyboardRow);
        }

        // и устанваливаем этот список нашей клавиатуре
        if (keyboard.size() != 0)
            replyKeyboardMarkup.setKeyboard(keyboard);
    }
}
