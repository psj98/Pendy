import React from 'react';
import './GoalBar.css';

const GoalBar = ({ color, current, goal, type, textcolor }) => {
  const midpoint = (current / goal) * 100 + '%';
  const barType = type != null ? type : 'default';
  const textColor = textcolor === 'red' ? 'red' : 'black';

  return (
    <div
      className={[`goal-bar-${barType}`].join(' ')}
      style={{
        '--midpoint': midpoint,
        '--color': color,
        '--textcolor': textColor,
      }}
    >
      <p>{Math.round((current / goal) * 100)}%</p>
    </div>
  );
};

export default GoalBar;
