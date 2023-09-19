from pydantic import BaseModel
#pydantic : base mode을 상속받아 정의 -> 신뢰도, 안정성을 위해 사용
#DTO

class DiaryRequest(BaseModel):
    ConsumptionLimit : int = 0 # default값
    ConsumptionDetails : dict

# ConsumptionLimit : 소비 한도
# ConsumptionDetails : 소비 내역

# {
# 		"ConsumptionLimit": 30000,
# 		"ConsumptionDetails": {
# 					"순대국밥": [9000, 3],
# 					"메가커피": [2000, 4],
# 		}
# }