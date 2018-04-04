import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class GetKeyboard {

    private List<KeyboardRow> keyboardRowList = new ArrayList<>();
    private List<InlineKeyboardMarkup> inlineKeyboardMarkupList = new ArrayList<>();

    public List<KeyboardRow> getKeyboardRowList() {
        return keyboardRowList;
    }

    public List<InlineKeyboardMarkup> getInlineKeyboardMarkupList() {
        return inlineKeyboardMarkupList;
    }

    private KeyboardRow keyboardRow() {

        keyboardRow().add("/Stary");

        return keyboardRow();
    }

    private KeyboardRow keyboardRowDownHome(){
        keyboardRowDownHome().add("Назад");
        keyboardRowDownHome().add("Домой");
        return keyboardRowDownHome();
    }




    private void keyboar() {

        KeyboardRow row = new KeyboardRow();
        KeyboardRow downHome = new KeyboardRow();

        row.add(0, "/Start");

        downHome.add("Назад");
        downHome.add("Домой");

        keyboardRowList.add(0, downHome);
        keyboardRowList.add(1, row);


    }


}
