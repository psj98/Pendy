import React, { useState } from 'react';
import './EmotionTemplate.css';
import useTodayList from '../../hooks/useTodayList';
import { CiFaceSmile, CiFaceMeh, CiFaceFrown } from 'react-icons/ci';
import { TfiFaceSmile, TfiFaceSad } from 'react-icons/tfi';

const EmotionTemplate = () => {
  const regDate = '2023-09-22T00:00:00';
  //eslint-disable-next-line
  const { response, loading } = useTodayList(regDate);
  
  console.log(response);
  console.log(response.todayList);

  const [selectedOption, setSelectedOption] = useState('option3');

  const handleRadioChange = (event) => {
    const option = event.target.value;
    setSelectedOption(option);
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
          {response.todayList.data.map((account, index) => (
            <div key={index} className="signup-input2">
              <input
                type="number"
                className="input"
                placeholder={`계좌 ${index + 1}`}
                variant="outlined"
                value={account}
              />
              <div className='emotion-option-button'>
                <input
          type="radio"
          name="chart-option"
          value="option2"
          checked={selectedOption === 'option1'}
          onChange={handleRadioChange}
        />
        <input
          type="radio"
          name="chart-option"
          value="option2"
          checked={selectedOption === 'option2'}
          onChange={handleRadioChange}
        />
        <input
          type="radio"
          name="chart-option"
          value="option2"
          checked={selectedOption === 'option3'}
          onChange={handleRadioChange}
        />
        <input
          type="radio"
          name="chart-option"
          value="option2"
          checked={selectedOption === 'option4'}
          onChange={handleRadioChange}
        />
        <input
          type="radio"
          name="chart-option"
          value="option2"
          checked={selectedOption === 'option5'}
          onChange={handleRadioChange}
        />
              </div>
              <div className='emotion-choose'>
          <TfiFaceSmile />
          <CiFaceSmile />
          <CiFaceMeh />
          <CiFaceFrown />
          <TfiFaceSad />
          </div>
            </div>
          ))}
        </div>
      </div>
      <div className="emotion-button"></div>
    </div>
  );
};

export default EmotionTemplate;
