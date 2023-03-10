import { Avatar, Box, Card, colors, Divider, Icon, IconButton, MenuItem, Typography } from '@mui/material'
import { useState, MouseEvent, useEffect } from 'react'
import MoreVertIcon from '@mui/icons-material/MoreVert';
import { Menu } from '@mui/material';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import ChatOutlinedIcon from '@mui/icons-material/ChatOutlined';
import ArrowDropDownOutlinedIcon from '@mui/icons-material/ArrowDropDownOutlined';
import ArrowDropUpOutlinedIcon from '@mui/icons-material/ArrowDropUpOutlined';
import { useNavigate, useParams } from 'react-router';
import { BOARD_LIST, LIKE_LIST } from 'src/mock';
import { ILikeUser, IPreviewItem } from 'src/interfaces';
import { useUserStore } from 'src/stores';
import FavoriteOutlinedIcon from '@mui/icons-material/FavoriteOutlined';
import LikeListItem from 'src/components/LikeListItem';

export default function BoardDetailView() {

    //? 일단 null을 넣어주자
    //? anchorElement를 지정해줘야 한다.
    const [anchorElement, setAnchorElement] = useState<null | HTMLElement>(null);

    const[menuFlag, setMenuFlag] = useState<boolean>(false);
    //? open이 true false형태이니 boolean으로 처음은 false
    const [menuOpen, setMenuOpen] = useState<boolean>(false);

    //? 일단 null이 가능하게
    const [board, setBoard] = useState<null | IPreviewItem>(null);

    //? 좋아요 눌렀을 때 색 변경
    const [likeState, setLikeState] = useState<boolean>(false);
    //? 누를때마다 좋아요 누른 사람이 나왔다가 사라짐.
    const [openLike, setOpenLike] = useState<boolean>(false);
    const [openComment, setOpenComment] = useState<boolean>(false);
    
    //? 기억하자 배열을 들고올 때는 interface이름 + []
    const [LikeList, setLikeList] = useState<ILikeUser[]>([]);

    //? boardNumber 데이터를 꺼내올거다.
    const { boardNumber } = useParams();
    const navigator = useNavigate();

    //? user 데이터 꺼내오기
    const { user } = useUserStore();

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

        //? 5
        setLikeList(LIKE_LIST);

        //? 4. user가 존재한다면 아래 코드 실행해서 setMenuFlag를 true로
        // if (user && user.nickname === board.writerNickname) {
        //     setMenuFlag(true);
        //     return;
        // }

        //? 위의 코드를 아래 방식으로도 사용이 가능하다.
        const owner = user && user.nickname === board.writerNickname;
        setMenuFlag(owner as boolean);

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
                        {menuFlag && (
                            <IconButton onClick={(event) => onMenuClickHandler(event)}>
                                <MoreVertIcon />
                            </IconButton>
                        )}
                    {/* //? Menu는 open속성이 필수라서 에러가 뜬다. */}
                    {/* //? true false 형태 */}
                    {/* //? anchorEl은 어디에 걸리는 거라고 한다. */}
                    <Menu anchorEl={anchorElement} open={menuOpen} onClose={onMenuCloseHandler}>
                        <MenuItem sx={{ p: '10px 59px ', opacity: 0.5 }} onClick={() => navigator(`/board/update${board?.boardNumber}`)}>수정</MenuItem>
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
                    <Box sx={{ display: 'flex', mr: '20px' }}>
                        { likeState ? 
                            ( <FavoriteOutlinedIcon sx={{ height: '24px', width: '24px', mr: '6px', opacity: 0.7, color: '#ff0000' }} onClick={() => setLikeState(!likeState)}/> ) : 
                            (<FavoriteBorderIcon sx={{ height: '24px', width: '24px', mr: '6px', opacity: 0.7 }} onClick={() => setLikeState(!likeState)} />) 
                        }
                        <Typography sx={{fontSize: '16px', fontWeight: 500, opacity: 0.7, mr: '6px' }}>{board?.likeCount}</Typography>
                        <IconButton sx={{ height: '24px', width: '24px' }} onClick={() => setOpenLike(!openLike)}>
                            {openLike ?
                                ( <ArrowDropUpOutlinedIcon />):
                                ( <ArrowDropDownOutlinedIcon />)
                            }
                        </IconButton>
                    </Box>
                    <Box sx={{ display: 'flex', mr : '20px'}}>
                        <ChatOutlinedIcon sx={{ height: '24px', width: '24px', mr: '6px', opacity: 0.7 }} />
                        <Typography sx={{fontSize: '16px', fontWeight: 500, opacity: 0.7, mr: '6px' }}>{board?.commentCount}</Typography>
                        <IconButton sx={{ height: '24px', width: '24px' }} onClick={() => setOpenComment(!openComment)}>
                            {openComment ?
                                (<ArrowDropUpOutlinedIcon />):
                                (<ArrowDropDownOutlinedIcon />)
                            }
                        </IconButton>
                    </Box>
            </Box>
        </Box>
        {openLike && (
            <Box sx={{mt: '20px'}}>
                <Card variant='outlined' sx={{ p: '20px' }}>
                    <Typography>좋아요 {board?.likeCount}</Typography>
                    <Box sx={{ m: '20px 0px' }}>
                        {/* //? map할 때도 헷갈리니 생각잘 해두자. */}
                        { LikeList.map((likeUser) => (<LikeListItem likeUser={likeUser} />))}
                    </Box>
                </Card>
            </Box>
        )}
        {openComment && (
            <Box sx={{ mt: '20px' }}>
                <Card variant='outlined' sx={{ p: '20px' }}>
                    <Typography>댓글 {board?.commentCount}</Typography>
                </Card>
            </Box>
        )}
    </Box>
  )
}
