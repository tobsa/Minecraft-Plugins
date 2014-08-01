package catapultblocks;

import org.bukkit.plugin.java.JavaPlugin;

public class CatapultBlocks extends JavaPlugin {
    
    @Override
    public void onEnable() {
        FileManager.setPlugin(this);
        CatapultManager catapultManager = new CatapultManager();
        
        getServer().getPluginManager().registerEvents(new OnPlayerMove(catapultManager), this);
        getServer().getPluginManager().registerEvents(new OnBlockBreak(catapultManager), this);
        
        getCommand("catapultblock").setExecutor(new CatapultBlockExecutor(catapultManager));
    }
    
    @Override
    public void onDisable() {
        
    }    
}
