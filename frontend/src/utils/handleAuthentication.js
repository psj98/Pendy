// 계좌 인증
import axiosCreate from '../axiosCreate';

const handleAuthentication = (accountNumber, authCode) => {
  console.log('handleAuthentication');
  const data = {
    accountNumber: accountNumber,
    authCode: authCode,
  };
  const serverUrl = '/api/accounts/certification';

  return axiosCreate.post(serverUrl, data);
};

export default handleAuthentication;
