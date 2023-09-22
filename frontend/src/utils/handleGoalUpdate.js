import authAxiosCreate from '../authAxiosCreate';

const handleGoalUpdate = (id, goalAmount, goalByCategoryList) => {
  const data = {
    id: id,
    goalAmount: goalAmount,
    goalByCategoryList: goalByCategoryList,
  };

  console.log('gbc', id);
  console.log('gbc', goalAmount);
  console.log('gbc', goalByCategoryList);

  const serverUrl = '/api/goals';

  return authAxiosCreate.put(serverUrl, data);
};

export default handleGoalUpdate;
