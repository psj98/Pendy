import React, { useState } from 'react';
// import './SettingTemplate.css';
// import handleModify from '../../utils/handleModify';
// import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import authAxiosCreate from '../../authAxiosCreate';

function ReceiptSendTemplate() {
  const [selectedFile, setSelectedFile] = useState(null);

  //   const token = sessionStorage.getItem('accessToken');

  // 헤더
  const headers = {
    // Authorization: 'Bearer ' + token,
    'Content-Type': 'multipart/form-data',
  };
  // 파일 선택 시 호출되는 함수
  const handleFileChange = (event) => {
    setSelectedFile(event.target.files[0]);
  };

  // 파일 업로드 버튼을 눌렀을 때 호출되는 함수
  const handleFileUpload = () => {
    if (!selectedFile) {
      alert('파일을 선택하세요.');
      return;
    }

    const formData = new FormData();
    formData.append('file', selectedFile);

    const serverUrl = '/api/s3/send';

    // 서버에 파일 업로드 요청 보내기 (여기서는 POST 요청 예제)
    // axios
    //   .post('/api/s3/send', formData, { headers })
    //   .then((response) => {
    //     console.log('파일 업로드 성공', response.data);
    //   })
    //   .catch((error) => {
    //     console.error('파일 업로드 실패', error);
    //   });
    authAxiosCreate.post(serverUrl, formData);
  };

  return (
    <div>
      <div
        style={{
          width: '200px',
          height: '200px',
          backgroundColor: 'lightgray',
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'center',
          cursor: 'pointer',
        }}
        onClick={() => document.getElementById('fileInput').click()} // div를 클릭하면 파일 선택 input 열기
      >
        {selectedFile ? <p>{selectedFile.name}</p> : <p>클릭하여 파일 선택</p>}
      </div>
      <input
        type="file"
        id="fileInput"
        accept=".jpg, .jpeg, .png .jfif" // 허용할 파일 확장자를 지정
        style={{ display: 'none' }}
        onChange={handleFileChange} // 파일 선택 시 호출되는 함수 연결
      />
      <button onClick={handleFileUpload}>파일 업로드</button>
    </div>
  );
}

export default ReceiptSendTemplate;
