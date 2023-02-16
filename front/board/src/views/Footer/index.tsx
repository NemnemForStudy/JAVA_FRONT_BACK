import { Box, IconButton, Typography } from '@mui/material'
import React from 'react'
import InstagramIcon from '@mui/icons-material/Instagram';
import FacebookIcon from '@mui/icons-material/Facebook';
import { color, fontWeight } from '@mui/system';

export default function Footer() {
    return (
      //? 상 우 하 좌(시계방향)
    <Box sx={{backgroundColor: '#373737', p: '40px 120px 50px 120px' }}>
            <Box sx={{ display:'flex', justifyContent:'space-between' }}>
            <Box>
                <Typography sx={{fontSize: '20px', fontWeight: 400, color: '#ffffff'}}>Nemnem's Board</Typography>
            </Box>
            <Box>
                <Typography component='span' sx={{ fontSize: '12px', fontWeight: 400, color: '#ffffff'}}>Nemnem@gmail.com</Typography>
                <IconButton sx={{ color: '#ffffff'}}>
                    <InstagramIcon />
                </IconButton>
                <IconButton sx={{ color: '#ffffff'}}>
                    <FacebookIcon />
                </IconButton>
            </Box>
        </Box>
        <Typography sx={{fontSize: '12px', fontWeight: 400, color: '#ffffff'}}>Copyright @ 2023 Nemnem. All right Reserved.</Typography>
    </Box>
  )
}
