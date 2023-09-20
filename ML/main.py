from fastapi import FastAPI
import uvicorn

#Custom
from diaries.DiaryDTO import DiaryRequest
from feedback.ReportDTO import ReportRequest
from diaries.Mkdiary import mkdiary
from feedback.Mkreport import mkreport


app = FastAPI()

# @app.get("/")
# async def root():
#     return {"message": "Hello World"}
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


if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=8000)