from langchain.embeddings import OpenAIEmbeddings
from langchain.document_loaders import TextLoader
from langchain.text_splitter import CharacterTextSplitter
from langchain.text_splitter import RecursiveCharacterTextSplitter
from langchain.vectorstores.faiss import FAISS
from langchain.docstore.document import Document

from ML.openaikey import apikey
import os

if __name__=="__main__":

    db_path = "./vector_faissdb"
    file_name = "./product.txt"

    os.environ["OPENAI_API_KEY"] = apikey

    loader = TextLoader(file_name,encoding='utf-8')
    documents = loader.load()
    #chunk_size : 최대 청크 길이, chunk_overlap : 인접한 청크 간에 중복되는 문자 수
    # text_splitter = CharacterTextSplitter(chunk_size=1000, chunk_overlap=0) # 빠름
    text_splitter = RecursiveCharacterTextSplitter(chunk_size=1000, chunk_overlap=0) # 느림
    docs = text_splitter.split_documents(documents)

    embeddings = OpenAIEmbeddings()
    db = FAISS.from_documents(docs, embeddings)
    db.save_local(db_path)

    # 경로가 문제인가 확인하기 위한 코드-> 경로문제는 아님
    # f = open(file_name, 'r',encoding='utf-8')
    # plain_txt = f.read()
    # f.close()
    # print(plain_txt)