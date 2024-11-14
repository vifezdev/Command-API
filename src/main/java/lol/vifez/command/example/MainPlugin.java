package lol.vifez.command.example;

import lol.vifez.command.api.CommandHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class MainPlugin extends JavaPlugin {

    private CommandHandler commandHandler;

    @Override
    public void onEnable() {
        commandHandler = new CommandHandler(this);
        registerCommands();
    }

    private void registerCommands() {
        commandHandler.registerCommand(new ExampleCommand());
    }
}

