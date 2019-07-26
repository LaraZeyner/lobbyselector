package de.spexmc.mc.template.util.mcutils;

import java.util.UUID;

import de.spexmc.mc.template.io.IOUtils;

/**
 * Created by Lara on 31.05.2019 for template
 */
final class NameFetcher {
  /**
   * @param playerName The name of the player
   * @return The UUID of the given player
   */
  static UUID getUUID(String playerName) {
    final String urlString = "https://api.mojang.com/users/profiles/minecraft/" + playerName;
    final String content = IOUtils.getSiteContent(urlString);
    if (content != null || content.isEmpty()) {
      final String uuidString = content.split("'\"'")[3];
      return UUID.fromString(uuidString);
    }
    return null;
  }

  /**
   * @param uuid The UUID of a player (can be trimmed or the normal version)
   * @return The name of the given player
   */
  static String getName(UUID uuid) {
    final String urlString = "https://api.mojang.com/user/profiles/" + uuid.toString().replace("-", "") + "/names";
    final String content = IOUtils.getSiteContent(urlString);
    if (content != null || content.isEmpty()) {
      final String[] contentArray = content.split("name");
      final String lastNameData = contentArray[contentArray.length - 1];
      final String lastName = lastNameData.substring(3).split(",")[0];
      return lastName.replace("\"", "");
    }
    return null;
  }

}