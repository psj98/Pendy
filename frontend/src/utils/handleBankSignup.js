// 계좌 생성
import axiosCreate from '../axiosCreate';

const handleBankSignup = (
  bankCode,
  accountNumber,
  accountPassword,
  balance,
) => {
  console.log('handleBankSignup');
  const data = {
    bankCode: bankCode,
    accountNumber: accountNumber,
    accountPassword: accountPassword,
    balance: balance,
  };

  const serverUrl = '/api/accounts/regist';

  return axiosCreate.post(serverUrl, data);
};

export default handleBankSignup;
