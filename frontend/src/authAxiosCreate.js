//baseUrl 설정을 위한 axiosCreate 함수
import axios from 'axios';

const authAxiosCreate = axios.create({
  //baseURL 설정
  baseURL: 'https://j9a410.p.ssafy.io',
  withCredentials: true,
});

authAxiosCreate.interceptors.request.use(
  async (request) => {
    const accesshToken = sessionStorage
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
