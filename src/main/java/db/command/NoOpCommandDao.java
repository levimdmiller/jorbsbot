package db.command;

import bot.events.ChatMessageEvent;
import org.apache.commons.lang3.tuple.Pair;

public class NoOpCommandDao implements CommandDao {

  @Override
  public void savePairings(Pair<ChatMessageEvent, ChatMessageEvent> commandPair) {

  }
}
