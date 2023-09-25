import React, { useState } from 'react';
import './EmotionTemplate.css';
import useTodayList from '../../hooks/useTodayList';
import { CiFaceSmile, CiFaceMeh, CiFaceFrown } from 'react-icons/ci';
import { TfiFaceSmile, TfiFaceSad } from 'react-icons/tfi';
import handleEmotionRegist from '../../utils/handleEmotionRegist';
import { useNavigate } from 'react-router-dom';

const EmotionTemplate = () => {
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

  //console.log(todayList);
  //console.log(todayList.data);

  // emotionList에 데이터 추가
  //eslint-disable-next-line
  const addEmotionData = (transactionId, emotionId) => {
    const newEmotionData = {
      transactionId,
      emotionId,
    };
    setEmotionList([...emotionList, newEmotionData]);
  };

  // 각 옵션 변경
  const handleRadioChange = (index, option) => {
    const newSelectedOptions = [...selectedOptions];
    newSelectedOptions[index] = option;
    setSelectedOptions(newSelectedOptions);
  };

  // 감정 등록 및 일기 생성
  const onEmotionRegistClick = async (event) => {
    event.preventDefault();
    try {
      const response = await handleEmotionRegist(emotionList);
      console.log(response);
      if (response.data.code === 1000) {
        console.log('emotion regist success');
        navigate('/diary', { replace: true });
      } else {
        console.error(response.data.code + ' ' + response.data.message);
        alert('등록에 실패하셨습니다');
      }
    } catch (error) {
      console.error('emotion regist failed');
      alert('등록에 실패하셨습니다');
    }
  };

  return (
    <div className="emotion-template">
      <div className="emotion-title">
        <div className="emotion-main-title">소비 자가진단</div>
        <div className="emotion-sub-title">
          오늘 소비한 내역에 대한 내 감정을 표현해주세요!
        </div>
      </div>
      <div className="emotion-content">
        <div className="emotion-option">
          <div className="emotion-option-left">
            {todayList.data.map(
              (list, index) =>
                index % 2 === 0 && (
                  <div key={index} className="emotion-list">
                    <div className="emotion-content-container">
                      <div className="emotion-text">{list.name}</div>
                      <div className="emotion-text">
                        {list.transactionAmount}원
                      </div>
                    </div>
                    <div className="emotion-option-button-container">
                      <div className="emotion-option-button">
                        <input
                          type="radio"
                          name={`chart-option-${index}`}
                          value="option2"
                          checked={selectedOptions[index] === 'option1'}
                          onChange={() => handleRadioChange(index, 'option1')}
                        />
                      </div>
                      <div className="emotion-option-button">
                        <input
                          type="radio"
                          name={`chart-option-${index}`}
                          value="option2"
                          checked={selectedOptions[index] === 'option2'}
                          onChange={() => handleRadioChange(index, 'option2')}
                        />
                      </div>
                      <div className="emotion-option-button">
                        <input
                          type="radio"
                          name={`chart-option-${index}`}
                          value="option2"
                          checked={selectedOptions[index] === 'option3'}
                          onChange={() => handleRadioChange(index, 'option3')}
                        />
                      </div>
                      <div className="emotion-option-button">
                        <input
                          type="radio"
                          name={`chart-option-${index}`}
                          value="option2"
                          checked={selectedOptions[index] === 'option4'}
                          onChange={() => handleRadioChange(index, 'option4')}
                        />
                      </div>
                      <div className="emotion-option-button">
                        <input
                          type="radio"
                          name={`chart-option-${index}`}
                          value="option2"
                          checked={selectedOptions[index] === 'option5'}
                          onChange={() => handleRadioChange(index, 'option5')}
                        />
                      </div>
                    </div>
                    <div className="emotion-choose">
                      <TfiFaceSmile />
                      <CiFaceSmile />
                      <CiFaceMeh />
                      <CiFaceFrown />
                      <TfiFaceSad />
                    </div>
                  </div>
                ),
            )}
          </div>
          <div className="emotion-option-right">
            {todayList.data.map(
              (list, index) =>
                index % 2 !== 0 && (
                  <div key={index} className="emotion-list">
                    <div className="emotion-content-container">
                      <div className="emotion-text">{list.name}</div>
                      <div className="emotion-text">
                        {list.transactionAmount}원
                      </div>
                    </div>
                    <div className="emotion-option-button-container">
                      <div className="emotion-option-button">
                        <input
                          type="radio"
                          name={`chart-option-${index}`}
                          value="option2"
                          checked={selectedOptions[index] === 'option1'}
                          onChange={() => handleRadioChange(index, 'option1')}
                        />
                      </div>
                      <div className="emotion-option-button">
                        <input
                          type="radio"
                          name={`chart-option-${index}`}
                          value="option2"
                          checked={selectedOptions[index] === 'option2'}
                          onChange={() => handleRadioChange(index, 'option2')}
                        />
                      </div>
                      <div className="emotion-option-button">
                        <input
                          type="radio"
                          name={`chart-option-${index}`}
                          value="option2"
                          checked={selectedOptions[index] === 'option3'}
                          onChange={() => handleRadioChange(index, 'option3')}
                        />
                      </div>
                      <div className="emotion-option-button">
                        <input
                          type="radio"
                          name={`chart-option-${index}`}
                          value="option2"
                          checked={selectedOptions[index] === 'option4'}
                          onChange={() => handleRadioChange(index, 'option4')}
                        />
                      </div>
                      <div className="emotion-option-button">
                        <input
                          type="radio"
                          name={`chart-option-${index}`}
                          value="option2"
                          checked={selectedOptions[index] === 'option5'}
                          onChange={() => handleRadioChange(index, 'option5')}
                        />
                      </div>
                    </div>
                    <div className="emotion-choose">
                      <TfiFaceSmile />
                      <CiFaceSmile />
                      <CiFaceMeh />
                      <CiFaceFrown />
                      <TfiFaceSad />
                    </div>
                  </div>
                ),
            )}
          </div>
        </div>
        <div className="emotion-button-container">
          <button className="emotion-button" onClick={onEmotionRegistClick}>
            일기 작성
          </button>
        </div>
      </div>
    </div>
  );
};

export default EmotionTemplate;
