package toggleblocks;

import toggleblocks.executors.ToggleBlocksExecutor;
import toggleblocks.executors.RedstoneLinkExecutor;
import toggleblocks.executors.ToggleOnExecutor;
import toggleblocks.executors.ToggleOffExecutor;
import toggleblocks.executors.DeleteExecutor;
import toggleblocks.executors.IncludeExecutor;
import toggleblocks.executors.ExcludeExecutor;
import toggleblocks.executors.ListExecutor;
import toggleblocks.executors.LinkExecutor;
import toggleblocks.executors.DelinkExecutor;
import toggleblocks.executors.InfoExecutor;
import toggleblocks.executors.SelectExecutor;
import toggleblocks.executors.RenameExecutor;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import org.bukkit.plugin.java.JavaPlugin;
import puzzlepack.CommandRegister;
import puzzlepack.HelpExecutor;
import toggleblocks.group.GroupManager;
import toggleblocks.group.executors.GroupDeleteExecutor;
import toggleblocks.group.executors.GroupExcludeExecutor;
import toggleblocks.group.executors.GroupExecutor;
import toggleblocks.group.executors.GroupIncludeExecutor;
import toggleblocks.group.executors.GroupIndexExecutor;
import toggleblocks.group.executors.GroupListExecutor;
import toggleblocks.group.executors.GroupRenameExecutor;

public class ToggleBlocks extends JavaPlugin {    
        
    @Override
    public void onEnable() {
        WorldEditPlugin worldEdit = (WorldEditPlugin)getServer().getPluginManager().getPlugin("WorldEdit");
        
        RegionManager regionManager = FileManager.loadRegionManager();
        GroupManager groupManager = FileManager.loadGroupManager();
        
        getServer().getPluginManager().registerEvents(new OnBlockPlacedEvent(regionManager), this);
        getServer().getPluginManager().registerEvents(new OnBlockBreakEvent(regionManager), this);
        getServer().getPluginManager().registerEvents(new OnPlayerInteractEvent(regionManager), this);
        getServer().getPluginManager().registerEvents(new OnBlockRedstoneEvent(regionManager), this);
        
        CommandRegister commandRegister = new CommandRegister();
        commandRegister.register(getCommand("toggleblocks"),       new ToggleBlocksExecutor(regionManager));
        commandRegister.register(getCommand("toggleblockson"),     new ToggleOnExecutor(regionManager));
        commandRegister.register(getCommand("toggleblocksoff"),    new ToggleOffExecutor(regionManager));
        commandRegister.register(getCommand("toggleblockslink"),   new LinkExecutor(regionManager));
        commandRegister.register(getCommand("toggleblocksrlink"),  new RedstoneLinkExecutor(regionManager));
        commandRegister.register(getCommand("toggleblocksdelink"), new DelinkExecutor(regionManager));
        commandRegister.register(getCommand("toggleblockslist"),   new ListExecutor(regionManager));
        commandRegister.register(getCommand("toggleblocksdelete"), new DeleteExecutor(regionManager));
        commandRegister.register(getCommand("toggleblocksinfo"),   new InfoExecutor(regionManager));
        commandRegister.register(getCommand("toggleblocksrename"), new RenameExecutor(regionManager));
        commandRegister.register(getCommand("toggleblocksselect"), new SelectExecutor(regionManager, worldEdit));
        commandRegister.register(getCommand("toggleblocksinclude"), new IncludeExecutor(regionManager, worldEdit));
        commandRegister.register(getCommand("toggleblocksexclude"), new ExcludeExecutor(regionManager, worldEdit));
        
        commandRegister.register(getCommand("tbgroup"),        new GroupExecutor(groupManager));
        commandRegister.register(getCommand("tbgrouplist"),    new GroupListExecutor(groupManager));        
        commandRegister.register(getCommand("tbgroupdelete"),  new GroupDeleteExecutor(groupManager));
        commandRegister.register(getCommand("tbgrouprename"),  new GroupRenameExecutor(groupManager));
        commandRegister.register(getCommand("tbgroupinclude"), new GroupIncludeExecutor(regionManager, groupManager));  
        commandRegister.register(getCommand("tbgroupexclude"), new GroupExcludeExecutor(regionManager, groupManager));  
        commandRegister.register(getCommand("tbgroupindex"),   new GroupIndexExecutor(groupManager));
        
        commandRegister.register(getCommand("toggleblockshelp"),   new HelpExecutor(commandRegister.getCommands()));
    }
}

