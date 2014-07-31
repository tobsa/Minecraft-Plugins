package area;

import areacollider.PlayerMessage;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AreaSelectExecutor implements CommandExecutor {
    private AreaManager areaManager;
    private WorldEditPlugin worldEdit;
    
    public AreaSelectExecutor(AreaManager areaManager, WorldEditPlugin worldEdit) {
        this.areaManager = areaManager;
        this.worldEdit = worldEdit;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return true;
        
        Player player = (Player)sender;
                
        if(args.length != 1) {
            player.sendMessage(PlayerMessage.getInvalidArguments(command.getUsage()));
            return true;
        }
        
        Area area = areaManager.getArea(player.getName(), args[0]);
        if(area == null) {
            player.sendMessage(PlayerMessage.getMissingArea(args[0]));
            return true;
        }
        
        worldEdit.setSelection(player, new CuboidSelection(Bukkit.getWorld("world"), area.getMinimumBlock().getLocation(), area.getMaximumBlock().getLocation()));        
        return true;
    }
}
