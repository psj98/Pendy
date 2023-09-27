from pydantic import BaseModel

class ChatBotRequest(BaseModel):
    preMessage: str = ""#이전 메세지
    tempMessage: str = "오늘의 명언"#현재 메세지