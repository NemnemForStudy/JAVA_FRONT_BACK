import { Box, Card, CardActionArea, Grid, Pagination, Typography } from '@mui/material'
import React, { useEffect } from 'react'
import ModeEditIcon from '@mui/icons-material/ModeEdit';
import { usePagingHook } from 'src/hooks';
import { useUserStore } from 'src/stores';
import { BOARD_LIST } from 'src/mock';
import { Stack } from '@mui/system';
import BoardListItem from 'src/components/BoardListItem';
import { getPageCount } from 'src/utils';
import { useNavigate } from 'react-router';

export default function MyPageContents() {

  const { boardList, viewList, pageNumber, setBoardList, onPageHandler, COUNT } = usePagingHook(5);
  //? 로그인 한 상태일 때 user정보를 가져올 수 있도록
  //? 스토어에서 user 상태를 가져옴
  const { user } = useUserStore();
  const navigator = useNavigate();

  useEffect(() => {
    //? 로그인이 되어있지 않으면 로그인 페이지로 이동
    //? 유저 정보가 없으면 된다.
    if(!user){
      alert('로그인이 필요한 작업입니다.');
      navigator('/auth');
      return;
    }

    //? BOARD_LIST(전체 게시물 리스트) 에서 작성자 nickname이
    //? 로그인한 회원의 nickname과 일치하는 게시물만 필터링해서
    //? 기준이 되는 새로운 리스트 생성
      const tmp = BOARD_LIST.filter((board) => board.writerNickname === user?.nickname);
      //? 기준이 되는 새로운 리스트를 boardList 상태에 저장
      setBoardList(tmp);
  }, []);

  return (
    <Box sx={{ p: '40px 120px', backgroundColor: 'rgba(0, 0, 0, 0.05)'}}>
      <Box>
        <Typography sx={{fontSize: '24px', fontWeight: 500 }}>내 게시물 {boardList.length}</Typography>
      </Box>
      <Box sx={{ mt: '20px', pb: '80px' }}>
        <Grid container spacing={3}>
          <Grid item sm={12} md={8}>
            <Stack spacing={2}>
              {/* //? 실제로 보여지는건 viewList이므로 viewList를 찍어주자 */}
              {viewList.map((boardItem) => (<BoardListItem item={boardItem} />))}
            </Stack>
          </Grid>
          <Grid item sm={12} md={4}>
            <Card variant='outlined'>
              <CardActionArea sx={{p: '25px 0px', display: 'flex', justifyContent: 'center'}} onClick={() => navigator('/board/write')}>
                <ModeEditIcon sx={{ mr: '6px'}}/>
                <Typography sx={{fontSize: '18px', fontWeight: 500 }}>글쓰기</Typography>
              </CardActionArea>
            </Card>
          </Grid>
        </Grid>
      </Box>
      <Box sx={{ display: 'flex', justifyContent: 'center' }}>
        {/* //? 바뀔 때 마다 onPageHandler value값이 달라지게 만들기 */}
        <Pagination page={pageNumber} count={getPageCount(boardList, COUNT)} onChange={(event, value) => onPageHandler(value)} />
      </Box>
    </Box>
  )
}
