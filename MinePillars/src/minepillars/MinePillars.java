package minepillars;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class MinePillars extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginCommand minepillars = getCommand("minepillars");
        PluginCommand minepillarshelp = getCommand("minepillarshelp");
        
        List<PluginCommand> commands = new ArrayList();  
        commands.add(minepillars);
        commands.add(minepillarshelp);
        
        minepillars.setExecutor(new MinePillarsExecutor());
        minepillarshelp.setExecutor(new HelpExecutor(commands));
    }
}
