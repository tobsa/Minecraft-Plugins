package bounceblocks;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class BounceBlocks extends JavaPlugin {
    
    @Override
    public void onEnable() {
        BounceBlockManager bounceBlockManager = new BounceBlockManager(this);
        
        getServer().getPluginManager().registerEvents(new OnPlayerMoveBounceBlock(bounceBlockManager), this);
        getServer().getPluginManager().registerEvents(new OnPlayerBounceBlockBreak(bounceBlockManager), this);
        
        PluginCommand bounceBlock = getCommand("bounceblock");
        PluginCommand bounceBlockHelp = getCommand("bounceblockhelp");
        
        List<PluginCommand> commands = new ArrayList();  
        commands.add(bounceBlock);
        commands.add(bounceBlockHelp);
        
        bounceBlock.setExecutor(new BounceBlockExecutor(bounceBlockManager));
        bounceBlockHelp.setExecutor(new HelpExecutor(commands));
    }
    
    @Override
    public void onDisable() {
        
    }
}
