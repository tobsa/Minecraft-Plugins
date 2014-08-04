package portals;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SerializedPortalManager implements Serializable {
    private List<SerializedPortal> serializedPortals = new ArrayList();
    
    public SerializedPortalManager(PortalManager portalManager) {
        for(Portal portal : portalManager.getPortals())
            serializedPortals.add(new SerializedPortal(portal));
    }
    
    public PortalManager getPortalManager() {
        PortalManager portalManager = new PortalManager();
        
        for(SerializedPortal serializedPortal : serializedPortals)
            portalManager.addPortal(serializedPortal.getPortal());
        
        return portalManager;
    }
}
