import authAxiosCreate from '../authAxiosCreate';

const handleGoalDetail = (age, salary, curDate) => {
  const data = {
    age: age,
    salary: salary,
    curDate: curDate,
  };

  const serverUrl = '/api/goals/detail';

  return authAxiosCreate.post(serverUrl, data);
};

export default handleGoalDetail;
