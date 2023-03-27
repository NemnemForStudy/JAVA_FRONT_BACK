package com.nemnem.board.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nemnem.board.common.constant.ResponseMessage;
import com.nemnem.board.dto.request.auth.SignInDto;
import com.nemnem.board.dto.request.auth.SignUpDto;
import com.nemnem.board.dto.response.ResponseDto;
import com.nemnem.board.dto.response.auth.SignInResponseDto;
import com.nemnem.board.dto.response.auth.SignUpResponseDto;
import com.nemnem.board.entity.UserEntity;
import com.nemnem.board.provider.TokenProvider;
import com.nemnem.board.repository.UserRepository;
import com.nemnem.board.service.AuthService;

@Service
public class AuthServiceImplements implements AuthService  {

    @Autowired private TokenProvider tokenProvider;
    //? 회원가입은 UserRepository에 있다 지정
    @Autowired private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //? 존재하는 데이터라면 존재한다고 반환을 해주는 역할을 만들어주자
    public ResponseDto<SignUpResponseDto> signUp(SignUpDto dto) {

        SignUpResponseDto data = null;

        String email = dto.getEmail();
        String telNumber = dto.getTelNumber();
        //? password는 다 맞으면 암호화해서 저장해줘야함.
        String password = dto.getPassword();

        try {

            boolean hasEmail = userRepository.existsByEmail(email);
            if (hasEmail) return ResponseDto.setFailed(ResponseMessage.EXIST_EMAIL);

            boolean hasTelNumber = userRepository.existsByTelNumber(telNumber);
            if (hasTelNumber) return ResponseDto.setFailed(ResponseMessage.EXIST_TEL_NUMBER);

            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

            data = new SignUpResponseDto(true);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

    }

    public ResponseDto<SignInResponseDto> signIn(SignInDto dto) {

        SignInResponseDto data = null;

        String email = dto.getEmail();
        String password = dto.getPassword();

        UserEntity userEntity = null;

        try {

            userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return ResponseDto.setFailed(ResponseMessage.FAIL_SIGN_IN);

            //? 입력받은 패스워드 하고 DB에 암호화된 비밀번호와 비교
            //? matches(password, password) 첫번째 password가 원문 뒤가 암호화 되어있는 password
            boolean isEqualPassword = passwordEncoder.matches(password, userEntity.getPassword());
            if (!isEqualPassword) return ResponseDto.setFailed(ResponseMessage.FAIL_SIGN_IN);
            //? 위까지 넘어왔으면 성공

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        try {
            String token = tokenProvider.create(email);
            data = new SignInResponseDto(userEntity, token);
        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.FAIL_SIGN_IN);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

    }

}