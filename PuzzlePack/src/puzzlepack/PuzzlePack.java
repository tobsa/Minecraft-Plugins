package puzzlepack;

import itemrestrict.OnBlockPlaceItemRestrict;
import org.bukkit.plugin.java.JavaPlugin;

public class PuzzlePack extends JavaPlugin {
    
    @Override
    public void onEnable() {                
        getServer().getPluginManager().registerEvents(new OnBlockPlaceItemRestrict(), this);
        getServer().getPluginManager().registerEvents(new OnFallDamageEvent(), this);
    }

    @Override
    public void onDisable() {
    }
}


/*
===== Block Usages =====

Gold:           Levers
Iron:           Ladders
Lapiz:
Emerald:
Diamond:
    
===== Wools =====
       
White:          Jumping 
Orange:         Teleport to
Magenta:        
LightBlue:
Yellow:         
Lime:
Pink:           
Grey:
LightGrey:
Cyan:
Purple:         Push block up/down with feather/bone
Blue:           Teleport from
Brown:          Teleport over block with blazerod 
Green:          Teleport under block with blazerod
Red:            Redstone wire 
Black:          Push block sideways with feather/bone

*/
