package telepads;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TelepadManager {
    private Map<String, Telepad> telepads = new LinkedHashMap();
        
    public void addTelepad(Telepad telepad) {
        telepads.put(telepad.getName(), telepad);
    }
    
    public boolean isAvailable(String name) {
        return !telepads.containsKey(name);
    }
    
    public Telepad getTelepad(String playerName, String name) {
        Telepad telepad = telepads.get(name);
        if(telepad == null)
            return null;
        if(telepad.getPlayerName().equals(playerName))
            return telepad;
        
        return null;        
    }
    
    public List<Telepad> getTelepads() {
        return new ArrayList(telepads.values());
    }
    
    public void removeTelepad(Telepad telepad) {
        telepads.remove(telepad.getName());
    }
}
