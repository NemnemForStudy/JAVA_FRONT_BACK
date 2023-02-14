import React, { useState } from 'react'
import { Link, Route, Routes, useNavigate, useParams } from 'react-router-dom'

import { Button, TextField, Typography } from '@mui/material'

//# Router
//? Server의 Resource 경로를 추적하고 있다가 해당 경로가 바뀌면
//? 지정된 경로의 Resource 반환해주는 역할
//? npm install react-router dom

//? root 경로의 index.tsx의 render 함수 내부에
//? <BrowserRouter> 로 App 컴포넌트를 감싸줘야 한다.
//? index.tsx는 RouterView가 아닌 src파일에 있는 index.tsx로 해줘야함.

//^ Route 컴포넌트
//? Resource Path에 따라 보여주고자 하는 컴포넌트를 지정할 때 사용

//^ Link 컴포넌트
//? Web Server 내에서 특정한 Resource Path로 변경하고자 할 때 사용

//^ useNavigate Hook함수
//? Resource Path를 변경 or 이동시켜주는 Hook 함수
//? import { useNavigate } from react-router-dom;

//? const 네비게이터 함수명 = useNavigate();
//? 네비게이터함수명(path);

//? Resource Path를 변경시키기 전에 특정 작업하고자 할 때 사용된다.

//^ useParams Hook 함수(경로 변수)
//? Resource Path로부터 특정 값을 가져올때 사용
//? import { useParams } from 'react-router-dom';
//? const { pathVariable명 } = useParams();
export default function RouterView() {

  const [path, setPath] = useState<string>('');
  const navigator = useNavigate();
  const { pathValue } = useParams();

  const movePath = () => {
    //? path는 입력받은 값
    console.log(path);
    //^ navigate hook 및 Link의 path 자리에
    //^ '/'가 붙지 않으면 현재 path 뒤에 '/'가 붙고 경로가 추가되서 변경됨.
    if(path !== '' && path !== 'main')
    navigator('/' + path);
  };

  return (
    <>
      <Typography variant='h3'>{ pathValue }</Typography>
      <Routes>
          <Route path='test' element={(<Typography variant='h3'>Test Page</Typography>)}/>
      </Routes>
      <Link to='/test'>test</Link> {' '}
      <Link to='/'>main</Link>
      {/* //? onChange={(event) => setPath(event.target.value)} */}
      {/* //! 위, 아래 코드 잊지 말자 */}
      <TextField variant='filled' label='path' onChange={(event) => setPath(event.target.value)}></TextField>
      <Button variant='contained' onClick={() => movePath()}>move!</Button>
    </> 
  )
}
