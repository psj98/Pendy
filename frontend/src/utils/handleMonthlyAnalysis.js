import authAxiosCreate from '../authAxiosCreate';

const handleMonthlyAnalysis = (month) => {
  console.log('fetching monthlyAnalysis data from server...');
  const data = {
    //현재 년 월 (연,월,일 시 분 초)
    curMonth: month,
  };
  console.log('curMonth', month);

  const serverUrl = '/api/diaries/monthly-analysis';
  return authAxiosCreate.post(serverUrl, data);
};

export default handleMonthlyAnalysis;
