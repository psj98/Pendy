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
        const data = {
          lastRegDate: regDate,
        };
        const serverUrl = '/api/transactions/today-list';
        const response = await authAxiosCreate.post(serverUrl, data);
        setTodayList(response.data);
        setLoading(false);
      } catch (error) {
        console.error('소비 내역을 불러오는 중 에러가 발생했습니다.', error);
        setLoading(false);
      }
    };

    getTodayList();
  }, []);

  return { todayList, loading };
}

export default useTodayList;
