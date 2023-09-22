import os
import re


if __name__=="__main__":
    file_path = './TextData/humor.txt'
    f = open(file_path, 'r',encoding='utf-8')
    plain_txt = f.read()
    f.close()

    print(plain_txt)

    output = []
    # text 정제
    for page in plain_txt:
        text = page.page_content
        text = re.sub(r"(\w+)-\n(\w+)", r"\1\2", text)  # 안녕-\n하세요 -> 안녕하세요
        text = re.sub(r"(?<!\n\s)\n(?!\s\n)", " ", text.strip())  # "인\n공\n\n지능펙\n토리 -> 인공지능펙토리
        text = re.sub(r"\n\s*\n", "\n\n", text)  # \n버\n\n거\n\n킹\n -> 버\n거\n킹
        output.append(text)


    print(text)