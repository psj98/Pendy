// 개인정보 수정
import authAxiosCreate from '../authAxiosCreate';

const handleModify = (password, age, salary) => {
  console.log('handleModify');
  const data = {
    password: password,
    age: age,
    salary: salary,
  };

  const serverUrl = '/api/members';

  return authAxiosCreate.put(serverUrl, data);
};

export default handleModify;
