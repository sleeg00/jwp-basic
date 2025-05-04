package core.nmvc;


import core.annotation.Controller;

import core.mvc.RequestMapping;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import org.reflections.Reflections;
import java.lang.reflect.Method;
import java.util.Set;

public class ControllerScan {
    public static Map<Class<?>, Object> map = new HashMap<>();

    ControllerScan()
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        final RequestMapping requestMapping = new RequestMapping();
        Reflections reflections = new Reflections("jwp_basic"); // 패키지명 정확히

        Set<Class<?>> controllers = reflections.getTypesAnnotatedWith(Controller.class);

        for (Class<?> clazz : controllers) {
            Object instance = clazz.getDeclaredConstructor().newInstance();
            map.put(clazz, instance);
        }
    }
}
