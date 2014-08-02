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
import org.bukkit.Sound;
import puzzlepack.CommandRegister;

public class AreaCollider extends JavaPlugin { 
    
    @Override
    public void onEnable() {
        FileManager.setConfig(this);
        
        AreaManager areaManager = new AreaManager();
        WorldEditPlugin worldEdit = (WorldEditPlugin)getServer().getPluginManager().getPlugin("WorldEdit");
        
        getServer().getPluginManager().registerEvents(new OnPlayerMove(areaManager), this);
        
        CommandRegister commandRegister = new CommandRegister();
        commandRegister.register(getCommand("area"),                new AreaExecutor(areaManager, worldEdit));
        commandRegister.register(getCommand("areasecret"),          new AreaSecretExecutor(areaManager, worldEdit));
        commandRegister.register(getCommand("areateleport"),        new AreaTeleportExecutor(areaManager, worldEdit));
        commandRegister.register(getCommand("arealist"),            new AreaListExecutor(areaManager));
        commandRegister.register(getCommand("areaselect"),          new AreaSelectExecutor(areaManager, worldEdit));
        commandRegister.register(getCommand("areadelete"),          new AreaDeleteExecutor(areaManager));
        commandRegister.register(getCommand("areaclearinventory"),  new AreaClearInventoryExecutor(areaManager, worldEdit));
        commandRegister.register(getCommand("arearename"),          new AreaRenameExecutor(areaManager));
        commandRegister.register(getCommand("areahelp"),            new HelpExecutor(commandRegister.getCommands()));
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
