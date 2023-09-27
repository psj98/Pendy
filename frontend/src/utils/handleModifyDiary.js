// 일기 내용 수정
import authAxiosCreate from '../authAxiosCreate';

const handleModifyDiary = (id, content) => {
  console.log('handleModifyDiary');
  const data = {
    content: content,
  };

  const serverUrl = `/api/diaries/${id}`;

  return authAxiosCreate.put(serverUrl, data);
};

export default handleModifyDiary;
