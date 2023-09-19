// 사용내역 등록
import axiosCreate from '../axiosCreate';

const handleBankRegist = (
  accountNumber,
  categoryId,
  transactionName,
  transactionAmount,
  transactionType,
  balance,
) => {
  console.log('handleBankRegist');
  const data = {
    accountNumber: accountNumber,
    categoryId: categoryId,
    transactionName: transactionName,
    transactionAmount: transactionAmount,
    transactionType: transactionType,
    balance: balance,
  };

  const serverUrl = '/api/transactions/regist';

  return axiosCreate.post(serverUrl, data);
};

export default handleBankRegist;
