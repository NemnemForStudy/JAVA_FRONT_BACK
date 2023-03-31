import { MouseEvent, useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom';

import { Avatar, Box, Button, Card, Divider, IconButton, Menu, Input, MenuItem, Pagination, Stack, Typography } from '@mui/material'
import MoreVertIcon from '@mui/icons-material/MoreVert';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import ChatOutlinedIcon from '@mui/icons-material/ChatOutlined';
import ArrowDropDownOutlinedIcon from '@mui/icons-material/ArrowDropDownOutlined';
import ArrowDropUpOutlinedIcon from '@mui/icons-material/ArrowDropUpOutlined';
import FavoriteIcon from '@mui/icons-material/Favorite';
 
import CommentListItem from 'src/components/CommentListItem';
import LikeListItem from 'src/components/LikeListItem';
import { usePagingHook } from 'src/hooks';
import { useUserStore } from 'src/stores';
import { Board, Comment, Liky } from 'src/interfaces';
import { getPageCount } from 'src/utils';
import axios, { AxiosResponse } from 'axios';
import ResponseDto from 'src/apis/response';
import { authorizationHeader, DELET_BOARD_URL, GET_BOARD_URL, LIKE_URL, POST_COMMENT_URL } from 'src/constants/api';
import { DeleteBoardResponseDto, GetBoardResponseDto, LikeResponseDto, PostCommentResponseDto } from 'src/apis/response/board';
import { useCookies } from 'react-cookie';
import { LikeDto, PostCommentDto } from 'src/apis/request/board';

export default function BoardDetailView() {

    const [cookies] = useCookies();

    //? 일단 null을 넣어주자
    //? anchorElement를 지정해줘야 한다.
    const [anchorElement, setAnchorElement] = useState<null | HTMLElement>(null);

    const[menuFlag, setMenuFlag] = useState<boolean>(false);
    //? open이 true false형태이니 boolean으로 처음은 false
    const [menuOpen, setMenuOpen] = useState<boolean>(false);

    //? 일단 null이 가능하게
    const [board, setBoard] = useState<Board | null>(null);

    //? 좋아요 눌렀을 때 색 변경
    const [likeState, setLikeState] = useState<boolean>(false);
    //? 누를때마다 좋아요 누른 사람이 나왔다가 사라짐.
    const [openLike, setOpenLike] = useState<boolean>(false);
    const [likeList, setLikeList] = useState<Liky[]>([]);
    
    //? 기억하자 배열을 들고올 때는 interface이름 + []
    const [openComment, setOpenComment] = useState<boolean>(false);
    const [commentContent, setCommentContent] = useState<string>('');

    const { boardList, setBoardList, viewList, COUNT, pageNumber, onPageHandler } = usePagingHook(3);

    //? boardNumber 데이터를 꺼내올거다.
    const { boardNumber } = useParams();
    const navigator = useNavigate();

    //? user 데이터 꺼내오기
    const { user } = useUserStore();

    const accessToken = cookies.accessToken;

    //? 조회수 1 증가 시키기
    let isLoad = false;

    const getBoard = () => {
        axios.get(GET_BOARD_URL(boardNumber as string))
            .then((response) => getBoardResponseHandler(response))
            .catch((error) => getBoardErrorHandler(error));
    }

    const getBoardResponseHandler = (response: AxiosResponse<any, any>) => {
        const { result, message, data } = response.data as ResponseDto<GetBoardResponseDto>
        if (!result || !data) {
            alert(message);
            navigator('/');
            return;
        }
        setBoardResponse(data);
    }

    const getBoardErrorHandler = (error: any) => {
        console.log(error.message);
    }

    const onMenuClickHandler = (event: MouseEvent<HTMLButtonElement>) => {
        setAnchorElement(event.currentTarget);
        setMenuOpen(true);
    }

    const onMenuCloseHandler = () => {
        setAnchorElement(null);
        setMenuOpen(false);
    }

    const onLikeHander = () => {
        //? 로그인이 안되어 있으면 좋아요 누를 수 없음
        if(!accessToken) {
            alert('로그인이 필요한 서비스입니다.');
            return;
        }

        //? undefined로 되어있기 때문에 강제로 as string으로 형 변환
        const data: LikeDto = { boardNumber: parseInt(boardNumber as string) };
        axios.post(LIKE_URL, data, authorizationHeader(accessToken))
            .then((response) => likeResponseHandler(response))
            .catch((error) => likeErrorHandler(error));
    }

    const likeResponseHandler = (response: AxiosResponse<any, any>) => {
        const { result, message, data } = response.data as ResponseDto<LikeResponseDto>;
        if (!result || !data) {
            alert(message);
            return;
        }
        setBoardResponse(data);
    }

    const likeErrorHandler = (error: any) => {
        console.log(error.message);
    }

    //? 버튼을 눌렸을 때 동작하는 것
    const onPostCommentHandler = () => {
        if(!accessToken) {
            alert('로그인이 필요한 서비스입니다.');
            return;
        }

        const data: PostCommentDto = { boardNumber: parseInt(boardNumber as string), commentContent };
        axios.post(POST_COMMENT_URL, data, authorizationHeader(accessToken))
            .then((response) => postCommentResponseHandler(response))
            .catch((error) => postCommentErrorHandler(error));
    }

    const postCommentResponseHandler = (response: AxiosResponse<any, any>) => {
        const { result, message, data } = response.data as ResponseDto<PostCommentResponseDto>;
        if(!result || !data) {
            alert(message);
            return;
        }
        setBoardResponse(data);
        setCommentContent('');
    }

    const postCommentErrorHandler = (error: any) => {
        console.log(error.message);
    }

    const onDeleteHandler = () => {
        if(!accessToken) {
            alert('로그인이 필요한 서비스입니다.');
            return;
        }
        if(board?.writerEmail !== user?.email){
            alert('권한이 없습니다');
            return;
        }

        axios.delete(DELET_BOARD_URL(boardNumber as string), authorizationHeader(accessToken))
            .then((response) => deleteBoardResponseHandler(response))
            .catch((error) => deleteBoardErrorHandler(error));
    }

    const deleteBoardResponseHandler = (response: AxiosResponse<any, any>) => {
        const { result, message, data } = response.data as ResponseDto<DeleteBoardResponseDto>;
        if ( !result || !data || !data.resultStatus ) {
            alert(message);
            return;
        }
        navigator('/');
    }

    const deleteBoardErrorHandler = (error: any) => {
        console.log(error.message);
    }

    const setBoardResponse = (data: GetBoardResponseDto | LikeResponseDto | PostCommentResponseDto) => {
        const { board, commentList, likeList } = data;
        setBoard(board);
        // 댓글 리스트를 3개까지 보여주도록 하는 로직
        setBoardList(commentList);
        setLikeList(likeList);
        const owner = user !== null && board.writerEmail === user.email;
        setMenuFlag(owner);
    }

    useEffect(() => {
        if(isLoad) return;
        //? boardNumber가 존재하는지 검증
        if (!boardNumber) {
            navigator('/');
            return;
        }
        isLoad = true;
        getBoard();
    }, [])

    useEffect(() => {
        if(!user) return;
        //? like를 하나씩 꺼내옴
        //? user 이메일이 같은지 봐줘야함
        const like = likeList.find((like) => like.userEmail === user.email);
        //? liky가 반환해주는게 undefined라서 넣었다. find를 보면 알 수 있다.
        setLikeState(like !== undefined);
        //? useEffect에서 존재하는지 안하는지 검사를 하고 setLikeStatus로 undefined를 확인하고 likeList가 바뀌게 해준다.
    }, [likeList]);

    return (
    //? p: { 'px' 'px '} <- 세로, 가로
    <Box sx={{ p: '100px 222px'}}>
        <Box>
            <Box>
                {/* //? ?가 붙는 이유는 null일 수도 있기 때문 */}
                <Typography sx={{ fontSize: '32px', fontWeight: 500 }}>{board?.boardTitle}</Typography>
                <Box sx={{ display: 'flex', justifyContent: 'space-between', mt: '20px'}}>
                    <Box sx={{ display: 'flex', alignItems: 'center'}}>
                        <Avatar src={board?.writerProfileUrl ? board?.writerProfileUrl : ''} sx={{ height: '32px', width: '32px', mr: '8px' }} />
                        <Typography sx={{ mr: '8px', fontSize: '16px', fontWeight: 500 }}>{board?.writerNickname}</Typography>
                        <Divider sx={{ mr: '8px' }} orientation='vertical' variant='middle' flexItem/>
                        <Typography sx={{ mr: '8px', fontSize: '16px', fontWeight: 400, opacity: 0.4 }}>{board?.boardWriteDatetime}</Typography> 
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
                        <MenuItem sx={{ p: '10px 59px ', opacity: 0.5 }} onClick={() => navigator(`/board/update/${board?.boardNumber}`)}>수정</MenuItem>
                        <Divider />
                        <MenuItem sx={{ p: '10px 59px ', color: '#ff0000', opacity: 0.5}} onClick={() => onDeleteHandler()}>삭제</MenuItem>
                    </Menu>
                </Box>
            </Box>             
            <Divider sx={{ m: '40px 0px' }} />
            <Box>
                <Typography sx={{ fontSize: '18px', fontWeight: 500, opacity: 0.7 }}>{board?.boardContent}</Typography>
                {/* //? board?.img로 하니 에러가 났다. 조건을 추가해주자*/}
                {board?.boardImgUrl && (<Box sx={{ width: '100%', mt: '20px'}} component='img' src={board?.boardImgUrl ? board?.boardImgUrl : ''} />)}
            </Box>
            <Box sx={{ display: 'flex', mt: '20px' }}>
                <Box sx={{ display: 'flex', mr: '20px' }}>
                    { likeState ? 
                        (<FavoriteIcon sx={{ height: '24px', width: '24px', mr: '6px', opacity: 0.7, color: '#ff0000' }} onClick={() => onLikeHander()}/> ) : 
                        (<FavoriteBorderIcon sx={{ height: '24px', width: '24px', mr: '6px', opacity: 0.7 }} onClick={() => onLikeHander()} />) 
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
                        { likeList.map((likeUser) => (<LikeListItem likeUser={likeUser} />))}
                    </Box>
                </Card>
            </Box>
        )}
        <Box>
        { openComment && (
            <Box>
                <Box sx={{ p: '20px' }}>
                    <Typography sx={{ fontSize: '16px', fontWeight: 500 }}>댓글 {boardList.length}</Typography>
                    <Stack sx={{ p: '20px 0px' }} spacing={3.75}>
                        {viewList.map((commentItem) => (<CommentListItem item={commentItem as Comment} />))}
                    </Stack>
                </Box>
                <Divider />
                <Box sx={{ p: '20px 0px', display: 'flex', justifyContent: 'center' }}>
                    <Pagination page={pageNumber} count={getPageCount(boardList, COUNT)} onChange={(event, value) => onPageHandler(value)} />
                </Box>
                <Box>
                    <Card variant='outlined' sx={{ p: '20px' }}>
                        <Input minRows={3} multiline disableUnderline fullWidth value={commentContent} onChange={(event) => setCommentContent(event.target.value)}/>
                        <Box sx={{ display: 'flex', justifyContent: 'end' }}>
                            <Button sx={{ p: '4px 23px', backgroundColor: '#000000', fontSize: '14px', fontWeight: 400, color: '#ffffff', borderRadius: '46px' }} onClick={() => onPostCommentHandler()}>댓글달기</Button>
                        </Box>
                    </Card>
                </Box>
            </Box>
        ) }
        </Box>
    </Box>
  )
}