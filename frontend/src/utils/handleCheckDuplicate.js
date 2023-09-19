// 중복확인
import axiosCreate from '../axiosCreate';

const handleDuplicate = (email) => {
  console.log('handleDuplicate');
  const data = {
    email: email,
  };

  const serverUrl = '/api/members/duplicate-check';

  return axiosCreate.post(serverUrl, data);
};

export default handleDuplicate;
