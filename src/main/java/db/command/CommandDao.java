package db.command;

import bot.events.ChatMessageEvent;
import org.apache.commons.lang3.tuple.Pair;

public interface CommandDao {

  /**
   * Saves the command pair
   *
   * @param commandPair - left=command, right=message
   */
  void savePairings(Pair<ChatMessageEvent, ChatMessageEvent> commandPair);
}
