import React, { useEffect, useState } from 'react';
import './GoalTemplate.css';
import DonutChart from '../../components/common/donut-chart/DonutChart';
import BarChart from '../../components/common/bar-chart/BarChart';
import handleGoalDetail from '../../utils/handleGoalDetail';
import format from 'date-fns/format';
const GoalTemplate = () => {
  const [categoryGoalAmounts, setCategoryGoalAmounts] = useState([]);
  const [categotyNames, setCategoryNames] = useState([]);

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

        console.log(response.data);
        const amounts = response.data.data.goalByCategoryList.map(
          (item) => item.categoryGoalAmount,
        );
        const label = response.data.data.goalByCategoryList.map(
          (item) => item.categoryName,
        );

        setCategoryGoalAmounts(amounts);
        setCategoryNames(label);
        // console.log(categotyNames);
      } catch (error) {
        console.log(error);
      }
    };
    fetchData();
  }, []);

  // console.log(categotyNames[0]);

  // const labelList = [
  //   // categotyNames[0],
  //   // categotyNames[1],
  //   // categotyNames[2],
  //   // categotyNames[3],
  //   // categotyNames[4],
  //   // categotyNames[5],
  //   // categotyNames[6],
  //   // categotyNames[7],
  // ];

  // console.log(categoryGoalAmounts[0]);

  const series = [
    categoryGoalAmounts[0],
    categoryGoalAmounts[1],
    categoryGoalAmounts[2],
    categoryGoalAmounts[3],
    categoryGoalAmounts[4],
    categoryGoalAmounts[5],
    categoryGoalAmounts[6],
    categoryGoalAmounts[7],
  ];

  return (
    <div className="goal-template">
      <div className="goal-container">
        {categoryGoalAmounts.length > 0 && (
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
            label={categotyNames}
          />
        )}
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
