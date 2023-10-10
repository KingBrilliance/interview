package design_pattern.proxy.staticProxy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 1.当代理对象只有一个时，可以使用静态代理
 * 2.当被代理的类的接口比较稳定时，可以使用静态代理
 * 3.当需要为多个被代理的类提供代理时，会导致代理类过多，不方便管理和维护，所以不建议使用静态代理。
 */
public class StaticProxyClient {
    public static void main(String[] args) {
        ProxyImpl proxy = new ProxyImpl(new TenantImpl1());
        proxy.rent(2000);
    }
}

interface Landlord1Service {
    /**
     * 出租
     * @param money 金额
     * @return
     */
    void rent(Integer money);
}

/**
 * 租客1
 * @author Created by njy on 2023/5/30
 */
class TenantImpl1 implements Landlord1Service {

    @Override
    public void rent(Integer money) {
        System.out.println("租下"+money+"元一个月的房子");
    }
}

/**
 * 租客2
 * @author Created by njy on 2023/5/30
 */
class TenantImpl2 implements Landlord1Service {

    @Override
    public void rent(Integer money) {
        System.out.println("租下"+money+"元两个月的房子");
    }
}

/**
 * 中介
 * @author Created by njy on 2023/5/30
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
class ProxyImpl implements Landlord1Service {

    /**
     * 房东有很多套房子，不想亲自出马了，于是找来了中介
     */
    private Landlord1Service target;

    /**
     * 优点就是在不改变原来的实现类的情况下对方法实现了增强
     * 缺点是如果原来的接口新增了方法，那么这里也要对应实现新的方法
     * @param money 金额
     * @return
     */
    @Override
    public void rent(Integer money) {
        System.out.println("[静态代理]交中介费");
        target.rent(money);
        System.out.println("[静态代理]中介负责维修管理");
    }
}