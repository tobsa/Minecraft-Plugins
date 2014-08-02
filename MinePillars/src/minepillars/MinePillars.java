package minepillars;

import org.bukkit.plugin.java.JavaPlugin;
import puzzlepack.CommandRegister;

public class MinePillars extends JavaPlugin {

    @Override
    public void onEnable() {
        CommandRegister commandRegister = new CommandRegister();
        commandRegister.register(getCommand("minepillars"),     new MinePillarsExecutor());
        commandRegister.register(getCommand("minepillarshelp"), new HelpExecutor(commandRegister.getCommands()));
    }
}
