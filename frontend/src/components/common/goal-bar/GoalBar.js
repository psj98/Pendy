import React, { useState } from 'react';
import './GoalBar.css';

const GoalBar = () => {
  const current = 0;
  const goal = 230;
  const [midpoint] = useState(`${(current / goal) * 100}%`);

  return (
    <div className="goal-bar">
      <div className="amount-container">
        <div className="current-amount">현재 소비 금액</div>&nbsp;
        <div className="goal-amount">/ 목표 소비 금액</div>
      </div>
      <div className="rounded-box" style={{ '--midpoint': midpoint }}>
        <p>{Math.round((current / goal) * 100)}%</p>
      </div>
      <br />
      <div className="amount-container">
        <div className="goal-amount">오늘의 고정 지출</div>
      </div>
    </div>
  );
};

export default GoalBar;
