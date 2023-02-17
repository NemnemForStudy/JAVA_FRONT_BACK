//? 각종 일반 함수 관리(반복적인 함수 관리, 훅 함수가 아님)


//? 없는 변수는 매개변수로 들고오자
//? 수식 한줄을 그냥 반환해주는데 {}를 굳이 적어줄 필요가 없다. return도 생략 가능
export const getPageCount = (list: any[], count: number) =>
    Math.floor((list.length - 1) / count) + 1;