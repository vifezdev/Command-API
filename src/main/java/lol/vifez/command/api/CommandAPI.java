package lol.vifez.command.api;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class CommandAPI {

    private final String label;
    private String permission;
    private boolean playerOnly;
    private final List<String> aliases = new ArrayList<>();

    public CommandAPI(String label) {
        this.label = label;
    }

    public abstract void execute(CommandSender sender, String[] args);

    public List<String> complete(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }

    public List<String> getPlayerNames() {
        List<String> names = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            names.add(player.getName());
        }
        return names;
    }

    public String getLabel() {
        return label;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public boolean isPlayerOnly() {
        return playerOnly;
    }

    public void setPlayerOnly(boolean playerOnly) {
        this.playerOnly = playerOnly;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public void addAliases(String... aliases) {
        Collections.addAll(this.aliases, aliases);
    }

    public boolean hasPermission(CommandSender sender) {
        if (permission != null && !sender.hasPermission(permission)) {
            sender.sendMessage(ChatColor.RED + "No permission.");
            return false;
        }
        return true;
    }
}

