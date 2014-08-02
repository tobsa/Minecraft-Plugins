package redstonecombiner;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class RedstoneCombiner extends JavaPlugin {
    
    @Override
    public void onEnable() {
        FileManager.setPlugin(this);
        
        CombinerManager combinerManager = new CombinerManager();
        
        getServer().getPluginManager().registerEvents(new OnBlockRedstoneEvent(combinerManager), this);
        getServer().getPluginManager().registerEvents(new OnBlockBreakEvent(combinerManager), this);
        
        PluginCommand redcombiner       = getCommand("redcombiner");
        PluginCommand redcombinerlink   = getCommand("redcombinerlink");
        PluginCommand redcombinerdelink = getCommand("redcombinerdelink");
        PluginCommand redcombinerlist   = getCommand("redcombinerlist");
        PluginCommand redcombinerdelete = getCommand("redcombinerdelete");
        PluginCommand redcombinerrename = getCommand("redcombinerrename");
        PluginCommand redcombinerhelp   = getCommand("redcombinerhelp");
        
        List<PluginCommand> commands = new ArrayList();
        commands.add(redcombiner);
        commands.add(redcombinerlink);
        commands.add(redcombinerdelink);
        commands.add(redcombinerlist);
        commands.add(redcombinerdelete);
        commands.add(redcombinerrename);
        
        redcombiner.setExecutor(new RedstoneCombinerExecutor(combinerManager));
        redcombinerlink.setExecutor(new RedstoneCombinerLinkExecutor(combinerManager));
        redcombinerdelink.setExecutor(new RedstoneCombinerDelinkExecutor(combinerManager));
        redcombinerlist.setExecutor(new RedstoneCombinerListExecutor(combinerManager));
        redcombinerdelete.setExecutor(new RedstoneCombinerDeleteExecutor(combinerManager));
        redcombinerrename.setExecutor(new RedstoneCombinerRenameExecutor(combinerManager));
        redcombinerhelp.setExecutor(new HelpExecutor(commands));
    }
    
    @Override
    public void onDisable() {
        
    }
}
