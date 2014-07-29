package toggleblocks;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class ToggleBlocks extends JavaPlugin {
    public static final ChatColor CHAT_HEADER = ChatColor.LIGHT_PURPLE;
    public static final ChatColor CHAT_NORMAL = ChatColor.AQUA;
    public static final ChatColor CHAT_ERROR  = ChatColor.RED;
    public static final ChatColor CHAT_HIGHLIGHT = ChatColor.YELLOW;
    
    private RegionManager regionManager;
    
    @Override
    public void onEnable() {
        regionManager = new RegionManager(this);
        
        getServer().getPluginManager().registerEvents(new OnBlockPlace(this), this);
        getServer().getPluginManager().registerEvents(new OnBlockBreak(this), this);
        getServer().getPluginManager().registerEvents(new OnPlayerInteract(this), this);
        
        getCommand("toggleblocks").setExecutor(new ToggleBlocksExecutor(this));
        getCommand("toggleblockson").setExecutor(new ToggleOnExecutor(this));
        getCommand("toggleblocksoff").setExecutor(new ToggleOffExecutor(this));
        getCommand("toggleblocksedit").setExecutor(new EditExecutor(this));
        getCommand("toggleblockslink").setExecutor(new LinkExecutor(this));
        getCommand("toggleblocksdelink").setExecutor(new DelinkExecutor(this));
        getCommand("toggleblockslist").setExecutor(new ListExecutor(this));
        getCommand("toggleblocksdelete").setExecutor(new ToggleBlocksDeleteExecutor(this));
    }
    
    @Override
    public void onDisable() {
        
    }
    
    public RegionManager getRegionManager() {
        return regionManager;
    }
}
