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
        console.log(response);
        setDiaryDetail(response.data);
      } catch (error) {
        console.error('일기 내용을 불러오는 중 에러가 발생했습니다.', error);
      } finally {
        setLoading(false);
      }
    };

    getDiaryDetail();
  }, [id]);

  return { diaryDetail, loading };
};

export default useDiaryDetail;
