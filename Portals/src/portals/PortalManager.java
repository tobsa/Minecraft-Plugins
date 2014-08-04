package portals;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PortalManager {
    private Map<String, Portal> portals = new LinkedHashMap();
        
    public void addPortal(Portal portal) {
        portals.put(portal.getName(), portal);
    }
    
    public void removePortal(Portal portal) {
        portals.remove(portal.getName());
    }
    
    public Portal getPortal(String playerName, String name) {
        Portal portal = portals.get(name);
        if(portal == null)
            return null;
        
        if(portal.getPlayerName().equalsIgnoreCase(playerName))
            return portal;
        
        return null;
    }
    
    public List<Portal> getPortals(String playerName) {
        List<Portal> playerPortals = new ArrayList();
        
        for(Portal portal : portals.values())
            if(portal.getPlayerName().equalsIgnoreCase(playerName))
                playerPortals.add(portal);
        
        return playerPortals;
    }
    
    public List<Portal> getPortals() {
        return new ArrayList(portals.values());
    }
}
