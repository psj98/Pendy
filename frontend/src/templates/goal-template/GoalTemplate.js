import React, { useEffect, useState } from 'react';
import './GoalTemplate.css';
import DonutChart from '../../components/common/donut-chart/DonutChart';
import BarChart from '../../components/common/bar-chart/BarChart';
import handleGoalDetail from '../../utils/handleGoalDetail';
import format from 'date-fns/format';
import handleGoalUpdate from '../../utils/handleGoalUpdate';

const GoalTemplate = () => {
  const [goalByCategory, setGoalByCategory] = useState([]);
  const [series, setSeries] = useState([]);
  const [totalGoals, setTotalGoals] = useState([]);
  const [editable, setEditable] = useState(false);
  const [buttonLabel, setButtonLabel] = useState('목표수정');
  const categoryNameToKor = {
    food: '식비',
    traffic: '교통',
    online: '온라인 쇼핑',
    offline: '오프라인 쇼핑',
    cafe: '카페/간식',
    housing: '고정지출',
    fashion: '패션/미용',
    culture: '문화/여가',
  };
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
        const goalByCategoryList = response.data.data.goalByCategoryList;
        const seriestList = response.data.data.goalByCategoryList.map(
          (index) => index.categoryGoalAmount,
        );
        const totalGoal = response.data.data.totalGoal;

        setGoalByCategory(goalByCategoryList);
        setSeries(seriestList);
        setTotalGoals(totalGoal);
      } catch (error) {
        console.log(error);
      }
    };
    fetchData();
  }, []);

  const handleButtonClick = () => {
    console.log('click');
    if (editable) {
      handleUpdate();
    }
    handleEditToggle();
  };

  const handleEditToggle = () => {
    setEditable(!editable);
    setButtonLabel(editable ? '목표수정' : '수정 완료');
  };

  const handleCancel = () => {
    setEditable(false);
    setButtonLabel('목표수정');
    // Reset the state to original (you may need to call the API again)
  };

  const handleUpdate = async () => {
    if (editable) {
      try {
        const id = totalGoals.id;
        const goalAmount = totalGoals.goalAmount;

        console.log('id', id);
        console.log('goalAmount', goalAmount);
        console.log('goalByCategory', goalByCategory);

        await handleGoalUpdate(id, goalAmount, goalByCategory);
        handleEditToggle();
      } catch (error) {
        console.error('Failed to update:', error);
      }
    }
  };

  return (
    <div className="goal-template">
      <h1>목표 설정</h1>
      <div className="goal-container">
        <div className="goal-chart">
          {goalByCategory.length > 0 && (
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
          )}
        </div>
        <button
          onClick={handleButtonClick}
          className="signup-button duplicatecheck-button"
          style={{ fontSize: 'smaller', padding: '5px 10px' }}
        >
          {buttonLabel}
        </button>
        {editable && (
          <button
            onClick={handleCancel}
            className="cancel-button"
            style={{ fontSize: 'smaller', padding: '5px 10px' }}
          >
            취소
          </button>
        )}
        <div className="goal-inputs-container">
          <div className="goal-inputs-left">
            {goalByCategory.slice(0, 4).map((category, index) => (
              <div key={index} className="goal-inputs-category">
                <div className="goal-inputs-category-name">
                  {categoryNameToKor[category.categoryName]}
                </div>
                <div
                  className="goal-inputs-rectangle-label"
                  style={{
                    backgroundColor: colors[index],
                  }}
                ></div>
                <input
                  className="goal-inputs-amount"
                  type="text"
                  placeholder={`Input ${index + 1}`}
                  value={category.categoryGoalAmount || ''}
                  readOnly={!editable}
                />
                원
              </div>
            ))}
          </div>
          <div className="goal-inputs-right">
            {goalByCategory.slice(4, 8).map((category, index) => (
              <div key={index} className="goal-inputs-category">
                <div className="goal-inputs-category-name">
                  {categoryNameToKor[category.categoryName]}
                </div>
                <div
                  className="goal-inputs-rectangle-label"
                  style={{
                    backgroundColor: colors[index + 4],
                  }}
                ></div>
                <input
                  className="goal-inputs-amount"
                  type="text"
                  placeholder={`Input ${index + 1}`}
                  value={category.categoryGoalAmount || ''}
                  readOnly={!editable}
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
