// 소비 내역 감정 평가 및 일기 생성
import authAxiosCreate from '../authAxiosCreate';

const handleEmotionRegist = async (emotionList) => {
  console.log('handleEmotionRegist');

  const serverUrl = '/api/diaries/regist';
  try {
    const response = await authAxiosCreate.post(serverUrl, emotionList);
    console.log(response);
    return response;
  } catch (error) {
    console.error('Goal Check failed', error);
    throw error;
  }
};

export default handleEmotionRegist;
