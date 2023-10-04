import React, { useState } from 'react';
import './DiaryForm.css';
import handleModifyDiary from '../../utils/handleModifyDiary';

const DiaryForm = ({ id, regDate, title, content, comment, stampType }) => {
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
  const [isEditing, setIsEditing] = useState(false);
  const [editedTitle, setEditedTitle] = useState(title);
  const [editedContent, setEditedContent] = useState(content);

  const onModifyButtonClick = async (event) => {
    event.preventDefault();
    if (editedContent.length >= 255) {
      alert('내용은 255자 이하여야 합니다.');
      return;
    }
    try {
      const response = await handleModifyDiary(id, editedTitle, editedContent);
      console.log(response);
      if (response.data.code === 1000) {
        console.log('modify diary success');
        window.location.reload();
      } else {
        console.error(response.data.code + ' ' + response.data.message);
      }
    } catch (error) {
      console.error('modify diary failed');
    }
  };

  return (
    <div className="diary">
      <div className="diary-form">
        <div className="diary-date-title-stamp-div">
          <div className="diary-date-title-div">
            <div className="diary-form-date">
              <p className="diary-date diary-p">
                {regDate.substring(0, 4)}년 {regDate.substring(5, 7)}월{' '}
                {regDate.substring(8, 10)}일
              </p>
            </div>
            <div className="diary-form-title-div">
              <p className="diary-title diary-p">제목</p>
              {isEditing ? (
                <textarea
                  type="text"
                  className="editable-textarea-title"
                  value={editedTitle}
                  onChange={(e) => {
                    setEditedTitle(e.target.value);
                  }}
                />
              ) : (
                <p className="diary-title-content diary-p">{title}</p>
              )}
            </div>
          </div>
          <div className="diary-form-stamp">
            <img src={stampImageSrc} alt={`Stamp ${stampType}`} />
          </div>
        </div>
        <div className="diary-form-content-div">
          {isEditing ? (
            <textarea
              type="text"
              className="editable-textarea-content"
              value={editedContent}
              onChange={(e) => {
                setEditedContent(e.target.value);
              }}
            />
          ) : (
            <p className="diary-content diary-p">{content}</p>
          )}
        </div>
        <div className="diary-form-comment-div">
          <p className="diary-comment-text diary-p">추천</p>
          <p className="diary-comment diary-p">{comment}</p>
        </div>
      </div>
      <div className="diary-button-div">
        {isEditing ? (
          <button className="diary-button" onClick={onModifyButtonClick}>
            수정 완료
          </button>
        ) : (
          <button className="diary-button" onClick={() => setIsEditing(true)}>
            수정하기
          </button>
        )}
      </div>
    </div>
  );
};

export default DiaryForm;
