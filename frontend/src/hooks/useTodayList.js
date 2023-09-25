// 소비 자가진단을 위한 조회
import authAxiosCreate from '../authAxiosCreate';
import { useState, useEffect } from 'react';

function useTodayList(regDate) {
  console.log('useTodayList');
  const [todayList, setTodayList] = useState([]);
  const [loading, setLoading] = useState(true);
  console.log('regDate', regDate);

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
          setLoading(false);
        } else {
          console.error(response.data.code + ' ' + response.data.message);
        }
      } catch (error) {
        console.error('invoke todayList failed', error);
        setLoading(false);
      }
    };
    getTodayList();
  }, [regDate]);

  return { todayList, loading };
}

export default useTodayList;
