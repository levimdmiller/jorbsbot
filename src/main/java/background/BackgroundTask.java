package background;

import java.util.concurrent.TimeUnit;
import util.StoppableRunnable;

public interface BackgroundTask extends StoppableRunnable {

  /**
   * @return - delay before starting task
   */
  long getDelay();

  /**
   * @return - period task should be run at
   */
  long getPeriod();

  /**
   * @return - unit of time getDelay and getPeriod are in.
   */
  TimeUnit getUnit();
}
