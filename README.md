# 🤑 Pendy 🤑

> 생성형 AI를 이용한 금융 일기 서비스

![pendy](https://github.com/psj98/Pendy/assets/60167488/ccb7753a-b5d6-4a76-9bb7-8a2703e91a07)

일상생활을 하다 보면 많은 소비를 하게 됩니다.

하지만, 거래 내역을 다시 보면 이전의 소비가 어디에, 왜 소비했는지 매번 기억하기는 어렵습니다.

이를 위해 그날의 소비 내역을 자동으로 기록해주는 서비스가 필요했습니다.

또한, 과소비나 충동 소비로 인해 카드 정산을 하게 되면 '이번 달에 왜 이렇게 많이 썼지?' 라고 생각할 때가 있습니다.

이를 위해 카테고리별로 얼마나 소비했는지 알려주고, 이를 통해 과소비 및 충동 소비에 대한 경각심을 주는 서비스가 필요했습니다.

<br/>

**Pendy (펜디)** 는 이를 위해 개발된 AI 금융 일기 서비스입니다.

그날의 소비에 대한 감정을 기반으로 AI가 일기를 작성해주고, 어느 카테고리에서 많이 사용했는지 알려주는 기능을 제공합니다.

매달 설정한 목표를 잘 달성했는지를 알려주고, 이에 맞는 카드를 추천해주는 월간 분석 기능을 제공합니다.

카테고리별 일간 / 월간 소비를 시각화하여 사용자가 한눈에 볼 수 있도록 합니다.

금융 · 경제 등의 질문에 대한 답변을 제공하는 챗봇을 제공합니다.

<br/>

**Pendy (펜디)를 활용하여 소비를 줄여보세요!!**

<br/>

## 주요 기능

**📝 AI 일기**

- 일간 소비 내역과 감정을 바탕으로 **금융 일기**를 작성해줍니다.
- **감정**을 바탕으로 어떤 부분이 좋은 소비였는지 또는 나쁜 소비였는지 나타내줍니다.
- **코멘트**를 통해 과소비한 부분을 알려줍니다.
- 소비 내역을 바탕으로 **도장**을 찍어줍니다.
- 카테고리별로 **남은 목표 금액**을 시각화하여 알려줍니다.

**📊 월간 분석**

- 월별 소비 내역을 기반으로 **월간 분석 내용 및 피드백**을 제공해줍니다.
- 분석 내용을 기반으로 **맞춤형 금융 상품**을 추천해줍니다.
- **카테고리별 소비내역 통계**를 시각화하여 알려줍니다.

**📌 목표 설정**

- **월간 목표**를 설정할 수 있습니다.
- **카테고리별 소비내역 통계**를 시각화하여 알려줍니다.

**🤖 챗봇**

- 금융 · 경제 등의 질문에 대한 답변을 제공합니다.

<br/>

## 세부 기능

|구분|기능|설명|비고|
|:---|:---|:---|:---|
|1|목표 설정|월간 목표 설정||
|2|목표 조회|일간, 월간 목표 조회 및 그래프로 시각화||
|3|감정 등록|일기 등록 시, 감정을 같이 등록||
|4|일기 등록|거래 내역 및 감정을 기반으로 AI가 작성한 일기 등록||
|5|일기 조회|이전에 작성된 일기 내용 조회||
|6|캘린더 조회|일기 도장을 캘린더에 표시||
|7|월간 분석 조회|AI가 소비 내역을 기반으로 월간 분석 내용 제공||
|8|챗봇|금융 · 경제 등의 질문에 대한 답변 제공||
|9|1원 인증|계좌 등록 시, 1원 인증을 통해 사용자가 소유한 계좌인지 인증||
|10|계좌 등록|회원 가입 시, 사용자가 소유한 계좌 등록||

<br/>

## 아키텍처

![아키텍처](https://github.com/psj98/Pendy/assets/60167488/5873d771-0fc2-4a02-a2dd-f2f8b6ad7348)

<br/>

## ERD

![ERD](https://github.com/psj98/Pendy/assets/60167488/79d89015-f6f0-4def-bb26-4cdbe60de0a6)

<br/>

## 개발 설정

1. 포팅 메뉴얼 : [포팅 메뉴얼](https://github.com/psj98/Pendy/blob/dev/exec/pendi_%ED%8F%AC%ED%8C%85%EB%A7%A4%EB%89%B4%EC%96%BC.pdf)

2. 데이터베이스 덤프 파일 : [데이터베이스 덤프 파일](https://github.com/psj98/Pendy/blob/dev/exec/3.%20pendy_database_dump_v1.0.5.sql)

<br/>

## 협업 툴

- GitLab
  - 코드 버전 관리
- Jira
  - 매주 목표를 설정하여 Sprint 진행
  - 업무의 양에 따라 Story Point 배정
  - 업무의 우선순위에 따라 중요도 배정
- Notion
  - 기획, 설계, 기능 명세서, API 명세서 등 정리
  - Git Convention 정리
  - TroubleShooting 정리
- MatterMost
  - 공지 및 소통
  - Git Commit 연동

<br/>

## 프로젝트 진행 기간

> 2023.08.21 ~ 2023.10.06

- 1주차 (2023.08.21 ~ 2023.08.27)
  - 그라운드 룰 설정
  - 팀 소개
- 2주차 (2023.08.28 ~ 2023.09.03)
  - 주제 선정 및 토론
- 3주차 (2023.09.04 ~ 2023.09.10)
  - 세부 기획 선정
  - API 설계
  - ERD 설계
  - 아키텍처 설계
- 4주차 (2023.09.11 ~ 2023.09.17)
  - Front-End : 기본 틀 생성
  - Back-End : 핵심 기능 개발
- 5주차 (2023.09.18 ~ 2023.09.24)
  - Front-End : 핵심 기능 개발
  - Back-End : 부가 기능 개발
- 6주차 (2023.09.25 ~ 2023.10.01)
  - Front-End : 부가 기능 개발
  - Back-End : ML 연결 및 테스트
- 7주차 (2023.10.02 ~ 2023.10.06)
  - 개발 마무리
  - 오류 확인 및 처리
  - 최종 확인

<br/>

## 팀원 소개

- 강한 : PM, BE
- 권현우 : BE, CI/CD
- 김동현 : BE, ML
- 김윤우 : BE
- 박동휘 : FE
- 박성준 : BE
