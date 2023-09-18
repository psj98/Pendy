
#랭체인
from langchain.llms import OpenAI

#GPT
from openaikey import apikey

#키 등록
import os
os.environ["OPENAI_API_KEY"] = apikey

# 데이터프레임
import pandas as pd

def mkreport(req):
    ret = {"message": "월별 AI 분석"}
    # tempurature : 0 ~ 1 로 높아질수록 랜덤한 답변 생성 / 창의력
    llm = OpenAI(temperature=1)

    # req 포맷 : json
    # req = {
    #     "food": [400, 10000],
    #     "traffic": [7000, 10000],
    #     "online": [0, 10000],
    #     "offline": [9000, 10000],
    #     "cafe": [0, 10000],
    #     "housing": [0, 10000],
    #     "fashion": [12000, 10000],
    #     "culture": [0, 10000]
    # }
    #소비 내역으로 input_txt 생성
    #고정적인 입력값
    # 프롬프트 수정 필요(영어로 role등 명시해서(기능은 잘 돌아감, 토큰이 많이듬))
    input_head_txt = (
        "입력은 아래의 형식과 같습니다\n"
        "카테고리명 : 사용 금액 , 제한 금액\n"
        "각 영단어는 매핑된 오른쪽 한글에 해당됩니다\n"

        "food : 식비\n"
        "traffic : 교통\n"
        "online : 온라인 쇼핑\n"
        "offline : 오프라인 쇼핑\n"
        "cafe : 카페/간식\n"
        "housing : 주거/통신\n"
        "fashion : 패션/미용\n"
        "culture : 문화/여가\n"
        "주어진 카테고리별 사용금액과 제한금액을 보고 공백 포함 150자 내외로 피드백을 한글로 컴팩트하게 작성해주세요\n"
    )

    # 중간 txt
    # req_cols = req.keys()
    consume_list = []
    for i in req.keys():
        consume_list += str(i) + ":"
        for j in req[i]:
            consume_list += str(j) + ","
        consume_list += "\n"
    consume_txt = ''.join(a for a in consume_list)

    # 최종적으로 GPT에 입력할 텍스트
    input_txt = input_head_txt+consume_txt
    result = llm(input_txt)

    ret["message"] = result
    return ret

    # food : 식비
    # traffic : 교통
    # online : 온라인 쇼핑
    # offline : 오프라인 쇼핑
    # cafe : 카페/간식
    # housing : 주거/통신
    # fashion : 패션/미용
    # culture : 문화/여가