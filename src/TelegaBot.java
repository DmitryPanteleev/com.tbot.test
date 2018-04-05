/**
 * Created by DPanteleev on 26.03.2018.
 */

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.methods.send.SendSticker;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;


public class TelegaBot extends TelegramLongPollingBot {

    Text text1 = new Text();

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
        Message msg = e.getMessage(); // Это нам понадобится
        String txt = msg.getText();
        switch (txt) {
            case "/start":
                sendStick("CAADAgADHwAD4F7EEbOmWe65VI4EAg",msg);
                sendMsg(msg, "Hello! \uD83D\uDE18");
                break;
            case "Назад":
                sendMsg(msg, "down");
                break;
            case "Домой":
                sendStick("CAADAgADHwAD4F7EEbOmWe65VI4EAg",msg);
                sendMsg(msg, "Hello!! \uD83D\uDE18");
                break;
        }
    }

    @Override
    public String getBotToken() {
        return "516033907:AAGl4zfvz6M_-vXhIF0zFW_-RO8r1y5gp5g";
    }

    private void sendMsg(Message msg, String text) {

        SendMessage s = new SendMessage()
                .setChatId(msg.getChatId())
                .setText(text);
        s.setReplyMarkup(new ReplyKeyboardMarkup().setResizeKeyboard(true).setKeyboard(sendKeyboard()));


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
    private List<KeyboardRow> sendKeyboard(){
        KeyboardRow rowUp = new KeyboardRow();
        KeyboardRow rowDown = new KeyboardRow();
//        rowUp.add("/start");
        rowDown.add("Назад");
        rowDown.add("Домой");
        List<KeyboardRow> getKeyboardList = new ArrayList<>();
        getKeyboardList.add(rowUp);
        getKeyboardList.add(rowDown);
        return getKeyboardList;
    }


}