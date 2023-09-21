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
      <div className="diary-form-date">{regDate}</div>
      <div className="diary-form-title">{title}</div>
      <div className="diary-form-content">{content}</div>
      <div className="diary-comment">
        <div className="diary-form-comment">{comment}</div>
        <div className="diary-form-stamp">
          <img src={stampImageSrc} alt={`Stamp ${stampType}`} />
        </div>
      </div>
    </div>
  );
};

export default DiaryForm;
