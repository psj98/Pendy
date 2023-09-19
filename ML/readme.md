(개발중)

# Contents
- 개요
- 주요 기능
- 기술 스택(고려중 포함)

# 개요
- fast api 이용 api 서버로 운용
- 인터프리터 : python 3.9


# 주요 기능
## 일기 생성_ diaries, 월별 보고서 작성 _ monthly-analysis/monthly-feedback
- gpt + langchain을 이용해 구현된 상태
- 월별 보고서의 경우 임베딩한 카드 정보를 기반으로 소비패턴에 적합한 카드를 추천합니다


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

## 현재 진행
### 월별 리포트
- 카드 정보 스크래이핑 중 (삼성카드)
- 카드 정보 임베딩