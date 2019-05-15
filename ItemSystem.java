package n0510;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;


class ItemModel extends AbstractTableModel{

	Object tableData[][]; int rows,cols;
	String[] columnName= {"상품번호","상품이름","상품가격","상품정보","원산지","이미지경로"};
	List<ItemInfo> list;
	ItemModel(CRUDprocess crud, Map map){
		list = crud.selectIteminfoCondition(map);
		cols = columnName.length; rows = list.size();
		setData();
	}
	void setData() {
		
		
	}
	@Override
	public int getColumnCount() {
		return cols;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return rows;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
public class ItemSystem extends JPanel implements ActionListener {
	// 부품들 선언함
	JButton[] btns;
	JLabel[] titles;
	JTextField[] inputs;
//	Checkbox[] madeIn;
//	CheckboxGroup group;
	JRadioButton[] madeIn;
	ButtonGroup group;
	JPanel[] panels;
	JTextArea item_desc;
	Font font;
	Panel centerPanel; // 상품정보
//	ImageSystem imageSystem; //상품 이미지

	void makePanel() {
		panels = new JPanel[6];
		for (int i = 0; i < panels.length; i++) {
			panels[i] = new JPanel();
		}
	}

	String[] titles_btn = { "삽입", "삭제", "변경", "조회", "지우기" };

	void makeButton() {
		btns = new JButton[5];
		for (int i = 0; i < btns.length; i++) {
			btns[i] = new JButton(titles_btn[i]);
			btns[i].setFont(font);
			btns[i].addActionListener(this);
		}
	}

	void makeTextField() {
		inputs = new JTextField[3];// 3배열 생성
		for (int i = 0; i < inputs.length; i++) {
			inputs[i] = new JTextField(15); // 길이 15텍필생성
		}
	}

	String[] titles_txt = { "상품번호", "상품이름", "상품가격", "상품설명", "상품원산지" }; // 레이블에 들어갈명칭들

	void makeLabel() {
		titles = new JLabel[5];// 5배열생성
		for (int i = 0; i < titles.length; i++) {
			titles[i] = new JLabel(titles_txt[i]);// 레이블생성
			titles[i].setFont(font);// 폰트 적용
		}

	}

	void makeRadioButton() { // 원산지용 라디오 버튼을 생성하는 메서드
		group = new ButtonGroup();// 라디오버튼을 위한 그룹생성
		madeIn = new JRadioButton[2]; // 2배열생성
		madeIn[0] = new JRadioButton("한국산", true); // 기본선택
		madeIn[1] = new JRadioButton("외국산", false);
		group.add(madeIn[0]);
		group.add(madeIn[1]);
		madeIn[0].setFont(font);
		madeIn[1].setFont(font);
	}

	public ItemSystem() {
		font = new Font("휴먼굴림체", font.BOLD, 20);
		this.setLayout(new BorderLayout());
//		this.setLayout(new GridLayout(6, 1));
		centerPanel = new Panel();
		centerPanel.setLayout(new GridLayout(5, 1));
		makePanel();
		makeButton();
		makeTextField();
		makeLabel();
		makeRadioButton();
		item_desc = new JTextArea(2, 15); // 15행 2열로 텍스트생성
		panels[0].add(titles[0]);
		panels[0].add(inputs[0]);
		panels[1].add(titles[1]);
		panels[1].add(inputs[1]);
		panels[2].add(titles[2]);
		panels[2].add(inputs[2]);
		panels[3].add(titles[3]);
		panels[3].add(item_desc);
		panels[4].add(titles[4]);
		panels[4].add(madeIn[0]);
		panels[4].add(madeIn[1]);
		panels[5].add(btns[0]);
		panels[5].add(btns[1]);
		panels[5].add(btns[2]);
		panels[5].add(btns[3]);
		panels[5].add(btns[4]);
		for (int i = 0; i < panels.length - 1; i++) {
			centerPanel.add(panels[i]);
		}
//		imageSystem = new ImageSystem(ts);
		this.add("Center", centerPanel);
		// 화면 가운데 centerPanel을 붙인다.
//		this.add("East",imageSystem);
		// 화면 오른쪽에 imageSystem을 붙인다.
		this.add("South", panels[5]);
		// 화면 아래에 panel[5]를 붙인다.(버튼을 가지고있는 패널);
//		this.setSize(500,400);
		this.setBackground(new Color(255, 204, 255));
//		this.addWindowListener(new LoginSystemExit());
//		this.setVisible(true);
	}

	public static void main(String[] args) {
//		new ItemSystem("아이템 정보 초기버전");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();
		if (o == btns[0]) { // 삽입
//			String code = inputs[0].getText();
//			if (code.equals("")) {
//				// 상품번호는 반드시 입력해야합니다. 라는 다이얼로그 출력
//				JOptionPane.showMessageDialog(ts, "상품번호는 반드시 입력해야 합니다.");
//			} else {
//				// 해당상품번호로 이미 존재하는지 검사
//				CRUDprocess crud = new CRUDprocess();
//				ItemInfo itemInfo = crud.selectItemCode(code);
//				if (itemInfo == null) {
//					// 삽입작업을 진행
//					String name = inputs[1].getText(); // 상품이름
//					String price = inputs[2].getText();// 상품가격
//					String info = item_desc.getText();// 상품설명
//					String origin = ""; // 원산지용 변수
//					if (madeIn[0].getState()) {// 한국산이 선택된 경우
//						origin = "한국산";
//					} else if (madeIn[1].getState()) {// 외국산이 선택된경우
//						origin = "외국산";
//					}
//					String image= SaveActionListener.path; //이미지 경로
//					ItemInfo item_info = new ItemInfo();
//					item_info.setCode(code);
//					item_info.setName(name);
//					item_info.setPrice(Integer.parseInt(price));
//					// 가격은 모델에서 정수이므로, 문자열을 정수로 변환해서 삽입한다.
//					item_info.setInfo(info);
//					item_info.setOrigin(origin);
//					item_info.setImage(image);
//					int result = crud.insertItemInfo(item_info);
//					// DB에 삽입
//					if (result > 0) {
//						JOptionPane.showMessageDialog(ts, "상품정보가 등록되었습니다.");
//					} else {
//						JOptionPane.showMessageDialog(ts, "상품정보 등록 중 문제가 발생했습니다.");
//					}
//				} else {
//					JOptionPane.showMessageDialog(ts, "이미 존재하는 상품번호 입니다.");
//					// 해당 상품번호가 이미 있으므로 경고 다이얼로그 출력
//				}
//
//			}
		} else if (o == btns[1]) { // 삭제
//			//버튼이 2개있는 다이얼로그를 출력한다.
//			int result =JOptionPane.showConfirmDialog(ts, "정말로 삭제하시겠습니까?","작업확인",JOptionPane.YES_NO_OPTION);
//			//yes와 no 버튼이 있는 다이얼로그 생성
//			if(result == JOptionPane.YES_OPTION) {
//				String code = inputs[0].getText();//상품번호 저장
//			if(code.equals("")) {//상품번호가 없는 경우 
//				JOptionPane.showMessageDialog(ts, "상품번호가 없어서 작업을 실행할 수 없습니다");
//			}else {//DB에서 해당 상품번호를 삭제
//				CRUDprocess crud = new CRUDprocess();
//				int r = crud.deleteItemCode(code);
//						if(r > 0) {
//							JOptionPane.showMessageDialog(ts, "해당 상품정보가 삭제되었습니다.");
//						}else {
//							JOptionPane.showMessageDialog(ts, "상품정보 삭제 중 문제가 발생했습니다.");
//						}
//			}
//				
//			}else if(result == JOptionPane.NO_OPTION) {
//				
//			
//			}
//
		} else if (o == btns[2]) { // 변경 누른경우
//			int result =JOptionPane.showConfirmDialog(ts, "정말로 변경하시겠습니까?","작업확인",JOptionPane.YES_NO_OPTION);
//					if(result == JOptionPane.YES_OPTION) {
//						String code = inputs[0].getText(); //상품번호 저장
//						if(code.equals("")) { //상품번호가 입력되지 않은 경우
//							JOptionPane.showMessageDialog(ts, "상품번호가 없어서 작업을 진행할 수 없습니다.");
//						}else {
//							CRUDprocess crud = new CRUDprocess();
//							String name = inputs[1].getText();
//							String price = inputs[2].getText();
//							String info=item_desc.getText();
//							String origin="";
//							if(madeIn[0].getState()==true) {
//								origin = "한국산";
//							}else if(madeIn[1].getState()==true) {
//								origin = "외국산";
//								
//							}
//							ItemInfo item_info = new ItemInfo();
//							item_info.setCode(code);
//							item_info.setName(name);
//							item_info.setPrice(Integer.parseInt(price));
//							item_info.setInfo(info);
//							item_info.setOrigin(origin);
//							int r= crud.updateItemInfo(item_info); //DB 업데이트
//							if(r > 0) {
//								JOptionPane.showMessageDialog(ts, "상품정보가 변경되었습니다.");
//							}else {
//								JOptionPane.showMessageDialog(ts, "상품정보 변경 중 문제가 발생했습니다.");
//							}
//						}
//					}else if(result == JOptionPane.NO_OPTION) {
//						
//					}
//
		} else if (o == btns[3]) { // 조회버튼 누른경우
			// 상품이름 가격 원산지가 입력되었는지 찾는다.
			HashMap<String, Object> map = new HashMap<String, Object>();
			if (!inputs[0].getText().equals("")) {
				map.put("code", inputs[0].getText());
			} // 상품번호가 입력된경우
			if (!inputs[1].getText().equals("")) {
				map.put("name", inputs[1].getText());
			} // 상품이름이 입력된 경우
			if (!inputs[2].getText().equals("")) {
				map.put("price", inputs[2].getText());
			}
			if (!madeIn[0].isSelected()) {
				map.put("origin", "한국산");
			} else if (madeIn[1].isSelected()) {
				map.put("origin", "외국산");
			}
			CRUDprocess crud = new CRUDprocess();
			List<ItemInfo> result = crud.selectIteminfoCondition(map);
			System.out.println("검색된 건수 :" + result.size());

		} else if (o == btns[4]) { // 지우기 버튼을 누른경우
			for (int i = 0; i < inputs.length; i++) {
				inputs[i].setText(" ");
				inputs[i].setText("");

			} // 세 개의 텍스트 필드에 입력된 값을 지움
			item_desc.setText(" ");// 상품정보를 지움
			item_desc.setText("");
		}
	}
}
