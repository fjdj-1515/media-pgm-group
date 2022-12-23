import java.io.*;

class ClientReadOnly {
    public static void main(String[] args) {
        CommClient cl = new CommClient("localhost", 10030);
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        String msg = "test", msg2;
        while (true) {
            msg2 = cl.recv();
            System.out.println(msg2);
        }
    }
}
