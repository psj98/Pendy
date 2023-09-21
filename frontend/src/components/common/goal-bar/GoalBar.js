import React, { useState } from 'react';
import './GoalBar.css';

const GoalBar = ({ color, current, goal, type }) => {
  const [midpoint] = useState(`${(current / goal) * 100}%`);
  const barType = ['rectangle'].includes(type) ? type : 'default';

  return (
    <div
      className={[`goal-bar-${barType}`].join(' ')}
      style={{ '--midpoint': midpoint, '--color': color }}
    >
      <p>{Math.round((current / goal) * 100)}%</p>
    </div>
  );
};

export default GoalBar;
