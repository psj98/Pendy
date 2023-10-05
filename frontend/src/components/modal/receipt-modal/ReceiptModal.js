import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './ReceiptModal.css';

import handleFileUpload from '../../../utils/handleFileUpload';

const ReceiptModal = ({ closeModal }) => {
  const [selectedFile, setSelectedFile] = useState(null);
  const navigate = useNavigate();

  // 파일 변경
  const fileChange = (event) => {
    setSelectedFile(event.target.files[0]);
  };

  // 파일 업로드
  const fileUpload = () => {
    if (!selectedFile) {
      alert('파일을 선택하세요.');
      return;
    }

    handleFileUpload(selectedFile);

    navigate('/');

    closeModal();
  };

  return (
    <div className="receipt-modal">
      <div className="receipt-modal-content">
        {/* 모달 종료 버튼 */}
        <button className="receipt-close-button" onClick={closeModal}>
          X
        </button>

        {/* 제목 */}
        <div className="receipt-modal-title">
          <h2 className="receipt-modal-main-title">영수증 등록</h2>
          <p className="receipt-modal-sub-title">
            영수증 사진을 등록해서 거래 내역에 추가할 수 있어요.
          </p>
        </div>

        {/* 파일 div */}
        <div
          className="receipt-file-div"
          onClick={() => document.getElementById('fileInput').click()} // div를 클릭하면 파일 선택 input 열기
        >
          {selectedFile ? (
            <p>{selectedFile.name}</p>
          ) : (
            <p>클릭하여 파일 선택</p>
          )}
        </div>
        <input
          type="file"
          className="receipt-file-input"
          id="fileInput"
          accept=".jpg, .jpeg, .png .jfif" // 허용할 파일 확장자를 지정
          style={{ display: 'none' }}
          onChange={fileChange} // 파일 선택 시 호출되는 함수 연결
        />

        {/* 파일 업로드 버튼 */}
        <button className="receipt-file-upload-btn" onClick={fileUpload}>
          파일 업로드
        </button>
      </div>
    </div>
  );
};

export default ReceiptModal;
