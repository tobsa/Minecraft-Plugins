package areacollider;

import area.AreaDeleteExecutor;
import area.AreaManager;
import area.AreaListExecutor;
import area.AreaExecutor;
import area.AreaRenameExecutor;
import area.AreaSelectExecutor;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import org.bukkit.plugin.java.JavaPlugin;
import areasecret.AreaSecretExecutor;
import areateleport.AreaTeleportExecutor;
import clearinventory.AreaClearInventoryExecutor;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Sound;
import org.bukkit.command.PluginCommand;

public class AreaCollider extends JavaPlugin { 
    
    @Override
    public void onEnable() {
        FileManager.setConfig(this);
        
        AreaManager areaManager = new AreaManager();
        WorldEditPlugin worldEdit = (WorldEditPlugin)getServer().getPluginManager().getPlugin("WorldEdit");
        
        getServer().getPluginManager().registerEvents(new OnPlayerMove(areaManager), this);
        
        PluginCommand area                  = getCommand("area");
        PluginCommand areasecret            = getCommand("areasecret");
        PluginCommand areateleport          = getCommand("areateleport");
        PluginCommand arealist              = getCommand("arealist");
        PluginCommand areaselect            = getCommand("areaselect");
        PluginCommand areadelete            = getCommand("areadelete");
        PluginCommand areaclearinventory    = getCommand("areaclearinventory");
        PluginCommand arearename            = getCommand("arearename");
        PluginCommand areahelp              = getCommand("areahelp");
                
        List<PluginCommand> commands = new ArrayList();  
        commands.add(area);
        commands.add(areasecret);
        commands.add(areateleport);
        commands.add(arealist);
        commands.add(areaselect);
        commands.add(areadelete);
        commands.add(areaclearinventory);
        commands.add(arearename);
        commands.add(areahelp);
        
        area.setExecutor(new AreaExecutor(areaManager, worldEdit));
        areasecret.setExecutor(new AreaSecretExecutor(areaManager, worldEdit));
        areateleport.setExecutor(new AreaTeleportExecutor(areaManager, worldEdit));
        arealist.setExecutor(new AreaListExecutor(areaManager));
        areaselect.setExecutor(new AreaSelectExecutor(areaManager, worldEdit));
        areadelete.setExecutor(new AreaDeleteExecutor(areaManager));
        areaclearinventory.setExecutor(new AreaClearInventoryExecutor(areaManager, worldEdit));
        arearename.setExecutor(new AreaRenameExecutor(areaManager));
        areahelp.setExecutor(new HelpExecutor(commands));
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
