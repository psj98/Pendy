//로그인 (테스트 필요)
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

// import axiosCreate from '../axiosCreate';

// const handleLogin = async (email, password) => {
//   try {
//     console.log('handleLogin');
//     const data = {
//       email: email,
//       password: password,
//     };

//     const serverUrl = '/api/members/login';

//     const response = await axiosCreate.post(serverUrl, data);
//     return response.data;
//   } catch (error) {
//     console.error('로그인 중 오류 발생:', error);
//     throw error;
//   }
// };

// export default handleLogin;
