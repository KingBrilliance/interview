package design_pattern.factory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 工厂模式
 * 工厂模式分为简单工厂、工厂方法与抽象工厂模式；
 * 1.简单、容易理解
 * 2.违反了OCP原则，即对扩展开放，对修改关闭。即当我们给类增加新功能的时候，尽量不修改已经写好的代码，或者尽可能少修改代码。
 * 3.比如我们这时要新增加一个Pizza的种类(CheesePizza披萨)，我们需要做如下修改.
 * 也许有人会说，不就一个地方修改了代码吗？但是在工作中，可能在其他的地方也有创建Pizza代码，意味着其他的地方也要修改，而创建Pizza代码的地方，有很多处的情况下，就麻烦了。
 * 思路：把创建Pizza对象封装到一个类中，这样我们有新的Pizza种类时，只需要修改该类就可，其它有创建到Pizza对象的代码就不需要修改了.-> 简单工厂模式
 */
public class SimpleFactory {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new OrderPizza();
    }
}


//将Pizza 类做成抽象
@Data
@AllArgsConstructor
@NoArgsConstructor
abstract class Pizza {
    protected String name; //名字

    //准备原材料, 不同的披萨不一样，因此，我们做成抽象方法
    public abstract void prepare();

    public void bake() {
        System.out.println(name + " baking;");
    }

    public void cut() {
        System.out.println(name + " cutting;");
    }

    //打包
    public void box() {
        System.out.println(name + " boxing;");
    }
}


class CheesePizza extends Pizza {

    @Override
    public void prepare() {
        // TODO Auto-generated method stub
        setName("奶酪pizza");
        System.out.println(name + " preparing;");
    }

}

class GreekPizza extends Pizza {

    @Override
    public void prepare() {
        // TODO Auto-generated method stub
        System.out.println(" 给希腊披萨 准备原材料 ");
    }

}

class PepperPizza extends Pizza {

    @Override
    public void prepare() {
        // TODO Auto-generated method stub
        System.out.println(" 给胡椒披萨准备原材料 ");
    }

}

class OrderPizza {

    //构造器
    public OrderPizza() {
        Pizza pizza = null;
        String orderType; // 订购披萨的类型
        do {
            orderType = getType();
            if (orderType.equals("greek")) {
                pizza = new GreekPizza();
                pizza.setName(" 希腊披萨 ");
            } else if (orderType.equals("cheese")) {
                pizza = new CheesePizza();
                pizza.setName(" 奶酪披萨 ");
            } else if (orderType.equals("pepper")) {
                pizza = new PepperPizza();
                pizza.setName("胡椒披萨");
            } else {
                break;
            }
            //输出pizza 制作过程
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();

        } while (true);
    }

    // 写一个方法，可以获取客户希望订购的披萨种类
    private String getType() {
        try {
            BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("input pizza 种类:");
            String str = strin.readLine();
            return str;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
