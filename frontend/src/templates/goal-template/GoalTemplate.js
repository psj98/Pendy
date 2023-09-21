import React, { useEffect, useState } from 'react';
import './GoalTemplate.css';
import DonutChart from '../../components/common/donut-chart/DonutChart';
import BarChart from '../../components/common/bar-chart/BarChart';
import handleGoalDetail from '../../utils/handleGoalDetail';
import format from 'date-fns/format';
const GoalTemplate = () => {
  const [categoryGoalAmounts, setCategoryGoalAmounts] = useState([]);
  const series = [1, 1, 1, 1, 1, 1, 1, 1];
  const colors = [
    '#FAF2E8',
    '#BDECEA',
    '#DAB8F1',
    'rgba(243, 213, 182, 0.63)',
    'rgba(208, 228, 197, 0.42)',
    'rgba(255, 170, 180, 0.50)',
    '#CFE4C5',
    'rgba(189, 236, 235, 0.53)',
  ];

  useEffect(() => {
    //age, salary
    const age = sessionStorage.getItem('age');
    const salary = sessionStorage.getItem('salary');
    const curDate = format(Date.now(), "yyyy-MM-dd'T'HH:mm:ss.SSS'+09:00'");

    const fetchData = async () => {
      try {
        const response = await handleGoalDetail(age, salary, curDate);

        const amounts = response.data.data.goalByCategoryList.map(
          (item) => item.categoryGoalAmount,
        );

        setCategoryGoalAmounts(amounts);
      } catch (error) {
        console.log(error);
      }
    };
    fetchData();
  }, []);

  // console.log(categoryGoalAmounts);

  return (
    <div className="goal-template">
      <div className="goal-container">
        <div className="goal-chart">
          <DonutChart
            series={series}
            title={'오늘 총 소비액'}
            legendShow={false}
            legendFont={20}
            labelShow={true}
            labelFont={18}
            labelColor={'black'}
            valueFont={16}
            valueShow={true}
            valueColor={'black'}
            colors={colors}
          />
        </div>
        <div className="goal-inputs-container">
          <div className="goal-inputs-left">
            {categoryGoalAmounts.slice(0, 4).map((amount, index) => (
              <div key={index}>
                <input
                  type="text"
                  placeholder={`Input ${index + 1}`}
                  value={amount || ''}
                  readOnly
                />
                원
              </div>
            ))}
          </div>

          <div className="goal-inputs-right">
            {categoryGoalAmounts.slice(4, 8).map((amount, index) => (
              <div key={index + 4}>
                <input
                  type="text"
                  placeholder={`Input ${index + 5}`}
                  value={amount || ''}
                  readOnly
                />
                원
              </div>
            ))}
          </div>
        </div>
      </div>
      <div className="goal-bar-chart">
        <BarChart />
      </div>
    </div>
  );
};

export default GoalTemplate;
