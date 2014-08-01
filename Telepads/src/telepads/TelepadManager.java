package telepads;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class TelepadManager {
    private Map<String, Telepad> telepads = new LinkedHashMap();
    
    public TelepadManager() {
        telepads = FileManager.load();
    }
    
    public void createTelepad(String name, String playerName) {
        telepads.put(name, new Telepad(name, playerName));
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
        
        FileManager.remove(telepad);
    }       
    
    public void saveFrom(Telepad telepad) {
        FileManager.saveFrom(telepad);
    }
    
    public void saveTo(Telepad telepad) {   
        FileManager.saveTo(telepad);
    }
    
    public void removeFrom(Telepad telepad) {
        telepad.setFrom(null);
        FileManager.removeFrom(telepad);
    }
    
    public void removeTo(Telepad telepad) {
        telepad.setTo(null, 0.f, 0.f);
        FileManager.removeTo(telepad);
    }
}
