/**
 * @author huanghudong
 * @ClassName MainTest.java
 * @Description TODO
 * @createTime 2021年04月27日 09:02:00
 */
public class MainTest {
    public static void main(String[] args) {
        Thread thread = new Thread() {
            public void run() {
                ping();
            }
        };
        thread.run();
        System.out.println("pong");
    }
    static void ping(){
        System.out.println("ping");
    }
}
