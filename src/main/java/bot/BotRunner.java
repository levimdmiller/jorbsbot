package bot;

import bot.events.MessageListener;
import config.Config;
import java.util.Set;
import javax.inject.Inject;
import lombok.extern.log4j.Log4j;
import util.StoppableRunnable;

@Log4j
public class BotRunner implements StoppableRunnable {

  private final IrcBot bot;
  private final Config config;
  private final Set<MessageListener> listeners;

  @Inject
  public BotRunner(IrcBot bot, Config config, Set<MessageListener> listeners) {
    this.bot = bot;
    this.config = config;
    this.listeners = listeners;
  }

  @Override
  public void run() {
    listeners.forEach(bot::addMessageListener);

    try {
      bot.connect();
    } catch (BotException e) {
      log.error("Error connecting bot.", e);
    }
  }

  @Override
  public void stop() {
    try {
      bot.disconnect();
    } catch (BotException e) {
      log.error("Error shutting down bot.", e);
    }
  }
}
