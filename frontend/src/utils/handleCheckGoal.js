// 월별 목표 생성 전, 목표 존재 유무 체크
import authAxiosCreate from '../authAxiosCreate';

const handleCheckGoal = () => {
  console.log('handleCheckGoal');

  const serverUrl = '/api/goals/check';

  return authAxiosCreate.get(serverUrl);
};

export default handleCheckGoal;
