// 일기 생성 후 조회
import authAxiosCreate from '../authAxiosCreate';
import { useEffect, useState } from 'react';

const useDiaryDetail = (id) => {
  console.log('useDiaryDetail');
  console.log(id);
  const [diaryDetail, setDiaryDetail] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const getDiaryDetail = async () => {
      try {
        const regDate = new Date();
        const data = {
          id: id,
          regDate: regDate,
        };

        const serverUrl = '/api/diaries/after';
        const response = await authAxiosCreate.post(serverUrl, data);
        if (response.data.code === 1000) {
          console.log('load diary detail success');
          setDiaryDetail(response.data);
          setLoading(false);
        } else {
          console.error(response.data.code + ' ' + response.data.message);
          setLoading(false);
        }
      } catch (error) {
        console.error('load diary detail failed', error);
        setLoading(false);
      }
    };

    getDiaryDetail();
  }, [id]);

  return { diaryDetail, loading };
};

export default useDiaryDetail;
