import React from 'react';
import './GoalBar.css';

const GoalBar = ({ color, current=0, goal=1, type, textcolor}) => {
    // NAN 문제 해결
    if (goal === 0){
        goal = 1;
    }
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
