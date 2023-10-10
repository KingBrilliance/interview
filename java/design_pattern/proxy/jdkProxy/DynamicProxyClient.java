package design_pattern.proxy.jdkProxy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Proxy;

/**
 * JDK动态代理是一种比较常见的代理方式，它是在程序运行时动态生成代理类，也就是说我们在编写代码时并不知道具体代理的是什么类，而是在程序运行时动态生成。
 * 对象必须实现一个或多个接口
 * 代理类的代理方法不需要额外的逻辑
 */
public class DynamicProxyClient {
    public static void main(String[] args) {
        Landlord1Service proxy = (Landlord1Service) new JDKProxy(new TenantImpl1()).getProxyInstance();
        System.out.println(proxy.rent("200"));
        proxy.rent(300);
    }
}

interface Landlord1Service {
    /**
     * 出租
     *
     * @param money 金额
     * @return
     */
    void rent(Integer money);

    String rent(String str);
}

/**
 * 租客1
 *
 * @author Created by njy on 2023/5/30
 */
class TenantImpl1 implements Landlord1Service {

    @Override
    public void rent(Integer money) {
        System.out.println("租下" + money + "元一个月的房子");
    }

    @Override
    public String rent(String str) {
        System.out.println("租下" + str + "元一个月的房子");
        return "租下" + str + "元一个月的房子";
    }
}

/**
 * 中介
 *
 * @author Created by njy on 2023/5/30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
class JDKProxy {

    /**
     * 房东有很多套房子，不想亲自出马了，于是找来了中介
     */
    private Object target;

    /**
     * 给目标对象生成代理对象
     *
     * @return 代理生成的对象
     */
    public Object getProxyInstance() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), target.getClass().getInterfaces(), (proxy, method, args) -> {
            System.out.println("[JDK动态代理]交中介费");
            if (method.getReturnType() == String.class) {
                System.out.println("我真的他哭死");
            }
            Object res = method.invoke(target,args);
            System.out.println("[JDK动态代理]中介负责维修管理");
            return res;
        });
    }
}