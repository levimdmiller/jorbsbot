package listener.command;

import bot.events.ChatMessageEvent;
import db.command.CommandDao;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.TimerTask;
import javax.inject.Inject;
import listener.command.annotations.CommandQueue;
import listener.command.annotations.MessageQueue;
import listener.command.matching.CommandMatcher;

public class CommandDequeueTask extends TimerTask {

  private final Queue<ChatMessageEvent> commands;
  private final Queue<ChatMessageEvent> botMessages;
  private final CommandMatcher commandMatcher;
  private final CommandDao commandDao;

  @Inject
  public CommandDequeueTask(
      @CommandQueue Queue<ChatMessageEvent> commands,
      @MessageQueue Queue<ChatMessageEvent> botMessages,
      CommandMatcher commandMatcher,
      CommandDao commandDao) {
    this.commands = commands;
    this.botMessages = botMessages;
    this.commandMatcher = commandMatcher;
    this.commandDao = commandDao;
  }

  @Override
  public void run() {
    Instant cutoff = Instant.now();
    commandMatcher.pairMessages(
        emptyQueue(commands, cutoff),
        emptyQueue(botMessages, cutoff)
    ).forEach(commandDao::savePairings);

  }

  /**
   * Polls the queue until it is empty or an element after the given time is found.
   *
   * @param queue - queue to poll from
   * @param maxTime - cutoff time
   * @return - polled items.
   */
  private List<ChatMessageEvent> emptyQueue(Queue<ChatMessageEvent> queue, Instant maxTime) {
    List<ChatMessageEvent> items = new ArrayList<>();
    while (queue.peek() != null && maxTime.compareTo(queue.peek().getTimestamp()) >= 0) {
      items.add(queue.poll());
    }
    return items;
  }
}
