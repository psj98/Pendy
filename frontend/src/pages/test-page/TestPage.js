import React from 'react';
import './TestPage.css';

import DonutChart from '../../components/common/donut-chart/DonutChart';
import Header from '../../components/common/header/Header';

const TestPage = () => {
  return (
    <div>
      <Header />
      <DonutChart />
    </div>
  );
};

export default TestPage;
