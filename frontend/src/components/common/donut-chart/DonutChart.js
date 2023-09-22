import React, { useState, useEffect } from 'react';
import './DonutChart.css';
import ReactApexChart from 'react-apexcharts';

const DonutChart = ({
  series,
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
  const [chartData, setChartData] = useState({
    series: series,
  });

  useEffect(() => {
    setChartData({ series: series });
  }, [series]);

  const options = {
    chart: {
      width: '100%',
    },
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
          size: 60,
        },
      },
    },
    labels: [
      '식비',
      '교통',
      '온라인 쇼핑',
      '오프라인 쇼핑',
      '카페/간식',
      '패션/미용',
      '문화/여가',
      '고정지출',
    ],
    colors: colors,
  };

  return (
    <div className="donut-chart">
      <ReactApexChart options={options} series={series} type="donut" />
    </div>
  );
};

export default DonutChart;
