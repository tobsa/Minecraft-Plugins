package restrictedarea;

import basepack.BasePack;
import com.sk89q.worldedit.BlockVector2D;
import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import com.sk89q.worldedit.bukkit.selections.Polygonal2DSelection;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SelectExecutor implements CommandExecutor {
    private AreaManager areaManager;
    private WorldEditPlugin worldEdit;
    
    public SelectExecutor(AreaManager areaManager, WorldEditPlugin worldEdit) {
        this.areaManager = areaManager;
        this.worldEdit = worldEdit;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return true;
        
        Player player = (Player)sender;
                
        if(args.length > 2 || args.length == 0) {
            player.sendMessage(Message.invalidArguments(command.getUsage()));
            return true;
        }
        
        Area area = areaManager.get(args[0], player.getName());
        if(area == null) {
            player.sendMessage(Message.missingArea(args[0]));
            return true;
        }
        
        if(args.length == 1) {
            Block minimumBlock = area.getMinimumBlock();
            Block maximumBlock = area.getMaximumBlock();

            worldEdit.setSelection(player, new CuboidSelection(player.getWorld(), minimumBlock.getLocation(), maximumBlock.getLocation()));
            return true;
        }
        
        Integer index = BasePack.getInteger(args[1]);
        if(index == null) {
            player.sendMessage(Message.invalidNumber(args[1]));
            return true;
        }
        
        if(index < 0 || index >= area.getSubAreas().size()) {
            player.sendMessage(Message.invalidIndex(args[1], area.getSubAreas().size()));
            return true;
        }
        
        SubArea subarea = area.getSubAreas().get(index);        
        worldEdit.setSelection(player, new Polygonal2DSelection(player.getWorld(), subarea.getPoints(), subarea.getMinimumY(), subarea.getMaximumY()));

        return true;
    }
}
