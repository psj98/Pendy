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
        text = re.sub(r"(\w+)-\n(\w+)", r"\1\2", text)
        text = re.sub(r"(?<!\n\s)\n(?!\s\n)", " ", text.strip())
        text = re.sub(r"\n\s*\n", "\n\n", text)
        output.append(text)


    print(text)