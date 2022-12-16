import java.util.*;
  
class MultiThreadServer {
  public static void main(String args[]) {
    int n=0;
    ServerObservable so = new ServerObservable();
    CommServer cs = new CommServer(10030);
    new ServerThread(cs,so,++n).start();
    while (true) {
      cs = new CommServer(cs);
      new ServerThread(cs,so,++n).start();
    }
  }
}
 
class ServerObservable extends Observable {
  private String message=null;
  public void sendMsg(String msg){
    message=msg;
    setChanged();
    notifyObservers();
  }
  public String getMsg(){
    return message;
  }
}
      
class ServerThread extends Thread implements Observer{
  CommServer cs;
  ServerObservable so;
  int clientNumber;
   
  public ServerThread(CommServer cs,ServerObservable so,int n) {
    this.cs = cs;
    this.so = so;
    this.clientNumber = n;
    so.addObserver(this); 
    so.sendMsg("[Client "+clientNumber+" has been joined !]");
  }

  public void update(Observable o,Object arg) {
    String msg = so.getMsg();
    cs.send(msg);
  }   
 
  public void run() {
    String msg;
    while((msg=cs.recv())!=null){
      so.sendMsg("[Client "+clientNumber+"] "+msg);
    }
  }
}
