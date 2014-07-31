package bounceblocks;

import org.bukkit.plugin.java.JavaPlugin;

public class BounceBlocks extends JavaPlugin {
    
    @Override
    public void onEnable() {
        BounceBlockManager bounceBlockManager = new BounceBlockManager(this);
        
        getServer().getPluginManager().registerEvents(new OnPlayerMoveBounceBlock(bounceBlockManager), this);
        getServer().getPluginManager().registerEvents(new OnPlayerBounceBlockBreak(bounceBlockManager), this);
        
        getCommand("bounceblock").setExecutor(new BounceBlockExecutor(bounceBlockManager));
    }
    
    @Override
    public void onDisable() {
        
    }
}
