import React, { useEffect, useState } from 'react';
import './GoalTemplate.css';
import DonutChart from '../../components/common/donut-chart/DonutChart';
import BarChart from '../../components/common/bar-chart/BarChart';
import handleGoalDetail from '../../utils/handleGoalDetail';
import format from 'date-fns/format';
import handleGoalUpdate from '../../utils/handleGoalUpdate';

const GoalTemplate = () => {
  const [goalByCategory, setGoalByCategory] = useState([]);
  const [originalGoalByCategory, setOriginalGoalByCategory] = useState([]);
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

  const handleInputChange = (e, index) => {
    const value = parseInt(e.target.value) || 0;
    setGoalByCategory((prevState) =>
      prevState.map((item, idx) => {
        if (idx !== index) return item;
        return { ...item, categoryGoalAmount: value };
      }),
    );
  };

  useEffect(() => {
    const age = sessionStorage.getItem('age');
    const salary = sessionStorage.getItem('salary');
    const curDate = format(Date.now(), "yyyy-MM-dd'T'HH:mm:ss.SSS'+09:00'");

    const fetchData = async () => {
      try {
        const response = await handleGoalDetail(age, salary, curDate);
        const goalByCategoryList = response.data.data.goalByCategoryList;
        const seriesList = goalByCategoryList.map(
          (index) => index.categoryGoalAmount,
        );
        const totalGoal = response.data.data.totalGoal;

        setGoalByCategory(goalByCategoryList);
        setOriginalGoalByCategory(goalByCategoryList); // Set original state
        setSeries(seriesList);
        setTotalGoals(totalGoal);
      } catch (error) {
        console.log(error);
      }
    };
    fetchData();
  }, []);
  const handleButtonClick = () => {
    if (editable) {
      handleUpdate();
    } else {
      setOriginalGoalByCategory(JSON.parse(JSON.stringify(goalByCategory))); // Deep copy
    }
    handleEditToggle();
  };

  const handleEditToggle = () => {
    if (!editable) {
      setOriginalGoalByCategory([...originalGoalByCategory]);
    }
    setEditable(!editable);
    setButtonLabel(editable ? '목표수정' : '수정 완료');
  };

  const handleCancel = () => {
    setGoalByCategory(JSON.parse(JSON.stringify(originalGoalByCategory))); // Reset to original state
    setEditable(false);
    setButtonLabel('목표수정');
  };

  const handleUpdate = async () => {
    if (editable) {
      try {
        const id = totalGoals.id;
        const goalAmount = totalGoals.goalAmount;
        const newGoalByCategory = goalByCategory.map((item) => {
          return {
            categoryId: item.categoryId, // 예를 들어, categoryName을 categoryId로 변환하는 함수
            categoryGoalAmount: item.categoryGoalAmount,
          };
        });

        await handleGoalUpdate(id, goalAmount, newGoalByCategory);

        const age = sessionStorage.getItem('age');
        const salary = sessionStorage.getItem('salary');
        const curDate = format(Date.now(), "yyyy-MM-dd'T'HH:mm:ss.SSS'+09:00'");

        const updatedSeries = goalByCategory.map(
          (item) => item.categoryGoalAmount,
        );
        setSeries(updatedSeries);

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
            className="signup-button duplicatecheck-button"
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
                  type="text"
                  className="input goal-inputs-amount"
                  placeholder={`Input ${index + 1}`}
                  variant="outlined"
                  value={category.categoryGoalAmount || ''}
                  readOnly={!editable}
                  onChange={(e) => handleInputChange(e, index)}
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
                  type="text"
                  className="input goal-inputs-amount"
                  placeholder={`Input ${index + 1}`}
                  variant="outlined"
                  value={category.categoryGoalAmount || ''}
                  readOnly={!editable}
                  onChange={(e) => handleInputChange(e, index + 4)}
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
