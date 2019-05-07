package bot.events;

import bot.BotException;

public interface MessageListener {

  void handle(ChatMessageEvent messageEvent) throws BotException;
}
