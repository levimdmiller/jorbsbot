package bot;

import auth.AuthProvider;
import bot.events.ChatMessageEvent;
import bot.events.MessageListener;
import config.Config;
import java.io.IOException;
import java.time.Instant;
import javax.inject.Inject;
import model.Channel;
import model.User;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.UtilSSLSocketFactory;
import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

public class PircBotXIrcBot implements IrcBot {

  private PircBotX bot;

  @Inject
  public PircBotXIrcBot(Config config, AuthProvider authProvider) {
    this.bot = new PircBotX(
        new Configuration.Builder()
            .addServer(config.getServer(), config.getPort())
            .setName(config.getName())
            .setLogin(config.getLogin())
            .addAutoJoinChannel(config.getChannel())
            .setSocketFactory(new UtilSSLSocketFactory().disableDiffieHellman())
            .setAutoNickChange(true)
            .setAutoReconnect(true)
            .setServerPassword(authProvider.getAuth())
            .buildConfiguration());
  }

  @Override
  public void connect() throws BotException {
    try {
      bot.startBot();
    } catch (IOException | IrcException e) {
      throw new BotException("Error starting bot.", e);
    }
  }

  @Override
  public void disconnect() throws BotException {
    bot.stopBotReconnect();
    bot.sendIRC().quitServer();
  }

  @Override
  public void addMessageListener(MessageListener listener) {
    bot.getConfiguration().getListenerManager().addListener(new ListenerAdapter() {
      @Override
      public void onMessage(MessageEvent event) throws Exception {
        listener.handle(ChatMessageEvent.builder()
            .channel(Channel.builder()
                .channelId(event.getChannel().getChannelId())
                .name(event.getChannel().getName())
                .build())
            .user(User.builder()
                .userId(event.getUser().getUserId())
                .realName(event.getUser().getRealName())
                .server(event.getUser().getServer())
                .build())
            .message(event.getMessage())
            .timestamp(Instant.now())
            .build());
      }
    });
  }
}
