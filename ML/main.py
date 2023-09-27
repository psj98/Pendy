from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
import uvicorn

#Custom
from diaries.DiaryDTO import DiaryRequest
from feedback.ReportDTO import ReportRequest
from chatbot.ChatBotDto import ChatBotRequest
from diaries.Mkdiary import mkdiary
from feedback.Mkreport import mkreport


app = FastAPI()

# CORS 에러 설정
app.add_middleware(
    CORSMiddleware,
    allow_origins=["http://localhost:3000"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# @app.get("/")
# async def root():
#     return {"message": "Hello FastAPI"}
#
#
# @app.get("/hello/{name}")
# async def say_hello(name: str):
#     return {"message": f"Hello {name}"}

@app.post("/ml/create-diary")
def create_diary(request: DiaryRequest):
    ret = mkdiary(request)
    return ret

@app.post("/ml/create-report")
def create_report(request: ReportRequest):
    ret = mkreport(request)
    return ret

@app.post("/ml/chatbot")
def chatbot_communication(request: ChatBotRequest):
    # 챗봇 반환 메시지 구현
    print(request)

if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=8000)