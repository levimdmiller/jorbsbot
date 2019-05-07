package auth;

public class TwitchOauthProvider implements AuthProvider {

  @Override
  public String getAuth() {
    return "oauth:<token>";
  }
}
