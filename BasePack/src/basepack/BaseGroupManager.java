package basepack;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BaseGroupManager<Item extends BaseItem, GroupItem extends BaseGroup> {
    private Map<String, GroupItem> groups = new LinkedHashMap();
    
    public void addGroup(GroupItem group) {
        groups.put(group.getName(), group);
    }
    
    public GroupItem getGroup(String name, String playerName) {
        GroupItem group = groups.get(name);
        if(group == null)
            return null;
        
        if(group.getPlayerName().equalsIgnoreCase(playerName))
            return group;
        
        return null;
    }
    
    public GroupItem getGroup(String name) {
        return groups.get(name);
    }
    
    public List<GroupItem> getGroups() {
        return new ArrayList(groups.values());
    }
    
    public void removeGroup(GroupItem group) {
        groups.remove(group.getName());
    }
    
    public void removeItemFromGroups(Item item, String playerName) {
        for(GroupItem group : groups.values())
            if(group.getPlayerName().equalsIgnoreCase(playerName))
                group.remove(item);
    }
    
    public void renameGroup(GroupItem group, String newName) {
        group.setName(newName);
        
        Map<String, GroupItem> renamedGroups = new LinkedHashMap();
        
        for(GroupItem oldGroup : groups.values())
            renamedGroups.put(oldGroup.getName(), oldGroup);
        
        groups = renamedGroups;  
    }

    public void renameAreaInGroups(String playerName, String originalName, String newName) {
        for(GroupItem group : groups.values())
            if(group.getPlayerName().equals(playerName))
                group.rename(originalName, newName);
    }
    
    public void setIndex(GroupItem group, int index) {        
        List<GroupItem> list = getGroups();
        
        list.remove(group);
        list.add(index, group);
        
        groups.clear();
        
        for(GroupItem listgroup: list)
            groups.put(listgroup.getName(), listgroup);
    }
}
