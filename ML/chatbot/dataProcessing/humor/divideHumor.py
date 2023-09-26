# 각 에피소드별로 이야기들을 나누는 코드
# 우선 정규표현식으로 데이터프레임에 담고 txt 입출력으로 텍스트파일 만들어주기




if __name__=="__main__":
    file_path = './TextData/humor.txt'
    f = open(file_path, 'r',encoding='utf-8')
    plain_txt = f.read()
    f.close()