// 1원 인증 요청
import axiosCreate from '../axiosCreate';

const handleSendCode = (bankCode, accountNumber) => {
  console.log('handleSendCode');
  const data = {
    bankCode: bankCode,
    accountNumber: accountNumber,
  };
  const serverUrl = '/api/accounts/send-code';

  return axiosCreate.post(serverUrl, data);
};

export default handleSendCode;
