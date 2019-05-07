package db.command;

import bot.events.ChatMessageEvent;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.tuple.Pair;

@Log4j
public class NoOpCommandDao implements CommandDao {

  @Override
  public void savePairings(Pair<ChatMessageEvent, ChatMessageEvent> commandPair) {
    log.info("Pair: " + commandPair.getLeft().getMessage() +
        commandPair.getRight().getMessage());
  }
}
