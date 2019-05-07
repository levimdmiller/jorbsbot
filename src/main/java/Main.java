import auth.AuthModule;
import background.BackgroundTaskRunner;
import bot.BotException;
import bot.BotModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import config.ConfigModule;
import java.net.MalformedURLException;
import listener.ListenerModule;

public class Main {

  public static void main(String[] args) throws MalformedURLException, BotException {
    Injector injector = Guice.createInjector(
        new ConfigModule(args),
        new BotModule(),
        new AuthModule(),
        new ListenerModule()
    );
    BackgroundTaskRunner taskRunner = injector.getInstance(BackgroundTaskRunner.class);
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      taskRunner.stop();
    }));
    taskRunner.run();
  }

}
