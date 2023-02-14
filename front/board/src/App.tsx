import { Route, Routes } from 'react-router-dom';
import './App.css';
import AuthenticationView from './views/AuthenticationView';
import NavigationBar from './views/NavigationBar';

//# Route 설계
//? 1. 'main' path 작성 - '/'
//? 2. 'auth' path 작성 - '/auth'
//? 3. 'myPage' path 작성 - '/myPage'
//? 4. 'boardSerach' path 작성 - '/board/serach/:content' <- 이런것을 모듈화(군집화)라고 함.
//? 5. 'boardDetail' path 작성 - '/board/detail/:boardNumber'
//? 6. 'boardWrite' path 작성 - '/board/write'
//? 7. 'boardUpdate' path 작성 - '/board/update/:boardNumber'

function App() {
  return (
    <>
      <NavigationBar />
      <Routes>
        <Route path={'/'} element={(<></>)} />
        <Route path={'/auth'} element={(<AuthenticationView/>)} />
        <Route path={'/myPage'} element={(<></>)} />
        {/* //? board가 중복되니 한번에 묶자 아래는 board라는 write가 뒤에 묶어서 오게 만들었다.*/}
        <Route path={'/board'}>
          <Route path='/search/:content' element={(<></>)} />
          <Route path='/detail/:boardNumber' element={(<></>)} />
          <Route path='/write' element={(<></>)} />
          <Route path='/update/:boardNumber' element={(<></>)} />
        </Route>

      </Routes>
      <AuthenticationView />
    </>
  );
}

export default App;
