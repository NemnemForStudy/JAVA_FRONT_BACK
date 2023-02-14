import React from "react";

//? Props - properties
interface DateProps {
  placeholder: string;
}

function YearInputGruop({ placeholder }: DateProps) {
  return (
    <div className="flex-1">
      <input
        className="input-middle-style"
        type="number"
        placeholder={placeholder}
      />
    </div>
  );
}

function MonthSelectGroup() {
  //# Typescript에서 배열 선언 및 초기화는 '[]'로 가능
  //? 배열 만든 이유 return에서는 for나 if같은 제어문을 사용하지 못한다.
  //? [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]
  const months: number[] = [];
  for (let i = 1; i <= 12; i++) months.push(i);

  return (
    <div className="flex-1">
      {/* input이 아닌 select 태그 사용 */
      /* 옵션 메뉴를 제공하는 컨트롤 */}
      <select className="input-middle-style">
        <option>월</option>
        {/* 익명함수*/}
        {/* 매개변수를 받으면 하나씩 받아와서 파라미터에 담아준다. 1~12가 담김*/}
        {/* {}는 typescript를 넣는다는 거임. ()는 html 그래서 아래에도 option은 html이기 때문에 소괄호 사용*/}
        {months.map((month) => (
          <option>{month}</option>
        ))}
      </select>
    </div>
  );
}

//! 함수형 컴포넌트의 첫글자는 필수로 대문자여야 한다.
export default function BirthInputGroup() {
  return (
    <div className="content">
      <div className="input-label">생년월일</div>
      <div className="flex">
        {/* style의 display 요소를 inline으로 적용해서 inline형식으로 변경*/}
        <YearInputGruop placeholder="년 (4자리)" />
        <MonthSelectGroup />
        <YearInputGruop placeholder="일" />
      </div>
    </div>
  );
}
