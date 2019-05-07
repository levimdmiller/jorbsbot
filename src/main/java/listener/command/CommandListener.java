package listener.command;

import bot.BotException;
import bot.events.ChatMessageEvent;
import bot.events.MessageListener;
import java.util.Queue;
import javax.inject.Inject;
import listener.command.annotations.BotName;
import listener.command.annotations.CommandQueue;
import listener.command.annotations.MessageQueue;

public class CommandListener implements MessageListener {

  private final String botName;
  private final Queue<ChatMessageEvent> commands;
  private final Queue<ChatMessageEvent> botMessages;

  @Inject
  public CommandListener(
      @BotName String botName,
      @CommandQueue Queue<ChatMessageEvent> commands,
      @MessageQueue Queue<ChatMessageEvent> botMessages) {
    this.botName = botName;
    this.commands = commands;
    this.botMessages = botMessages;
  }

  @Override
  public void handle(ChatMessageEvent messageEvent) throws BotException {
    if (botName.equalsIgnoreCase(messageEvent.getUser().getRealName())) {
      botMessages.offer(messageEvent);
    }
    if (messageEvent.getMessage().startsWith("!")) {
      commands.offer(messageEvent);
    }
  }
}
