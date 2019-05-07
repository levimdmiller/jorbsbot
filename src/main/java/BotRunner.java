import bot.BotException;
import bot.IrcBot;
import bot.events.MessageListener;
import config.Config;
import java.util.Set;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BotRunner implements Runnable {
  private static final Logger log = LoggerFactory.getLogger(BotRunner.class);
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

    registerShutdownHook();
    try {
      bot.connect();
    } catch (BotException e) {
      log.error("Error connecting bot.", e);
    }
  }

  private void registerShutdownHook() {
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      try {
        bot.disconnect();
      } catch (BotException e) {
        log.error("Error shutting down bot.", e);
      }
    }));
  }
}
