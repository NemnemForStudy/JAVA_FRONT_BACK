import { Avatar, Box, Divider, IconButton, MenuItem, Typography } from '@mui/material'
import { useState, MouseEvent } from 'react'
import MoreVertIcon from '@mui/icons-material/MoreVert';
import { Menu } from '@mui/material';

export default function BoardDetailView() {

    //? 일단 null을 넣어주자
    //? anchorElement를 지정해줘야 한다.
    const [anchorElement, setAnchorElement] = useState<null | HTMLElement>(null);
    //? open이 true false형태이니 boolean으로 처음은 false
    const [menuOpen, setMenuOpen] = useState<boolean>(false);

    //? event 받을 적에 event 타입을 모른다. 그럴때는 마우스 올리면 타입이 나온다.
    const onMenuClickHandler = (event : MouseEvent<HTMLButtonElement>) => { 
        setAnchorElement(event.currentTarget);
        setMenuOpen(true);
    }

    const onMenuCloseHandler = () => {
        setAnchorElement(null);
        setMenuOpen(false);
    }

    return (
    //? p: { 'px' 'px '} <- 세로, 가로
    <Box sx={{ p: '100px 222px'}}>
        <Box>
            <Box>
                <Typography>title</Typography>
                <Box>
                    <Box>
                        <Avatar />
                        <Typography></Typography>
                        <Divider orientation='vertical' variant='middle'/>
                        <Typography></Typography> 
                    </Box>
                    <IconButton onClick={(event) => onMenuClickHandler(event)}>
                        <MoreVertIcon />
                    </IconButton>
                    {/* //? Menu는 open속성이 필수라서 에러가 뜬다. */}
                    {/* //? true false 형태 */}
                    {/* //? anchorEl은 어디에 걸리는 거라고 한다. */}
                    <Menu anchorEl={anchorElement} open={menuOpen} onClose={onMenuCloseHandler}>
                        <MenuItem sx={{ p: '10px 59px ' , opacity: 0.5 }}>수정</MenuItem>
                        <Divider />
                        <MenuItem sx={{ p: '10px 59px ', color: '#ff0000', opacity: 0.5}}>삭제</MenuItem>
                    </Menu>
                </Box>
            </Box>             
            <Divider />
            <Box></Box>
            <Box></Box>
        </Box>
        <Box></Box>
        <Box></Box>
    </Box>
  )
}
