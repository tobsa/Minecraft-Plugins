package toggleblocks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import toggleblocks.group.Group;

public class SerializedGroup implements Serializable {
    private String name;
    private String playerName;
    private List<SerializedRegion> regions = new ArrayList();
    
    public SerializedGroup(Group group) {
        this.name = group.getName();
        this.playerName = group.getPlayerName();
        
        for(Region region : group.get())
            regions.add(new SerializedRegion(region));
    }
    
    public Group getGroup() {
        Group group = new Group(name, playerName);
        
        for(SerializedRegion region : regions)
            group.add(region.getRegion());
            
        return group;
    }
}
