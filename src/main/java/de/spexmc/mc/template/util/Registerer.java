package de.spexmc.mc.template.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import de.spexmc.mc.template.Template;
import de.spexmc.mc.template.commands.TestCommand;
import de.spexmc.mc.template.listener.TestEvent;
import de.spexmc.mc.template.storage.Data;
import de.spexmc.mc.template.storage.Messages;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;

/**
 * Created by Lara on 26.02.2019 for template
 */
public final class Registerer {

  public static void performRegistration() {
    registerCommands();
    registerEvents();

    if (checkErrors()) {
      Messenger.administratorMessage(Messages.CONFIG_ERROR);
      Data.setForceDisable(true);
      Template.getInstance().onDisable();
    }
  }

  private static boolean checkErrors() {
    final Connection connection = Data.getInstance().getSql().getConnection();
    try {
      return connection == null || connection.isClosed();
    } catch (SQLException ignored) {
      return true;
    }
  }

  private static void registerEvents() {
    // Insert Events here
    final List<Listener> listeners = Arrays.asList(new TestEvent());
    for (Listener listener : listeners) {
      BungeeCord.getInstance().getPluginManager().registerListener(Template.getInstance(), listener);
    }
  }

  private static void registerCommands() {
    // Insert Commands here
    final List<Command> commands = Arrays.asList(new TestCommand("test"));
    for (Command commandExecutor : commands) {
      BungeeCord.getInstance().getPluginManager().registerCommand(Template.getInstance(), commandExecutor);
    }
  }

  /*private static void registerCommands() {
    final Reflections reflections = new Reflections("de.spexmc.mc.template.commands");
    for (Class<? extends CommandExecutor> commandClass : reflections.getSubTypesOf(CommandExecutor.class)) {
      BungeeCord.getInstance().getPluginManager().registerCommand(Template.getInstance(), commandExecutor);
    }
  }

  private static void registerEvents() {
    final Reflections reflections = new Reflections("de.spexmc.mc.template.listener");
    for (Class<? extends Listener> listenerClass : reflections.getSubTypesOf(Listener.class)) {
      BungeeCord.getInstance().getPluginManager().registerListener(Template.getInstance(), listener);
    }
  }*/
}
