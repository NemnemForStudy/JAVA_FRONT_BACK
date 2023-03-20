package board;

import java.util.ArrayList;
import java.util.List;

// WAS에서 처리하는 역할
// 지금까지 배웠던 것을 사용해서
// 회원 등록
// 회원 정보 보기
// 회원 정보 수정
// 회원 삭제
// 회원 리스트

public class Main {

	// 3. User의 값들의 메모리를 저장해놓자
	// new로 만들 수 없다.
	// 이유는 인터페이스는 구현한 클래스를 사용해서 생성을 해야하기 때문이다.
	// ArrayList로 해주자
	private List<User> database = new ArrayList<User>();
	// 다 메서드로 만들어서 구현할 것임.
	// 모든 웹 어플리케이션에서의 기본 기능
	// 그 기본 기능의 이름을 CRUD라고 함.
	
	// 1. 뭘 관리할건지 정하기 -> User 정의(Class하나 만들어 주자)
	// 2. 어떻게 관리할건지 정하기
	
	// 4. 회원 등록
	// 필요한거는? - 앞에서 만든 것들 4개(외부에서 가져올 것)
	// add 함수를 사용해 추가
	// 마지막에는 true를 반환
	// 여기서 4-1.id가 0보다 크면서, 
	// 4-2. password, name, phoneNumber가 빈 값이 아니면서 
	// 4-3. null도 아니어야함.
	private boolean createUser(int id, String password, String name, String phoneNumber) {
		// 파라미터가 정상적인 값인지 검증
		if(id < 0) return false;
		if(password.isEmpty() || name.isEmpty() || phoneNumber.isEmpty()) return false;
		if(password == null || name == null || phoneNumber == null) return false;
		
		// 6. 아래에서 만든 Overlab에 관한 코드 추가
		// true면 중복이 된다. false면 중복 X
		// 아이디 중복 체크
		if(checkOverlap(id)) return false;
			
		
		// 관리하고자하는 객체를 생성
		User user = new User(id, password, name, phoneNumber);
		// 생성한 객체를 DB에 삽입한 것.
		database.add(user);
		// 삽입 결과 반환
		return true;
	}
	// 7. 회원 정보 보기
	// 특정한 어떠한 id. 유저 정보 반환
	// id값 외부에서 들고오기 -> ()안에 int id
	private User readUser(int id) {
		// 파라미터가 정상적인지 값인지 검증
		if(id < 0) return null;
		// 9. 남은 건 찾기이다. database에서 해당 id 검색하는 것을 만들어 주고 사용
		User user = selectUser(id);
		// 검색 결과 반환
		return user;
	}
	// 10. 회원 정보 수정
	// 필요한 것 - id, 요소(나열)
	// 매개변수를 3개 이상 파라미터를 받아오면 나열형태가 아닌 대부분 객체로 받아옴.
	// 이 기능을 위해 UpdataeUserDTO 클래스를 하나 만듦
	
	// 12. 새로만든 DTO클래스를 여기에 넣어주자
	// 
	private User updateUser(UpdateUserDTO dto) {
		int id = dto.getId();
		// 입력된 id 값 검증
		if(id < 0) return null;
		// DB에서 입력받은 id에 해당하는 user정보를 불러옴. -> readUser
		User user = selectUser(id);
		// 입력받은 id에 해당하는 user정보가 있는지 검증
		// 존재하지 않으니 null
		if(user == null) return null;
		// 입력받은 password가 해당 유저의 password와 같은지 검증
		String password = dto.getPassword();
		if(!password.equals(dto.getPassword())) return null;
		
		// 유저 정보 변경
		String name = dto.getName();
		String phoneNumber = dto.getPhoneNumber();
		user.setName(name);
		user.setPhoneNumber(phoneNumber);
		
		// 13번 전 바꾼이유
		// return database.get(i)로
		// 실제 주소를 반납했기 때문에 user에서 받는 건 직접 참조가 가능하다.
		// user값을 바꾸면 실제값이 바뀌게 됨.
		// 실제 주소가 오는지 복사된 값이 오는지 나중에 많이 해보면 알거라 했음.
		return user;
	}
	// 13. 회원 삭제
	private boolean deleteUser(int id) {
		// 입력된 id 값 검증
		// 반환타입이 boolean이기 때문에 false
		if(id < 0) return false;
		// 입력받은 id에 해당하는 user의 index를 가져옴
		int index = getUserIndex(id);
		// Index가 -1인지 검증
		if(index == -1) return false;
		// database에서 해당 인덱스 요소 제거
		database.remove(index);
		return true;
		
	}
	// 14. 회원 리스트
	private List<User> readUserList(){
		return database;
	}
	
	// 15. 메인 메서드
	public static void main(String[] args) {
		
		while(true) {
			// 1. 생성, 2. 읽기, 3. 수정, 4. 삭제, 5. 리스트 불러오기, 6. 종료
		}
	}
	
	// 5. 회원 아이디가 중복되는지 검사하는 메서드
	// 외부에서 받아와야 하는 id가 필요
	// 중복이 같으면 true, 다르면 false
	private boolean checkOverlap(int id) {
		for(User user: database) {
			if(user.getId() == id) return true;
		}
		return false;
	}
	
	// 8. 특정 회원 아이디를 database에서 검색하는 메서드
	private User selectUser(int id) {
		
		// 13번 전 바꿨는데 바꾼 이유를 알아보자.
		// ArrayList에 객체 배열에는 실제로 주소가 들어있다.
		// user를 반환했다 치면 A라는 주소를 주지않고 F라는 주소를 복사해서 준다.
		// 아래 같은 경우는 실제 값을 그래도 주기 때문에 A라는 애를 그대로 반납하기 때문에
		
		// for-each문에서 바꿨다.
		// ArrayList이기 때문에 length가 아닌 size()
		for(int i = 0; i < database.size(); i++) {
			// User의 getId가 존재한다면 user 자체를 반환
			if(database.get(i).getId() == id) return database.get(i);
		}
		return null;
	}
	
	// 특정 회원 아이디를 database에서 검색해서 해당 인덱스를 반환하는 메서드
	private int getUserIndex(int id) {
		// 인덱스 반환 -> for each로는 뽑아올 수 없음
		for(int i = 0; i < database.size(); i++) {
			if(database.get(i).getId() == id) return i;
		}
		// 존재하지 않는 인덱스 -1을 넣어주자
		return -1;
	}
	
}
