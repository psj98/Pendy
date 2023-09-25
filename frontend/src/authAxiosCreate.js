//baseUrl 설정을 위한 axiosCreate 함수
import axios from 'axios';

const authAxiosCreate = axios.create({
  //baseURL 설정
  baseURL: 'http://localhost:8081',
  withCredentials: true,
});

authAxiosCreate.interceptors.request.use(
  async (request) => {
    const accesshToken = localStorage
      .getItem('accessToken')
      .replaceAll('"', '')
      .trim();
    request.headers['accessToken'] = `${accesshToken}`;
    authAxiosCreate.defaults.headers.common['accessToken'] = `${accesshToken}`;
    return request;
  },
  (error) => {
    return Promise.reject(error);
  },
);

export default authAxiosCreate;
