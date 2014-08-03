package areacollider;

import areacollider.general.GeneralExecutor;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import org.bukkit.plugin.java.JavaPlugin;
import areacollider.secret.SecretExecutor;
import areacollider.teleport.TeleportExecutor;
import areacollider.clearinventory.ClearInventoryExecutor;
import areacollider.message.MessageExecutor;
import areacollider.sound.SoundExecutor;
import org.bukkit.Sound;
import puzzlepack.CommandRegister;
import puzzlepack.HelpExecutor;

public class AreaCollider extends JavaPlugin { 
    
    @Override
    public void onEnable() {        
        AreaManager areaManager = FileManager.load();
        WorldEditPlugin worldEdit = (WorldEditPlugin)getServer().getPluginManager().getPlugin("WorldEdit");
        
        getServer().getPluginManager().registerEvents(new OnPlayerMove(areaManager), this);
        
        CommandRegister commandRegister = new CommandRegister();
        commandRegister.register(getCommand("areageneral"),         new GeneralExecutor(areaManager, worldEdit));
        commandRegister.register(getCommand("areasecret"),          new SecretExecutor(areaManager, worldEdit));
        commandRegister.register(getCommand("areateleport"),        new TeleportExecutor(areaManager, worldEdit));
        commandRegister.register(getCommand("areaclearinventory"),  new ClearInventoryExecutor(areaManager, worldEdit));
        commandRegister.register(getCommand("areamessage"),         new MessageExecutor(areaManager, worldEdit));
        commandRegister.register(getCommand("areasound"),           new SoundExecutor(areaManager, worldEdit));
        
        commandRegister.register(getCommand("arealist"),            new ListExecutor(areaManager));
        commandRegister.register(getCommand("areaselect"),          new SelectExecutor(areaManager, worldEdit));
        commandRegister.register(getCommand("areadelete"),          new DeleteExecutor(areaManager));
        
        commandRegister.register(getCommand("arearename"),          new RenameExecutor(areaManager));
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
