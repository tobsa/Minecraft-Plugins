package forestertool;

public class FileManager {
    private static ForesterTool plugin;
    
    public static void set(ForesterTool plugin) {
        FileManager.plugin = plugin;
    }
    
    public static void save(Tool tool) {
        plugin.getConfig().set("tool.radius", tool.getRadius());
        plugin.getConfig().set("tool.leaves", tool.getLeaves());
        plugin.getConfig().set("tool.air", tool.getAir());
        plugin.getConfig().set("tool.replace", tool.getReplace());
        plugin.getConfig().set("tool.distance", tool.getDistanceMode());
        plugin.saveConfig();
    }
    
    public static Tool load() {
        Tool tool = new Tool();
        
        tool.setRadius(plugin.getConfig().getInt("tool.radius", 3));
        tool.setLeaves(plugin.getConfig().getInt("tool.leaves", 65));
        tool.setAir(plugin.getConfig().getInt("tool.air", 20));
        tool.setReplace(plugin.getConfig().getBoolean("tool.replace", false));
        tool.setDistanceMode(plugin.getConfig().getBoolean("tool.distance", false));
        
        return tool;
    }
    
}
