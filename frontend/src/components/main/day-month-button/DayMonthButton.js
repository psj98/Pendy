import React from 'react';

const DayMonthButton = () => {
  return (
    <div className="chart-button">
      <label>
        <input
          type="radio"
          name="chart-option"
          value="option1"
          defaultChecked
        />
        오늘
      </label>
      <label>
        <input type="radio" name="chart-option" value="option2" /> 월간
      </label>
    </div>
  );
};

export default DayMonthButton;
