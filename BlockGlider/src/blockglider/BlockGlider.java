package blockglider;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockGlider extends JavaPlugin {
    public static final ChatColor CHAT_HEADER = ChatColor.LIGHT_PURPLE;
    public static final ChatColor CHAT_NORMAL = ChatColor.AQUA;
    public static final ChatColor CHAT_ERROR  = ChatColor.RED;
    public static final ChatColor CHAT_HIGHLIGHT = ChatColor.YELLOW;
    
    private WorldEditPlugin we;
    
    public GlidePath path1;
    
    @Override
    public void onEnable() {
        we = (WorldEditPlugin) getServer().getPluginManager().getPlugin("WorldEdit");
        
        getServer().getPluginManager().registerEvents(new OnPlayerMove(this), this);
        getServer().getPluginManager().registerEvents(new OnBlockBreak(this), this);
        
        getCommand("glidepath").setExecutor(new GlidePathExecutor(this));
    }
    
    @Override
    public void onDisable() {
        
    }
    
    public WorldEditPlugin getWorldEdit() {
        return we;
    }
    
}
