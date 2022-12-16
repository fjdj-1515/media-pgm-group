// 2つのクライアントを接続できるサーバ
// 片方のクライアントがメッセージを送ると両方の
// サーバにメッセージを返信します．

// 最初に2つのクライアントが接続しないと，
// チャットは始められません．

// 3つ以上のクライアントに対応させて，しかも会話の
// 途中から新たなクライアントが加わるようにするには
// MultiThreadを利用する必要があります．

class MultiServer{
    public static void main(String[] args){

        // 1つめのソケットは 10030 番でオープン．
	CommServer cs = new CommServer(10030);
        // 2つめのソケットも同じポート番号でオープン．
	// ポート番号が同じ場合は引数にポート番号でなくて，
        // 1つめのCommServerオブジェクトを渡す．
	CommServer cs2= new CommServer(cs);
        String from,msg;
        // 実行がストップしないようにタイムアウトを10ミリ秒に設定．
	// この設定がないと，cs.recv() ではデータ受信があるまで待ち続ける．
	// タイムアウト設定をすると，タイムアウト時間だけ待って，受信データ
	// がなければ，null を返す．
        cs.setTimeout(10);
        cs2.setTimeout(10);

        while(true){
	  do{
  	    if ((msg=cs.recv())!=null)  { from="[Cilent1] "; break; }
  	    if ((msg=cs2.recv())!=null) { from="[Cilent2] "; break; }
  	  }while(true);
	  if (msg.equals("END")) break;
          cs.send(from+msg);
	  cs2.send(from+msg);
        }
        cs.close();
        cs2.close();
    }
}
