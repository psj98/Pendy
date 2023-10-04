import React, { useState } from 'react';
import './BarChart.css';
import ReactApexChart from 'react-apexcharts';
const BarChart = ({ series, avgConsumptions }) => {
  const actualAvgConsumption = Array.isArray(avgConsumptions)
    ? avgConsumptions
    : [];
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
        name: '나의 평균 소비 금액',
        data: series || [],
      },
      {
        name: '비슷한 조건의 소비자의 평균 소비 금액',
        data: actualAvgConsumption || [],
      },
    ],
  });

  return (
    <ReactApexChart
      options={{
        chart: {
          fontFamily: 'The Jamsil 3',
          type: 'bar',
          toolbar: {
            show: false,
          },
        },
        plotOptions: {
          bar: {
            columnWidth: '75%',
            distributed: false,
          },
        },
        legend: {
          position: 'top', // Move the legend to the right side
          fontSize: '15 px',
        },
        dataLabels: {
          style: {
            fontSize: '10px',
            fontFamily: 'The Jamsil 1',
            fontWeight: 'light',
          },
        },
        xaxis: {
          categories: chartData.categories,
        },
        colors: ['#0076BE', '#AADAFF'],
      }}
      series={chartData.series}
      type="bar"
    />
  );
};

export default BarChart;
