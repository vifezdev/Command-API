package lol.vifez.command.example;

import lol.vifez.command.api.CommandAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class ExampleCommand extends CommandAPI {

    public ExampleCommand() {
        super("example");
        setPermission("plugin.example");
        setPlayerOnly(true);
        addAliases("alias1", "alias2", "alias3");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        player.sendMessage(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "------------------------------");
        player.sendMessage(ChatColor.RED + "Vifez CommandAPI");
        player.sendMessage(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "------------------------------");
        player.sendMessage(ChatColor.WHITE + "Version: " + ChatColor.RED + "1.0");
        player.sendMessage(ChatColor.WHITE + "Author: " + ChatColor.RED + "vifez");
        player.sendMessage(ChatColor.WHITE + "Discord: " + ChatColor.RED + "https://discord.gg/stkWZEKVd5");
        player.sendMessage(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "------------------------------");

    }

    @Override
    public List<String> complete(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }
}