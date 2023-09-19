
#랭체인
from langchain.llms import OpenAI

#GPT
from openaikey import apikey

#키 등록
import os
os.environ["OPENAI_API_KEY"] = apikey

# 데이터프레임
import pandas as pd

#json
import json

def mkdiary(req):
    # ret = {
    #     "content": "오늘의 먹방 대모험, 오늘은 여러 군데에서 맛있는 것들을 먹어봤어! 먼저 서브웨이에서 5900원을 쓰고 먹었는데, 맛이 별로였어. 그 다음엔 매머드커피에서 2000원을 주고 뭔가를 먹었어, 그건 괜찮았단다! 그리고 바나프레소에서 2600원을 주고 먹었는데, 그것도 별로였어. 마지막으로 BBQ치킨에서 완전 대박이었어! 29000원을 주고 치킨을 먹었는데, 그건 정말 대만족!",
    #     "comment ": "우와, 너 정말 많이 먹었네! 근데, 예산이 10000원이라고 했잖아? 너무 많이 초과했어. 다음에는 예산 안에서 먹을 수 있는 맛있는 걸 찾아보자!",
    #     "stamp_type": 1
    # }
    ret = {
        "content": "오늘은 그냥 잠만 잤다", # text
        "comment ": "참 잘했어요", # text
        "stamp_type": 5 # int
    }

    # tempurature : 0 ~ 1 로 높아질수록 랜덤한 답변 생성 / 창의력
    llm = OpenAI(temperature=0.9)

    # req 포맷 : json
    # {
    # 		"ConsumptionLimit": 30000,
    # 		"ConsumptionDetails": {
    # 					"순대국밥": [9000, 3],
    # 					"메가커피": [2000, 4],
    # 		}
    # }

    # 받아온 req로 res_plain_txt 수정
        # 접근하기 쉽게 데이터프레임화
    # BaseModel로 col을 명시해서 col_name와도 문제가 없습니다
    req = json.loads(req)

    req_cols = list(req.keys())
    req_limit_amount = pd.DataFrame([req[req_cols[0]]])#[]로 감싼 이유_error: scalar values사용시 인덱스를 써주거나 list로 래핑
    req_consume_list = pd.DataFrame(req[req_cols[1]])

    limit_amount = req_limit_amount.iloc[0,0] #목표 금액
    # limit_amount = req_limit_amount["금액"][0]

    # feeling = ["매우 불만족","불만족","보통","만족","매우 만족"]
    feeling = ["Shit", "Dissatisfied", "Neutral", "Satisfied", "Stoked"]

    consume_list = "[Consume List]\n{"
    for i in req_consume_list.columns:
        consume_one = str(i) + ":" + str(req_consume_list[i][0]) + "Won " + str(
            feeling[(req_consume_list[i][1])]) + "\n"
        consume_list += consume_one
    consume_list += "}\n"

    #소비 내역으로 instructions 생성
    #고정적인 입력값
    # 영어 지시문 혹은 한국어 지시문 둘 다 작성해두었습니다
    instructions = """
        [Instructions]
        - Write a diary entry in Korean following the instructions below, referring to the 'Response Format' and 'Consume List'
        - Be sure to follow the 'Response Format' and do not respond otherwise.
        - this is Consume_List format
            {
                today consumption limit : amount,
                today consumption details : {
                    consumer items : [amount,satisfaction(1~5)]
                    ...
                }
            }
        [Response Format]
        {
        "content": "like an 75-years-old, write a fun  diary(title over 10 characters + content over 50 characters)",
        "comment": "As an elementary school tescher, give comment",
        "stamp_type": "assign a score judging the spending details from a range of 1 to 5, int".
        }
    """

    #구버전, 개요 확인용으로 남겨두었습니다
    # instructions_kor ="""
    #    아래의 지시대로 Response Format과 Consume List를 참고하여 일기를 써주세요
    #
    #
    #     [Response Format]
    #     {
    #     "content": 내용을 요약한 제목을 재미있는 10자 내외 제목 + 일기를 작성해주세요,
    #     "comment": 초등학교 선생님의 입장에서 반말로 목표금액을 고려하여 경제적인 관점에서 작성해주세요,
    #     "stamp_type": 1~5까지의 숫자 중 소비내역을 판단하여 점수를 매겨주세요
    #     }
    #
    # """
    # limit_amount_txt = "\n소비한도금액:"+limit_amount+"\n"
    limit_amount_txt = "\nSpendingLimit:" + limit_amount + "Won\n"

    # 최종적으로 GPT에 입력할 텍스트
    input_txt = instructions+consume_list+limit_amount_txt
    res_plain_txt = llm(input_txt)

    # OPEN AI로부터 res를 원하는 형식으로 못 받을 경우 에러 발생
    # ret["content"]
    # ret["comment"]
    # ret["stamp_type"]
    ret = json.loads(res_plain_txt)
    return ret

    # food : 식비
    # traffic : 교통
    # online : 온라인 쇼핑
    # offline : 오프라인 쇼핑
    # cafe : 카페/간식
    # housing : 주거/통신
    # fashion : 패션/미용
    # culture : 문화/여가