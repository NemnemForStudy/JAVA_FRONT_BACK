import { Box, Fab } from '@mui/material'
import Divider from '@mui/material/Divider'
import IconButton from '@mui/material/IconButton'
import Input from '@mui/material/Input'
import React, { useState } from 'react'
import PhotoCameraBackIcon from '@mui/icons-material/PhotoCameraBack';
import EditIcon from '@mui/icons-material/Edit';
import { useNavigate } from 'react-router'

export default function BoardWriteView() {

    const [boardTitle, setBoardTitle] = useState<string>('');
    const [boardContent, setBoardContent] = useState<string>('');
    const navigator = useNavigate();

    const onWriteHandler = () => {
        //? 제목 및 내용 검증 (값이 존재하는지)
        if(!boardTitle.trim() || !boardContent.trim()){
            alert('모든 내용을 입력해주세요');
            return;
        }
        //? 존재한다면 myPage로
        navigator('/myPage');
    }

  return (
    <Box sx={{ p: '0px 198px', backgroundColor: 'rgba(0, 0, 0, 0.05)'}}>
        <Box sx={{ p: '100px 24px', backgroundColor: '#ffffff'}}>
            <Input fullWidth disableUnderline placeholder='제목을 입력하세요' sx={{fontSize: '30px', fontWeight: 500, border: '0px'}} onChange={(event) => setBoardTitle(event.target.value)}/>
            <Divider sx={{ m: '40px 0px'}} variant='fullWidth'/>
            <Box sx={{display: 'flex', alignItems: 'start'}}>
                <Input fullWidth disableUnderline multiline minRows={20} placeholder='내용을 작성해주세요' sx={{fontSize: '18px', fontWeight: 500, lineHeight: '150%' }} onChange={(event) => setBoardContent(event.target.value)}/>
                <IconButton>
                    <PhotoCameraBackIcon />
                </IconButton>
            </Box>
        </Box>
        <Fab sx={{ position: 'fixed', bottom: '200px', right: '248px', backgroundColor: 'rgba(0, 0, 0, 0.4)'}} onClick={onWriteHandler}>
            <EditIcon />
        </Fab>
    </Box>
  )
}
