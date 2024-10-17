package Common.utils;

public class ThreadUtils {
    private static final ThreadLocal<Long> threadLocal = new ThreadLocal<>();
    public static void setLocal(long id){
        threadLocal.set(id);
    }
    public static Long getLocal(){
        return threadLocal.get();
    }
    public static void delLocal(){
        threadLocal.remove();
    }
}
