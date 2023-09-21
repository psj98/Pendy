import authAxiosCreate from '../authAxiosCreate';

const handleGoalUpdate = (id, goalAmount, goalByCategory) => {
  const data = {
    id: id,
    goalAmount: goalAmount,
    goalByCategory: goalByCategory,
  };

  const serverUrl = '/api/goals';

  return authAxiosCreate.put(serverUrl, data);
};

export default handleGoalUpdate;
