import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Class<Reflection> clazz = Reflection.class;

        Field[] declaredFields = clazz.getDeclaredFields();
        List<Field> fields = Arrays.stream(declaredFields).sorted(Comparator.comparing(Field::getName)).collect(Collectors.toList());
        for (Field field : fields) {
            int modifiers = field.getModifiers();
            if (!Modifier.isPrivate(modifiers)){
                System.out.printf("%s must be private!\n", field.getName());
            }

        }

        Method[] methods = clazz.getDeclaredMethods();
        Arrays.stream(methods).filter(m-> m.getName().startsWith("get")).sorted(Comparator.comparing(Method::getName))
                .forEach(m-> {
                    if (!Modifier.isPublic(m.getModifiers())){
                        System.out.printf("%s have to be public!\n",m.getName());
                    }

                });




        Arrays.stream(methods).filter(m-> m.getName().startsWith("set")).sorted(Comparator.comparing(Method::getName))
                .forEach(m-> {

                    if (!Modifier.isPrivate(m.getModifiers())){

                        System.out.printf("%s have to be private!\n", m.getName());

                    }
                });


    }

}
