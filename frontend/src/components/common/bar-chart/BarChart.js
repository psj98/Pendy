import React, { useState } from 'react';
import './BarChart.css';

import ReactApexChart from 'react-apexcharts';

const BarChart = () => {
  //eslint-disable-next-line
  const [chartData, setChartData] = useState({
    categories: [
      '식비',
      '교통',
      '온라인 쇼핑',
      '오프라인 쇼핑',
      '카페/간식',
      '주거/통신',
      '패션/미용',
      '문화/여가',
    ],
    series: [
      {
        name: '평균 소비 금액',
        data: [10, 18, 13, 25, 7, 14, 20, 12],
      },
      {
        name: '나의 소비 금액',
        data: [15, 8, 20, 10, 12, 16, 22, 19],
      },
    ],
  });

  const options = {
    chart: {
      type: 'bar',
    },
    xaxis: {
      categories: chartData.categories,
    },
    colors: ['#0076BE', '#AADAFF'],
  };

  return (
    <div className="bar-chart">
      <ReactApexChart options={options} series={chartData.series} type="bar" />
    </div>
  );
};

export default BarChart;
