import { Box, Divider, Fab, IconButton, Input } from '@mui/material';
import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { BOARD_LIST } from 'src/mock';
import PhotoCameraBackIcon from '@mui/icons-material/PhotoCameraBack';
import EditIcon from '@mui/icons-material/Edit';
import { useUserStore } from 'src/stores';

export default function BoardUpdateView() {

  const [boardTitle, setBoardTitle] = useState<string>('');
  const [boardContent, setBoardContent] = useState<string>('');
  const {boardNumber} = useParams();
  const navigator = useNavigate();
  const {user} = useUserStore();

  const onUpdateHandler = () => {
    //? 제목 및 내용 검증 (값이 존재하는지)
    if(!boardTitle.trim() || !boardContent.trim()){
        alert('모든 내용을 입력해주세요');
        return;
    }
    //? 업데이트 기능 수행
    //? 실제로는 DB에서 작업해줘야한다.
    navigator('/myPage');
}

  useEffect(() => {
    //? item.boardNumber === boardNumber는 타입이 다르기 때문에
    //? boardNumber가 존재할때만 작업을 하도록 만들어주자
    //? undefined일때 main화면으로 보내는거임.
    //^ 정상적이지 않은 경로로 접근을 시도했을 적에 main화면으로 돌려보냄
    if(!boardNumber){
      //? '/'는 main 화면으로 설정했었다.
      navigator('/');
      return;
    }

    //? boardNumber를 이제 숫자로 형변환을 시켜주자 parseInt
    //^ pathValuable로 전달받은 boardNumber에 해당하는 board 데이터 검색
    //^ 여기서 pathValuable는 / 이다.
    const board = BOARD_LIST.find((item) => item.boardNumber === parseInt(boardNumber));

    //? 한번 더 검증
    //? 뭐든지 const board = BOARD_LIST.find((item) => item.boardNumber === parseInt(boardNumber)); 를 받았으면
    //? const board = BOARD_LIST.find((item) => item.boardNumber === parseInt(boardNumber)); 이게 있는지 없는지 확인해야한다.
    //^ 검색결과가 존재하지 않으면 Main화면으로 돌려보냄.
    if(!board) {
      navigator('/');
      return;
    }

    //? 로그인 검증(되어 있는지)
    //? 이걸 해주려면 store에서 user를 들고와야함.
    //? 로그인 상태가 아니라면 auth로(로그인 화면) 돌려보냄
    if(!user) {
      navigator('/auth');
      return;
    }


    //? 검색된 board의 작성자가 로그인한 user와 일치하는지 검증
    //? writerNickname이 유저 nickname과 다르면 auth로 보냄
    if(board?.writerNickname !== user.nickname){
      navigator('/auth');
      return;
    }

    setBoardTitle(board.boardTitle);
    setBoardContent(board.boardContent);
  }, [])

  //? 일반적으로 수정페이지는 작성페이지와 거의 똑같음
  return (
    <Box sx={{ p: '0px 198px', backgroundColor: 'rgba(0, 0, 0, 0.05)'}}>
        <Box sx={{ p: '100px 24px', backgroundColor: '#ffffff'}}>
            <Input fullWidth disableUnderline placeholder='제목을 입력하세요' sx={{fontSize: '30px', fontWeight: 500, border: '0px'}} value={boardTitle} onChange={(event) => setBoardTitle(event.target.value)}/>
            <Divider sx={{ m: '40px 0px'}} variant='fullWidth'/>
            <Box sx={{display: 'flex', alignItems: 'start'}}>
                <Input fullWidth disableUnderline multiline minRows={20} placeholder='내용을 작성해주세요' sx={{fontSize: '18px', fontWeight: 500, lineHeight: '150%' }} defaultValue={boardContent} onChange={(event) => setBoardContent(event.target.value)}/>
                <IconButton>
                    <PhotoCameraBackIcon />
                </IconButton>
            </Box>
        </Box>
        <Fab sx={{ position: 'fixed', bottom: '200px', right: '248px', backgroundColor: 'rgba(0, 0, 0, 0.4)'}} onClick={onUpdateHandler}>
            <EditIcon />
        </Fab>
    </Box>
  )
}
