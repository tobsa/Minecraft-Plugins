package puzzlepack;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;

public class CommandRegister {
    private List<PluginCommand> commands = new ArrayList();
    
    public void register(PluginCommand command, CommandExecutor commandExecutor) {
        command.setExecutor(commandExecutor);
        commands.add(command);
    }
    
    public List<PluginCommand> getCommands() {
        return commands;
    }
}
