package restrictedarea;

import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RestrictedAreaExecutor implements CommandExecutor {
//    private AreaManager areaManager;
    private WorldEditPlugin worldEdit;
    
    public RestrictedAreaExecutor(/*AreaManager areaManager,*/ WorldEditPlugin worldEdit) {
//        this.areaManager = areaManager;
        this.worldEdit = worldEdit;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return true;
        
        Player player = (Player)sender;
        
        Selection selection = worldEdit.getSelection(player);
        if (selection == null) {
            player.sendMessage(PlayerMessage.missingSelection());
            return true;
        }
        
        if(args.length != 0) {
            player.sendMessage(PlayerMessage.invalidArguments(command.getUsage()));
            return true;
        }
                        
        try {
            SubArea area = new SubArea(selection.getRegionSelector().getRegion().polygonize(-1), selection.getMinimumPoint().getBlockY(), selection.getMaximumPoint().getBlockY());
            RestrictedArea.area = area;
        } catch (IncompleteRegionException ex) {
            Bukkit.getConsoleSender().sendMessage(ex.toString());
        }
        
        
                
//        areaManager.addArea(new Area(player.getName(), args[0], block1, block2, new GeneralResponse(AreaCollider.combineArguments(args, 2), sound)));
//        FileManager.save(areaManager);
//        player.sendMessage(PlayerMessage.areaCreated(args[0]));
        return true;
    }
}
