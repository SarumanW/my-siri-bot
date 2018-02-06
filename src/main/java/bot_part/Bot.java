package bot_part;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import wiki_work.SearchWiki;

public class Bot extends TelegramLongPollingBot{
    private final static int SIZE = 2000;

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            String query = update.getMessage().getText();
            SearchWiki searchWiki = new SearchWiki();
            String messageText = searchWiki.run(query);
            long chatId = update.getMessage().getChatId();

            if(messageText.length() > SIZE)
                messageText = Redactor.cut(messageText, SIZE);

            SendMessage message = new SendMessage()
                    .setChatId(chatId)
                    .setText(messageText);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "Sara Bot";
    }

    @Override
    public String getBotToken() {
        return "517527028:AAHIVfGwLHxDUPG2q_ymLf4zxfK6AAC9ANo";
    }

    public static void main(String[] args) {
        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
