package bot;

import com.google.inject.AbstractModule;

public class BotModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(IrcBot.class).to(PircBotXIrcBot.class);
  }
}
