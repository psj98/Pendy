import React, { useState } from 'react';
import './UserTemplate.css';
import { addMonths, subMonths } from 'date-fns';
import CalenderHeader from '../../components/main/calender-header/CalenderHeader';
import CalenderDays from '../../components/main/calender-days/CalenderDays';
import CalenderCells from '../../components/main/calender-cells/CalenderCells';
import DoughnutChart from '../../components/common/doughnut-chart/DoughnutChart';

const UserTemplate = () => {
  const [currentMonth, setCurrentMonth] = useState(new Date());
  console.log('usertemplate ' + currentMonth);

  const prevMonth = () => {
    setCurrentMonth(subMonths(currentMonth, 1));
  };

  const nextMonth = () => {
    setCurrentMonth(addMonths(currentMonth, 1));
  };

  return (
    <div className="user">
      <div className="calender-container">
        <div className="calender-content">
          <CalenderHeader
            currentMonth={currentMonth}
            prevMonth={prevMonth}
            nextMonth={nextMonth}
          />
          <CalenderDays />
          <CalenderCells currentMonth={currentMonth} />
        </div>
      </div>
      <div className="chart-container">
        <div className="chart-content">
          <DoughnutChart />
        </div>
      </div>
    </div>
  );
};

export default UserTemplate;
