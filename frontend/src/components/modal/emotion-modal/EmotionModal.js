//추가 소비내역 감정 등록 모달
import React, { useState } from 'react';
import './EmotionModal.css';

import { useNavigate } from 'react-router-dom';
import handleEmotionRegist from '../../../utils/handleEmotionRegist';

const EmotionModal = ({ closeModal, todayList }) => {
  const emoji = [1, 2, 3, 4, 5];
  const [selectedOptions, setSelectedOptions] = useState([]);
  const [emotionList, setEmotionList] = useState([]);
  const navigate = useNavigate();

  // emotionList에 데이터 추가
  const addEmotionData = (id, emotionId) => {
    const existingIndex = emotionList.findIndex(
      (item) => item.transactionId === id,
    );
    if (existingIndex !== -1) {
      const updatedEmotionList = [...emotionList];
      updatedEmotionList[existingIndex] = {
        transactionId: id,
        emotionId: emotionId,
      };
      setEmotionList(updatedEmotionList);
    } else {
      const newEmotionData = {
        transactionId: id,
        emotionId: emotionId,
      };
      setEmotionList([...emotionList, newEmotionData]);
    }
  };

  // 각 옵션 변경
  const handleRadioChange = (index, option) => {
    const newSelectedOptions = [...selectedOptions];
    newSelectedOptions[index] = option;
    setSelectedOptions(newSelectedOptions);

    const transactionId = todayList.data[index].id;
    addEmotionData(transactionId, option);
  };

  // 감정 등록 및 일기 생성
  const onEmotionRegistClick = async (event) => {
    event.preventDefault();
    console.log(emotionList);
    if (todayList.data.length === emotionList.length) {
      try {
        const response = await handleEmotionRegist(emotionList);
        console.log(response);
        if (response.data.code === 1000) {
          console.log('emotion regist success');
          await closeModal();
          navigate('/');
        } else {
          console.error(response.data.code + ' ' + response.data.message);
          alert('등록에 실패하셨습니다');
        }
      } catch (error) {
        console.error('emotion regist failed');
        alert('등록에 실패하셨습니다');
      }
    } else {
      alert('아직 평가하지 않은 항목이 있습니다.');
    }
  };

  return (
    <div className="emotion-modal">
      <div className="emotion-modal-content">
        <div className="emotion-modal-title">
          <h1 className="emotion-modal-main-title">추가 소비내역</h1>
          <p className="emotion-modal-sub-title">
            일기 작성 후 추가 소비내역이 있어요. 추가 소비내역에 대해 감정을
            표현해주세요.
          </p>
        </div>
        <div className="emotion-modal-option">
          {todayList.data.map((list, index) => (
            <div key={index} className="emotion-modal-list">
              <div className="emotion-modal-content-container">
                <div className="emotion-modal-text">{list.name}</div>
                <div className="emotion-modal-text">
                  {list.transactionAmount}원
                </div>
              </div>
              <div className="emotion-modal-option-button-container">
                {emoji.map((num) => (
                  <div className="emotion-modal-option-button">
                    <input
                      type="radio"
                      name={`chart-option-${index}`}
                      value={num.num}
                      checked={selectedOptions[index] === num}
                      onChange={() => handleRadioChange(index, num)}
                    />
                  </div>
                ))}
              </div>
              <div className="emotion-modal-choose">
                <img
                  className="face-emoji"
                  src="/emoji-img/emoji-terrible.png"
                  alt="매우나쁨"
                />
                <img
                  className="face-emoji"
                  src="/emoji-img/emoji-bad.png"
                  alt="나쁨"
                />
                <img
                  className="face-emoji"
                  src="/emoji-img/emoji-soso.png"
                  alt="보통"
                />
                <img
                  className="face-emoji"
                  src="/emoji-img/emoji-good.png"
                  alt="좋음"
                />
                <img
                  className="face-emoji"
                  src="/emoji-img/emoji-amazing.png"
                  alt="매우좋음"
                />
              </div>
            </div>
          ))}
        </div>
        <div className="emotion-modal-button-container">
          <button
            className="emotion-modal-button"
            onClick={onEmotionRegistClick}
          >
            일기 작성
          </button>
        </div>
      </div>
    </div>
  );
};

export default EmotionModal;
