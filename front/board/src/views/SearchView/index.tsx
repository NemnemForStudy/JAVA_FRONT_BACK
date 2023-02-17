import { Box, Grid, Pagination, Typography } from '@mui/material'
import { Stack } from '@mui/system'
import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom';
import BoardListItem from 'src/components/BoardListItem';
import PopularCard from 'src/components/PopularCard'
import usePagingHook from 'src/hooks/paging.hook';
import { IPreviewItem } from 'src/interfaces';
import { BOARD_LIST } from 'src/mock';
import { getPageCount } from 'src/utils';

export default function SearchView() {

    // //? path에서 변수를 가져온다. App.tsx에서 :content <- 콜론 뒤에 녀석이 변수고
    // //? 들고올거면 저 콜론 뒤에 녀석이 와야한다.
    const { content } = useParams();
    //? 아래의 변수들이 필요한데, 변수들은 커스텀 훅 함수 안에 선언이 되어있다.
    //? 사용을 하려면 커스텀 훅에서 받아와야한다. 그래서 return을 해줬고
    //? 여기서 받아올 수 있음. 그래서 객체로 받아왔음.
    //? 이것을 비구조화 할당이라고 함.
    const {boardList, pageNumber, COUNT, onPageHandler, viewList} = usePagingHook(content as string);

    // const [boardList, setBoardList] = useState<IPreviewItem[]>([]);
    // const [pageNumber, setPageNumer] = useState<number>(1);
    // const [viewList, setViewList] = useState<IPreviewItem[]>([]);

    // const COUNT = 5;
    // const onPageHandler = (page: number) => {
    //     setPageNumer(page);

    //     const tmpList: IPreviewItem[] = [];
    //     const startIndex = COUNT * (page - 1); 
    //     const endIndex = COUNT * page - 1;

    //     for (let index = startIndex; index <= endIndex; index++){
    //         if (boardList.length < index + 1) break;
    //         //? 이렇게만 하면 인덱스를 범위 초과하는 예외가 발생할 수 있어서 위에 if문을 적어주자
    //         tmpList.push(boardList[index]);
    //     }
    //     setViewList(tmpList);
    // }

    // useEffect (() => {
    //     //? 이러면 검색결과가 아닌 전부가 들어간다.
    //     //? setBoardList(BOARD_LIST)
    //     //? 그래서 BOARD_LIST를 Array 메서드를 사용해서 찾아보자.
    //     //? 특정한 요소를 하나씩 돌면서 조건에 부합하는 녀석들만 올것이다.
    //     //# array.filter(요소 => 조건)
    //     //? 특정한 조건에 부합하는 요소만 모아 새 배열로 만들어 반환하는 메서드
    //     //# string.includes(검색할 문자열)
    //     //? 해당 문자열에서 검색할 문자열이 존재하면 true, 존재하지 않는다면 false
    //     //! as 강제로 타입을 지정해줌
    //     //? content를 위에 useParams에서 쓰니 밑에서도 content를 쓰면 에러가 발생해서 as로 강제로 타입을 지정해줬다.
    //     //? 중괄호가 없으면 바로 리턴이 됨. 있으면 리턴이 안됨.
    //     const tmp = BOARD_LIST.filter((board) => board.boardTitle.includes(content as string))
    //     setBoardList(tmp);
    // }, [])

    // //? boardList가 바뀔때 마다 
    // useEffect(() => {
    //     onPageHandler(pageNumber);
    // }, [boardList])

    return (
      //? 높이가 40px, 가로가 120px
        //? rgba - 레드, 그린, 블루, 투명도(a)
      <Box sx={{p: '40px 120px', backgroundColor: 'rgba(0, 0, 0, 0.05)' }}>
            <Box sx={{ fontSize: '24px', fontWeight: 500 }} >
                <Box component='strong'>{content}</Box>
                {/* //? strong은 inline되고 굵게 됨(강조) */}
              <Typography component='span' sx={{ fontSize: '24px', fontWeight: 500, opacity: '0.7'}}>에 대한 검색결과 입니다.</Typography>
              {/* //? 전체 사이즈가 나옴. */}
                <Box component='strong'>{boardList.length}</Box>
          </Box>
          <Box sx={{ pt: '20px', pd: '80px' }}>
              {/* //? 부모 Grid에는 Container를 넣어줘야 한다. 
                  //? 자식 Grid에는 item을 지정*/}
              <Grid container spacing={3}>
                  <Grid item sm={12} md={8}>
                        <Stack spacing={2}>
                            {viewList.map((boardItem) => (<BoardListItem item={boardItem} />))}
                        </Stack>  
                  </Grid>
                  <Grid item sm={12} md={4}>
                      <PopularCard title={'연관 검색어'} />
                  </Grid>
              </Grid>
          </Box>
            <Box sx={{ display: 'flex', justifyContent: 'center' }} >
                <Pagination page={pageNumber} onChange={(event, value) => onPageHandler(value)} count={getPageCount(boardList, COUNT)}/>
          </Box>
    </Box>
  )
}
