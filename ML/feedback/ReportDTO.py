from pydantic import BaseModel
#pydantic : base mode을 상속받아 정의 -> 신뢰도, 안정성을 위해 사용
#DTO

class ReportRequest(BaseModel):
    categoryData: dict[str, list[int]] = {"categoryName": [1000, 20000]} # default 값

# food : 식비
# traffic : 교통
# online : 온라인 쇼핑
# offline : 오프라인 쇼핑
# cafe : 카페/간식
# housing : 주거/통신
# fashion : 패션/미용
# culture : 문화/여가