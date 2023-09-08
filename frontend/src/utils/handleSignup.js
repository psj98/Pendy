// 회원가입 (테스트 필요)
import axiosCreate from '../axiosCreate';

const handleSignup = (email, password, name, age, salary, accounts) => {
  console.log('handleSignup');
  const data = {
    email: email,
    password: password,
    name: name,
    age: age,
    salary: salary,
    accounts: accounts,
  };

  const serverUrl = '/api/members/join';

  return axiosCreate.post(serverUrl, data);
};

export default handleSignup;
