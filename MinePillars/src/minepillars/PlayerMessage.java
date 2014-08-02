package minepillars;

import java.util.List;
import org.bukkit.ChatColor;

public class PlayerMessage {
    private static final ChatColor HEADER = ChatColor.GREEN;
    private static final ChatColor NORMAL = ChatColor.WHITE;
    private static final ChatColor ERROR = ChatColor.RED;
    private static final ChatColor HIGHLIGHT = ChatColor.GREEN;
    
    public static String invalidArguments(String usage) {
        return ERROR + "Invalid arguments. Usage: " + usage;
    }

    public static String invalidNumber(String number) {
        return ERROR + "'" + HIGHLIGHT + number + ERROR + "' must be a number!";
    }

    public static String numbers(int length, List<Integer> numbers) {
        String values = NORMAL + "Length (" + HIGHLIGHT + length + NORMAL + ") = [";
        for(int i = 0; i < numbers.size() - 1; i++)
            values = values + HIGHLIGHT + numbers.get(i) + NORMAL + ", ";
        
        values = values + HIGHLIGHT + numbers.get(numbers.size() - 1) + NORMAL + "]";
        
        return values;
    }

    public static String help() {
        return HIGHLIGHT + "========== Help ==========";
    }
}
