// 계좌 생성
import axiosCreate from '../axiosCreate';

const handleBankSignup = (
  accountNumber,
  bankCode,
  accountPassword,
  balance,
) => {
  console.log('handleBankSignup');
  const data = {
    accountNumber: accountNumber,
    bankCode: bankCode,
    accountPassword: accountPassword,
    balance: balance,
  };

  const serverUrl = '/api/accounts/regist';

  return axiosCreate.post(serverUrl, data);
};

export default handleBankSignup;
