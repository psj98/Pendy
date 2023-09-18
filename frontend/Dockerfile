FROM node:18.16.1
WORKDIR /app
COPY package.json .
RUN npm install
# 현재 디렉토리의 모든 파일을 도커 컨테이너 워킹 디렉토리에 저장
COPY . .
EXPOSE 3000
CMD ["npm","start"]
