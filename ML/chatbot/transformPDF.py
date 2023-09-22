# pdf -> txt transform
from PyPDF2 import PdfReader
import re
import os
def search(dirname):
    filenames = os.listdir(dirname)
    for filename in filenames:
        full_filename = os.path.join(dirname, filename)
        print(full_filename)

if __name__=="__main__":


    # 단일 pdf에서 일단 저장하는 코드
    dir_path = "pdfdir"
    # pdf_path = "df"
    # txt_path = "df"

    pdf_names = os.listdir(dir_path)
    for pdf_name in pdf_names:
        pdf_path = os.path.join(dir_path, pdf_name)
        txt_path = os.path.join(dir_path, pdf_name[:-4]+".txt")

        reader = PdfReader(pdf_path)
        num_of_pages = len(reader.pages)

        plain_txt=""
        for i in range(num_of_pages):
            page = reader.pages[i]
            plain_txt += page.extract_text()#strip은 일단안함
        plain_txt = plain_txt.replace("\n"," ")

        with open(txt_path, 'w', encoding='utf-8') as f:
            f.write(plain_txt)
