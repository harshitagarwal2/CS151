
interface AbstractFactory {
    public AbstractProductA createProductA();
    public AbstractProductB createProductB();
}
class ConcreteFactory1 implements AbstractFactory {
    public AbstractProductA createProductA() {
        return new ProductA1();
    }
    public AbstractProductB createProductB() {
        return new ProductB1();
    }
}
class ConcreteFactory2 implements AbstractFactory {
    public AbstractProductA createProductA() {
        return new ProductA2();
    }
    public AbstractProductB createProductB() {
        return new ProductB2();
    }
}


interface AbstractProductA {
    public String getName();
    public String getPlatform();
}

class ProductA1 implements AbstractProductA {
    public String getName() {
        return "ProductA";
    }
    public String getPlatform() {
        return "Platform1";
    }
}

class ProductA2 implements AbstractProductA {
    public String getName() {
        return "ProductA";
    }
    public String getPlatform() {
        return "Platform2";
    }
}

interface AbstractProductB {
    public String getTitle();
    public String getPlatform();
}

class ProductB1 implements AbstractProductB {
    public String getTitle() {
        return "ProductB";
    }
    public String getPlatform() {
        return "Platform1";
    }
}

class ProductB2 implements AbstractProductB {
    public String getTitle() {
        return "ProductB";
    }
    public String getPlatform() {
        return "Platform2";
    }
}

public class AbstractFactoryDemo {
    
    public static void main(String[] args) {

        AbstractFactory ab1 = new ConcreteFactory1();
        AbstractProductA product = ab1.createProductA();
        System.out.println(product.getName());
    }
}