package toggleblocks;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SelectExecutor implements CommandExecutor {
    private RegionManager regionManager;
    private WorldEditPlugin worldEdit;
    
    public SelectExecutor(RegionManager regionManager, WorldEditPlugin worldEdit) {
        this.regionManager = regionManager;
        this.worldEdit = worldEdit;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        Player player = (Player) sender;

        if(args.length != 1) {
            player.sendMessage(PlayerMessage.invalidArguments(command.getUsage()));
            return true;
        }
         
        Region region = regionManager.getRegion(player.getName(), args[0]);
        if(region == null) {
            player.sendMessage(PlayerMessage.missingRegion(args[0]));
            return true;
        }
        
        int minimumX = Integer.MAX_VALUE;
        int minimumY = Integer.MAX_VALUE;
        int minimumZ = Integer.MAX_VALUE;
        
        int maximumX = Integer.MIN_VALUE;
        int maximumY = Integer.MIN_VALUE;
        int maximumZ = Integer.MIN_VALUE;
        
        for(ToggleBlock block : region.getToggleBlocks()) {           
            minimumX = Math.min(block.getBlock().getX(), minimumX);
            minimumY = Math.min(block.getBlock().getY(), minimumY);
            minimumZ = Math.min(block.getBlock().getZ(), minimumZ);
            
            maximumX = Math.max(block.getBlock().getX(), maximumX);
            maximumY = Math.max(block.getBlock().getY(), maximumY);
            maximumZ = Math.max(block.getBlock().getZ(), maximumZ);
        }
                
        Block minimumBlock = player.getWorld().getBlockAt(minimumX, minimumY, minimumZ);
        Block maximumBlock = player.getWorld().getBlockAt(maximumX, maximumY, maximumZ);        
        
        worldEdit.setSelection(player, new CuboidSelection(player.getWorld(), minimumBlock.getLocation(), maximumBlock.getLocation()));
        
        return true;
    }
    
}

