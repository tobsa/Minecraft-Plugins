package toggleblocks;

import java.util.LinkedHashMap;
import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ToggleBlocks extends JavaPlugin implements Listener {
    private Map<Player, RegionManager> regions = new LinkedHashMap();
    
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }
    
    @Override
    public void onDisable() {
        
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return true;
        if(!command.getName().equalsIgnoreCase("toggleblocks"))
            return true;
        
        Player player = (Player)sender;
        
        if(args.length == 0) {
            onCommandHelp(player);
            return true;
        }
                               
        if(args[0].equalsIgnoreCase("create"))
            onCommandCreate(player, args);
        if(args[0].equalsIgnoreCase("edit"))
            onCommandEdit(player, args);
        if(args[0].equalsIgnoreCase("toggleon"))
            onCommandToggleOn(player, args);
        if(args[0].equalsIgnoreCase("toggleoff"))
            onCommandToggleOff(player, args);
        
        return true;
    }
    
    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event) {
        RegionManager regionManager = regions.get(event.getPlayer());
        if(regionManager == null || !regionManager.isEditMode())
            return;
        
        Region region = regionManager.getEditRegion();
        
        region.addBlock(new ToggleBlock(event.getBlock(), event.getBlock().getType()));
        event.getPlayer().sendMessage("Block added to '" + region.getName() + "'");
    }
    
    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        RegionManager regionManager = regions.get(event.getPlayer());
        if(regionManager == null || !regionManager.isEditMode())
            return;
        
        Region region = regionManager.getEditRegion();
        if(region.removeBlock(event.getBlock()))
            event.getPlayer().sendMessage("Block removed from '" + region.getName() + "'");
    }
    
    private void onCommandHelp(Player player) {
        player.sendMessage(ChatColor.DARK_AQUA + "----- ToggleBlocks -----");
        player.sendMessage("/tb help - Show a list of all available commands.");
        player.sendMessage("/tb create <name> - Create a new region.");
        player.sendMessage("/tb delete <name> - Delete an existing region.");
        player.sendMessage("/tb edit <name> - Toggle edit mode for a region.");
        player.sendMessage("/tb toggleon <name> - Toggle on all blocks in a region.");
        player.sendMessage("/tb toggleoff <name> - Toggle off all blocks in a region.");
        player.sendMessage("/tb list - List all existing regions.");
    }

    private void onCommandCreate(Player player, String[] args) {
        if(args.length != 2) {
            player.sendMessage(ChatColor.RED + "Invalid arguments! Usage: /tb create <name>");
            return;
        }
        
        RegionManager regionManager = regions.get(player);
        if(regionManager == null) {
            regionManager = new RegionManager();
            regions.put(player, regionManager);
        }
        
        if(regionManager.findRegion(args[1])) {
            player.sendMessage(ChatColor.RED + "Region '" + args[1] + "' already exists. Please choose a new name!");
            return;
        }
        
        regionManager.addRegion(args[1]);
        player.sendMessage(ChatColor.AQUA + "Region '" + args[1] + "' has been created!");
        
        Region region = regionManager.getRegion(args[1]);
        regionManager.setEditMode(region);
    }

    private void onCommandEdit(Player player, String[] args) {
        if(args.length != 2) {
            player.sendMessage(ChatColor.RED + "Invalid arguments! Usage: /tb edit <name>");
            return;
        }
        
        RegionManager regionManager = regions.get(player);
        Region region = regionManager.getRegion(args[1]);
        if(region == null) {
            player.sendMessage(ChatColor.RED + "Region '" + args[1] + "' doesn't exist!");
            return;
        }
        
        if(!region.equals(regionManager.getEditRegion())) {
            player.sendMessage(ChatColor.AQUA + "Stop edit region '" + regionManager.getEditRegion().getName() + "'!");
            regionManager.setEditMode(region);
            player.sendMessage(ChatColor.AQUA + "Start edit region '" + regionManager.getEditRegion().getName() + "'!");
            return;
        }
        
        if(regionManager.isEditMode()) {
            player.sendMessage(ChatColor.AQUA + "Stop edit region '" + args[1] + "'!");
            regionManager.setEditMode(null);
        }
        else {
            player.sendMessage(ChatColor.AQUA + "Start edit region '" + args[1] + "'!");
            regionManager.setEditMode(region);
        }
    }

    private void onCommandToggleOn(Player player, String[] args) {
        if(args.length != 2) {
            player.sendMessage(ChatColor.RED + "Invalid arguments! Usage: /tb toggleon <name>");
            return;
        }
        
        RegionManager regionManager = regions.get(player);
        Region region = regionManager.getRegion(args[1]);
        if(region == null) {
            player.sendMessage(ChatColor.RED + "Region '" + args[1] + "' doesn't exist!");
            return;
        }
        
        region.toggleOn();
    }

    private void onCommandToggleOff(Player player, String[] args) {
        if(args.length != 2) {
            player.sendMessage(ChatColor.RED + "Invalid arguments! Usage: /tb toggleoff <name>");
            return;
        }
        
        RegionManager regionManager = regions.get(player);
        Region region = regionManager.getRegion(args[1]);
        if(region == null) {
            player.sendMessage(ChatColor.RED + "Region '" + args[1] + "' doesn't exist!");
            return;
        }
        
        region.toggleOff();
    }
}
