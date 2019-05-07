package listener.command.matching;

import bot.events.ChatMessageEvent;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.jgrapht.Graph;
import org.jgrapht.alg.matching.MaximumWeightBipartiteMatching;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.builder.GraphTypeBuilder;

@Log4j
public class BipartiteMatchingCommandMatcher implements CommandMatcher {

  @Override
  public Collection<Pair<ChatMessageEvent, ChatMessageEvent>> pairMessages(
      List<ChatMessageEvent> commands,
      List<ChatMessageEvent> messages) {

    Graph<ChatMessageEvent, DefaultWeightedEdge> graph = generateGraph(commands, messages);
    MaximumWeightBipartiteMatching<ChatMessageEvent, DefaultWeightedEdge> matchingAlgorithm =
        new MaximumWeightBipartiteMatching<>(graph, new HashSet<>(commands), new HashSet<>(messages));

    log.info("Command/Message size: " + commands.size() + "/" + messages.size());
    log.info("Matching weight: " + matchingAlgorithm.getMatchingWeight());

    return matchingAlgorithm.getMatching().getEdges().stream()
        .map(e -> new ImmutablePair<>(graph.getEdgeSource(e), graph.getEdgeTarget(e)))
        .collect(Collectors.toList());
  }

  /**
   * Generates a complete bipartite graph, where commands and messages are the two sets of
   * vertices.
   *
   * The weight of the edges is the absolute value of the difference between the two
   * ChatMessageEvent Timestamps
   *
   * @param commands - list of command events
   * @param messages - list of message events
   * @return - complete bipartite graph.
   */
  private Graph<ChatMessageEvent, DefaultWeightedEdge> generateGraph(
      List<ChatMessageEvent> commands,
      List<ChatMessageEvent> messages) {
    Graph<ChatMessageEvent, DefaultWeightedEdge> graph = GraphTypeBuilder.undirected()
        .allowingMultipleEdges(false)
        .allowingSelfLoops(false)
        .edgeClass(DefaultWeightedEdge.class)
        .vertexClass(ChatMessageEvent.class)
        .weighted(true)
        .buildGraph();

    commands.forEach(graph::addVertex);
    messages.forEach(graph::addVertex);

    // Add edge to all vertices.
    for (ChatMessageEvent command : commands) {
      for (ChatMessageEvent message : messages) {
        long distance = command.getTimestamp().toEpochMilli()
            - message.getTimestamp().toEpochMilli();
        DefaultWeightedEdge e = graph.addEdge(command, message);
        graph.setEdgeWeight(e, -Math.abs(distance));
      }
    }
    return graph;
  }
}
