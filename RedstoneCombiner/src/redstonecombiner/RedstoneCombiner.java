package redstonecombiner;

import org.bukkit.plugin.java.JavaPlugin;

public class RedstoneCombiner extends JavaPlugin {
    
    @Override
    public void onEnable() {
        FileManager.setPlugin(this);
        
        CombinerManager combinerManager = new CombinerManager();
        
        getServer().getPluginManager().registerEvents(new OnBlockRedstoneEvent(combinerManager), this);
        getServer().getPluginManager().registerEvents(new OnBlockBreakEvent(combinerManager), this);
        
        getCommand("redcombiner").setExecutor(new RedstoneCombinerExecutor(combinerManager));
        getCommand("redcombinerlink").setExecutor(new RedstoneCombinerLinkExecutor(combinerManager));
        getCommand("redcombinerdelink").setExecutor(new RedstoneCombinerDelinkExecutor(combinerManager));
        getCommand("redcombinerlist").setExecutor(new RedstoneCombinerListExecutor(combinerManager));
        getCommand("redcombinerdelete").setExecutor(new RedstoneCombinerDeleteExecutor(combinerManager));
        getCommand("redcombinerrename").setExecutor(new RedstoneCombinerRenameExecutor(combinerManager));
    }
    
    @Override
    public void onDisable() {
        
    }
}
