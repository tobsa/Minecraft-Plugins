package area.clearinventoryarea;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import area.Area;

public class ClearInventoryArea extends Area {
    private Map<Player, Boolean> players = new HashMap();
    
    public ClearInventoryArea(String playerName, String name, Block block1, Block block2) {
        super(playerName, name, block1, block2);
    }
    
    public ClearInventoryArea(String playerName, String name, String message, Block block1, Block block2) {
        super(playerName, name, message, block1, block2);
    }
    
    @Override
    public boolean isInside(Player player, Block block) {        
        if(intersects(block)) {
            Boolean value = players.get(player);
            if(value == null) {
                players.put(player, true);
                return true;
            }
            else {
                if(!value.booleanValue()) {
                    players.put(player, true);
                    return true;
                }
            }
                
            return false;
        }
        else {
            players.put(player, false);
            return false;
        }
    }
    
}
