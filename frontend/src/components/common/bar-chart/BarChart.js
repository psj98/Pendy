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
      width: '100%',
      height: '100%',
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

// 동적 차트
// import React, { useState } from 'react';
// import './BarChart.css';
// import ReactApexChart from 'react-apexcharts';

// const BarChart = () => {
//   const [inputData, setInputData] = useState({
//     averageConsumption: [10, 18, 13, 25, 7, 14, 20, 12],
//     myConsumption: [15, 8, 20, 10, 12, 16, 22, 19],
//   });

//   // Y축 데이터를 업데이트하는 함수
//   const updateChartData = (newData) => {
//     setChartData({
//       ...chartData,
//       series: [
//         {
//           name: '평균 소비 금액',
//           data: newData.averageConsumption,
//         },
//         {
//           name: '나의 소비 금액',
//           data: newData.myConsumption,
//         },
//       ],
//     });
//   };

//   const [chartData, setChartData] = useState({
//     categories: [
//       '식비',
//       '교통',
//       '온라인 쇼핑',
//       '오프라인 쇼핑',
//       '카페/간식',
//       '주거/통신',
//       '패션/미용',
//       '문화/여가',
//     ],
//     series: [
//       {
//         name: '평균 소비 금액',
//         data: inputData.averageConsumption,
//       },
//       {
//         name: '나의 소비 금액',
//         data: inputData.myConsumption,
//       },
//     ],
//   });

//   const options = {
//     chart: {
//       type: 'bar',
//     },
//     xaxis: {
//       categories: chartData.categories,
//     },
//     colors: ['#0076BE', '#AADAFF'],
//   };

//   return (
//     <div className="bar-chart">
//       <button
//         onClick={() => {
//           // 입력 데이터를 업데이트하고 차트를 다시 렌더링
//           const newInputData = {
//             averageConsumption: [/* 업데이트할 데이터 배열 */],
//             myConsumption: [/* 업데이트할 데이터 배열 */],
//           };
//           updateChartData(newInputData);
//         }}
//       >
//         데이터 업데이트
//       </button>
//       <ReactApexChart options={options} series={chartData.series} type="bar" />
//     </div>
//   );
// };

// export default BarChart;
