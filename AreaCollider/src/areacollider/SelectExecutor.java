package areacollider;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import org.bukkit.Bukkit;
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
                
        if(args.length != 1) {
            player.sendMessage(Message.invalidArguments(command.getUsage()));
            return true;
        }
        
        Area area = areaManager.getArea(player.getName(), args[0]);
        if(area == null) {
            player.sendMessage(Message.missingArea(args[0]));
            return true;
        }
        
        worldEdit.setSelection(player, new CuboidSelection(Bukkit.getWorld("world"), area.getMinimumBlock().getLocation(), area.getMaximumBlock().getLocation()));        
        return true;
    }
}
