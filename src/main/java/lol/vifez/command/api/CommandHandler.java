package lol.vifez.command.api;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandHandler {

    private final Plugin plugin;
    private final CommandMap commandMap;

    public CommandHandler(Plugin plugin) {
        this.plugin = plugin;
        this.commandMap = getCommandMap();
    }

    public void registerCommand(CommandAPI command) {
        try {
            PluginCommand pluginCommand = createPluginCommand(command.getLabel());
            pluginCommand.setExecutor((sender, cmd, label, args) -> {
                if (command.isPlayerOnly() && !(sender instanceof Player)) {
                    sender.sendMessage(ChatColor.RED + "Console cannot execute this command.");
                    return true;
                }

                if (!command.hasPermission(sender)) {
                    return true;
                }

                command.execute(sender, args);
                return true;
            });

            pluginCommand.setTabCompleter((sender, cmd, label, args) -> {
                if (!command.hasPermission(sender)) {
                    return Collections.emptyList();
                }
                List<String> completions = new ArrayList<>(command.complete(sender, args));
                completions.removeIf(s -> !s.toLowerCase().startsWith(args[args.length - 1].toLowerCase()));
                return completions;
            });

            pluginCommand.setAliases(command.getAliases());
            pluginCommand.setPermission(command.getPermission());

            commandMap.register(plugin.getName(), pluginCommand);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private CommandMap getCommandMap() {
        try {
            Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            return (CommandMap) field.get(Bukkit.getServer());
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve command map", e);
        }
    }

    private PluginCommand createPluginCommand(String name) throws Exception {
        Constructor<PluginCommand> constructor = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
        constructor.setAccessible(true);
        return constructor.newInstance(name, plugin);
    }
}