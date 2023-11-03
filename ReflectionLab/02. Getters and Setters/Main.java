import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Class<Reflection> reflectionClass = Reflection.class;

        Method[] declaredMethods = reflectionClass.getDeclaredMethods();

        Arrays.stream(declaredMethods).filter(m-> m.getName().startsWith("get")).sorted(Comparator.comparing(Method::getName))
                .forEach(m-> System.out.printf("%s will return class %s\n", m.getName(), m.getReturnType().getName()));

        Arrays.stream(declaredMethods).filter(m-> m.getName().startsWith("set")).sorted(Comparator.comparing(Method::getName))
                .forEach(m-> System.out.printf("%s and will set field of class %s\n", m.getName(), m.getParameterTypes()[0].getName()));


    }

}
