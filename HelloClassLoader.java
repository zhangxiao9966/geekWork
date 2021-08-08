import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author zhangxiao -[Create on 2021/8/7]
 */
public class HelloClassLoader extends ClassLoader {

    public static void main(String[] args) {

        Class clazz = new HelloClassLoader().findClass("Hello");
        try {
            Object object = clazz.newInstance();
            Method method = clazz.getMethod("hello");
            method.invoke(object);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected Class<?> findClass(String name) {
        Path path = Paths.get("E:/Hello.xlass");
        byte[] data = new byte[0];
        try {
            data = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte num = (byte) (255);
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) ((num - data[i]));
        }

        return defineClass(name, data, 0, data.length);
    }
}
