import React from 'react';
import './TestPage.css';
import ChartCategory from '../../components/common/chart-category/ChartCategory';

// import DonutChart from '../../components/common/donut-chart/DonutChart';
// import Header from '../../components/common/header/Header';
// import BarChart from '../../components/common/bar-chart/BarChart';

const TestPage = () => {
  return (
    <div className="test-page">
      {/* <Header />
      <DonutChart />
      <BarChart /> */}
      <ChartCategory />
    </div>
  );
};

export default TestPage;
