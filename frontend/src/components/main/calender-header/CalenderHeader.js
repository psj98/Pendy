import React from 'react';
import './CalenderHeader.css';
import { Icon } from '@iconify/react';
import { format } from 'date-fns';

const CalenderHeader = ({ currentMonth, prevMonth, nextMonth }) => {
  return (
    <div className="calendar-header">
      <div className="col-start">
        <span className="col-text">
          <span className="col-month">{format(currentMonth, 'M')}월</span>
          {format(currentMonth, 'yyyy')}
        </span>
      </div>
      <div className="col-end">
        <Icon icon="bi:arrow-right-circle-fill" onClick={nextMonth} />
        <Icon icon="bi:arrow-left-circle-fill" onClick={prevMonth} />
      </div>
    </div>
  );
};

export default CalenderHeader;
