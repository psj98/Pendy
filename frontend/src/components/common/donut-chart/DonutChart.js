import React, { useState } from 'react';
// , useEffect
import './DonutChart.css';

import ReactApexChart from 'react-apexcharts';
// import axios from 'axios';

const DonutChart = () => {
  //eslint-disable-next-line
  const [chartData, setChartData] = useState({
    series: [20, 10, 15, 20, 1, 14, 20], // 카테고리별 수치 받아오기
  });

  // useEffect(() => {
  //   axios
  //     .get('your-api-endpoint-here')
  //     .then((response) => {
  //       const newData = {
  //         series: response.data.series,
  //         labels: response.data.labels,
  //       };
  //       setChartData(newData);
  //     })
  //     .catch((error) => {
  //       console.error('API 요청 중 오류 발생:', error);
  //     });
  // }, []);

  const options = {
    chart: {
      width: '100%',
    },
    legend: {
      show: false,
    },
    plotOptions: {
      pie: {
        donut: {
          labels: {
            show: true,
            total: {
              showAlways: true,
              show: true,
              label: '오늘 총 소비액',
              fontSize: '20px',
              color: 'black',
            },
            value: {
              fontSize: '22px',
              show: true,
              color: 'blue',
            },
          },
        },
      },
    },
    // colors: [
    //   '#FAF2E8',
    //   '#BDECEA',
    //   '#DAB8F1',
    //   'rgba(243, 213, 182, 0.63)',
    //   'rgba(208, 228, 197, 0.42)',
    //   'rgba(255, 170, 180, 0.50)',
    //   '#CFE4C5',
    // ],
  };

  return (
    <div className="donut-chart">
      <ReactApexChart
        options={options}
        series={chartData.series}
        type="donut"
      />
    </div>
  );
};

export default DonutChart;
