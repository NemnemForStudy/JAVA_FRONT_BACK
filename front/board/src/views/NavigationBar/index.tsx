import * as React from 'react';
import { styled, alpha } from '@mui/material/styles';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import SearchIcon from '@mui/icons-material/Search';
import { Button, FormControl, InputAdornment, OutlinedInput } from '@mui/material';
import { useLocation, useNavigate } from 'react-router-dom';
import Search from '@mui/icons-material/Search';
import { useState } from 'react';

export default function NavigationBar() {

  //? 초기화값을 지정하지 않으면 () 안에 undefined가 된 것과 똑같아 진다.
  //? 타입을 2개 받을 수 있게 됨. 무조건 초기화를 해줘야 함.
  const [content, setContent] = useState<string>('');
  const navigator = useNavigate();
  const path = useLocation();
  const onSearchHandler = () => {
    if (!content.trim()) {
      alert('검색어를 입력하세요');
      return;
    };

    navigator(`/board/search/${content}`);
  }

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar variant='outlined' position="static" sx={{ p: '0px 120px', backgroundColor: '#ffffff'}}>
        <Toolbar>
          <Typography
            variant="h6"
            noWrap
            component="div"
            sx={{ flexGrow: 1, display: { xs: 'none', sm: 'block', color: "#000000" }}}
            onClick={() => navigator('/')}
          >
            Nemnem's Board
          </Typography>
          <Box sx={{ display: 'flex' }}>
            <FormControl variant='outlined' sx={{ mr:'10px' }}>
              <OutlinedInput
                size='small'
                type='text'
                placeholder='검색어를 입력해주세요'
                endAdornment={
                  //? 포지션이 필수로 들어가야함.
                  <InputAdornment position='end'>
                    <IconButton edge='end' onClick={onSearchHandler}>
                      <SearchIcon />
                    </IconButton>
                  </InputAdornment>
                }
                onChange={(event) => setContent(event.target.value)}
              />

            </FormControl>
            {path.pathname !== '/auth' && (<Button variant='contained' sx={{backgroundColor: '#000000'}} onClick={() => navigator('/auth')}>로그인</Button>)}
          </Box>
        </Toolbar>
      </AppBar>
    </Box>
  );
}
