//baseUrl 설정을 위한 axiosCreate 함수
import axios from 'axios';

const axiosCreate = axios.create({
  //baseURL 설정
  baseURL: 'localhost:8081',
  withCredentials: true,
});

export default axiosCreate;
