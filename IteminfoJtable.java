package n0510;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

class ItemInfoModel extends AbstractTableModel{
	private Object[][] tableData; int cols,rows;
	private String[] columnName= {"상품번호","상품이름","상품가격",
			"상품정보","원산지","상품이미지경로"};
	private List<ItemInfo> list;
	
	ItemInfoModel(){
		CRUDprocess crud = new CRUDprocess();
		list = crud.selectAllIteminfo();
		setData();
	}
	void setData() {//DB의 검색 결과를 tableData 배열(2차원)에 넣는메서드
		Iterator it = list.iterator();
		rows = list.size();//검색된 데이터 건수를 행의 갯수로 사용
		cols = columnName.length;//열 제목의 갯수를 열의 갯수로 사용
		tableData = new Object[rows][cols];//2차원 배열 생성
		int r = 0;
		while(it.hasNext()) {
			ItemInfo item = (ItemInfo)it.next();
			tableData[r][0] = item.getCode();//상품 번호
			tableData[r][1] = item.getName();//상품 이름
			tableData[r][2] = item.getPrice();//상품 가격
			tableData[r][3] = item.getInfo();//상품설명
			tableData[r][4] = item.getOrigin();//원산지
			tableData[r][5] = item.getImage();//이미지 경로 및 이름
			r++;
		}
	}
	
	@Override
	public String getColumnName(int arg0) {
		return columnName[arg0];
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
	
}

public class ItemInfoJtable extends JFrame 
	implements ActionListener, MouseListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		table.setModel(new ItemInfoModel());
	}
	public JButton select; public JTable table;
	public JScrollPane scroll; public JPanel panel,btnPanel;
	
	public ItemInfoJtable(String str,ItemSystem is) {
		super(str);
		this.is = is;
		select = new JButton("상품정보 전체조회");
		select.addActionListener(this);
		table = new JTable(); table.addMouseListener(this);
		scroll = new JScrollPane(table);
		panel = new JPanel(); 
		panel.add(is); panel.add(scroll); 
		this.add("Center",panel); btnPanel = new JPanel();
		btnPanel.add(select); this.add("South",btnPanel);
		this.setSize(1000, 400); this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new ItemInfoJtable("상품정보 전체조회 ver1.0", new ItemSystem());
	}
	ItemSystem is;
	@Override
	public void mouseClicked(MouseEvent arg0) {
		//선택한 행의 전체정보를 불러온다.
		int selectedRow = table.getSelectedRow();
		int columnCount = table.getColumnCount();//열의 갯수 저장
		for(int i =0; i<6; i++) {
			is.inputs[0].setText(table.getValueAt(selectedRow, 0)+"");//상품번호
			is.inputs[1].setText(table.getValueAt(selectedRow, 1)+"");//상품이름
			is.inputs[2].setText(table.getValueAt(selectedRow, 2)+"");//상품가격
			is.item_desc.setText(table.getValueAt(selectedRow, 3)+"");//상품설명
			String str = table.getValueAt(selectedRow, 4)+""; //원산지
			if(str.equals("한국산")) {
				is.madeIn[0].setSelected(true);
				//첫번째 라디오버튼의 상태를 선택으로 설정
			}else {
				is.madeIn[1].setSelected(true);
				//두번쨰 라디오 버튼의 상태를 선택으로 설정
			}
			
		}
		//선택한 행과 열의 데이터를 ItemSystem으로 출력한다.
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
}
