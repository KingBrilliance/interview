package design_pattern.singleton;

public class Singleton {
    public static void main(String[] args) {
        HungerSingleton.getInstance();
        HungerBlockSingleton.getInstance();
        LazySingleton.getInstance();
        LazySyncSingleton.getInstance();
        LazyInSyncSingleton.getInstance();
        DoubleCheckSingleton.getInstance();
        StaticSingleton.getInstance();
        EnumSingleton instance = EnumSingleton.INSTANCE;
    }
}

// HungerSingleton 饿汉式单例

/**
 * 1.饿汉式（静态常量）(推荐使用)
 * 在类加载子系统中、有加载、连接、初始化三个阶段；
 * 在初始化阶段、会执行clinit方法、这个方法是JVM自动帮我们生成的、会根据所有的静态变量、静态代码块生成一个clinit方法、由这个方法执行static代码块、以及static变量的初始化过程；
 */
class HungerSingleton {
    private HungerSingleton() {
    }

    private static HungerSingleton instance = new HungerSingleton();

    public static HungerSingleton getInstance() {
        return instance;
    }
}

/**
 * 2.饿汉式（静态代码块）(推荐使用)
 */
class HungerBlockSingleton {
    private HungerBlockSingleton() {
    }

    private static HungerBlockSingleton instance;

    static {
        instance = new HungerBlockSingleton();
    }

    public static HungerBlockSingleton getInstance() {
        return instance;
    }
}

/**
 * 3.懒汉式(线程不安全)
 * 1）起到了懒加载的效果，但是只能在单线程下使用。
 * 2）在多线程环境下，一个线程进入 if(singleton == null)判断语句块，还未来得及往下执行，另一个线程也通过了这个判断语句，这时便会产生多个实例。所以在多线程下不可使用这种方式。
 */
class LazySingleton {
    private LazySingleton() {
    }

    private static LazySingleton instance;

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }

        return instance;
    }
}

/**
 * 4.懒汉式(线程安全，同步方法)
 * 1）解决了线程安全问题
 * 2）效率太低，每个线程在想获得类的实例时候，执行getInstance()方法都要进行同步。而其实这个方法只执行一次实例化代码就够了，后面的想获得该类实例，直接return 就行了。方法进行同步效率太低。
 */
class LazySyncSingleton {
    private LazySyncSingleton() {
    }

    private static LazySyncSingleton instance;

    public static synchronized LazySyncSingleton getInstance() {
        if (instance == null) {
            instance = new LazySyncSingleton();
        }

        return instance;
    }
}

/**
 * 5.懒汉式(线程安全，同步代码块)
 * 线程安全问题，如果在多线程下，一个线程进入 if(singleton == null)判断语句块，还未来得及往下执行，另一个线程也通过了这个判断语句，这时便会产生多个实例。所以在多线程下不可使用这种方式。
 */
class LazyInSyncSingleton {
    private LazyInSyncSingleton() {
    }

    private static LazyInSyncSingleton instance;

    public static LazyInSyncSingleton getInstance() {
        if (instance == null) {
            synchronized (LazyInSyncSingleton.class) {
                instance = new LazyInSyncSingleton();
            }
        }

        return instance;
    }
}

/**
 * 6. 双重检查（双端检索）
 * 优缺点说明
 * 1)双重检查，如代码所示，进行了两次 if(instance == null)检查，这样就可以保证线程安全。
 * 2）线程安全，延迟加载，效率较高
 * 结论：在实际开发中，推荐使用这种单例模式。
 */
class DoubleCheckSingleton {
    private DoubleCheckSingleton() {
    }

    private static volatile DoubleCheckSingleton instance;

    public static DoubleCheckSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckSingleton();
                }
            }
        }

        return instance;
    }
}

/**
 * 7.静态内部类
 * 1）这种方式采用了类加载的机制来保证初始化实例时只有一个线程
 * 2）静态内部类方式在StaticSingleton类被加载时并不会立即实例化，而是在调用getInstance()方法，才会装载SingletonInstance类，从而完成StaticSingleton的实例化。
 * 3) 类的静态属性只会在第一次加载类的时候初始化，所以在这里，JNM帮助我们保证了线程的安全性，在类进行初始化时，别的线程时无法进入的。
 * 4）避免了多线程同步问题，利用静态内部类特点实现延迟加载，效率高
 * 结论：
 * 在实际开发中，推荐使用这种单例模式。
 */
class StaticSingleton {
    private StaticSingleton() {
    }

    ;

    private static class SingletonInstance {
        private static final StaticSingleton instance = new StaticSingleton();
    }

    public static StaticSingleton getInstance() {
        return SingletonInstance.instance;
    }
}

/**
 * 8.枚举 (推荐使用)
 * 避免了多线程同步问题，而且还能防止反序列化重新城建新的对象结论：在实际开发中，推荐使用这种单例模式。
 */
enum EnumSingleton {
    INSTANCE;
}