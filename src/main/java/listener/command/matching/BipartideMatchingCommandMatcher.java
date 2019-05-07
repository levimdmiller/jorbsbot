package listener.command.matching;

import bot.events.ChatMessageEvent;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public class BipartideMatchingCommandMatcher implements CommandMatcher {

  @Override
  public List<Pair<ChatMessageEvent, ChatMessageEvent>> pairMessages(
      List<ChatMessageEvent> commands, List<ChatMessageEvent> messages) {
    return Collections.emptyList();
  }
}
