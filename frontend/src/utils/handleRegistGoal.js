import authAxiosCreate from '../authAxiosCreate';

const handleRegistGoal = (id, goalAmount, goalByCategoryList) => {
  const data = {
    id: id,
    goalAmount: goalAmount,
    goalByCategoryList: goalByCategoryList,
  };

  console.log('gbc', id);
  console.log('gbc', goalAmount);
  console.log('gbc', goalByCategoryList);

  const serverUrl = '/api/goals/regist';

  return authAxiosCreate.post(serverUrl, data);
};

export default handleRegistGoal;
