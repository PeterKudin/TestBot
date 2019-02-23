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
    private String lastmsg = "";
    private List<BotCommand> botCommands = new ArrayList<>();

    TestBot() {
        botCommands.add(new BotCommandHello());
        botCommands.add(new BotCommandWeather());
        botCommands.add(new BotCommandTime());
        botCommands.add(new BotCommandLesson2());
        botCommands.add(new BotCommandLesson3());
    }

    @Override
    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();
        if (message == null || !message.hasText()) return;

        Answer answer = new Answer();
        for (BotCommand bot : botCommands) {
            bot.process(message.getText(), answer);
            if (answer.text.length() != 0) {
                sendMsg(message, answer.text, answer.withname, answer.Row1, answer.Row2);
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

        try {
            if (message != null && message.hasText()) {
                switch (message.getText().toLowerCase()) {
                    case "привет":
                        if (lastmsg.equals("привет")) {
                            sendMsg(message, "Мы уже здоровались. Второй раз здороваться не надо (правила этикета)", false);
                        } else {
                            sendMsg(message, "Привет", true);
                        }
                        break;
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
        } finally {
            if (message != null && message.hasText())
                lastmsg = message.getText().toLowerCase();
        }
    }

    private void sendMsg(Message message, String text, Boolean needname, List<String> FirstRowCaptions, List<String> SecondRowCaptions) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
//        sendMessage.setReplyToMessageId(message.getMessageId());
        if (needname) text = text + ", " + message.getChat().getFirstName() + "!";
        sendMessage.setText(text);

        AddButtons(sendMessage, FirstRowCaptions, SecondRowCaptions);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendMsg(Message message, String text, Boolean needname, List<String> FirstRowCaptions) {
        sendMsg(message, text, needname, FirstRowCaptions, Collections.emptyList());
    }

    private void sendMsg(Message message, String text, Boolean needname) {
        sendMsg(message, text, needname, Collections.emptyList(), Collections.emptyList());
    }

    private void AddButtons(SendMessage sendMessage, List<String> FirstRowCaptions, List<String> SecondRowCaptions) {

        if ((FirstRowCaptions.size() == 0) && (SecondRowCaptions.size() == 0)) return;

        // Создаем клавиуатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        if (FirstRowCaptions.size() > 0) {
            KeyboardRow keyboardFirstRow = new KeyboardRow();
            for (String temp : FirstRowCaptions)
                keyboardFirstRow.add(new KeyboardButton(temp));
            // Добавляем строчкe клавиатуры в список
            keyboard.add(keyboardFirstRow);
        }

        // Добавляем кнопки в первую строчку клавиатуры
        if (SecondRowCaptions.size() > 0) {
            KeyboardRow keyboardSecondRow = new KeyboardRow();
            for (String temp : SecondRowCaptions)
                keyboardSecondRow.add(new KeyboardButton(temp));
            keyboard.add(keyboardSecondRow);
        }

        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);
    }

}
