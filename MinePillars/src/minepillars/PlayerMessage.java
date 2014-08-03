package minepillars;

import java.util.List;
import puzzlepack.BaseMessage;

public class PlayerMessage extends BaseMessage {
    
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
}
