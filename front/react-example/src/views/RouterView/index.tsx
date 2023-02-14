import React from 'react'
import { Route } from 'react-router-dom'

//# Router
//? Server의 Resource 경로를 추적하고 있다가 해당 경로가 바뀌면
//? 지정된 경로의 Resource 반환해주는 역할
//? npm install react-router dom

//? root 경로의 index.tsx의 render 함수 내부에
//? <BrowserRouter> 로 App 컴포넌트를 감싸줘야 한다.

//^ Route 컴포넌트
//? Resource Path에 따라 보여주고자 하는 컴포넌트를 지정할 때 사용
export default function RouterView() {
  return (
    <Route path='test'></Route>
  )
}
