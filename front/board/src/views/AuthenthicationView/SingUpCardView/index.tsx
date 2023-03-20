import {
  Typography,
  Box,
  TextField,
  Button,
  FormControl,
  InputLabel,
  Input,
  InputAdornment,
  IconButton,
} from "@mui/material";
import React, { Dispatch, useState } from "react";
import VisibilityOff from "@mui/icons-material/VisibilityOff";
import Visibility from "@mui/icons-material/Visibility";
import { useSignUpStore } from "src/stores";
import ArrowForwardIosIcon from '@mui/icons-material/ArrowForwardIos';

function FirstPage() {
  //? true하면 보이는게 꺼져있고, false하면 보이는게 커져있음.
  const [showPassword, setShowPassword] = useState<boolean>(true);
  const [showPasswordCheck, setShowPasswordCheck] = useState<boolean>(false);
  //? 객체를 받을때는 중괄호
  const { setEmail, setPassword, setPasswordCheck } = useSignUpStore();
  const { email, password, passwordCheck } = useSignUpStore();

  return (
    //? value={} 를 해주면 2페이지를 다녀와도 값이 사라지지 않는다.
    <Box>
      <TextField
        sx={{ mt: "40px" }}
        fullWidth
        variant="standard"
        label="이메일 주소*"
        value={email}
        onChange={(event) => setEmail(event.target.value)}
      />
      <FormControl fullWidth variant="standard" sx={{ mt: "40px" }}>
        <InputLabel htmlFor="standard-adornment-password">비밀번호*</InputLabel>
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
          onChange={(event) => setPassword(event.target.value)}
        />
      </FormControl>
      <FormControl fullWidth variant="standard" sx={{ mt: "40px" }}>
        <InputLabel htmlFor="standard-adornment-password">
          비밀번호 확인*
        </InputLabel>
        <Input
          type={showPasswordCheck ? "text" : "password"}
          endAdornment={
            <InputAdornment position="end">
              <IconButton
                onClick={() => setShowPasswordCheck(!showPasswordCheck)}>
                {showPasswordCheck ? <VisibilityOff /> : <Visibility />}
              </IconButton>
            </InputAdornment>
          }
          value={passwordCheck}
          onChange={(event) => setPasswordCheck(event.target.value)}
        />
      </FormControl>
    </Box>
  );
}

function SecondPage() {

  const { nickName, telNumber, address, addressDetail } = useSignUpStore();
  const { setNickName, setTelNumber, setAddress, setAddressDetail } = useSignUpStore();

  return (
    <Box>
      <TextField sx={{ mt: '40px' }} fullWidth label='닉네임*' variant="standard" value={nickName} onChange={ (event) => setNickName(event.target.value) } />
      <TextField sx={{ mt: '40px' }} fullWidth label="휴대폰 번호*" variant="standard" value={telNumber} onChange={ (event) => setTelNumber(event.target.value) } />
      <FormControl fullWidth variant="standard" sx={{ mt: '40px' }}>
        <InputLabel>주소*</InputLabel>
        <Input type="text" endAdornment={
          <InputAdornment position="end">
            <IconButton>
              {/* 아이콘 삽입 */}
              <ArrowForwardIosIcon />
            </IconButton>
          </InputAdornment>
        }
          value={address}
          onChange={(event) => setAddress(event.target.value)}
        />
      </FormControl>
      <TextField sx={{ mt: '40px' }} fullWidth label='상세 주소*' variant="standard" value={addressDetail} onChange={(event) => setAddressDetail(event.target.value)} />
    </Box>
  );
}

interface Props {
  setLoginView: Dispatch<React.SetStateAction<boolean>>;
}

export default function SignUpCardView({ setLoginView }: Props) {

  const [page, setPage] = useState<number>(1);
  const { email, password, passwordCheck } = useSignUpStore();
  const { nickName, telNumber, address, addressDetail} = useSignUpStore();

  //? 여기는 {} 중괄호라서 조건문이 사용 가능하다.
  //? 초기화 값을 ''(빈문자)로 해놨기 때문에 null 사용 X
  //? (typescript에서는 ''도 부정임.)
  const onNextButtonHandler = () => {
    //todo: 이메일, 비밀번호, 비밀번호 확인 검증,
    //? 해당 문자열 변수가 빈값인지 확인
    //? 1. 해당 변수 == '';
    //? 2. 해당 변수 길이 == 0;
    if (!email || !password || !passwordCheck) {
      alert('모든 값을 입력하세요');
      return;
    }

    if (password !== passwordCheck) {
      alert('비밀번호가 같은지 확인해주세요');
      return;
    }
    //todo: 검증 실패하면 false
    //todo: 검증 성공하면 페이지 변경
    setPage(2);
  };

  const onSignUpHandler = () => {
    if (!email || !password || !passwordCheck) {
      alert('모든 값을 입력하세요.');
      setPage(1);
      return;
    }
      
    if (!nickName || !telNumber || !address || !addressDetail) {
      alert('모든 값을 입력하세요.');
      setPage(2);
      return;
    }
  
    if (password !== passwordCheck) {
      alert('비밀번호가 서로 다릅니다.')
      setPage(1);
      return;
    }
    alert('회원가입을 축하드립니다.');
    const data = { email, password, nickName, telNumber, address, addressDetail }
      console.log(data);
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
        <Box display="flex" justifyContent="space-between">
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
            onClick={onNextButtonHandler}>
            다음 단계
          </Button>
        )}
        {page === 2 && (
          <Button
            fullWidth
            variant="contained"
            size="large"
            sx={{ mb: "20px" }}
            onClick={onSignUpHandler}>
            회원가입
          </Button>
        )}

        {/* 문자열은 굳이 {}가 필요없다. */}
        <Typography textAlign="center">
          이미 계정이 있으신가요?
          {/* 여기는 true를 넣어주자 */}
          <Typography
            component="span"
            fontWeight={900}
            onClick={() => setLoginView(true)}>
            로그인
          </Typography>
        </Typography>
      </Box>
    </Box>
  );
}
