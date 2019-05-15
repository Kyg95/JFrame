package n0510;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JtableTest extends JFrame{
	JTable table;
	String[] columnName = {"학번","이름","주소","연락처"};
	String[][] data = {
			{"2001A01","홍길동","서울시 송파구","010-111-2222"},
			{"2001B01","마길동","인천시 송도구","010-8744-2222"},
			{"2001C01","김용근","인천시 미추홀구","010-8724-2567"}
	};
	JPanel panel; JScrollPane scroll;
	public JtableTest(String str) {
		super(str);
		table = new JTable(data, columnName);
		scroll = new JScrollPane(table);
		panel = new JPanel(); panel.add(scroll);
		this.add("Center",panel);
		this.setSize(450,300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new JtableTest("제이테이블");
	}

}
//제이테이블은 뷰 역활만하는것임
//보여지는것은 모델(class)객체임
//제이테이블 만들때 j테이블이 데이터를 가지고있는게아님
//행과 열의 정보를 얻어서 행과열로 테이블로 만들어주는게 다임	