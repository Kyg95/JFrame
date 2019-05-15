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

class EmplModel extends AbstractTableModel {
	private Object[][] tableData;
	private int rows, cols;
	private String[] columnName = { "사번", "이름", "부서명", "성별", "주소", "입사일" };
	private List<Empl_info> list;

	EmplModel() {
		CRUDprocess crud = new CRUDprocess();
		list = crud.selectAllEmpl();
		setData();
	}

	private void setData() {
		rows = list.size();
		cols = columnName.length;
		tableData = new Object[rows][cols];// 배열 생성
		Iterator it = list.iterator();
		int r = 0;
		while (it.hasNext()) {
			Empl_info empl = (Empl_info) it.next();
			tableData[r][0] = empl.getEmp_id();// 사번을 읽음
			tableData[r][1] = empl.getEmp_name();// 이름을 읽음
			tableData[r][2] = empl.getEmp_dept();// 부서명을 읽음
			tableData[r][3] = empl.getEmp_gender();// 성별을 읽음
			tableData[r][4] = empl.getEmp_addr();// 주소를 읽음
			tableData[r][5] = empl.getEmp_hire();// 입사일을 읽음
			r++;
		}
	}

	@Override
	public int getColumnCount() {
		return cols;
	}

	@Override
	public int getRowCount() {
		return rows;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return tableData[rowIndex][columnIndex];
	}

	@Override
	public String getColumnName(int column) {
		return columnName[column];
	}

}

public class EmplInfoJtable extends JFrame implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		table.setModel(new EmplModel());
	}

	JButton select;
	JTable table;
	JScrollPane scroll;
	JPanel centerPanel, southPanel;

	public EmplInfoJtable(String str) {
		super(str);
		select = new JButton("전체 검색");
		table = new JTable();
		select.addActionListener(this);
		scroll = new JScrollPane(table);
		centerPanel = new JPanel();
		southPanel = new JPanel();
		centerPanel.add(scroll);
		southPanel.add(select);
		this.add("Center", centerPanel);
		this.add("South", southPanel);
		this.setSize(500, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new EmplInfoJtable("사원정보 전체 조회");
	}

}
