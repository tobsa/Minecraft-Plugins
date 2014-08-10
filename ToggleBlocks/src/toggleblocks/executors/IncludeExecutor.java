package toggleblocks.executors;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import com.sk89q.worldedit.bukkit.selections.Selection;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import toggleblocks.FileManager;
import toggleblocks.Message;
import toggleblocks.Region;
import toggleblocks.RegionManager;
import toggleblocks.ToggleBlock;

public class IncludeExecutor implements CommandExecutor {
    private RegionManager regionManager;
    private WorldEditPlugin worldEdit;
    
    public IncludeExecutor(RegionManager regionManager, WorldEditPlugin worldEdit) {
        this.regionManager = regionManager;
        this.worldEdit = worldEdit;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        Player player = (Player) sender;

        if(args.length != 1) {
            player.sendMessage(Message.invalidArguments(command.getUsage()));
            return true;
        }
         
        Region region = regionManager.get(args[0], player.getName());
        if(region == null) {
            player.sendMessage(Message.missingRegion(args[0]));
            return true;
        }
                  
        Selection selection = worldEdit.getSelection(player);
        if (selection == null) {
            player.sendMessage(Message.missingRegionSelection());
            return true;
        }
        
        int minimumX = selection.getMinimumPoint().getBlockX();
        int minimumY = selection.getMinimumPoint().getBlockY();
        int minimumZ = selection.getMinimumPoint().getBlockZ();
        
        int maximumX = selection.getMaximumPoint().getBlockX();
        int maximumY = selection.getMaximumPoint().getBlockY();
        int maximumZ = selection.getMaximumPoint().getBlockZ();
               
        int numBlocks = 0;
        int numOmittedBlocks = 0;
                
        for(int x = minimumX; x <= maximumX; x++) {
            for(int y = minimumY; y <= maximumY; y++) {
                for(int z = minimumZ; z <= maximumZ; z++) {                    
                    Block block = player.getWorld().getBlockAt(x, y, z);
                    
                    if(region.hasBlock(block)) {
                        numOmittedBlocks++;
                        continue;
                    }
                    
                    if(block.getType() != Material.AIR) {
                        region.addBlock(new ToggleBlock(block));
                        numBlocks++;
                    }
                }
            }
        }
        
        FileManager.save(regionManager);
        
        player.sendMessage(Message.includeBlocks(numBlocks, args[0]));
        
        if(numOmittedBlocks != 0)
            player.sendMessage(Message.omittedBlocks(numOmittedBlocks));
        
        return true;
    }
    
}

