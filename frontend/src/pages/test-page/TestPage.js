import React from 'react';
import './TestPage.css';

import DoughnutChart from '../../components/common/doughnut-chart/DoughnutChart';
// import HamburgerMenu from '../../components/common/hamburger-menu/HamburgerMenu';
import Header from '../../components/common/header/Header';

const TestPage = () => {
  return (
    <div>
      <Header />
      <DoughnutChart />
    </div>
  );
};

export default TestPage;
