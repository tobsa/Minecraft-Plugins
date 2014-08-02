package catapultblocks;

import org.bukkit.plugin.java.JavaPlugin;
import puzzlepack.CommandRegister;

public class CatapultBlocks extends JavaPlugin {
    
    @Override
    public void onEnable() {
        FileManager.setPlugin(this);
        CatapultManager catapultManager = new CatapultManager(FileManager.load());
        
        getServer().getPluginManager().registerEvents(new OnPlayerMove(catapultManager), this);
        getServer().getPluginManager().registerEvents(new OnBlockBreak(catapultManager), this);
        
        CommandRegister commandRegister = new CommandRegister();
        commandRegister.register(getCommand("catapultblock"),     new CatapultBlockExecutor(catapultManager));
        commandRegister.register(getCommand("catapultblockhelp"), new HelpExecutor(commandRegister.getCommands()));
    }  
}
