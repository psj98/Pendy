from pydantic import BaseModel

class ChatBotRequest(BaseModel):
    message: str = "기본 내용"