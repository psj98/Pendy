// 계좌 로그인
import authAxiosCreate from '../authAxiosCreate';

const handleFileUpload = (file) => {
  console.log('handleFileUpload');

  const formData = new FormData();
  formData.append('file', file);

  authAxiosCreate.post('/api/s3/send', formData);

  return;
};

export default handleFileUpload;
