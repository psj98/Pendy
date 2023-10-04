//baseUrl 설정을 위한 axiosCreate 함수
import axios from 'axios';

const axiosCreate = axios.create({
  //baseURL 설정
  baseURL: 'https://j9a410.p.ssafy.io',
  withCredentials: true,
});

export default axiosCreate;
