import java.io.*;

class ChatServer {
   public static void main(String[] args) {
      CommServer sv = new CommServer(10010);

      BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
      String msg = "test";
      try {
         while (msg.compareTo("END") != 0) {
            msg = buf.readLine();
            sv.send("[REPLY] " + msg);

            System.out.println(msg);
         }
      } catch (IOException e) {
      }
   }
}
