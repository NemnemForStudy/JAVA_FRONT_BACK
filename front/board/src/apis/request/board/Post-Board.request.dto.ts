interface RequestDto {
    boardTitle: string;
    boardContent: string;
    //? null이 올 수 있으므로 null을 넣어주자
    boardImgUrl: string | null;
}

export default RequestDto;