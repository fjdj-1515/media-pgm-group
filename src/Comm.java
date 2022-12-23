import java.net.*;
import java.io.*;

class CommServer {
  private ServerSocket serverS = null;
  private Socket clientS = null;
  private PrintWriter out = null;
  private BufferedReader in = null;
  private int port = 0;

  CommServer() {
  }

  CommServer(int port) {
    open(port);
  }

  CommServer(CommServer cs) {
    serverS = cs.getServerSocket();
    open(cs.getPortNo());
  }

  ServerSocket getServerSocket() {
    return serverS;
  }

  int getPortNo() {
    return port;
  }

  boolean open(int port) {
    this.port = port;
    try {
      if (serverS == null) {
        serverS = new ServerSocket(port);
      }
    } catch (IOException e) {
      System.err.println("ポートにアクセスできません。");
      System.exit(1);
    }
    try {
      clientS = serverS.accept();
      out = new PrintWriter(clientS.getOutputStream(), true);
      in = new BufferedReader(new InputStreamReader(clientS.getInputStream()));
    } catch (IOException e) {
      System.err.println("Acceptに失敗しました。");
      System.exit(1);
    }
    return true;
  }

  boolean send(String msg) {
    if (out == null) {
      return false;
    }
    out.println(msg);
    return true;
  }

  String recv() {
    String msg = null;
    if (in == null) {
      return null;
    }
    try {
      msg = in.readLine();
    } catch (SocketTimeoutException e) {
      // System.err.println("タイムアウトです．");
      return null;
    } catch (IOException e) {
      System.err.println("受信に失敗しました。");
      System.exit(1);
    }
    return msg;
  }

  int setTimeout(int to) {
    try {
      clientS.setSoTimeout(to);
    } catch (SocketException e) {
      System.err.println("タイムアウト時間を変更できません．");
      System.exit(1);
    }
    return to;
  }

  void close() {
    try {
      in.close();
      out.close();
      clientS.close();
      serverS.close();
    } catch (IOException e) {
      System.err.println("ソケットのクローズに失敗しました。");
      System.exit(1);
    }
    in = null;
    out = null;
    clientS = null;
    serverS = null;
  }
}

class CommClient {
  Socket clientS = null;
  BufferedReader in = null;
  PrintWriter out = null;

  CommClient() {
  }

  CommClient(String host, int port) {
    open(host, port);
  }

  boolean open(String host, int port) {
    try {
      clientS = new Socket(InetAddress.getByName(host), port);
      in = new BufferedReader(new InputStreamReader(clientS.getInputStream()));
      out = new PrintWriter(clientS.getOutputStream(), true);
    } catch (UnknownHostException e) {
      System.err.println("ホストに接続できません。");
      System.exit(1);
    } catch (IOException e) {
      System.err.println("IOコネクションを得られません。");
      System.exit(1);
    }
    return true;
  }

  boolean send(String msg) {
    if (out == null) {
      return false;
    }
    out.println(msg);
    return true;
  }

  String recv() {
    String msg = null;
    if (in == null) {
      return null;
    }
    try {
      msg = in.readLine();
    } catch (SocketTimeoutException e) {
      return null;
    } catch (IOException e) {
      System.err.println("受信に失敗しました。");
      System.exit(1);
    }
    return msg;
  }

  int setTimeout(int to) {
    try {
      clientS.setSoTimeout(to);
    } catch (SocketException e) {
      System.err.println("タイムアウト時間を変更できません．");
      System.exit(1);
    }
    return to;
  }

  void close() {
    try {
      in.close();
      out.close();
      clientS.close();
    } catch (IOException e) {
      System.err.println("ソケットのクローズに失敗しました。");
      System.exit(1);
    }
    in = null;
    out = null;
    clientS = null;
  }
}
