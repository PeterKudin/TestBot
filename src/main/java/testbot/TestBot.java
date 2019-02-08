package testbot;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.TimeZone;

public class TestBot extends TelegramLongPollingBot {
    private static final String BOT_NAME = "Misha2010TestBot";
    private static final String BOT_TOKEN = "722649547:AAFobeolFSuWDdTU3PBiRqEDdJVkF_Wnc40";


    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) switch (message.getText().toLowerCase()) {
            case "привет":
                sendMsgWithName(message, "Привет");
                break;
            case "здравствуй":
                sendMsg(message, "Здравствуй, " + message.getChat().getFirstName() + "!");
                break;
            case "пока":
                sendMsgWithName(message, "Пока");
                break;
            case "до свидания":
                sendMsgWithName(message, "До свидания");
                break;
            case "как дела":
                sendMsg(message, "Отлично");
                break;
            case "сколько тебе лет":
                sendMsg(message, "Роботам такие вопросы не задают.");
                break;
            case "время":
            case "который час": {
                Date date = new Date();
                DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                df.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
                sendMsg(message, "Сейчас " + df.format(date));
                break;
            }
            default:
                sendMsg(message, "Я не знаю что ответить на это");
                break;
        }
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
//        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);

        AddButtons(sendMessage);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendMsgWithName(Message message, String text) {
        sendMsg(message, text + ", "+ message.getChat().getFirstName() + "!");
    }

    private void AddButtons(SendMessage sendMessage) {
        // Создаем клавиуатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add(new KeyboardButton("Привет"));

        // Вторая строчка клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        // Добавляем кнопки во вторую строчку клавиатуры
        keyboardSecondRow.add(new KeyboardButton("Пока"));

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);
    }

}
