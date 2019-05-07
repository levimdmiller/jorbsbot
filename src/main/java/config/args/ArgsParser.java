package config.args;

public interface ArgsParser {

  /**
   * Parses the given command line arguments.
   *
   * @param args - arguments to parse.
   */
  void parse(String[] args);
}
