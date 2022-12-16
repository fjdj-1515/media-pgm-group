import java.io.*;

class Client {
    public static void main(String[] args) {
        CommClient cl = new CommClient("localhost", 10030);
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        String msg = "test", msg2;
        try {
            while (msg.compareTo("END") != 0) {
                msg = buf.readLine();
                cl.send(msg);
                msg2 = cl.recv();
                System.out.println(msg2);
            }
        } catch (IOException e) {
        }
    }
}
