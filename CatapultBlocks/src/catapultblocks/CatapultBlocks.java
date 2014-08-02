package catapultblocks;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class CatapultBlocks extends JavaPlugin {
    
    @Override
    public void onEnable() {
        FileManager.setPlugin(this);
        CatapultManager catapultManager = new CatapultManager();
        
        getServer().getPluginManager().registerEvents(new OnPlayerMove(catapultManager), this);
        getServer().getPluginManager().registerEvents(new OnBlockBreak(catapultManager), this);
        
        PluginCommand catapultblock = getCommand("catapultblock");
        PluginCommand catapultblockHelp = getCommand("catapultblockhelp");
        
        List<PluginCommand> commands = new ArrayList();
        commands.add(catapultblock);
        commands.add(catapultblockHelp);
        
        catapultblock.setExecutor(new CatapultBlockExecutor(catapultManager));
        catapultblockHelp.setExecutor(new HelpExecutor(commands));
    }
    
    @Override
    public void onDisable() {
        
    }    
}
