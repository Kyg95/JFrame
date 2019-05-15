package n0510;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CRUDprocess {
//이 클래스의 목적 : MyBatis의 매퍼를 호출한다.
	// 무슨일을 하나? 1. mybatis환경설정을 읽는다
	// 2.위의 1의 작업으로 매퍼를 호출할 객체(sqlSession)를 생성한다.
	// 특이사항 : sqlSession은 sqlSessionFactory가 생성한다.
	// sqlSessionFactory는 sqlSessionFactoryBuiler가 생성한다.
	/////// sqlSession을 만드는 메서드/////////////////
	private SqlSession getSession() {// 다른 객체에서 쓰면안되니깐 private로 만듬
		String path = "n0510/mybatis_config.xml";// 파일 경로주소 추가해줘야함
		InputStream/* 어떤 파일을 읽느냐에 따라서 달라짐 */ is = null;// 파일의 내용을 읽을 객체
		try {
			is = Resources.getResourceAsStream(path);
		} catch (Exception e) {
			System.out.println("환경설정 오류 발생");
		}
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder(); // 빌더를 만들어야 팩토리를 쓸수있음
		SqlSessionFactory factory = builder.build(is); // 팩토리만들고 빌더생성자 부름
		SqlSession session = factory.openSession(); // 팩토리 생성자에서
		return session;
	}

	public List<ItemInfo> selectIteminfoCondition(Map<String, Object> condition) {
		SqlSession s = getSession();
		List<ItemInfo> info = null;
		try {
			info = s.selectList("loginmapper.selectIteminfoCondition",condition);
			return info;
		} finally {
			s.close();
		}
	}

	public List<ItemInfo> selectAllIteminfo() {
		SqlSession s = getSession();
		List<ItemInfo> info = null;
		try {
			info = s.selectList("loginmapper.selectAllIteminfo");
			return info;
		} finally {
			s.close();
		}
	}

	public List<Empl_info> selectAllEmpl() {
		SqlSession s = getSession();
		List<Empl_info> info = null;
		try {
			info = s.selectList("loginmapper.selectAllEmpl");
			return info;
		} finally {
			s.close();
		}
	}

	public List<Customer_info> selectAllCustomer() {
		SqlSession s = getSession();
		List<Customer_info> info = null;
		try {
			info = s.selectList("loginmapper.selectAllCustomer");
			return info;
		} finally {
			s.close();
		}
	}
	// db작성순서 mapper작성 crud소스 작성

}
//파일 네트워크,db연동,스레드 
//반드시 "예외처리"를 해야한다. try {파일처리 프로그렘, 네트워크프로그램 ,스레드} catch(exception A 모두가 상속하는것을 씀,다형성){"안내메세지출력"}
//자바에서 발생하는 모든것은 객체임