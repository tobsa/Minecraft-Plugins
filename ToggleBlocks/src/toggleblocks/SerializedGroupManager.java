package toggleblocks;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import toggleblocks.group.Group;
import toggleblocks.group.GroupManager;

public class SerializedGroupManager implements Serializable {
    private Map<String, SerializedGroup> groups = new LinkedHashMap();
    
    public SerializedGroupManager(GroupManager groupManager) {
                
        for(Group group : groupManager.getGroups())
            groups.put(group.getName(), new SerializedGroup(group));
    }    
    
    public GroupManager getGroupManager() {
        GroupManager groupManager = new GroupManager();
        
        for(SerializedGroup group : groups.values())
            groupManager.addGroup(group.getGroup());
        
        return groupManager;
    }
}
