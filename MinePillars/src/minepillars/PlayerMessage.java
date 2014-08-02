package minepillars;

import java.util.List;
import org.bukkit.ChatColor;
import puzzlepack.CommandColor;

public class PlayerMessage {
    private static final ChatColor HEADER    = CommandColor.HEADER;
    private static final ChatColor NORMAL    = CommandColor.NORMAL;
    private static final ChatColor ERROR     = CommandColor.ERROR;
    private static final ChatColor HIGHLIGHT = CommandColor.HIGHLIGHT;
    
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
