import { Dispatch, useState } from "react";
import {Box,Typography,TextField,FormControl,InputLabel,InputAdornment,IconButton,Input,Button} from "@mui/material";
import VisibilityOff from "@mui/icons-material/VisibilityOff";
import Visibility from "@mui/icons-material/Visibility";

interface Props {
  setLoginView: Dispatch<React.SetStateAction<boolean>>
}

//? {} : Props -> 비할당 구조화, 파괴해버리고 바로 가져다 쓰겠다는 의미
export default function LoginCardView({ setLoginView }: Props) {

  const [showPassword, setShowPassword] = useState<boolean>(false);

  return (
    <Box
      display="flex"
      sx={{
        height: "100%",
        flexDirection: "column",
        justifyContent: "space-between",
      }}>
      {/* //? 인풋 공간 */}
      <Box>
        <Typography variant="h5" fontWeight="900">
          로그인
        </Typography>
        {/* TextField로 값을 가져옴 */}
        <TextField
          sx={{ mt: "40px" }}
          fullWidth
          label="이메일 주소"
          variant="standard"
        />

        <FormControl fullWidth variant="standard" sx={{ mt: "40px" }}>
          <InputLabel htmlFor="standard-adornment-password">
            비밀번호
          </InputLabel>
          <Input
            type={showPassword ? "text" : "password"}
            endAdornment={
              <InputAdornment position="end">
                <IconButton
                  //! !로 showPassword의 현재 상태의 역이 저장된다.
                  onClick={() => setShowPassword(!showPassword)}>
                  {showPassword ? <VisibilityOff /> : <Visibility />}
                </IconButton>
              </InputAdornment>
            }
          />
        </FormControl>
      </Box>
      {/* //? 버튼 공간 */}
      <Box>
        <Button sx={{ mb: "20px" }} fullWidth variant="contained" size="large">
          로그인
        </Button>
        <Typography textAlign={"center"}>신규 사용자이신가요?
          {/* onClick={()=>}에서 {}안에는 함수를 쓰면 된다. 
          이렇게 작성하는 이유는 onClick자리에 오는게 함수인데 중괄호 안에는 함수를 쓸 수 없다.
          Ts는 함수를 변수처럼 쓸 수 있다.*/}
          {/* setLoginView가 true로 했기 때문에 false로 넣어주면 된다.  */}
          <Typography component="span" fontWeight={900} onClick={()=>setLoginView(false)}> 회원가입</Typography></Typography>
      </Box>
    </Box>
  );
}
