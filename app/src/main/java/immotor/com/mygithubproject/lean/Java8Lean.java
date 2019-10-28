package immotor.com.mygithubproject.lean;



import java.io.IOException;
import java.util.Comparator;



import retrofit2.Converter;

public class Java8Lean {
    public static void main(String args[]){
        //Lambda
        //newInstance();
        //方法引用 ::
        methodCite();
    }
    interface Operation<T> {
        T operation(T a, T b);
    }
    interface Con<T> {
        void accept(T var1);
    }
    /**
     * Java 8 中我们可以通过 `::` 关键字来访问类的构造方法，对象方法，静态方法
     *  ::返回一个接口类型
     * 可访问构造方法,静态方法,对象方法
     */
    static void methodCite(){

        //访问构造方法
        IConvert<String, Something> newIn = new IConvert<String, Something>() {
            @Override
            public Something convert(String form) {
                return new Something(form);
            }
        };
        IConvert<String, Something> newInstan = (a) -> { return new Something(a);};

        IConvert<String, Something> newInstance = Something::new;
        newInstance.convert("Something");

        Supplier<Something> get = Something::new;
        Something instansce = Something.create(get);

        //无返回
        Consumer<String> print = System.out::println;

        //有返回，访问静态方法
        IConvert<String, String> startsWith = Something::startsWith;

        print.accept(startsWith.convert("123"));

        Something something = new Something();
        //有返回，访问对象方法
        IConvert<String, String> endWith = something::endWith;

        print.accept(endWith.convert("Java"));

    }
    @FunctionalInterface
    interface Supplier<F>{
        F get();
    }
    @FunctionalInterface
    public interface Consumer<T> {
        void accept(T var1);
    }
    @FunctionalInterface
    interface IConvert<F, T> {
        T convert(F form);
    }
    static class Something {

        public static Something create(Supplier<Something> supplier) {
            return supplier.get();
        }
        public Something(){

        }
        public Something(String something) {
            System.out.println(something);
        }
        // static methods
        static String startsWith(String s) {
            return String.valueOf(s.charAt(0));
        }
        // object methods
        String endWith(String s) {
            return String.valueOf(s.charAt(s.length() - 1));
        }

    }

    /**
     * Lambda 表达式，也可称为闭包，它是推动 Java 8 发布的最重要新特性
     *
     * Lambda 允许把函数作为一个方法的参数（函数作为参数传递进方法中）
     *
     * lambda 表达式的局部变量可以不用声明为 final，但是必须不可被后面的代码修改
     */
    public interface Action {

        void run() throws Exception;
    }
    static void newInstance(){
        Action action = () ->{};

        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;
        // 不用类型声明
        MathOperation subtraction = (a, b) -> {
            int c = a - b;
            System.out.println("10 - 5 = " + c);
            return c;
        };
        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> {
            int c = a * b;
            //System.out.println("10 x 5 = " + c);
            return c; };
        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;

        Java8Lean tester = new Java8Lean();
        System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
        tester.operate(10, 5, subtraction);
        System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + tester.operate(10, 5, division));

        System.out.println("###  10 + 5 = " + tester.operate(10, 5, (int a, int b) -> a + b));
        System.out.println("###  10 - 5 = " + tester.operate(10, 5, (a, b) -> {return a - b;}));
        System.out.println("###  10 x 5 = " + tester.operate(10, 5, (int a, int b) -> { return a * b; }));
        System.out.println("###  10 / 5 = = " + tester.operate(10, 5, (int a, int b) -> a / b));

        String salutation = "Hello! ";
        // 不用括号
        GreetingService greetService1 = message ->{
           // salutation =""; 报错 隐性final
            System.out.println(salutation + message);
        };


        // 用括号
        GreetingService greetService2 = (message) ->
                System.out.println("Hello " + message);

        greetService1.sayMessage("Runoob");
        greetService2.sayMessage("Google");

        final int num = 1;
        Converter<Integer, String> s = (value) ->{
            System.out.println(String.valueOf(value + num));
            return "";
        };
        try {
            s.convert(2);
        } catch (IOException e) {
            e.printStackTrace();
        }
       // final String first = "";
        Comparator<String> comparator = (first, second) -> Integer.compare(first.length(), second.length());
        System.out.println(comparator.compare("abc","ab"));
    }

    interface MathOperation {
        int operation(int a, int b);
    }
    private int operate(int a, int b, MathOperation mathOperation){
        return mathOperation.operation(a, b);
    }
    interface GreetingService {
        void sayMessage(String message);
    }


}
