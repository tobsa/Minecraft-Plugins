package basepack;

import org.bukkit.Sound;
import org.bukkit.plugin.java.JavaPlugin;

public class BasePack extends JavaPlugin {
    
    @Override
    public void onEnable() {
        
        
        
        
    }
    
    public static Integer getInteger(String number) {
        try {
            return Integer.valueOf(number);
        } catch(NumberFormatException ex) {
            return null;
        }
    }
    
    public static String combineArguments(String[] args, int arg) {
        String argument = "";
        for(int i = arg; i < args.length; i++)
            argument += args[i] + " ";
        
        return argument;        
    }
    
    public static Sound getSound(String arg) {
        Sound sound; 
        try {
            sound = Sound.valueOf(arg.toUpperCase());
        } catch(IllegalArgumentException ex) {
            return null;
        }
        
        return sound;
    }
}
