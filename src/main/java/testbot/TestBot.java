package testbot;

import org.apache.commons.lang3.ArrayUtils;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

public class TestBot extends TelegramLongPollingBot {
    private static final String BOT_NAME = "Misha2010TestBot";
    private static final String BOT_TOKEN = "722649547:AAFobeolFSuWDdTU3PBiRqEDdJVkF_Wnc40";


    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) switch (message.getText().toLowerCase()) {
            case "привет":
                sendMsg(message, "Привет", true);
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
                sendMsg(message, "Роботам такие вопросы не задают.", false,
                        Arrays.asList("Привет", "Пока"));
                break;
            case "время":
            case "который час": {
                Date date = new Date();
                DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                df.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
                sendMsg(message, "Сейчас " + df.format(date), false);
                break;
            }
            default:
                sendMsg(message, "Я не знаю что ответить на это", false);
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

    private void sendMsg(Message message, String text, Boolean needname, List<String> FirstRowCaptions, List<String> SecondRowCaptions) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
//        sendMessage.setReplyToMessageId(message.getMessageId());
        if (needname) text = text + ", "+ message.getChat().getFirstName() + "!";
        sendMessage.setText(text);

        AddButtons(sendMessage, FirstRowCaptions, SecondRowCaptions);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendMsg(Message message, String text, Boolean needname, List<String> FirstRowCaptions)  {
        sendMsg(message, text, needname, FirstRowCaptions, Collections.emptyList());
    }

    private void sendMsg(Message message, String text, Boolean needname)  {
        sendMsg(message, text, needname, Collections.emptyList(), Collections.emptyList());
    }

    private void AddButtons(SendMessage sendMessage, List<String> FirstRowCaptions, List<String> SecondRowCaptions ) {
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
        for (String temp: FirstRowCaptions)
            keyboardFirstRow.add(new KeyboardButton(temp));
        // Добавляем кнопки в первую строчку клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        for (String temp: SecondRowCaptions)
            keyboardSecondRow.add(new KeyboardButton(temp));
        // Добавляем кнопки во вторую строчку клавиатуры
/*        keyboardFirstRow.add(new KeyboardButton("Привет"));
        keyboardFirstRow.add(new KeyboardButton("Пока"));

        // Вторая строчка клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        // Добавляем кнопки во вторую строчку клавиатуры
        keyboardSecondRow.add(new KeyboardButton("Погода"));
        keyboardSecondRow.add(new KeyboardButton("Кнопка 2"));*/

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);
    }

}
