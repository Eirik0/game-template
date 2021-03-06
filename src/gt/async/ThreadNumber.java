package gt.async;

import java.util.HashMap;
import java.util.Map;

public class ThreadNumber {
    private static final Map<String, Integer> threadNumMap = new HashMap<>();

    private ThreadNumber() {
    }

    public static synchronized int getThreadNum(Class<?> threadObjClass) {
        return getThreadNum(threadObjClass.getName());
    }

    public static synchronized int getThreadNum(String className) {
        Integer num = threadNumMap.get(className);
        int threadNum = num == null ? 0 : num.intValue() + 1;
        threadNumMap.put(className, Integer.valueOf(threadNum));
        return threadNum;
    }
}
