from pydantic import BaseModel
#pydantic : base mode을 상속받아 정의 -> 신뢰도, 안정성을 위해 사용
#DTO

class ReportRequest(BaseModel):
    food : int = 0 # default값
    traffic : int = 0
    online : int = 0
    offline : int = 0
    cafe : int = 0
    housing : int = 0
    fashion : int = 0
    culture : int = 0

# food : 식비
# traffic : 교통
# online : 온라인 쇼핑
# offline : 오프라인 쇼핑
# cafe : 카페/간식
# housing : 주거/통신
# fashion : 패션/미용
# culture : 문화/여가