package com.example.board.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.dto.GetTestResponseDto;
import com.example.board.dto.PostTestRequestDTO;
import com.example.board.dto.ResponseDto;
import com.example.board.service.MainService;

//? 해당 클래스를 REST API로 사용되는 Controller로 지정할 수 있음.
//? Contoller = react의 Route와 비슷하다.
@RestController

//? 해당 클래스를 특정 Request URL 패턴에서 사용하도록 지정
@RequestMapping("apis")
public class MainController {

	//# Autowired
	//? @Component 등록이 되어 있는 클래스의 생성작업을 스프링이 알아서 처리 해준다.
	@Autowired
	private MainService mainService;
	
	//? HTTP 메서드 중 GET 방식의 요쳉에 대한 처리를 지정할 때 사용
	@GetMapping("/")
	public ResponseDto<String> getMain() {

		ResponseDto<String> result = mainService.getMain();
		return result;
	}
	
	//# PathVariable(path) : GET / DELETE 방식에서 사용할 수 있음
	//#						URL Path로 클라이언트로부터 데이터를 받아서 변수로 사용
	//? 여기에서는 중괄호
	//? 외부레이어에서 데이터를 들고 온다.
	@GetMapping("/variable/{data}")
	//? 가져올때는 파라미터로 받아와야 하니 ()안에 @PathVariable + 파라미터 써야한다.
	//? 파라미터 data는 이름이 달라도 되는데
	//! 주의) 위 {} 안에 이름과 Path()안에 이름이 같아야 한다.
	public ResponseDto<String> getVariable(@PathVariable("data") String data) {
		ResponseDto<String> result = mainService.getVariable(data);
		return result;
	}
	
	//? browser는 Get방식으로만 통신(요청)이 가능하다.
	//? HTTP 메서드 중 POST 방식의 요청에 대한 처리를 지정할 때 사용
	//? GET과 POST의 차이점은 기록이 남고 안남고의 차이이다.
	//? data 보낼 때 차이점은 get방식은 path에 무조건 붙여서 보내줘야하는데
	//? post는 url로 보낼 수 없고 body에 담아서 보내줘야한다.
	@PostMapping("/")
	public ResponseDto<String> PostMain() {
		ResponseDto<String> result = mainService.postMain();
		return result;
	}
	
	//? body에 있는 전부를 받아온다.
	//# @RequestBody: POST / PATCH 방식에서 사용할 수 있음
	//#				 클라이언트로부터 request body로 데이터를 받고자 할 때 사용할 수 있음.
	@PostMapping("/requestBody")
	public ResponseDto<String> postRequestBody(@RequestBody String data) {
		ResponseDto<String> result = mainService.postRequestBody(data);
		return result;
	}
	
	//? HTTP 메서드 중 PATCH 방식의 요청에 대한 처리를 지정할 때 사용
	@PatchMapping("/")
	public ResponseDto<String> patchMain() {

		ResponseDto<String> result = mainService.patchMain();
		return result; 
	}
	
	//? HTTP 메서드 중 Delete 방식의 요청에 대한 처리를 지정할 때 사용
	@DeleteMapping("/")
	public ResponseDto<String> deleteMain() {

		ResponseDto<String> result = mainService.deleteMain();
		return result; 
	}
	
	//? 전달해줄 게 없으니 requestbody뒤에 () 안붙임.
	//? Request body or response body로 객체를 담을 때는 Dto를 사용해서
	//? 전송 or 수신
	@PostMapping("/test")
	public ResponseDto<String> postTest(@Valid @RequestBody PostTestRequestDTO requestBody) {
		
		ResponseDto<String> result = mainService.postTest(requestBody);
		return result;
	}
	
	//? 내보낼 적에 기본 타입이 아닌 오브젝트를 보내보자
	@GetMapping("/test")
	public ResponseDto<GetTestResponseDto> getTest() {

		ResponseDto<GetTestResponseDto> result = mainService.getTest();
		return result;
	}
	
}
