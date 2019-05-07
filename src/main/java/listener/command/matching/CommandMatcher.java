package listener.command.matching;

import bot.events.ChatMessageEvent;
import java.util.Collection;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public interface CommandMatcher {

  /**
   * Returns a pairing of the two lists of message events. Entries are not guaranteed to be returned
   * in the paring.
   *
   * @param commands - list of commands
   * @param messages - list of messages
   * @return - pairing
   */
  Collection<Pair<ChatMessageEvent, ChatMessageEvent>> pairMessages(
      List<ChatMessageEvent> commands,
      List<ChatMessageEvent> messages
  );
}
