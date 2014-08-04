package redstonecombiner;

import org.bukkit.plugin.java.JavaPlugin;
import puzzlepack.CommandRegister;

public class RedstoneCombiner extends JavaPlugin {
    
    @Override
    public void onEnable() {
        CombinerManager combinerManager = FileManager.load();

        getServer().getPluginManager().registerEvents(new OnBlockRedstoneEvent(combinerManager), this);
        getServer().getPluginManager().registerEvents(new OnBlockBreakEvent(combinerManager), this);
        
        CommandRegister commandRegister = new CommandRegister();
        commandRegister.register(getCommand("redcombiner"),       new RedstoneCombinerExecutor(combinerManager));
        commandRegister.register(getCommand("redcombinerlink"),   new RedstoneCombinerLinkExecutor(combinerManager));
        commandRegister.register(getCommand("redcombinerdelink"), new RedstoneCombinerDelinkExecutor(combinerManager));
        commandRegister.register(getCommand("redcombinerlist"),   new RedstoneCombinerListExecutor(combinerManager));
        commandRegister.register(getCommand("redcombinerdelete"), new RedstoneCombinerDeleteExecutor(combinerManager));
        commandRegister.register(getCommand("redcombinerrename"), new RedstoneCombinerRenameExecutor(combinerManager));
        commandRegister.register(getCommand("redcombinerhelp"),   new HelpExecutor(commandRegister.getCommands()));
    }
}
