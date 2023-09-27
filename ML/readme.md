(개발중)

# Contents
- 개요
- 주요 기능
- 기술 스택(고려중 포함)

# 개요
- fast api 이용 api 서버로 운용
- 인터프리터 : python 3.9


# 주요 기능
## 일기 _ 완료
- gpt + langchain
## 월별 보고서 _ 완성중
- 소비 내역 데이터는 카드 추천이 가장 적합하다고 판단되어 카드 추천 기능을 제공합니다
- 카드 정보 일단 삼성카드로 10개 정도만 넣어두었음

## 챗봇 _ 미구현 상태
- 자료 조사 완료

## 챗봇 _ chatbot
- chromadb에 금융 관련 정보를 임베딩(텍스트를 ml에서 사용되는 vector화)하고 
  - OpenAI의 chat gpt를 이용해서 similarity_search_with_score로 탐색
- 생성형 AI의 특성상 모르는 정보를 지어내서 대답할 수도 있어서 명시적으로 모르는 경우 모른다고 대답하라고 명령해야 합니다
- Prompt Engineering
  - chat gpt를 사용하는 경우 프롬프트를 어떻게 구성하는지가 답변에 많은 영향을 끼칩니다
  - 가장 좋은 프롬프트 작성 요령은 잘 작성된 프롬프트를 참고하는 방법입니다
    - https://github.com/f/awesome-chatgpt-prompts
  - 일반적으로 고정적인 instruction을 명시하며 role을 지정해주면 원하는 답변을 얻는데 도움됩니다.
  - 행동을 먼저 규정하고 뒤에 설명을 하는 방법 역시 도움됩니다
  - 한글로 작성된 프롬프트보다 영어로 작성된 프롬프트가 토큰 수를 덜 소모하여 비용이 덜 듭니다.



# 기술 스택

- Langchain
- chromadb
- chat gpt
  - prompt engineering
- fastapi


## 고려중
- ~~KoAlpaca~~(학습 리소스 부족, 지연 시간 등으로 X)
- ~~LoRA~~

## 포팅 매뉴얼
### openai api key
- ML/openaikey.py 파일에 openai api key를 넣어주세요
### 가상환경(virtual venv)
- python -m venv myvenv 로 가상환경을 만들어주세요
- source myvenv/Scripts/activate 로 가상환경을 실행해주세요
### 필수 패키지 설치
- pip install -r requirements.txt 로 필요한 패키지를 설치해주세요
### main.py run
- main.py 파일을 run 해주세요



## 현재 진행
### 월별 리포트
- 카드 정보 스크래이핑 중 (삼성카드)
- 카드 정보 임베딩

### Todo
- 월별 리포트 임베딩, req 포멧 확인


# 오류 정리
## pydantic 버전 오류

ERROR: pip's dependency resolver does not currently take into account all the packages that are installed. This behaviour is the source of the following dependency conflicts.
chromadb 0.4.10 requires pydantic<2.0,>=1.9, but you have pydantic 2.3.0 which is incompatible.
fastapi 0.99.1 requires pydantic!=1.8,!=1.8.1,<2.0.0,>=1.7.4, but you have pydantic 2.3.0 which is incompatible.
- 요약 : chromadb와 fastapi가 pydantic 버전을 특정 범위로 요구하는데 이를 충족하지 못하는 2.3.0이 설치되어서 충돌이 발생함
- 해결 : 1.9 설치


## TypeError: issubclass() arg 1 must be a class
- 요약 : 앞선 pydantic 버전을 수정하고 나서 발생한 오류 
- 자세히 : 2023 09 20 기준 chromadb는 1.10 이하의 pydantic 버전을강제, fast api는 2.0이상을 필요로 함 
  - fast api 버전을 낮추거나 chromadb 대신 다른 라이브러리를 사용
- 해결 : faiss 사용, python버전 3.9에서 3.11.5로 변경
- 비고 : 에러 메세지를 보고 무슨 원인인지 파악하기 어려움

## TypeError: expected string or bytes-like object, got 'list'
- 요약 :  text_splitter.split_text() 메서드에 문자열 전달되지 않아서 발생한 오류
- 자세히 : plaintext인 product.txt파일에 있어야 할 카드 상품 데이터가 비워져 있어서 발생한 오류로 다시 작성해주었습니다. 

## UnicodeDecodeError: 'cp949' codec can't decode byte 0x85 in position 8: illegal multibyte sequence
- 요약 :  인코딩 포맷으로 인한 오류
- 자세히 : 
```
Traceback (most recent call last):
  File "pjt경로\\myvenv\Lib\site-packages\langchain\document_loaders\text.py", line 41, in load
    text = f.read()
           ^^^^^^^^
UnicodeDecodeError: 'cp949' codec can't decode byte 0x85 in position 8: illegal multibyte sequence

The above exception was the direct cause of the following exception:

Traceback (most recent call last):
  File "pjt경로\ML\feedback\embedding.py", line 19, in <module>
    documents = loader.load()
                ^^^^^^^^^^^^^
  File "pjt경로\myvenv\Lib\site-packages\langchain\document_loaders\text.py", line 54, in load
    raise RuntimeError(f"Error loading {self.file_path}") from e
RuntimeError: Error loading ./product.txt
```
- 해결 : 에러 메세지를 보고 경로 오류인가 착각하여 경로를 수정하여도 해결되지 않아 txt 로 읽어본 후 경로문제가 아님을 확인하고 loader 수정하여 해결


# todo 
- chatbot readme 확인하고 erp, autogpt 비슷한 기능 적용