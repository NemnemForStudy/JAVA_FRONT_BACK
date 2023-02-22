import { Avatar, Box, Divider, Icon, IconButton, MenuItem, Typography } from '@mui/material'
import { useState, MouseEvent, useEffect } from 'react'
import MoreVertIcon from '@mui/icons-material/MoreVert';
import { Menu } from '@mui/material';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import ChatOutlinedIcon from '@mui/icons-material/ChatOutlined';
import ArrowDropDownOutlinedIcon from '@mui/icons-material/ArrowDropDownOutlined';
import ArrowDropUpOutlinedIcon from '@mui/icons-material/ArrowDropUpOutlined';
import { useNavigate, useParams } from 'react-router';
import { BOARD_LIST } from 'src/mock';
import { IPreviewItem } from 'src/interfaces';

export default function BoardDetailView() {

    //? 일단 null을 넣어주자
    //? anchorElement를 지정해줘야 한다.
    const [anchorElement, setAnchorElement] = useState<null | HTMLElement>(null);
    //? open이 true false형태이니 boolean으로 처음은 false
    const [menuOpen, setMenuOpen] = useState<boolean>(false);

    //? 일단 null이 가능하게
    const [board, setBoard] = useState<null | IPreviewItem>(null);

    //? boardNumber 데이터를 꺼내올거다.
    const { boardNumber } = useParams();
    const navigator = useNavigate();

    useEffect(() => {
        //? 1. 입력받은 파라미터(boardNumber)가 존재하는지 검증
        if (!boardNumber) {
            navigator('/'); //? <- main으로 돌려보냄
            return;
        }
        
        //? 2. BOARD_LIST에서 boardNumber에 해당하는 board를 가져옴
        //? 동일한 녀석을 찾아옴
        const board = BOARD_LIST.find((boardItem) => boardItem.boardNumber === parseInt(boardNumber));
        
        //? 3. 위의 코드의 검색한 결과가 존재하는지 검증
        if (!board) {
            navigator('/');
            return;
        }

        setBoard(board);

    }, [])

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
                {/* //? ?가 붙는 이유는 null일 수도 있기 때문 */}
                <Typography sx={{ fontSize: '32px', fontWeight: 500 }}>{board?.boardTitle}</Typography>
                <Box sx={{ display: 'flex', justifyContent: 'space-between', mt: '20px'}}>
                    <Box sx={{ display: 'flex', alignItems: 'center'}}>
                        <Avatar src={board?.writerProfile} sx={{ height: '32px', width: '32px', mr: '8px' }} />
                        <Typography sx={{ mr: '8px', fontSize: '16px', fontWeight: 500 }}>{board?.writerNickname}</Typography>
                        <Divider sx={{ mr: '8px' }} orientation='vertical' variant='middle' flexItem/>
                        <Typography sx={{ mr: '8px', fontSize: '16px', fontWeight: 400, opacity: 0.4 }}>{board?.writeDate}</Typography> 
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
            <Divider sx={{ m: '40px 0px' }} />
            <Box>
                <Typography sx={{ fontSize: '18px', fontWeight: 500, opacity: 0.7 }}>{board?.boardContent}</Typography>
                {/* //? board?.img로 하니 에러가 났다. 조건을 추가해주자*/}
                {board?.img && (<Box sx={{ width: '100%', mt: '20px'}} component='img' src={board?.img} />)}
            </Box>
                <Box sx={{ display: 'flex', mt: '20px' }}>
                    <Box sx={{ display: 'flex', mr : '20px'}}>
                        <FavoriteBorderIcon sx={{ height: '24px', width: '24px', mr: '6px', opacity: 0.7 }} />
                        <Typography sx={{fontSize: '16px', fontWeight: 500, opacity: 0.7, mr: '6px' }}>{board?.likeCount}</Typography>
                        <IconButton sx={{ height: '24px', width: '24px' }}>
                            <ArrowDropDownOutlinedIcon />
                        </IconButton>
                    </Box>
                    <Box sx={{ display: 'flex', mr : '20px'}}>
                        <ChatOutlinedIcon sx={{ height: '24px', width: '24px', mr: '6px', opacity: 0.7 }} />
                        <Typography sx={{fontSize: '16px', fontWeight: 500, opacity: 0.7, mr: '6px' }}>{board?.commentCount}</Typography>
                        <IconButton sx={{ height: '24px', width: '24px' }}>
                            <ArrowDropDownOutlinedIcon />
                        </IconButton>
                    </Box>
            </Box>
        </Box>
        <Box></Box>
        <Box></Box>
    </Box>
  )
}
