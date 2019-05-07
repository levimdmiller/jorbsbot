package listener;

import bot.events.ChatMessageEvent;
import bot.events.MessageListener;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import listener.command.CommandListener;
import listener.command.annotations.BotName;
import listener.command.annotations.CommandQueue;
import listener.command.annotations.MessageQueue;
import listener.command.matching.BipartideMatchingCommandMatcher;
import listener.command.matching.CommandMatcher;

public class ListenerModule extends AbstractModule {

  @Override
  protected void configure() {
    Multibinder<MessageListener> listenerBinder = Multibinder.newSetBinder(binder(), MessageListener.class);
    listenerBinder.addBinding().to(CommandListener.class);

    bindConstant().annotatedWith(BotName.class).to("adventskynet");
    bind(new TypeLiteral<Queue<ChatMessageEvent>>(){})
        .annotatedWith(CommandQueue.class)
        .to(new TypeLiteral<ConcurrentLinkedQueue<ChatMessageEvent>>(){});
    bind(new TypeLiteral<Queue<ChatMessageEvent>>(){})
        .annotatedWith(MessageQueue.class)
        .to(new TypeLiteral<ConcurrentLinkedQueue<ChatMessageEvent>>(){});

    bind(CommandMatcher.class).to(BipartideMatchingCommandMatcher.class);

  }
}
