// 2�ĤΥ��饤����Ȥ���³�Ǥ��륵����
// �����Υ��饤����Ȥ���å������������ξ����
// �����Ф˥�å��������ֿ����ޤ���

// �ǽ��2�ĤΥ��饤����Ȥ���³���ʤ��ȡ�
// ����åȤϻϤ���ޤ���

// 3�İʾ�Υ��饤����Ȥ��б������ơ���������ä�
// ���椫�鿷���ʥ��饤����Ȥ��ä��褦�ˤ���ˤ�
// MultiThread�����Ѥ���ɬ�פ�����ޤ���

class MultiServer{
    public static void main(String[] args){

        // 1�Ĥ�Υ����åȤ� 10030 �֤ǥ����ץ�
	CommServer cs = new CommServer(10030);
        // 2�Ĥ�Υ����åȤ�Ʊ���ݡ����ֹ�ǥ����ץ�
	// �ݡ����ֹ椬Ʊ�����ϰ����˥ݡ����ֹ�Ǥʤ��ơ�
        // 1�Ĥ��CommServer���֥������Ȥ��Ϥ���
	CommServer cs2= new CommServer(cs);
        String from,msg;
        // �¹Ԥ����ȥåפ��ʤ��褦�˥����ॢ���Ȥ�10�ߥ��ä����ꡥ
	// �������꤬�ʤ��ȡ�cs.recv() �Ǥϥǡ�������������ޤ��Ԥ�³���롥
	// �����ॢ��������򤹤�ȡ������ॢ���Ȼ��֤����Ԥäơ������ǡ���
	// ���ʤ���С�null ���֤���
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
