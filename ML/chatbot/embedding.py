from langchain.embeddings import OpenAIEmbeddings
from langchain.document_loaders import TextLoader
from langchain.text_splitter import CharacterTextSplitter
from langchain.text_splitter import RecursiveCharacterTextSplitter
from langchain.vectorstores.faiss import FAISS
from langchain.docstore.document import Document

from ML.openaikey import apikey
import os


def load_single_document(file_path):
    loader = TextLoader(file_path, encoding="utf-8")
    return loader.load()[0]
def load_documents(source_dir):
    all_files = os.listdir(source_dir)
    # 결과를 리스트에 저장하여 반환
    return [load_single_document(f"{source_dir}/{file_name}") for file_name in all_files]

if __name__=="__main__":

    db_path = "./vector_faissdb"
    os.environ["OPENAI_API_KEY"] = apikey
    embeddings = OpenAIEmbeddings()

    # file_name = "./news.txt"
    file_path = './TextData'
    transcript = load_documents(file_path)

    text_splitter = RecursiveCharacterTextSplitter(chunk_size=1000, chunk_overlap=0) # 빠름
    docs = text_splitter.split_documents(transcript)

    #chunk_size : 최대 청크 길이, chunk_overlap : 인접한 청크 간에 중복되는 문자 수


    db = FAISS.from_documents(docs, embeddings)
    db.save_local(db_path)

    # 경로가 문제인가 확인하기 위한 코드-> 경로문제는 아님
    # f = open(file_name, 'r',encoding='utf-8')
    # plain_txt = f.read()
    # f.close()
    # print(plain_txt)