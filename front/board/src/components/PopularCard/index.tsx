import React, { useEffect, useState } from 'react'
import { Box, Card, Chip, Typography } from '@mui/material'
import { POPULAR_LIST } from 'src/mock';
import { useNavigate } from 'react-router';

interface Props{
    title: string;
}

export default function PopularCard({title}: Props) {

    const [popularList, setPopularList] = useState<string[]>([]);
    const navigate = useNavigate();

    useEffect(() => {
        setPopularList(POPULAR_LIST);
    }, [])

    return (
    //? 상 우 하 좌 로 지정된다.(간단하게 시계방향)
      <Card variant='outlined' sx={{ p: '24px 12px 26px 24px'}}>
          <Typography sx={{fontSize: '24px', fontWeight: 500}}>{title}</Typography>
          <Box sx={{ mt: '24px'}}>
              {popularList.map((popular) => (
                  <Chip sx={{ fontSize: '14px', fontWeight: 500, mr: '12px', mb: '12px' }} label={popular} variant="outlined" onClick={() => navigate(`/board/search/${popular}`)} />
                ))}
          </Box>
    </Card>
  )
}