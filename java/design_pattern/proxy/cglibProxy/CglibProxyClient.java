package design_pattern.proxy.cglibProxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 被代理的类没有实现接口或者无法实现接口
 * 代理类的代理方法需要进行额外的逻辑，如事务处理等。
 * 1.对于没有实现接口的类，只能使用CGLIB代理
 * 2.对于实现了接口的类，可以使用JDK代理或者CGLIB代理，如果要求比较高的话，建议使用JDK代理。
 * 3.对于单个代理类的情况，并且被代理类实现了接口，可以使用静态代理。
 * 4.对于多个被代理类的情况，建议使用JDK代理或CGLIB代理。
 */
public class CglibProxyClient {
    public static void main(String[] args) {
        Landlord3Service service = (Landlord3Service) new CglibProxy(new Landlord3Service()).getProxyInstance();
        service.rent(100);
    }
}

class Landlord3Service {
    /**
     * 出租房屋
     *
     * @param money
     * @return
     */
    public void rent(Integer money) {
        System.out.println("租下" + money + "元一个月的房子");
    }
}

/**
 * JDKProxy：cglib子类代理工厂
 * 1.代理的类不能为final
 * 2.目标对象的方法如果为final/static，那么就不会被拦截，也不会执行目标对象的业务方法
 *
 * @author Created by njy on 2023/5/30
 */
class CglibProxy implements MethodInterceptor {

    /**
     * 目标对象
     */
    private final Object target;

    public CglibProxy(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        //1.工具类
        Enhancer en = new Enhancer();
        //2.设置父类
        en.setSuperclass(target.getClass());
        //3.设置回调函数
        en.setCallback(this);
        //4.创建子类（代理对象）
        return en.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("[Cglib代理]交中介费");
        method.invoke(target, objects);
        System.out.println("[Cglib代理]中介负责维修管理");
        return null;
    }
}