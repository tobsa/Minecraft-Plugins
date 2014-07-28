package worldeditplus;

import org.bukkit.plugin.java.JavaPlugin;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;

public class WorldEditPlus extends JavaPlugin {
    private WorldEditPlugin we;
    
    
    @Override
    public void onEnable() {
        we = (WorldEditPlugin) getServer().getPluginManager().getPlugin("WorldEdit");

        getCommand("floor").setExecutor(new CommandFloorExecutor(this));
        getCommand("roof").setExecutor(new CommandRoofExecutor(this));
        getCommand("corner").setExecutor(new CommandCornerExecutor(this));
    }

    @Override
    public void onDisable() {

    }

    WorldEditPlugin getWorldEdit() {
        return we;
    }
    
    
}
