/**
 * Created by DPanteleev on 26.03.2018.
 */

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.methods.send.SendSticker;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiValidationException;

import java.util.ArrayList;
import java.util.List;


public class TelegaBot extends TelegramLongPollingBot {
    private List<List<InlineKeyboardButton>> inlineKeyboard;

//    TextMessage textMessage1 = new TextMessage();

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new TelegaBot());

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getBotUsername() {
        return "DimooonBot"; // bot name
    }

    @Override
    public void onUpdateReceived(Update e) {
        Message msg = e.getMessage(); // Это нам понадобится // тут мы получаем сообщение
        long chatId;
        try {
            chatId = msg.getChatId();
        } catch (Exception i) {
            chatId = e.getCallbackQuery().getFrom().getId();
        }
        try {
            String txt = msg.getText();
            switch (txt) {
                case "/start":
                    inlineKeyboard = sendInlineKeyboard1();
                    sendStick("CAADAgADHwAD4F7EEbOmWe65VI4EAg", msg);
                    sendMsg(chatId, "Hello! \uD83D\uDE18");
                    break;
                case "Назад":
                    inlineKeyboard = sendInlineKeyboard2();
                    sendMsg(msg.getChatId(), "down");
                    break;
                case "Домой":
                    inlineKeyboard = sendInlineKeyboard1();
                    sendStick("CAADAgADHwAD4F7EEbOmWe65VI4EAg", msg);
                    sendMsg(chatId, "Hello!! \uD83D\uDE18");
                    break;

            }
        } catch (Exception i) {

            CallbackQuery query = e.getCallbackQuery();

            if (e.getCallbackQuery() != null) {
                if (query.getData().equals("test1")) {
                    inlineKeyboard = sendInlineKeyboard1();
                    sendMsg(query.getFrom().getId(), new TextMessage().getTest1() + " " + query.getData());

                    EditMessageReplyMarkup markup = new EditMessageReplyMarkup().setMessageId(query.getMessage().getMessageId()).setChatId(query.getId());
                    try {
                        markup.validate();
                    } catch (TelegramApiValidationException e1) {
                        e1.printStackTrace();
                    }
//                    return;
                } else if (query.getData().equals("test2")) {
                    inlineKeyboard = sendInlineKeyboard2();
                    sendMsg(query.getFrom().getId(), new TextMessage().getTest2() + " " + query.getData());
                    EditMessageReplyMarkup markup = new EditMessageReplyMarkup().setMessageId(query.getMessage().getMessageId()).setChatId(query.getId());
                    try {
                        markup.validate();
                    } catch (TelegramApiValidationException e1) {
                        e1.printStackTrace();
                    }
//                    return;
                }
            }
        }
    }


    @Override
    public String getBotToken() {
        return "";
    }

    private void sendMsg(long chatId, String text) {

        SendMessage s = new SendMessage()
                .setChatId(chatId)
                .setText(text);
        s.setReplyMarkup(new ReplyKeyboardMarkup().setResizeKeyboard(true).setKeyboard(sendKeyboard()));
        s.setReplyMarkup(new InlineKeyboardMarkup().setKeyboard(inlineKeyboard));

        try {
            execute(s);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendStick(String stickerId, Message msg) {
        try {
            sendSticker(new SendSticker().setChatId(msg.getChatId()).setSticker(stickerId));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    private void sendPhoto(String photoId, Message msg) {
        try {
            sendPhoto(new SendPhoto().setChatId(msg.getChatId()).setPhoto(photoId));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    private List<KeyboardRow> sendKeyboard() {
//        KeyboardRow rowUp = new KeyboardRow();
        KeyboardRow rowDown = new KeyboardRow();
//        rowUp.add("/start");
        rowDown.add("Назад");
        rowDown.add("Домой");
        List<KeyboardRow> getKeyboardList = new ArrayList<>();
//        getKeyboardList.add(rowUp);
        getKeyboardList.add(rowDown);
        return getKeyboardList;
    }

    private List<List<InlineKeyboardButton>> sendInlineKeyboard1() {
        InlineKeyboardMarkup firstInlineKeyboardRow = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> inlineKeyboardMarkupList = new ArrayList<>();
        List<InlineKeyboardButton> firstButtonList = new ArrayList<>();
        firstButtonList.add(new InlineKeyboardButton().setText("button1").setCallbackData("test1"));
        firstButtonList.add(new InlineKeyboardButton().setText("button2").setCallbackData("test2"));
        inlineKeyboardMarkupList.add(firstButtonList);
        firstInlineKeyboardRow.setKeyboard(inlineKeyboardMarkupList);

        return inlineKeyboardMarkupList;
    }

    private List<List<InlineKeyboardButton>> sendInlineKeyboard2() {

        List<List<InlineKeyboardButton>> inlineKeyboardMarkupList = new ArrayList<>();
        List<InlineKeyboardButton> firstButtonList = new ArrayList<>();
        firstButtonList.add(new InlineKeyboardButton().setText("button3").setCallbackData("test3"));
        inlineKeyboardMarkupList.add(firstButtonList);

        return inlineKeyboardMarkupList;
    }

}