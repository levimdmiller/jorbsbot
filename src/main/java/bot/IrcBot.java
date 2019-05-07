package bot;

import bot.events.EventListener;
import bot.events.MessageListener;

public interface IrcBot {

  /**
   * Connects the bot to the given server.
   *
   * @throws BotException - if an exception occurs during the connection.
   */
  void connect() throws BotException;

  /**
   * Disconnect from the server.
   *
   * @throws BotException - if an exception happens while disconnecting.
   */
  void disconnect() throws BotException;

  /**
   * Adds an message event listener.
   *
   * @param listener - handles messages
   */
  void addMessageListener(MessageListener listener);
}
