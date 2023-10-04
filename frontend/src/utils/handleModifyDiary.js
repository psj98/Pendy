// 일기 내용 수정
import authAxiosCreate from '../authAxiosCreate';

const handleModifyDiary = (id, editedTitle, editedContent) => {
  console.log('handleModifyDiary');
  const data = {
    title: editedTitle,
    content: editedContent,
  };

  const serverUrl = `/api/diaries/${id}`;

  return authAxiosCreate.put(serverUrl, data);
};

export default handleModifyDiary;
