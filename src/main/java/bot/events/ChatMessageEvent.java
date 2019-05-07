package bot.events;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import model.Channel;
import model.User;

@Data
@Builder
@AllArgsConstructor
public class ChatMessageEvent {

  private final Channel channel;
  private final String channelSource;
  private final User user;
  private final String message;
  private final Instant timestamp;
}
