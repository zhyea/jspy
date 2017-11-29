import com.zhyea.jspy.commons.model.TimerEntry;
import com.zhyea.jspy.commons.tools.TimerClerk;

import java.util.Collection;

public class Test {


    //http://blog.csdn.net/wwwxxdddx/article/details/51878772
    //http://blog.csdn.net/wwwxxdddx/article/details/45064219


    public static void main(String[] args) throws IllegalAccessException {
        //ObjectMeasure.measure(new Basket());
        System.out.println(all());
    }


    private static Collection<TimerEntry> all() {
        return TimerClerk.getAll();
    }

}
