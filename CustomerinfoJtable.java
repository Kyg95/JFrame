package n0510;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

class CustomerModel extends AbstractTableModel {
//전부다 Jtable을 누를때 동작함

	private Object[][] tableData; // DB조회 결과가 저장될 배열
	private int cols, rows;// 열의 갯수와 행의 갯수가 저장될 변수
	private String[] columnName = { "고객번호", "고객이름", "고객주소", "가입일" };
	private List<Customer_info> list; // set ,map은 안맞음
	// customer_info는 이 정보만 들어갈수있게 선언해줌 제너릭
	// 컬렉션 프레임워크의 리스트 객체 선언

	CustomerModel(){
		CRUDprocess crud = new CRUDprocess();
		list = crud.selectAllCustomer();//DB조회 결과가 list에 저장됨
		//list에 있는 조회결과를 tableData인 2차원 배열에 넣는다.
		setData();
	}

	private void setData() {
		// list에 있는 조회결과를 2차원 배열(tableData)에 넣는다.
		// 컬렉션 프레임워크에 저장된 데이터를 검색하는 방법 -> Iterator
		Iterator it = list.iterator();
		rows = list.size();// 데이터의 갯수를 rows에 저장(행의 갯수)
		cols = columnName.length;// 제목의 갯수를 cols저장(열의 갯수)
		tableData = new Object[rows][cols]; //배열생성
		int r = 0;
		while (it.hasNext()) {
			Customer_info cs = (Customer_info) it.next();// 조회결과를 가져옴
			tableData[r][0] = cs.getCust_id();
			tableData[r][1] = cs.getCust_name();
			tableData[r][2] = cs.getCust_addr();
			tableData[r][3] = cs.getCust_reg_date();
			r++;
		}

	}

//여기부턴 전부 콜백메서드
	@Override

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return cols;
		// DB조회 결과의 열의 갯수를 리턴
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return rows;
		// DB조회 결과의 행의 갯수를 리턴
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return tableData[arg0][arg1];
		// 내가 실행을 누르면 실행됨
	}

	@Override
	public String getColumnName(int arg0) {
		return columnName[arg0];
	}

}

//메퍼는 조회결과를 클래스에 담아줌 
//한번에 담아주기위해서 리스트에 담도록 작성함
public class CustomerinfoJtable extends JFrame implements ActionListener {
	JButton select;
	JTable table;
	JPanel centerPanel, southPanel;
	JScrollPane scroll;

	public CustomerinfoJtable(String str) {
		super(str);
		select = new JButton("전체조회");
		select.addActionListener(this);
		table = new JTable();
		centerPanel = new JPanel();
		southPanel = new JPanel();
		scroll = new JScrollPane(table);
		centerPanel.add(scroll);
		southPanel.add(select);
		this.add("Center", scroll);
		this.add("South", southPanel);
		this.setSize(500, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		new CustomerinfoJtable("확인점검");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		table.setModel(new CustomerModel());
		// 테이블이 사용하는 모델을 customermodel로 설정한다.
	}

}
