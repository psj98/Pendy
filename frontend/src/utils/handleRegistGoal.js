import authAxiosCreate from '../authAxiosCreate';

const handleRegistGoal = (goalAmount, goal) => {
  const data = {
    goalAmount: goalAmount,
    goalByCategoryList: goal,
  };

  console.log('gbc', data);

  const serverUrl = '/api/goals/regist';

  return authAxiosCreate.post(serverUrl, data);
};

export default handleRegistGoal;
