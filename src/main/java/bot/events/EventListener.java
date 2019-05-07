package bot.events;

import bot.BotException;

public interface EventListener {
  void handle() throws BotException;
}
