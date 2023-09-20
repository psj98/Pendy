import authAxiosCreate from '../authAxiosCreate';

const handleFetchData = (todayDate, todayMonth) => {
  console.log('fetching data from serve...');
  const data = {
    //달력 부른 시간 (연,월,일 시 분 초)
    todayDate: todayDate,
    //현재 날짜 (연,월)
    todayMonth: todayMonth,
  };
  console.log('todayDate', todayDate);
  console.log('todayMonth', todayMonth);
  const serverUrl = '/api/diaries/calendar';

  return authAxiosCreate.post(serverUrl, data);
};

export default handleFetchData;
