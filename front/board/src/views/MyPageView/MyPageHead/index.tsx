import { ChangeEvent, useEffect, useRef } from 'react'
import { useNavigate } from 'react-router-dom';
import { useCookies } from 'react-cookie';

import axios, { AxiosResponse } from 'axios';
import { Avatar, Box, Typography, IconButton } from '@mui/material';
import EditOutlinedIcon from '@mui/icons-material/EditOutlined';

import { useUserStore } from 'src/stores';
import { PatchProfileDto } from 'src/apis/request/user';
import ResponseDto from 'src/apis/response';
import { PatchProfileResponseDto } from 'src/apis/response/user';
import { authorizationHeader, FILE_UPLOAD_URL, PATCH_PROFILE_URL, multipartHeader } from 'src/constants/api';

export default function MyPageHead() {

    //          Hook          //
    const navigator = useNavigate();
    const imageRef = useRef<HTMLInputElement | null>(null);

    const [cookies, setCookies] = useCookies();
    const { user, setUser, resetUser } = useUserStore();

    const accessToken = cookies.accessToken;

    //          Event Handler          //
    const onLogoutHandler = () => {
        // TODO : 로그아웃 처리 안됨. 해결하자
        setCookies('accessToken', '', { expires: new Date(), path: '/' });
        resetUser();
        navigator('/');
    }

    const onProfileUploadChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
        if(!event.target.files) return;

        const data = new FormData();
        data.append('file', event.target.files[0]);

        axios.post(FILE_UPLOAD_URL, data, multipartHeader())
            .then((response) => imageUploadResponseHandler(response))
            .catch((error) => imageUploadErrorHandler(error));
    }

    // TODO
    const onProfileUploadButtonHandler = () => {
        if(!imageRef.current) return;
        imageRef.current.click();
    }

    


    //          Response Handler          //
    const imageUploadResponseHandler = (response: AxiosResponse<any, any>) => {
        //? 이건 업로드 한번하고 끝나면 안되고
        //? 다시 axios를 보내야 하고 profile을 호출해야한다.
        const profile = response.data as string;
        const data: PatchProfileDto = { profile };

        //? patch를 보낼 때 token이 필요하다.
        axios.patch(PATCH_PROFILE_URL, data, authorizationHeader(accessToken))
            .then((response) => patchProfileResponseHandler(response))
            .catch((error) => patchProfileErrorHandler(error));
    }

    const patchProfileResponseHandler = (response: AxiosResponse<any, any>) => {
        const { result, message, data } = response.data as ResponseDto<PatchProfileResponseDto>;
        if(!result || !data) {
            alert(message);
            return;
        }
        setUser(data);
    }

    //          Error Handler          //
    const imageUploadErrorHandler = (error: any) => {
        console.log(error.message);
    }

    const patchProfileErrorHandler = (error: any) => {
        console.log(error.message);
    }

    //          use Effect          //
    useEffect(() => {
        if(!accessToken) {
            navigator('/auth');
            return;
        }
    }, [])

  return (
    <Box sx={{ p: '40px 120px', display: 'flex' }}>
        <Box>
            <IconButton onClick={onLogoutHandler}>
                <Avatar sx={{ height: '120px', width: '120px' }} alt={user?.nickname} src={user?.profile ? user.profile: ''} />
            </IconButton>
        </Box>
        <Box sx={{ ml: '25px', display: 'flex', flexDirection: 'column', justifyContent: 'center' }}>
            <Box sx={{ display: 'flex', alignItems: 'center' }}>
                <Typography sx={{ fontSize: '24px', fontWeight: 500, color: 'rgba(0, 0, 0, 0.7)' }}>{user?.nickname}</Typography>
                <IconButton sx={{ ml: '10px' }} onClick={() => onProfileUploadButtonHandler()}>
                    <EditOutlinedIcon />
                    <input ref={imageRef} hidden type='file' accept='image/*' onChange={(event) => onProfileUploadChangeHandler(event)}/>
                </IconButton>
            </Box>
            <Typography sx={{ mt: '10px', fontSize: '16px', fontWeight: 500, color: 'rgba(0, 0, 0, 0.4)' }}>{user?.email}</Typography>
        </Box>
    </Box>
  )
}

