from langchain.embeddings import OpenAIEmbeddings
from langchain.document_loaders import TextLoader
from langchain.text_splitter import CharacterTextSplitter
from langchain.text_splitter import RecursiveCharacterTextSplitter
from langchain.vectorstores.faiss import FAISS
from langchain.docstore.document import Document

import os
from dotenv import load_dotenv

import re

def load_single_document(file_path):
    loader = TextLoader(file_path, encoding="utf-8")
    return loader.load()[0]
def load_documents(source_dir):
    all_files = os.listdir(source_dir)
    # 결과를 리스트에 저장하여 반환
    return [load_single_document(f"{source_dir}/{file_name}") for file_name in all_files]

if __name__=="__main__":
    load_dotenv()
    os.environ["OPENAI_API_KEY"] = os.getenv("apikey")

    db_path = "../vector_faissdb"
    embeddings = OpenAIEmbeddings()

    # file_name = "./news.txt"
    #유머 파일은 청크 다르게 만들기
    file_path = '../TextData'
    # humor_path = './humor'

    transcript = load_documents(file_path)
    # humor_script = load_documents(humor_path)

    #humor은 청크 에피소드별로
    # pattern = r'([0-9]+\.)'
    #chunks = re.split(pattern, humor_script)

    # humor_splitter = RecursiveCharacterTextSplitter(chunk_size=1000,separators=[pattern] ,chunk_overlap=100)
    text_splitter = RecursiveCharacterTextSplitter(chunk_size=1000,separators=["\n\n", "\n", ".", "!", "?", ",", " ", ""] ,chunk_overlap=0)

    # docs 기존에 만들고 + 다른 splitter로 유머별로 chunk화한 docs append하고 임베딩
    docs = text_splitter.split_documents(transcript)
    # humor_doc = humor_splitter.split_documents(humor_script)

    #합치기
    # docs.extend(humor_doc)
    #chunk_size : 최대 청크 길이, chunk_overlap : 인접한 청크 간에 중복되는 문자 수
    db = FAISS.from_documents(docs, embeddings)
    db.save_local(db_path)

    # 경로가 문제인가 확인하기 위한 코드-> 경로문제는 아님
    # f = open(file_name, 'r',encoding='utf-8')
    # plain_txt = f.read()
    # f.close()
    # print(plain_txt)