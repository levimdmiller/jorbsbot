import auth.AuthModule;
import bot.BotException;
import bot.BotModule;
import com.google.inject.Guice;
import config.ConfigModule;
import listener.ListenerModule;
import java.net.MalformedURLException;

public class Main {

  public static void main(String[] args) throws MalformedURLException, BotException {
    Guice.createInjector(
        new ConfigModule(args),
        new BotModule(),
        new AuthModule(),
        new ListenerModule()
    ).getInstance(BotRunner.class).run();
  }
}
