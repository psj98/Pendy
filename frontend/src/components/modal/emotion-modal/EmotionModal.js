import React, { useState } from 'react';
import './EmotionModal.css';

import { useNavigate } from 'react-router-dom';
import { CiFaceSmile, CiFaceMeh, CiFaceFrown } from 'react-icons/ci';
import { TfiFaceSmile, TfiFaceSad } from 'react-icons/tfi';
import useTodayList from '../../../hooks/useTodayList';
import handleEmotionRegist from '../../../utils/handleEmotionRegist';

const EmotionModal = ({ closeModal }) => {
  const regDate = '2023-09-25T00:00:00';
  //eslint-disable-next-line
  const { todayList, loading } = useTodayList(regDate);
  const [selectedOptions, setSelectedOptions] = useState([]);
  const [emotionList, setEmotionList] = useState([]);
  const navigate = useNavigate();

  if (loading) {
    return <div>Loading...</div>;
  }

  if (todayList.data.length === 0) {
    navigate('/diary', { replace: true });
  }

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
          navigate('/diary', { replace: true });
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
    <div className="emotion-modal-template">
      <div className="emotion-modal-title">
        <div className="emotion-modal-main-title">소비 자가진단</div>
        <div className="emotion-modal-sub-title">
          오늘 소비한 내역에 대한 내 감정을 표현해주세요!
        </div>
      </div>
      <div className="emotion-modal-content">
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
                <div className="emotion-modal-option-button">
                  <input
                    type="radio"
                    name={`chart-option-${index}`}
                    value={5}
                    checked={selectedOptions[index] === 5}
                    onChange={() => handleRadioChange(index, 5)}
                  />
                </div>
                <div className="emotion-modal-option-button">
                  <input
                    type="radio"
                    name={`chart-option-${index}`}
                    value={4}
                    checked={selectedOptions[index] === 4}
                    onChange={() => handleRadioChange(index, 4)}
                  />
                </div>
                <div className="emotion-modal-option-button">
                  <input
                    type="radio"
                    name={`chart-option-${index}`}
                    value={3}
                    checked={selectedOptions[index] === 3}
                    onChange={() => handleRadioChange(index, 3)}
                  />
                </div>
                <div className="emotion-modal-option-button">
                  <input
                    type="radio"
                    name={`chart-option-${index}`}
                    value={2}
                    checked={selectedOptions[index] === 2}
                    onChange={() => handleRadioChange(index, 2)}
                  />
                </div>
                <div className="emotion-modal-option-button">
                  <input
                    type="radio"
                    name={`chart-option-${index}`}
                    value={1}
                    checked={selectedOptions[index] === 1}
                    onChange={() => handleRadioChange(index, 1)}
                  />
                </div>
              </div>
              <div className="emotion-modal-choose">
                <TfiFaceSmile />
                <CiFaceSmile />
                <CiFaceMeh />
                <CiFaceFrown />
                <TfiFaceSad />
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
