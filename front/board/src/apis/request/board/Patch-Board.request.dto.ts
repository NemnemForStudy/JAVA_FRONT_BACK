interface RequestDto {
    boardContent: string;
    //? TS의 integer는 number이다.
    boardNumber: number;
    boardTitle: string;
    boardImgUrl: string | null;
}

export default RequestDto;