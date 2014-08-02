package bounceblocks;

import org.bukkit.plugin.java.JavaPlugin;
import puzzlepack.CommandRegister;

public class BounceBlocks extends JavaPlugin {
    
    @Override
    public void onEnable() {
        BounceBlockManager bounceBlockManager = new BounceBlockManager(this);
        
        getServer().getPluginManager().registerEvents(new OnPlayerMoveBounceBlock(bounceBlockManager), this);
        getServer().getPluginManager().registerEvents(new OnPlayerBounceBlockBreak(bounceBlockManager), this);
                
        CommandRegister commandRegister = new CommandRegister();
        commandRegister.register(getCommand("bounceblock"),     new BounceBlockExecutor(bounceBlockManager));
        commandRegister.register(getCommand("bounceblockhelp"), new HelpExecutor(commandRegister.getCommands()));
    }
    
    @Override
    public void onDisable() {
        
    }
}
