package com.whiteBox.core.factory;

import com.whiteBox.core.anno.White;
import com.whiteBox.core.element.WhiteMethod;
import com.whiteBox.core.operator.AbstractProcessor;
import com.whiteBox.core.util.AutoSourceUtil;
import com.whiteBox.core.util.PackageUtil;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: kaccn
 * @description: 白盒测试入口处理器
 * @create: 2020-08-17 23:40
 **/
public abstract class MethodsFactory extends AbstractProcessor {

    /**
     * 白盒测试的方法池
     */
    public static final Map<String, WhiteMethod> whiteMethods = new ConcurrentHashMap<>();

    /**
     * 实现白盒测试方法捕捉器
     *
     * @param object 捕获入口，包路径或IOC容器
     * @throws Exception
     */
    @Override
    public void doCatch(Object object) throws Exception {
        if (object instanceof String) {
            initFactory((String) object);
        } else if (object instanceof String[]) {
            initFactory((String[]) object);
        } else if (object instanceof ApplicationContext) {
            initFactory((ApplicationContext) object);
        }
    }

    /**
     * 实现白盒测试方法执行器
     *
     * @throws Exception
     */
    @Override
    public void doWhiteMethod() throws Exception {
        mockWhiteMethods();
    }

    /**
     * 通过包名初始化工厂
     *
     * @param packageNames 扫描的包名组
     * @throws Exception
     */
    public void initFactory(String[] packageNames) throws Exception {
        initFactory(packageNames, null);
    }

    /**
     * 通过包名初始化工厂（二）
     *
     * @param packageName 扫描的包名
     * @throws Exception
     */
    public void initFactory(String packageName) throws Exception {
        String[] packageNames = new String[]{packageName};
        initFactory(packageNames, null);

    }

    /**
     * 通过容器初始化工厂
     *
     * @param context IOC容器
     * @throws Exception
     */
    public void initFactory(ApplicationContext context) throws Exception {
        initFactory(null, context);
    }

    /**
     * 初始化工厂
     *
     * @param packageNames 扫描的包名
     * @param context      IOC容器
     * @throws Exception
     */
    public void initFactory(String[] packageNames, ApplicationContext context) throws Exception {
        //初始化普通方法入池
        if (packageNames != null) {
            Arrays.asList(packageNames).stream().forEach(name -> initWhiteMethods(name));
        }
        //初始化容器相关方法入池
        if (context != null) {
            initWhiteMethods(context);
        }
    }

    /**
     * 通过包名路径初始化方法池
     *
     * @param packageName
     */
    public void initWhiteMethods(String packageName) {
        List<String> list = PackageUtil.getAllTargets(packageName);
        if (list.size() == 0) {
            return;
        }
        list.stream().forEach(className -> {
            try {
                Class<?> clazz = Class.forName(className);
                Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(White.class)) {
                        White white = method.getAnnotation(White.class);
                        if (!white.fromIOC()) {//只收集非容器关联的方法
                            whiteMethods.put("package_key_" + className + "_" + method.getName(), new WhiteMethod(className, white.value(), clazz, method, white.fromIOC(), null));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 通过spring容器初始化方法池
     *
     * @param applicationContext
     */
    public void initWhiteMethods(ApplicationContext applicationContext) {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        Arrays.asList(beanDefinitionNames).stream().forEach(beanDefinitionName -> {
            try {
                Class<?> clazz = applicationContext.getType(beanDefinitionName);
                Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(White.class)) {
                        White white = method.getAnnotation(White.class);
                        if (white.fromIOC()) {
                            whiteMethods.put("spring_key_" + beanDefinitionName + "_" + method.getName(), new WhiteMethod(beanDefinitionName, method.getAnnotation(White.class).value(), clazz, method, white.fromIOC(), applicationContext.getBean(beanDefinitionName)));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 遍历并执行方法池所有方法
     */
    public void mockWhiteMethods() {
        whiteMethods.forEach((k, v) -> {
            System.out.println("======= Test Staring ======>>  [class/bean:" + v.getClassName() + ", method:" + v.getMethod().getName() + "]");
            try {
                Method method = v.getMethod();
                //拼接动态参数
                Object[] args = AutoSourceUtil.getSampleParams(method);
                AutoSourceUtil.doMethod(v.getClazz(), method, args, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
