package background;

import bot.BotRunner;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import listener.command.CommandDequeueTask;

public class BackgroundTaskModule extends AbstractModule {

  @Override
  protected void configure() {
    Multibinder<BackgroundTask> taskBinder = Multibinder.newSetBinder(binder(), BackgroundTask.class);
    taskBinder.addBinding().to(BotRunner.class);
    taskBinder.addBinding().to(CommandDequeueTask.class);
  }
}
