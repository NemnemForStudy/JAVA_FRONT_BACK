import { useState } from "react";
//? Box 컴포넌트 : mui에서 공간을 할당하는 모든 태그를 포함
//? Grid 컴포넌트 : mui에서 공간을 12등분해 가로 사이즈에 따라 반응형 웹 개발 지원
//? Typograph 컴포넌트 : mui에서 글자를 출력하는 모든 태그를 포함
import {Box, Card, Grid, Typography,} from "@mui/material";
import ContentPasteTwoToneIcon from "@mui/icons-material/ContentPaste";
import LoginCardView from "./LoginCardView";
import SignUpCardView from "./SingUpCardView";

//# 컴포너트 return 안에서 논리연산자, 삼항연산자를 조건문처럼 사용할 때
//? 논리연산자 (&&) : if문만 쓸 때
//? 삼항연산자 (조건 ? 참 : 거짓) : if - else / if - else if - else 쓸 때 

export default function AuthenthicationView() {
  
  // 2가지만 존재하는 녀석이기 때문에 boolean선언
  const [loginView, setLoginView] = useState<boolean>(true);

  return (
    <Box sx={{ pr: "120px", pl: "120px" }}>
      <Grid container spacing={2}>
        <Grid item lg={7} sm={12}>
          <Box
            sx={{
              display: "flex",
              height: "100%",
              flexDirection: "column",
              justifyContent: "center",
              alignItems: "center",
            }}>
            <ContentPasteTwoToneIcon sx={{ fontSize: 40 }} />
            <Typography variant="h4">환영합니다.</Typography>
            <Typography variant="h4">Nemnem Board입니다.</Typography>
          </Box>
        </Grid>
        <Grid item lg={5} sm={12}>
          <Card sx={{ height: '630px', mt: '100px', mb: '80px', pt: '50px', pb: '30px', pl: '50px', pr: '50px' }}>
            {/* 태그를 찍어주려면 소괄호 찍어줘야함. <></>는 아무 의미 없는 거 */}
            { loginView ? (<LoginCardView setLoginView={setLoginView} />) : (<SignUpCardView setLoginView={setLoginView}/>)}
          </Card>
        </Grid>
      </Grid>
    </Box>
  );
}
