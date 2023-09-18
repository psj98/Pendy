from fastapi import FastAPI

#Custom
from diaries.DiaryDTO import DiaryRequest
from feedback.ReportDTO import ReportRequest
from diaries.Mkdiary import mkdiary
from feedback.Mkreport import mkreport



app = FastAPI()


@app.get("/")
async def root():
    return {"message": "Hello World"}


@app.get("/hello/{name}")
async def say_hello(name: str):
    return {"message": f"Hello {name}"}


@app.post("/creatediary")
def create_diary(request: DiaryRequest):
    ret = mkdiary(request)
    return ret

@app.post("/createreport")
def create_diary(request: ReportRequest):
    ret = mkreport(request)
    return ret
