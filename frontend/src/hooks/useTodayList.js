// 소비 자가진단을 위한 조회
import authAxiosCreate from '../authAxiosCreate';
import { useState, useEffect } from 'react';

function useTodayList(regDate) {
  console.log('useTodayList');
  const [todayList, setTodayList] = useState([]);
  const [todayLoading, setTodayLoading] = useState(true);

  useEffect(() => {
    const getTodayList = async () => {
      try {
        const serverUrl = '/api/transactions/today-list';
        const response = await authAxiosCreate.post(serverUrl, {
          lastRegDate: regDate,
        });
        if (response.data.code === 1000) {
          console.log('invoke todayList success');
          setTodayList(response.data);
        } else {
          console.error(response.data.code + ' ' + response.data.message);
        }
        setTodayLoading(false);
      } catch (error) {
        console.error('invoke todayList failed', error);
        setTodayLoading(false);
      }
    };
    getTodayList();
  }, []);

  return { todayList, todayLoading };
}

export default useTodayList;
