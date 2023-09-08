//baseUrl 설정을 위한 axiosCreate 함수
import axios from 'axios';

const axiosCreate = axios.create({
  //baseURL 설정
  baseURL: 'http://localhost:8080',
  withCredentials: true,
});

export default axiosCreate;
