package auth;

import com.google.inject.AbstractModule;

public class AuthModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(AuthProvider.class).to(TwitchOauthProvider.class);
  }
}
