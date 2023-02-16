import { Avatar, Box, Card, Typography } from '@mui/material'
import CardActionArea from '@mui/material/CardActionArea'
import React from 'react'
import { useNavigate } from 'react-router'
import { IPreviewItem } from 'src/interfaces'

interface Props {
    //? boardItem의 타입이 IPreviewItem이라서 여기도 IPreviewItem이다.
    item: IPreviewItem
}

export default function BoardListItem({ item }: Props) {
    
    const navigate = useNavigate();

  return (
    <Card variant='outlined'>
        <CardActionArea sx={{ display: 'flex', justifyContent: 'space-between', p: '24px', backgroundColor: '#ffffff' }} onClick={() => navigate(`/board/detail/${item.boardNumber}`)}>
        <Box>
            <Box sx={{ display: 'flex' }}>
                <Box sx={{ mr: '8px' }}>
                      <Avatar alt={ item.writerNickname } src={ item.writerProfile } />
                </Box>
                <Box>
                      <Typography sx={{ fontSize: '12px', fontWeight: 500, color: '#000000' }}>{item.writerNickname}</Typography>
                    <Typography sx={{ mt: '2px', fontSize: '12px', fontWeight: 400, color: 'rgba(0, 0, 0, 0.7)' }}>{item.writeDate}</Typography>
                </Box>
            </Box>
            <Box sx={{ mt: '16px', mb: '16px' }}>
                <Typography sx={{ fontSize: '16px', fontWeight: 500, color: '#000000' }}>{item.boardTitle}</Typography>
                  <Typography sx={{ mt: '5px', fontSize: '12px', fontWeight: 400, color: 'rgba(0, 0, 0, 0.7)' }}>{item.boardContent}</Typography>
            </Box>
            <Box>
                <Typography sx={{ fontSize: '12px', fontWeight: 400, color: 'rgba(0, 0, 0, 0.7)' }}>{`댓글 ${item.commentCount} · 좋아요 ${item.likeCount} · 조회수 ${item.viewCount}`}</Typography>
            </Box>
          </Box>
          {item.img && (
            <Card>
                {/* 이미지 들어올 공간 */}
                {/* //? as string으로 강제적으로 string으로 다루겠다는 의미 */}
                <Box component='img' src={item.img as string} sx={{height: '152px', width: '152px', borderRadius: '5%' }}/>
            </Card>  
          )}
        </CardActionArea>
    </Card>
  )
}
