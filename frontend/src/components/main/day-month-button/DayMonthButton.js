import React, { useState } from 'react';
import './DayMonthButton.css';

const DayMonthButton = ({ onOptionChange }) => {
  const [selectedOption, setSelectedOption] = useState('option1');

  const handleRadioChange = (event) => {
    const option = event.target.value;
    setSelectedOption(option);
    onOptionChange(option); // 선택된 옵션을 부모 컴포넌트로 전달
  };
  return (
    <div className="chart-button">
      <label className="chart-label">
        <input
          type="radio"
          name="chart-option"
          value="option1"
          defaultChecked={selectedOption === 'option1'}
          onChange={handleRadioChange}
        />
        <span className="chart-span">오늘</span>
      </label>
      <label className="chart-label">
        <input
          type="radio"
          name="chart-option"
          value="option2"
          checked={selectedOption === 'option2'}
          onChange={handleRadioChange}
        />
        <span className="chart-span">월간</span>
      </label>
    </div>
  );
};

export default DayMonthButton;
