import React, { useState } from 'react';
import './GoalBar.css';

const GoalBar = () => {
  const current = 120;
  const goal = 230;
  const [midpoint] = useState(`${(current / goal) * 100}%`);

  return (
    <div className="goal-bar" style={{ '--midpoint': midpoint }}>
      <p>{Math.round((current / goal) * 100)}%</p>
    </div>
  );
};

export default GoalBar;
