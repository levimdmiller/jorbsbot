package db;

import com.google.inject.AbstractModule;
import db.command.CommandDao;
import db.command.NoOpCommandDao;

public class DbModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(CommandDao.class).to(NoOpCommandDao.class);
  }
}
