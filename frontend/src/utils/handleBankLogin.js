// 계좌 로그인
import axiosCreate from '../axiosCreate';

const handleBankLogin = (accountNumber, bankCode, accountPassword) => {
  console.log('handleBankLogin');
  const data = {
    accountNumber: accountNumber,
    bankCode: bankCode,
    accountPassword: accountPassword,
  };

  const serverUrl = '/api/accounts/login';

  return axiosCreate.post(serverUrl, data);
};

export default handleBankLogin;
