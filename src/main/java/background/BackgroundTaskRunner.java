package background;

import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Inject;
import util.StoppableRunnable;

public class BackgroundTaskRunner implements StoppableRunnable {

  private final Set<BackgroundTask> backgroundTasks;
  private final ScheduledExecutorService executorService;

  @Inject
  public BackgroundTaskRunner(
      Set<BackgroundTask> backgroundTasks,
      ScheduledExecutorService executorService) {
    this.backgroundTasks = backgroundTasks;
    this.executorService = executorService;
  }

  @Override
  public void run() {
    backgroundTasks.forEach(
        t -> executorService.scheduleAtFixedRate(t, t.getDelay(), t.getPeriod(), t.getUnit()));
  }

  @Override
  public void stop() {
    backgroundTasks.forEach(StoppableRunnable::stop);
    executorService.shutdown();
  }
}
