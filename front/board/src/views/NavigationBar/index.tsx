import * as React from 'react';
import { styled, alpha } from '@mui/material/styles';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import SearchIcon from '@mui/icons-material/Search';
import { Button } from '@mui/material';
import { useLocation, useNavigate } from 'react-router-dom';

export default function NavigationBar() {

  const navigator = useNavigate();
  const path = useLocation();

  console.log(path.pathname);

  return (
    <Box sx={{ flexGrow: 1, pr: "120px", pl: "120px" }}>
      <AppBar position="static">
        <Toolbar>
          <Typography
            variant="h6"
            noWrap
            component="div"
            sx={{ flexGrow: 1, display: { xs: 'none', sm: 'block' } }}
            onClick={() => navigator('/')}
          >
            Hoons Board
          </Typography>
          <Box>
            <IconButton color={"default"}>
              <SearchIcon />
            </IconButton>
            {path.pathname !== '/auth' && (<Button variant='contained' color='secondary' onClick={() => navigator('/auth')}>로그인</Button>)}
          </Box>
        </Toolbar>
      </AppBar>
    </Box>
  );
}
