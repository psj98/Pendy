from pydantic import BaseModel
#pydantic : base mode을 상속받아 정의 -> 신뢰도, 안정성을 위해 사용
#DTO

class DiaryRequest(BaseModel):
    consumptionLimits: int = 0
    consumptionDetails: dict[str, list[int]] = {"abcd": [1000, 1]}

# ConsumptionLimit : 소비 한도
# ConsumptionDetails : 소비 내역

# {
# 		"ConsumptionLimit": 30000,
# 		"ConsumptionDetails": {
# 					"순대국밥": [9000, 3],
# 					"메가커피": [2000, 4],
# 		}
# }