import React, { useEffect } from 'react';
import './GoalTemplate.css';
import DonutChart from '../../components/common/donut-chart/DonutChart';
import BarChart from '../../components/common/bar-chart/BarChart';
import handleGoalDetail from '../../utils/handleGoalDetail';
import format from 'date-fns/format';
const GoalTemplate = () => {
  useEffect(() => {
    //age, salary
    const age = sessionStorage.getItem('age');
    const salary = sessionStorage.getItem('salary');
    const curDate = format(Date.now(), "yyyy-MM-dd'T'HH:mm:ss.SSS'+09:00'");

    const fetchData = async () => {
      try {
        const response = await handleGoalDetail(age, salary, curDate);

        console.log('goal', response.data);
      } catch (error) {
        console.log(error);
      }
    };
    fetchData();
  });

  return (
    <div className="goal-template">
      <div className="goal-container">
        <div className="goal-chart">
          <DonutChart />
        </div>
        <div className="goal-category">목표 카테고리</div>
      </div>
      <div className="goal-bar-chart">
        <BarChart />
      </div>
    </div>
  );
};

export default GoalTemplate;
