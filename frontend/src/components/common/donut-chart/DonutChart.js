import React, { useState } from 'react';
// , useEffect
import './DonutChart.css';

import ReactApexChart from 'react-apexcharts';

const DonutChart = ({
  series,
  chartLabel,
  title,
  legendShow,
  legendFont,
  labelShow,
  labelFont,
  labelColor,
  valueFont,
  valueShow,
  valueColor,
  colors,
}) => {
  //eslint-disable-next-line
  const [chartData, setChartData] = useState({
    series: series,
  });

  const options = {
    chart: {
      width: '100%',
    },
    chartOptions: {},
    legend: {
      position: 'bottom',
      show: legendShow,
      fontSize: legendFont,
    },
    plotOptions: {
      pie: {
        donut: {
          labels: {
            show: labelShow,
            total: {
              showAlways: true,
              show: true,
              label: `${title}`,
              fontSize: labelFont,
              color: labelColor,
            },
            value: {
              fontSize: valueFont,
              show: valueShow,
              color: valueColor,
            },
          },
        },
      },
    },
    labels: chartLabel,

    colors: colors,
  };

  return (
    <div className="donut-chart">
      <ReactApexChart options={options} series={series} type="donut" />
    </div>
  );
};

export default DonutChart;
