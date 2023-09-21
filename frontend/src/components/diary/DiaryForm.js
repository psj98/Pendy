import React from 'react';
import './DiaryForm.css';

const DiaryForm = () => {
  return (
    <div className="diary-form">
      <div className="diary-form-date">일기 작성 날짜</div>
      <div className="diary-form-title">일기 제목</div>
      <div className="diary-form-content">일기 내용</div>
      <div className="diary-form-comment">코멘트</div>
    </div>
  );
};

export default DiaryForm;
