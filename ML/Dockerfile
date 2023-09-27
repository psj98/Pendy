FROM python:3.11
WORKDIR /fast
# COPY requirements.txt .
# RUN pip install -r /requirements.txt

COPY ./requirements.txt /fast/requirements.txt
RUN pip install --no-cache-dir --upgrade -r /fast/requirements.txt

COPY . /fast
EXPOSE 8000
# CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8000"]

CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8000"]
