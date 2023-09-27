// 일기 생성 후 조회
import authAxiosCreate from '../authAxiosCreate';
import { useEffect, useState } from 'react';

const useDiaryDetail = (id) => {
  console.log('useDiaryDetail');
  const [diaryDetail, setDiaryDetail] = useState([]);
  const [diaryLoading, setDiaryLoading] = useState(true);

  useEffect(() => {
    const getDiaryDetail = async () => {
      try {
        const regDate = sessionStorage.getItem('regDate');
        const data = {
          id: id,
          regDate: regDate,
        };
        const serverUrl = '/api/diaries/after';
        const response = await authAxiosCreate.post(serverUrl, data);
        if (response.data.code === 1000) {
          console.log('load diary detail success');
          setDiaryDetail(response.data);
        } else {
          console.error(response.data.code + ' ' + response.data.message);
        }
        setDiaryLoading(false);
      } catch (error) {
        console.error('load diary detail failed', error);
        setDiaryLoading(false);
      }
    };

    getDiaryDetail();
  }, [id]);

  return { diaryDetail, diaryLoading };
};

export default useDiaryDetail;
