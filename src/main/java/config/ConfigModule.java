package config;

import com.google.inject.AbstractModule;

public class ConfigModule extends AbstractModule {

  private final String[] args;

  public ConfigModule(String[] args) {
    this.args = args;
  }

  @Override
  protected void configure() {
    bind(Config.class).to(HardCodedConfig.class);
  }
}
