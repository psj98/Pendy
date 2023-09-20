// 월별 목표 생성 전, 목표 존재 유무 체크
import authAxiosCreate from '../authAxiosCreate';

const handleCheckGoal = async () => {
  console.log('handleCheckGoal');

  const serverUrl = '/api/goals/check';
  try {
    const response = await authAxiosCreate.get(serverUrl);
    const isCheckGoal = response.data.data.check;
    return isCheckGoal;
  } catch (error) {
    console.error('Goal Check failed', error);
    throw error;
  }
};

export default handleCheckGoal;
