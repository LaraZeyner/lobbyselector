package de.spexmc.mc.template.util.mcutils;

import java.net.InetSocketAddress;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.score.Objective;
import net.md_5.bungee.api.score.Scoreboard;
import net.md_5.bungee.protocol.packet.ScoreboardDisplay;
import net.md_5.bungee.protocol.packet.ScoreboardObjective;
import net.md_5.bungee.protocol.packet.ScoreboardScore;

/**
 * Created by Lara on 28.05.2019 for template
 */
public class PlayerScoreboard {
  private final ProxiedPlayer player;

  public PlayerScoreboard(ProxiedPlayer player) {
    this.player = player;
  }

  public void update() {
    if (isEmpty()) {
      createScoreboard();
    } else {
      updateScoreboard();
    }
  }

  public void show() {
    update();
  }

  public void hide() {
    final Scoreboard playerScoreboard = player.getScoreboard();
    playerScoreboard.removeObjective("objective");
  }

  private void updateScoreboard() {
    hide();
    evaluateScoreboardContent();
  }

  private void evaluateScoreboardContent() {
    final InetSocketAddress socketAddress = player.getServer().getAddress();
    addScoreboardInfos("§7----------------",
        "§7Name:",
        "§7 -> §b" + player.getDisplayName(),
        "  ",
        "§7IP:",
        "§7 -> §6" +
            (socketAddress.getHostName().equals("") ? "127.0.0.1" : socketAddress.getHostName()) + ":" +
            socketAddress.getPort(),
        "   ",
        "§7Welt:",
        "§7 -> §6" + player.getServer().getInfo().getName());
  }

  private void addScoreboardInfos(String... infos) {
    for (int i = infos.length; i > 0; i--) {
      final String info = infos[i - 1];
      final ScoreboardScore scoreboardScore = new ScoreboardScore
          (info, (byte) 0, "objective", infos.length - i + 1);
      player.unsafe().sendPacket(scoreboardScore);
    }
  }

  private void createScoreboard() {
    final ScoreboardObjective objective = new ScoreboardObjective("objective", "Test",
        ScoreboardObjective.HealthDisplay.INTEGER, (byte) 0);
    player.unsafe().sendPacket(objective);

    final ScoreboardDisplay display = new ScoreboardDisplay();
    display.setPosition((byte) 1);
    display.setName("§5     Template     ");
    player.unsafe().sendPacket(objective);

    evaluateScoreboardContent();
  }

  private boolean isEmpty() {
    return getObjective() == null;
  }

  private Objective getObjective() {
    return player.getScoreboard().getObjective("objective");
  }
}