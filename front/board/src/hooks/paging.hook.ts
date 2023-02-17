//? 커스텀 Hook의 규칙 - use로 사용해야한다, 컴포넌트 최상위에 위치, 그 안에서만 사용 가능

import { useEffect, useState } from "react";
import { useParams } from "react-router";
import { IPreviewItem } from "src/interfaces";
import { BOARD_LIST } from "src/mock";

//? content를 사용하기 위해 매개변수로 받아옴
const usePagingHook = (content?: string) => {

    const [boardList, setBoardList] = useState<IPreviewItem[]>([]);
    const [pageNumber, setPageNumer] = useState<number>(1);
    const [viewList, setViewList] = useState<IPreviewItem[]>([]);

    const COUNT = 5;
    const onPageHandler = (page: number) => {
        setPageNumer(page);

        const tmpList: IPreviewItem[] = [];
        const startIndex = COUNT * (page - 1); 
        const endIndex = COUNT * page - 1;

        for (let index = startIndex; index <= endIndex; index++){
            if (boardList.length < index + 1) break;
            tmpList.push(boardList[index]);
        }
        setViewList(tmpList);
    }

    useEffect(() => {
        //? content === undefined ? ~라고도 쓸 수 있고
        //? !content ? ~ 라고도 쓸 수 있다.
        const tmp =  !content ? BOARD_LIST : BOARD_LIST.filter((board) => board.boardTitle.includes(content as string))
        setBoardList(tmp);
    }, [])

    useEffect(() => {
        onPageHandler(pageNumber);
    }, [boardList])

    //? 내보내 주는 return
    //? {}해야 그냥 받아올 수 있다.
    //? 대괄호를 쓰면 이름을 바꿀 수 있는데 전부 다 받아와야 한다.
    return { boardList, viewList, pageNumber, onPageHandler, COUNT };
}

export default usePagingHook;