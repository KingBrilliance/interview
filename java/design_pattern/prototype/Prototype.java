package design_pattern.prototype;

import lombok.*;

import java.io.*;

/**
 * 1）用原型实例指定厂家对象的种类、并且通过拷贝这些原型、创建新的对象；
 * 2）原型模式是一种创建型设计模式、允许一个对象在创建一个可定制的对象、无需知道如何创建的细节；
 * 3）工作原理：通过将一个原型对象传给哪个要发动创建的对象、这个要发动创建的对象通过请求原型拷贝他们自己来实施创建、 对象.clone();
 * 1.Prototype : 原型类，声明一个克隆自己的接口
 * 2.ConcretePrototype: 具体的原型类, 实现一个克隆自己的操作
 * 3.Client: 让一个原型对象克隆自己，从而创建一个新的对象(属性一样)
 */
public class Prototype {
    public static void main(String[] args) throws Exception {
        System.out.println("原型模式完成对象的创建");
        // TODO Auto-generated method stub
        Prototypes sheep = new Prototypes("tom", 1, "白色", "北京", new Prototypes());
        Prototypes sheep2 = (Prototypes) sheep.clone(); //克隆
        Prototypes sheep3 = (Prototypes) sheep.clone(); //克隆
        Prototypes sheep4 = (Prototypes) sheep.clone(); //克隆
        Prototypes sheep5 = (Prototypes) sheep.clone(); //克隆

        System.out.println("sheep2 =" + sheep2);
        sheep5.getMyProto().setAge(20);
        System.out.println("sheep3 =" + sheep3);
        System.out.println("sheep4 =" + sheep4);
        System.out.println("sheep5 =" + sheep5);
        System.out.println("sheep5 =" + sheep2);


        // TODO Auto-generated method stub
        DeepClone p = new DeepClone();
        p.setName("宋江");
        p.setClones(new DeepCloneableTarget("大牛", "小牛"));

        //方式1 完成深拷贝

        DeepClone p2 = (DeepClone) p.clone();

        System.out.println("p.name=" + p.getName() + "p.deepCloneableTarget=" + p.getClones().hashCode());
        System.out.println("p2.name=" + p.getName() + "p2.deepCloneableTarget=" + p2.getClones().hashCode());

        //方式2 完成深拷贝
        DeepClone p3 = (DeepClone) p.deepClone();

        System.out.println("p.name=" + p.getName() + "p.deepCloneableTarget=" + p.getClones().hashCode());
        System.out.println("p3.name=" + p.getName() + "p3.deepCloneableTarget=" + p3.getClones().hashCode());
    }
}

/**
 * 浅拷贝
 * 1.对于数据类型是基本数据类型的成员变量、浅拷贝会直接进行值传递、将值复制一份给新的对象；
 * 2.如果是引用数据类型的成员变量、浅拷贝会进行引用传递、也就是将该成员变量的引用值（内存地址）复制一份给新的对象。实际上两个对象的该成员变量都指向同一个实例。
 * 3.在这种情况下、在一个对象中修改该成员变量会影响另一个对象的成员变量值；
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
class Prototypes implements Cloneable {
    private String name;
    private int age;

    private String color;

    private String address;

    private Prototypes myProto;

    public Object clone() {
        Prototypes prototypes = null;
        try {
            prototypes = (Prototypes) super.clone();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        // TODO Auto-generated method stub
        return prototypes;
    }

    @Override
    public String toString() {
        return "Sheep [name=" + name + ", age=" + age + ", color=" + color + ", address=" + address + ", proto=" + myProto.age + "]";
    }
}

/**
 * 深拷贝
 * 1）复制对象的所有基本数据类型的成员变量值；
 * 2）为所有引用数据类型的成员变量申请存储空间、并复制每个引用类型成员变量所引用的对象，直到该对象可达的所有对象；也就是、对象进行深拷贝就是对整个对象进行拷贝；
 * 3）深拷贝实现方式：重写Clone方法来实现深拷贝；
 * 4）深拷贝实现方式2：通过对象序列化实现深拷贝；
 */
class DeepCloneableTarget implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;

    private String cloneName;

    private String cloneClass;

    //构造器
    public DeepCloneableTarget(String cloneName, String cloneClass) {
        this.cloneName = cloneName;
        this.cloneClass = cloneClass;
    }

    //因为该类的属性，都是String , 因此我们这里使用默认的clone完成即可
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class DeepClone implements Serializable, Cloneable {
    private String name;
    private DeepCloneableTarget clones;

    protected Object clone() throws CloneNotSupportedException {

        Object deep = null;
        //这里完成对基本数据类型(属性)和String的克隆
        deep = super.clone();
        //对引用类型的属性，进行单独处理
        DeepClone deepProtoType = (DeepClone) deep;
        deepProtoType.clones = (DeepCloneableTarget) clones.clone();

        // TODO Auto-generated method stub
        return deepProtoType;
    }

    //深拷贝 - 方式2 通过对象的序列化实现 (推荐)
    public Object deepClone() {
        final DeepClone _this = this;
        //创建流对象
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos) {
                 {
                     writeObject(_this);
                 }
             };
             ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            //序列化
            //反序列化
            DeepClone copyObj = (DeepClone) ois.readObject();
            return copyObj;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }
}

/**
 * 注意事项
 * 1.创建新的对象比较复杂时，可以利用原型模式简化对象的创建过程，同时也能够提高效率
 * 2.不用重新初始化对象，而是动态地获得对象运行时的状态
 * 3.如果原始对象发生变化(增加或者减少属性)，其它克隆对象的也会发生相应的变化，无需修改代码
 * 4.在实现深克隆的时候可能需要比较复杂的代码
 * 5.缺点：需要为每一个类配备一个克隆方法，这对全新的类来说不是很难，但对已有的类进行改造时，需要修改其源代码，违背了ocp原则，这点请同学们注意
 */