package areacollider;

import area.AreaDeleteExecutor;
import area.AreaManager;
import area.AreaListExecutor;
import area.AreaExecutor;
import area.AreaSelectExecutor;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import org.bukkit.plugin.java.JavaPlugin;
import areasecret.AreaSecretExecutor;
import areateleport.AreaTeleportExecutor;
import clearinventory.AreaClearInventoryExecutor;
import org.bukkit.Sound;

public class AreaCollider extends JavaPlugin { 
    
    @Override
    public void onEnable() {
        FileManager.setConfig(this);
        
        AreaManager areaManager = new AreaManager();
        WorldEditPlugin worldEdit = (WorldEditPlugin)getServer().getPluginManager().getPlugin("WorldEdit");
        
        getServer().getPluginManager().registerEvents(new OnPlayerMove(areaManager), this);
                
        getCommand("area").setExecutor(new AreaExecutor(areaManager, worldEdit));
        getCommand("areasecret").setExecutor(new AreaSecretExecutor(areaManager, worldEdit));
        getCommand("areateleport").setExecutor(new AreaTeleportExecutor(areaManager, worldEdit));
        getCommand("arealist").setExecutor(new AreaListExecutor(areaManager));
        getCommand("areaselect").setExecutor(new AreaSelectExecutor(areaManager, worldEdit));
        getCommand("areadelete").setExecutor(new AreaDeleteExecutor(areaManager));
        getCommand("areaclearinventory").setExecutor(new AreaClearInventoryExecutor(areaManager, worldEdit));
    }
    
    @Override
    public void onDisable() {
        
    }
    
    public static String combineArguments(String[] args, int arg) {
        String argument = "";
        for(int i = arg; i < args.length; i++)
            argument += args[i] + " ";
        
        return argument;        
    }
    
    public static Sound getSound(String arg) {
        Sound sound; 
        try {
            sound = Sound.valueOf(arg.toUpperCase());
        } catch(IllegalArgumentException ex) {
            return null;
        }
        
        return sound;
    }
}
