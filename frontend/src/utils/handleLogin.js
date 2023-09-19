//로그인
import axiosCreate from '../axiosCreate';

const handleLogin = (email, password) => {
  console.log('handleLogin');
  const data = {
    email: email,
    password: password,
  };

  const serverUrl = '/api/members/login';

  return axiosCreate.post(serverUrl, data);
};

export default handleLogin;
