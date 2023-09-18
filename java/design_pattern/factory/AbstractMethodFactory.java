package design_pattern.factory;

/**
 * 提供一个创建一系列相关或互相依赖对象的接口，而无需指定他们的具体的类
 * 应用场景：
 * 1.程序需要处理不同系列的相关产品，但是您不希望它依赖于这些产品的具体类时
 * 2.可以使用抽象工厂
 * 优点：
 * 1.可以确信你从工厂得到的产品彼此是兼容的。
 * 2.可以避免具体产品和客户端代码之间的紧密耦合。
 * 3.符合单一职责原则
 * 4.符合开闭原则
 * <p>
 * java.sql.Connection
 * java.sql.Driver
 */
public class AbstractMethodFactory {
    public static void main(String[] args) {
        IDBComponent component = new MysqlDbComponent();
        component.getCommand();
        component.getConnection();
    }
}

interface IConnection {

    void connection();
}

class MyConnection implements IConnection {

    @Override
    public void connection() {
        System.out.println("mysql: connect.");
    }
}

class OracleConnection implements IConnection {

    @Override
    public void connection() {
        System.out.println("oracle:connect.");
    }
}

interface ICommand {
    void command();
}

class MyCommand implements ICommand {

    @Override
    public void command() {
        System.out.println("mysql: command.");
    }
}

class OracleCommand implements ICommand {

    @Override
    public void command() {
        System.out.println("oracle:command.;");
    }
}

interface IDBComponent {
    IConnection getConnection();

    ICommand getCommand();
}


//“1”在这里值的是MySQL
class MysqlDbComponent implements IDBComponent {

    @Override
    public IConnection getConnection() {
        return new MyConnection();
    }

    @Override
    public ICommand getCommand() {
        return new MyCommand();
    }
}

//“2”在这里值的是Oracle系列
class OracleDbComponent implements IDBComponent {

    @Override
    public IConnection getConnection() {
        return new OracleConnection();
    }

    @Override
    public ICommand getCommand() {
        return new OracleCommand();
    }
}


