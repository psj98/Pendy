- chromadb는 pydantic 종속성에 따른 fastapi와의 버전 충돌 문제로 사용하지 않기로 결정 -> db 폴더의 chromadb로 임베딩 된 파일 사용 X (우선 남겨둠)
- faiss로 임베딩 하기로 결정


- bychromadb
  - 현재 사용 X
- embedding.py
  - faiss로 임베딩하는 코드