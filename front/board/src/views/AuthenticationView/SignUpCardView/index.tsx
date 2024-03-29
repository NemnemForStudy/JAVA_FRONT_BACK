import { ChangeEvent, Dispatch, SetStateAction, useState } from "react";

import axios, { Axios, AxiosResponse } from "axios";
import {
  Box,
  Button,
  Typography,
  TextField,
  FormControl,
  InputLabel,
  Input,
  InputAdornment,
  IconButton,
  FormHelperText,
  Checkbox,
} from "@mui/material";
import VisibilityOff from "@mui/icons-material/VisibilityOff";
import Visibility from "@mui/icons-material/Visibility";
import KeyboardArrowRightIcon from '@mui/icons-material/KeyboardArrowRight';

import { useSignUpStore } from 'src/stores';
import { SignUpDto } from "src/apis/request/auth";
import  ResponseDto  from "src/apis/response";
import { SignUpResponseDto } from "src/apis/response/auth";
import { SIGN_UP_URL, VALIDATE_EMAIL_URL, VALIDATE_NICKNAME_URL, VALIDATE_TEL_NUMBER_URL } from "src/constants/api";
import CheckIcon from '@mui/icons-material/Check';
import ValidateEmailResponseDto from "src/apis/response/user/Validate-Email.response.dto";
import { ValidateEmailDto, ValidateNicknameDto, ValidateTelNumberDto } from "src/apis/request/user";
import { ValidateNicknameResponseDto, ValidateTelNumberResponseDto } from "src/apis/response/user";


//          Component          //
function FirstPage() {

  //          Hook          //
  const { email, password, passwordCheck } = useSignUpStore();
  const { setEmail, setPassword, setPasswordCheck } = useSignUpStore();
  const { emailPatternCheck, emailValidate, passwordPatternCheck, passwordValidate, telNumberPatternCheck, telNumberValidate } = useSignUpStore();
  const { setEmailPatternCheck, setEmailValidate, setPasswordPatternCheck, setPasswordValidate, setTelNumberPatternCheck, setTelNumberValidate} = useSignUpStore();
  const { signUpError } = useSignUpStore();

  const [showPassword, setShowPassword] = useState<boolean>(false);
  const [showPasswordCheck, setShowPasswordCheck] = useState<boolean>(false);

  //? /^ <- 정규식의 시작 $/ <- 정규식의 종료
  //? * <- 0개 또는 여러개가 올 수 있다.
  //? @ <- 문자 패턴 이후에 @가 와야함
  //? @이후로 문자 패턴이 올 수 있다는 것을 적어주고
  //? [-.]?[A-Za-z0-9] <- [-.] 또는 [A-Za-z0-9]가 올 수 있다.
  //? \. <- .이 오도록 함.

  //? [A-Za-z0-9]* <- 어떠한 길이만큼 올 수 있다.(길이 지정 안했으니 어떠한 길이가 와도 된다.)
  //? @가 와야하고 또 [A-Za-z0-9]이 형태가 와야함.(첫 시작은 알파벳이나 숫자)
  //? 그 다음엔 .이나 - 가 오던가 문자가 와야함.
  //? \.[A-Za-z0-9]{2, 3} <- 무조건 .을 찍고 알파벳 2자리나 3자리가 와야함.
  const emailValidator = /^[A-Za-z0-9]([-.]?[A-Za-z0-9])*@[A-Za-z0-9]([-.]?[A-Za-z0-9])*\.[A-Za-z0-9]{2,3}$/;

  //? ?=.[A-Za-z0-9] <- 필수로 [A-Za-z][0-9][!?_] 를 포함해야한다.
  const passwordValidator = /^(?=.[A-Za-z]*)(?=.[0-9]*)(?=.[!?_]*).{8,20}$/;

  //          Event Handler          //
  const onEmailChangeHandler = (event: ChangeEvent<HTMLTextAreaElement | HTMLInputElement>) => {
    const value = event.target.value;
    const isMatched = emailValidator.test(value);
    //? 패턴이 맞지 않으면 아예 되지 않게 지정
    setEmailPatternCheck(isMatched);
    setEmail(value);
  }

  const onEmailValidateButtonHandler = () => {
    if (!emailPatternCheck) return;
    const data: ValidateEmailDto = { email };

    axios.post(VALIDATE_EMAIL_URL, data)
      .then((response) => validateEmailResponseHandler(response))
      .catch((error) => validateEmailErrorHandler(error));
  }

  const onPasswordChangeHandler = (event: ChangeEvent<HTMLTextAreaElement | HTMLInputElement>) => {
    const value = event.target.value;
    const isMatched = passwordValidator.test(value);
    setPasswordPatternCheck(isMatched);
    setPassword(value);
  }

  const onPasswordCheckChangeHandler = (event: ChangeEvent<HTMLTextAreaElement | HTMLInputElement>) => {
    const value = event.target.value;
    const isMatched = password === value;
    setPasswordValidate(isMatched);
    setPasswordCheck(value);    
  }

   //          Response Handler          //
   const validateEmailResponseHandler = (response: AxiosResponse<any, any>) => {
    const { result, message, data } = response.data as ResponseDto<ValidateEmailResponseDto>;
    if (!result || !data) {
      alert(message);
      return;
    }
    setEmailValidate(data.result);
  }

  //          Error Handler          //
  const validateEmailErrorHandler = (error: any) => {
    console.log(error.message);
  }


  return (
    <Box>
      <FormControl sx={{mt: "40px"}} error={signUpError} fullWidth variant="standard">
        <InputLabel>이메일 주소*</InputLabel>
        <Input type="text" endAdornment = {
          <InputAdornment position="end">
            <IconButton onClick={() => onEmailValidateButtonHandler()} >
              <CheckIcon />
            </IconButton>
          </InputAdornment>
        }
        value={email}
        onChange={(event) => onEmailChangeHandler(event)}
        />
        { 
          emailPatternCheck === null  && emailValidate === null? (<></>):
          !emailPatternCheck ? (<FormHelperText sx={{ color: 'red' }}>이메일 형식이 맞지 않습니다.</FormHelperText>) :
          !emailValidate ? (<FormHelperText sx={{ color: 'red' }}>중복된 이메일입니다.</FormHelperText>) :
                          (<FormHelperText sx={{ color: 'green' }}>사용 가능한 이메일입니다.</FormHelperText>)
        }
      </FormControl>
      <FormControl sx={{ mt: "40px" }} error={signUpError} fullWidth variant="standard" >
        <InputLabel>비밀번호*</InputLabel>
        <Input
          type={showPassword ? "text" : "password"}
          endAdornment={
            <InputAdornment position="end">
              <IconButton onClick={() => setShowPassword(!showPassword)}>
                {showPassword ? <VisibilityOff /> : <Visibility />}
              </IconButton>
            </InputAdornment>
          }
          value={password}
          onChange={(event) => onPasswordChangeHandler(event)} 
          />
        { 
          passwordPatternCheck === false ? 
            (<FormHelperText sx={{ color: 'red'}}>{'영대문자 + 영소문자 + 숫자 + 특수문자(!?_)를 포함한 8-20자를 입력해주세요.'}</FormHelperText>) :
            (<></>)
        }

      </FormControl>
      <FormControl sx={{ mt: "40px" }} error={signUpError} fullWidth variant="standard" >
        <InputLabel>비밀번호 확인*</InputLabel>
        <Input
          type={showPasswordCheck ? "text" : "password"}
          endAdornment={
            <InputAdornment position="end">
              <IconButton
                onClick={() => setShowPasswordCheck(!showPasswordCheck)}
              >
                {showPasswordCheck ? <VisibilityOff /> : <Visibility />}
              </IconButton>
            </InputAdornment>
          }
          value={passwordCheck}
          onChange={(event) => onPasswordCheckChangeHandler(event)}
        />
        { passwordValidate === false ? 
          (<FormHelperText sx={{color: 'red'}}>비밀번호가 서로 일치하지 않습니다.</FormHelperText>) :
          (<></>)
        }
      </FormControl>
    </Box>
  );
}

//          Component          //
function SecondPage() {

  //          Hook          //
  const { nickname, telNumber, address, addressDetail } = useSignUpStore();
  const { setNickname, setTelNumber, setAddress, setAddressDetail } = useSignUpStore();
  const { nicknameValidate, telNumberValidate, telNumberPatternCheck } = useSignUpStore();
  const { setNicknameValidate, setTelNumberValidate, setTelNumberPatternCheck} = useSignUpStore();
  const { signUpError } = useSignUpStore(); 

  // const telNumberVaildator = /^[0-9]{0,13}$/;
  const telNumberVaildator = /^[0-9]{3}-[0-9]{3,4}-[0-9]{3,4}$/;

  //          Event Handler          //
  const onTelNumberHandler = (event: ChangeEvent<HTMLTextAreaElement | HTMLInputElement>) => {
    const value = event.target.value;
    const isMatched = telNumberVaildator.test(value);
    setTelNumberPatternCheck(isMatched);
    setTelNumber(value);
  }

  const onTelNumberValidateButtonHandler = () => {
    if (!telNumberPatternCheck) return;
    const data: ValidateTelNumberDto = { telNumber };

    axios.post(VALIDATE_TEL_NUMBER_URL, data)
      .then((response) => validateTelNumberResponseHandler(response))
      .catch((error) => validateTelNumberErrorHandler(error));
  }

  const onNicknameValidateButtonHandler = () => {
    if (!nickname) return;
    const data: ValidateNicknameDto = { nickname };

    axios.post(VALIDATE_NICKNAME_URL, data)
      .then((response) => validateNicknameResponseHandler(response))
      .catch((error) => validateNicknameErrorHandler(error));
  }

  //          Response Handler          //
  const validateTelNumberResponseHandler = (response: AxiosResponse<any, any>) => {
    const { result, message, data } = response.data as ResponseDto<ValidateTelNumberResponseDto>;
    if (!result || !data) {
      alert(message);
      return;
    }
    setTelNumberValidate(data.result);
  }

  const validateNicknameResponseHandler = (response: AxiosResponse<any, any>) => {
    const { result, message, data } = response.data as ResponseDto<ValidateNicknameResponseDto>;
    if (!result || !data) {
      alert(message);
      return;
    }
    setNicknameValidate(data.result);
  }

  //          Error Handler          //
  const validateTelNumberErrorHandler = (error: any) => {
    console.log(error.message);
  }

  const validateNicknameErrorHandler = (error: any) => {
    console.log(error.message);
  }
  
  return (
    <Box>
      <FormControl sx={{mt: '40px'}} error={signUpError} fullWidth variant="standard">
        <InputLabel>닉네임*</InputLabel>
        <Input type="text" endAdornment={
          <InputAdornment position="end">
            <IconButton onClick={() => onNicknameValidateButtonHandler()}>
              <CheckIcon />
            </IconButton>
          </InputAdornment>
        } 
        value={nickname}
        onChange={(event) => setNickname(event.target.value)}
        />
        {
          nicknameValidate === null ? (<></>):
          nicknameValidate ? (<FormHelperText sx={{ color: 'green' }}>사용 가능한 닉네임입니다.</FormHelperText>) : 
                             (<FormHelperText sx={{ color: 'red' }}>사용 중인 닉네임입니다.</FormHelperText>)
        }
      </FormControl>
      <FormControl sx={{mt: '40px'}} error={signUpError} fullWidth variant="standard">
        <InputLabel>휴대폰 번호*</InputLabel>
        <Input type="text" endAdornment={
          <InputAdornment position="end">
            <IconButton onClick={() => onTelNumberValidateButtonHandler()}>
              <CheckIcon />
            </IconButton>
          </InputAdornment>
        } 
        value={telNumber}
        onChange={(event) => onTelNumberHandler(event)}
        />
        {
          telNumberValidate === null &&  telNumberPatternCheck === null ? (<></>) :
          !telNumberPatternCheck ? (<FormHelperText sx={{ color: 'red' }}>전화번호 패턴이 일치하지 않습니다.</FormHelperText>) :
          telNumberValidate ? (<FormHelperText sx={{ color: 'green' }}>사용 가능한 전화번호입니다.</FormHelperText>) :
                              (<FormHelperText sx={{ color: 'red' }}>사용중인 전화번호입니다.</FormHelperText>)
        }
      </FormControl>
      <FormControl sx={{mt: '40px'}} error={signUpError} fullWidth variant="standard">
        <InputLabel>주소*</InputLabel>
        <Input type="text" endAdornment={
          <InputAdornment position="end">
            <IconButton>
              <KeyboardArrowRightIcon />
            </IconButton>
          </InputAdornment>
        } 
        value={address}
        onChange={(event) => setAddress(event.target.value)}
        />
      </FormControl>
      <TextField sx={{mt: '40px'}} error={signUpError} fullWidth label="상세 주소*" variant="standard" value={addressDetail} onChange={(event) => setAddressDetail(event.target.value)} />
      <Box sx={{ display: 'flex', alignItems: 'center', mt: '24px' }}>
        <Checkbox color="default" />
        <Typography sx={{mr: '4px', color: 'red', fontWeight: 400}}>개인정보동의</Typography>
        <Typography sx={{fontWeight: 700}}>더보기&gt;</Typography>
      </Box>
    </Box>
  );
}

interface Props {
  setLoginView: Dispatch<SetStateAction<boolean>>;
}

export default function SignUpCardView({ setLoginView }: Props) {

  //          Hook          //
  const { email, password, passwordCheck } = useSignUpStore();
  const { nickname, telNumber, address, addressDetail } = useSignUpStore();
  const { setSignUpError } = useSignUpStore();
  const { emailPatternCheck, passwordPatternCheck, telNumberPatternCheck } = useSignUpStore();
  const { emailValidate, passwordValidate, nicknameValidate, telNumberValidate } = useSignUpStore();

  const [page, setPage] = useState<number>(1);

  //          Event Handler          //
  const onNextButtonHandler = () => {
    //? 해당 문자열 변수가 빈값인지 확인
    //? 1. 해당 변수 == '';
    //? 2. 해당 변수의 길이 == 0;
    if (!email || !password || !passwordCheck) {
      setSignUpError(true);
      return;
    }
    if (!emailPatternCheck || !passwordPatternCheck) return;
    if (!passwordValidate) return;
    
    setSignUpError(false);
    setPage(2);
  };

  const onSignUpHandler = () => {
    if (!email || !password || !passwordCheck) {
      setSignUpError(true);
      setPage(1);
      return;
    }

    if (!nickname || !telNumber || !address || !addressDetail) {
      alert('모든 값을 입력하세요.');
      setSignUpError(true);
      setPage(2);
      return;
    }

    if(!emailPatternCheck || !passwordPatternCheck) {
      setPage(1);
      return;
    }


    if (!emailValidate || !passwordValidate) {
      setPage(1);
      return;
    }

    if(!telNumberPatternCheck) {
      setPage(2);
      return;
    }

    if(!nicknameValidate || !telNumberValidate){
      setPage(2);
      return;
    }

    setSignUpError(false);

    const data: SignUpDto = { email, password, nickname, telNumber, address: `${address} ${addressDetail}` };

    //? post - 어떠한 URL로 주소 요청을 할 것인가, RequestBody에 들어갈 값(JSON형태), 
    //?        authorization과 같은 헤더에 추가해야 하는 것을 config에 추가시켜 보냄
    //? then - 결과를 기다리지 않고 다음 코드 실행. 만약 에러가 발생하면
    //? catch - catch로 에러를 받을 수 있음.
    axios.post(SIGN_UP_URL, data)
    .then((response => {signUpResponseHandler(response)}))
    .catch((error) => {signUpErrorHandler(error)});

    //? 기다렸다가 작업 처리
    //? await(비동기) - 동작을 하다가 동기처리로 바꾸겠다
    //? 동기처리로 바꿔주겠다 하면 동기 함수로 바꿔줘야한다.
    // const response = await axios.post("http://localhost:4040/auth/sign-up", data);

  }

  //          Response Handler          //
  const signUpResponseHandler = (response: AxiosResponse<any, any>) => {
    const { result, message, data } = response.data as ResponseDto<SignUpResponseDto>;
      if(result) setLoginView(true);
      else alert(message);
  }

  //          Error Handler          //
  const signUpErrorHandler = (error: any) => {
    console.log(error.response.status);
  }

  return (
    <Box
      display="flex"
      sx={{
        height: "100%",
        flexDirection: "column",
        justifyContent: "space-between",
      }}
    >
      <Box>
        <Box display="flex" sx={{ justifyContent: "space-between" }}>
          <Typography variant="h5" fontWeight="900">
            회원가입
          </Typography>
          <Typography variant="h5" fontWeight="900">
            {page}/2
          </Typography>
        </Box>
        {page === 1 ? <FirstPage /> : <SecondPage />}
      </Box>
      <Box>
        {page === 1 && (
          <Button
            fullWidth
            variant="contained"
            size="large"
            sx={{ mb: "20px" }}
            onClick={onNextButtonHandler}
          >
            다음 단계
          </Button>
        )}
        {page === 2 && (
          <Button
            fullWidth
            variant="contained"
            size="large"
            sx={{ mb: "20px" }}
            onClick={onSignUpHandler}
          >
            회원가입
          </Button>
        )}
        <Typography textAlign="center">
          이미 계정이 있으신가요?
          <Typography
            component="span"
            fontWeight={900}
            onClick={() => setLoginView(true)}
          >
            {" "}
            로그인
          </Typography>
        </Typography>
      </Box>
    </Box>
  );
}
