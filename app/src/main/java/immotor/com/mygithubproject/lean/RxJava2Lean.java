package immotor.com.mygithubproject.lean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class RxJava2Lean {
    /**
     * 观察者（Observer）
     * 被观察者（Observable）
     * 订阅（subscribe）
     *
     * 被观察者发送数据给观察者
     */
    public static void main(String args[]) {
        //以下是部分创建创建操作符
        /*create();
        just();
        fromArray();
        fromCallable();
        fromIterable();
        timer();*/

        //以下是部分转换操作符
      /*  map();
        flatMap();
        scan();*/
      
      //以下是部分功能操作符
        subscribeOn();
    }

    /**
     * subscribeOn()指定被观察者的线程，要注意的时，如果多次调用此方法，只有第一次有效
     * observeOn()指定观察者的线程，每指定一次就会生效一次
     */
    static void subscribeOn(){
        TAG = "subscribeOn";
        Observable.create(new ObservableOnSubscribe < Integer > () {
            @Override
            public void subscribe(ObservableEmitter < Integer > e) throws Exception {
                Log.d(TAG, " currentThread name: " + Thread.currentThread().getName());
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        })
                //.subscribeOn(Schedulers.newThread())
               // .observeOn(Schedulers.newThread())
                .subscribeWith(new Observer < Integer > () {
                    @Override
                    public void onSubscribe(Disposable d) {

                        Log.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext Thread name " + Thread.currentThread().getName());
                        Log.d(TAG, "onNext " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError" + e.getMessage()) ;
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });
    }

    /**
     * scan
     * 观察者收到的结果返给scan
     * 将数据以一定的逻辑聚合起来
     */
    static void scan(){
        TAG = "scan";
        Observable.just(1, 2, 3, 4, 5)
                .scan(new BiFunction< Integer, Integer, Integer >() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                       /* Log.d(TAG, "apply ");
                        Log.d(TAG, "integer " + integer);
                        Log.d(TAG, "integer2 " + integer2);*/
                        return integer + integer2;
                    }
                })
                .subscribe(new Consumer < Integer > () {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "accept " + integer);
                    }
                });
    }
    /**
     * flatMap
     * 这个方法可以将事件序列中的元素进行整合加工，返回一个新的被观察者
     * flatMap() 其实与 map() 类似，但是 flatMap() 返回的是一个 Observerable
     * concatMap()
     * concatMap() 和 flatMap() 基本上是一样的，只不过 concatMap() 转发出来的事件是有序的，而 flatMap() 是无序的
     */
    static void flatMap(){
        TAG = "flatMap";
        List<String> actionList = new ArrayList<>();
        actionList.add("穿衣服");
        actionList.add("叠被子");

        Plan plan = new Plan();
        plan.setActionList(actionList);

        List<Plan> planList = new ArrayList<>();
        planList.add(plan);

        Person person = new Person();
        person.setPlanList(planList);

        List<Person> personList = new ArrayList<>();
        personList.add(person);
        personList.add(person);

        Observable.fromIterable(personList)
                .map(new Function < Person, List < Plan >> () {
                    @Override
                    public List < Plan > apply(Person person) {
                        return person.getPlanList();
                    }
                })
                .subscribe(new Observer < List < Plan >> () {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(List < Plan > plans) {
                        Log.e(TAG, "onNext");
                        for (Plan plan: plans) {
                            List < String > planActionList = plan.getActionList();
                            for (String action: planActionList) {
                                Log.d(TAG, "action " + action);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete");
                    }
                });

        Observable.fromIterable(personList)
                .flatMap(person1 -> {
                    return Observable.fromIterable(person.getPlanList());
                })
                .flatMap(plan1 -> {
                    return Observable.fromIterable(plan.getActionList());
                })
                .subscribe(s ->Log.d(TAG, "=action: " + s) );
    }
    static class Person {
        private List<Plan> planList = new ArrayList<>();
        public Person() {
        }
        public List<Plan> getPlanList() {
            return planList;
        }
        public void setPlanList(List<Plan> planList) {
            this.planList = planList;
        }

    }
    static class Plan {
        private List<String> actionList = new ArrayList<>();
        public Plan() {
        }
        public List<String> getActionList() {
            return actionList;
        }
        public void setActionList(List<String> actionList) {
            this.actionList = actionList;
        }
    }
    /**
     *map
     * 可以将被观察者发送的数据类型转变成其他的类型
     * 以下代码将 Integer 类型的数据转换成 String
     */
    static void map(){
        TAG = "map";
        Observable.just(1, 2, 3)
                .map(new Function< Integer, String >() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return "I'm " + integer;
                    }
                })
                .subscribe(new Observer < String > () {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, "onNext " + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        Observable.just(1, 2, 3)
                .map(integer -> "I'm " + integer)
                .subscribe(s -> {
                    Log.e(TAG, "accept " + s);
                });
    }

    /**
     * timer
     * 当到指定时间后就会发送一个 0L 的值给观察者。
     */
    static void timer(){
        TAG = "timer";
        Observable.timer(2, TimeUnit.MILLISECONDS)
                .subscribe(new Observer < Long > () {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.d(TAG, "onNext " + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete ");
                    }
                });
    }
    /**
     * fromIterable
     *直接发送一个 List 集合数据给观察者
     */
    static void fromIterable(){
        TAG = "fromIterable";
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        Observable.fromIterable(list)
                .subscribe(integer ->{
                    Log.d(TAG, "accept " + integer);
                });

       /* Observable.fromIterable(list)
                .subscribe(new Observer < Integer > () {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete ");
                    }
                });*/
    }

    /**
     * fromCallable
     * 这里的 Callable 是 java.util.concurrent 中的 Callable，Callable 和 Runnable 的用法基本一致，只是它会返回一个结果值，这个结果值就是发给观察者的。
     */
    static void fromCallable(){
        TAG = "fromCallable";
        Observable.fromCallable(new Callable< Integer >() {

            @Override
            public Integer call() throws Exception {
                return 1;
            }
        })/*.subscribe(new Observer < Integer > () {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete ");
            }
        });*/
               /* .subscribe(new Consumer< Integer >() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "accept " + integer);
                    }
                });*/
               .subscribe(integer ->{
                   Log.d(TAG, "accept " + integer);
               });
    }

    /**
     * fromArray
     * 和 just() 类似，只不过 fromArray 可以传入多于10个的变量，并且可以传入一个数组
     */
    static void fromArray(){
        TAG = "fromArray";
        Integer array[] = {1, 2, 3, 4,5};
        Observable.fromArray(array)
                .subscribe(new Observer < Integer > () {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete ");
                    }
                });
    }

    /**
     * just
     * 创建一个被观察者，并发送事件，发送的事件不可以超过10个以上
     */
    static void just(){
        TAG = "just";
        Observable.just(1, 2, 3)
                .subscribe(new Observer < Integer > () {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete ");
                    }
                });

    }
    static void create(){
        TAG = "create";
        //创建观察者
        Observer observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError" + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        };
        //创建被观察者
        Observable observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            //ObservableEmitter 发射器，向观察者发送事件
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.d(TAG, "currentThread name: " + Thread.currentThread().getName());
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        });
        //订阅, 被观察者发送事件给观察者
        observable.subscribe(observer);
    }
    static String TAG = "";
    static class Log{
        public static void d(String tag,String msg){
            System.out.println(tag + "  " + msg);
        }

        public static void e(String tag,String msg){
            System.out.println(tag + "  " + msg);
        }
    }

}
