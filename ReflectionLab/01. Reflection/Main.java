import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Class<Reflection> reflectionClass = Reflection.class;

        System.out.println(reflectionClass);

//        System.out.println(reflectionClass.getName());
        System.out.println(reflectionClass.getSuperclass());
        Class<?>[] interfaces = reflectionClass.getInterfaces();
        Arrays.stream(interfaces).forEach(System.out::println);

        Reflection reflection = reflectionClass.getDeclaredConstructor().newInstance();
        System.out.println(reflection);

    }

}
