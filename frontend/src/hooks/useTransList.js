//계좌 내역 조회
import axiosCreate from '../axiosCreate';
import { useState, useEffect } from 'react';

function useTransList(accountNumber) {
  const [transList, setTransList] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const getTransList = async () => {
      try {
        const serverUrl = '/api/transactions/list';
        const response = await axiosCreate.post(serverUrl, {
          accountNumber: accountNumber,
        });
        setTransList(response.data);
        setLoading(false);
      } catch (error) {
        console.error('계좌 내역을 불러오는 중 에러가 발생했습니다.', error);
        setLoading(false);
      }
    };

    getTransList();
  }, [accountNumber]);

  return { transList, loading };
}

export default useTransList;
