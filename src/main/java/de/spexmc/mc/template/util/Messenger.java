package de.spexmc.mc.template.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import de.spexmc.mc.template.storage.Messages;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

/**
 * Created by Lara on 20.07.2019 for template
 */
public final class Messenger {
  private static final Logger logger;

  static {
    logger = Logger.getLogger(Messenger.class.getName());
  }

  public static void administratorMessage(String msg) {

    for (ProxiedPlayer player : BungeeCord.getInstance().getPlayers()) {
      if (player.hasPermission("admin") || player.hasPermission("*")) {
        sendMessage(player, "&a&l" + msg);
      }
    }

    logger.log(Level.INFO, msg);
  }

  public static void sendMessage(ProxiedPlayer player, String msg) {
    player.sendMessage(new TextComponent(Messages.PREFIX + msg));
  }

  public static void broadcast(String msg) {
    BungeeCord.getInstance().broadcast(Messages.PREFIX + msg);
  }

}
