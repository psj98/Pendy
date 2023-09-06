import React from 'react';
import './CalenderDays.css';

const CalenderDays = () => {
  const days = [];
  const date = ['Sun', 'Mon', 'Thu', 'Wed', 'Thrs', 'Fri', 'Sat'];

  for (let i = 0; i < 7; i++) {
    days.push(
      <div className="calender-col" key={i}>
        {date[i]}
      </div>,
    );
  }

  return <div className="calender-days">{days}</div>;
};

export default CalenderDays;
