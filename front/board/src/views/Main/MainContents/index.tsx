import { Box, Grid, Pagination, Typography} from '@mui/material'
import { Stack } from '@mui/system';
import { useEffect, useState } from 'react'

import BoardListItem from 'src/components/BoardListItem'
import PopularCard from 'src/components/PopularCard'
import { usePagingHook } from 'src/hooks';
import { IPreviewItem } from 'src/interfaces';
import { BOARD_LIST } from 'src/mock';
import { getPageCount } from 'src/utils';
import { EndOfLineState } from 'typescript';

export default function MainContents() {

  const {boardList, pageNumber, COUNT, onPageHandler, viewList} = usePagingHook();

  // //? 전체는 boardList안에, 5개는 viewList안에 있다고 생각하자
  // const [boardList, setBoardList] = useState<IPreviewItem[]>([]);
  // const [viewList, setViewList] = useState<IPreviewItem[]>([]);

  // const [pageNumber, setPageNumer] = useState<number>(1);
  // //? 한 페이지에 5개의 게시물을 보여주고자 할 때 
  // //? 배열의 시작 인덱스    5 * pageNumber -5 => 5 * (pageNumber -1)
  // //? 배열의 마지막 인덱스  5 * pageNumber -1

  // const COUNT = 5;
  // const onPageHandler = (page: number) => {
  //   setPageNumer(page);

  //   const tmpList: IPreviewItem[] = [];
  //   const startIndex = COUNT * (page -1);
  //   const endIndex = COUNT * page - 1;

  //   for (let index = startIndex; index <= endIndex; index++){
  //     //? if문이 없으면 실행되지 않는다.
  //     if (boardList.length < index + 1) break;
  //     tmpList.push(BOARD_LIST[index]);
  //   }
    
  //   setViewList(tmpList);
  // }

  // useEffect(() => {
  //   setBoardList(BOARD_LIST);
  // }, [])

  // //? BOARD_LIST가 바뀌면 onPageHandler(pageNumber)로 적용
  // //? 이러면 들어갔을 때 부터 렌더가 된다.
  // useEffect(() => {
  //   onPageHandler(pageNumber);
  // }, [boardList])

  return (
    <Box sx={{ p: '40px 120px', backgroundColor: 'rgba(0, 0, 0, 0.05)' }}>
      <Box>
        <Typography sx={{ fontSize: '24px', fontWeight: 500 }}>최신 게시물</Typography>
      </Box>
      <Box sx={{ pt: '20px', pb: '80px' }}>
        <Grid container spacing={3}>
          <Grid item sm={12} md={8}>
            <Stack spacing={2}>
              {viewList.map((boardItem) => (<BoardListItem item={boardItem}/>))}
            </Stack>
          </Grid>
          <Grid item sm={12} md={4}>
            <PopularCard title='인기 검색어' />
          </Grid>
        </Grid>
      </Box>
      <Box sx={{ display: 'flex', justifyContent: 'center' }}>
        <Pagination page={pageNumber} count={getPageCount(boardList, COUNT)} onChange={(event, value) => onPageHandler(value)} />
      </Box>
    </Box>
  )
}
