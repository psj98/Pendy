from pydantic import BaseModel

class ChatBotRequest(BaseModel):
    # preMessage: str = ""#이전 메세지
    # tempMessage: str = "오늘의 명언"#현재 메세지
    chatBotMessage: dict[str, str] = {"preMessage": "previous message", "tempMessage": "temp message"}