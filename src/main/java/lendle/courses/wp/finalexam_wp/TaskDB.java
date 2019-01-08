package lendle.courses.wp.finalexam_wp;

import java.util.HashMap;
import java.util.Map;

public class TaskDB
{
    private static Map<String, String> db=new HashMap<>();
    
    public static void save(String title, String content)
    {
        db.put(title, content);
    }
    
    public static String get(String title)
    {
        return db.get(title);
    }
}
