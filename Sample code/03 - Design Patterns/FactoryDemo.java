class Product {
    public void demo() {
        System.out.println("In Product");
    }
}

class SpecialProduct extends Product {
    public void demo() {
        System.out.println("In Special Product");
    }
}

class Creator  {
    public Product createProduct() {
        return new Product();
    }

    public void demo() {
        Product p = createProduct();
        p.demo();
    }
}

class SpecialCreator extends Creator {
    public Product createProduct() {
        return new SpecialProduct();
    }
}

public class FactoryDemo {
    public static void main (String[] args) {
        Creator c = new SpecialCreator();
        c.demo();
    }
}