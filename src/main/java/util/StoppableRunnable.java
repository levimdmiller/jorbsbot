package util;

public interface StoppableRunnable extends Runnable {

  /**
   * Gracefully stops the runnable.
   */
  void stop();
}
