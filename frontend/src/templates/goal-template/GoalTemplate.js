import React from 'react';
import './GoalTemplate.css';
import DonutChart from '../../components/common/donut-chart/DonutChart';
import BarChart from '../../components/common/bar-chart/BarChart';

const GoalTemplate = () => {
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
