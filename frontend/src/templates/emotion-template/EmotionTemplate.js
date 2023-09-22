import React from 'react';
import './EmotionTemplate.css';
import useTodayList from '../../hooks/useTodayList';
import { CiFaceSmile, CiFaceMeh, CiFaceFrown } from 'react-icons/ci';
import { TfiFaceSmile, TfiFaceSad } from 'react-icons/tfi';

const EmotionTemplate = () => {
  const regDate = '2023-09-22T00:00:00';
  const { response, loading } = useTodayList(regDate);
  console.log(response);
  console.log(response.todayList);
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
          {response.todayList.data.map((account, index) => (
            <div key={index} className="signup-input2">
              <input
                type="number"
                className="input"
                placeholder={`계좌 ${index + 1}`}
                variant="outlined"
                value={account}
              />
            </div>
          ))}
          <TfiFaceSmile />
          <CiFaceSmile />
          <CiFaceMeh />
          <CiFaceFrown />
          <TfiFaceSad />
        </div>
      </div>
      <div className="emotion-button"></div>
    </div>
  );
};

export default EmotionTemplate;
