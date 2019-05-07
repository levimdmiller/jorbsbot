package config;

/**
 * https://dev.twitch.tv/docs/irc/guide/
 */
public class HardCodedConfig implements Config {

  @Override
  public String getName() {
    return "qbbl";
  }

  @Override
  public String getLogin() {
    return "qbbl";
  }

  @Override
  public String getChannel() {
    return "#jorbs";
  }

  @Override
  public String getServer() {
    return "irc.chat.twitch.tv";
  }

  @Override
  public int getPort() {
    return 6697;
  }
}
