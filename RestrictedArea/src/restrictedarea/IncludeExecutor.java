package restrictedarea;

import com.sk89q.worldedit.BlockVector2D;
import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class IncludeExecutor implements CommandExecutor {
    private AreaManager areaManager;
    private WorldEditPlugin worldEdit;
    
    public IncludeExecutor(AreaManager areaManager, WorldEditPlugin worldEdit) {
        this.areaManager = areaManager;
        this.worldEdit = worldEdit;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return true;
        
        Player player = (Player)sender;
        
        Selection selection = worldEdit.getSelection(player);
        if (selection == null) {
            player.sendMessage(Message.missingSelection());
            return true;
        }
        
        if(args.length != 1) {
            player.sendMessage(Message.invalidArguments(command.getUsage()));
            return true;
        }
                        
        Area area = areaManager.get(args[0], player.getName());
        if(area == null) {
            player.sendMessage(Message.missingArea(args[0]));
            return true;
        }
        
        try {
            List<BlockVector2D> points = selection.getRegionSelector().getRegion().polygonize(-1);
            int minimumY = selection.getMinimumPoint().getBlockY();
            int maximumY = selection.getMaximumPoint().getBlockY();
            int numBlocks = selection.getRegionSelector().getRegion().getArea();
            
            area.addSubArea(new SubArea(points, minimumY, maximumY));            
            FileManager.save(areaManager);
            player.sendMessage(Message.subareaCreated(args[0], numBlocks));
        } catch (IncompleteRegionException ex) {
            player.sendMessage(ex.toString());
            Bukkit.getConsoleSender().sendMessage(ex.toString());
        }

        return true;
    }
}
