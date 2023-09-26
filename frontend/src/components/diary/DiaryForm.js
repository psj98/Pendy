import React from 'react';
import './DiaryForm.css';

const DiaryForm = ({ regDate, title, content, comment, stampType }) => {
  let stampImageSrc = '';
  switch (stampType) {
    case 1:
      stampImageSrc = '/stamp1.png';
      break;
    case 2:
      stampImageSrc = '/stamp2.png';
      break;
    case 3:
      stampImageSrc = '/stamp3.png';
      break;
    case 4:
      stampImageSrc = '/stamp4.png';
      break;
    case 5:
      stampImageSrc = '/stamp5.png';
      break;
    default:
  }

  return (
    <div className="diary-form">
      <div className="diary-form-date">
        {regDate.substring(0, 4)}년&nbsp;{regDate.substring(5, 7)}월&nbsp;
        {regDate.substring(8, 10)}일
      </div>
      <div className="diary-form-title">
        <div className="diary-title">제목</div>
        <div className="diary-title-content">{title}</div>
      </div>
      <div className="diary-form-content">
        <div className="diary-content">{content}</div>
      </div>
      <div className="diary-form-comment">
        <div className="diary-comment-text">추천</div>
        <div className="diary-comment">{comment}</div>
        <div className="diary-form-stamp">
          <img src={stampImageSrc} alt={`Stamp ${stampType}`} />
        </div>
      </div>
    </div>
  );
};

export default DiaryForm;
