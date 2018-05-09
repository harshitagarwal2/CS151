interface Shape {
    public String draw();
}

class Circle implements Shape {
    public String draw() {
        return "I am a Circle";
    }
}

abstract class Decorator implements Shape {
    Shape shapeObj;
    public Decorator(Shape s) {
        shapeObj = s;
    }
}

class LabelDecorator extends Decorator {
    public LabelDecorator(Shape s) {
        super(s);
    }
    public String draw() {
        return "Applying label" ;//+ shapeObj.draw();
        
    }
}
public class DecoratorDemo {
   public static void main(String[] args) {
        Shape circle = new LabelDecorator(new Circle());
        System.out.println(circle.draw());
    }
}