import auth.AuthModule;
import background.BackgroundTaskModule;
import background.BackgroundTaskRunner;
import bot.BotModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import config.ConfigModule;
import db.DbModule;
import listener.ListenerModule;

public class Main {

  public static void main(String[] args) {
    Injector injector = Guice.createInjector(
        new ConfigModule(args),
        new BotModule(),
        new AuthModule(),
        new ListenerModule(),
        new BackgroundTaskModule(),
        new DbModule()
    );
    BackgroundTaskRunner taskRunner = injector.getInstance(BackgroundTaskRunner.class);
    Runtime.getRuntime().addShutdownHook(new Thread(taskRunner::stop));
    taskRunner.run();
  }

}
