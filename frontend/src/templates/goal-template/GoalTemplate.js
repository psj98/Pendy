import React, { useEffect, useState } from 'react';
import './GoalTemplate.css';

import DonutChart from '../../components/common/donut-chart/DonutChart';
import BarChart from '../../components/common/bar-chart/BarChart';
import GoalBar from '../../components/common/goal-bar/GoalBar';
import ChatBot from '../../components/common/chat-bot/ChatBot';

import handleGoalDetail from '../../utils/handleGoalDetail';
import handleGoalUpdate from '../../utils/handleGoalUpdate';

import format from 'date-fns/format';

const GoalTemplate = () => {
  const [goalByCategory, setGoalByCategory] = useState([]);
  const [originalGoalByCategory, setOriginalGoalByCategory] = useState([]);
  const [monthlyTotalAmount, setMonthlyTotalAmount] = useState();
  const [series, setSeries] = useState([]);
  const [totalGoals, setTotalGoals] = useState([]);
  const [editable, setEditable] = useState(false);
  const [buttonLabel, setButtonLabel] = useState('수정');
  const [avgConsumptions, setAvgConsumption] = useState([]);
  const [inputValues, setInputValues] = useState({});
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
    const categoryName = goalByCategory[index].categoryName;
    const newValue = parseInt(e.target.value) || 0;

    setInputValues((prevValues) => ({
      ...prevValues,
      [categoryName]: newValue,
    }));

    const updatedValues = {
      ...inputValues,
      [categoryName]: newValue,
    };

    const updatedSeries = Object.values(updatedValues);

    const updatedTotalGoalAmount = Object.values(updatedValues).reduce(
      (acc, val) => acc + val,
      0,
    );

    setTotalGoals((prevState) => ({
      ...prevState,
      goalAmount: updatedTotalGoalAmount,
    }));
    setSeries(updatedSeries);
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
        const monthlyStatisticAmount =
          response.data.data.monthlyStatistic.totalAmount;
        const avgCon = response.data.data.avgConsumptionAmountAvg.map(
          (index) => index.amount,
        );

        setAvgConsumption(avgCon);
        setMonthlyTotalAmount(monthlyStatisticAmount);
        setGoalByCategory(goalByCategoryList);
        setOriginalGoalByCategory(goalByCategoryList); // Set original state
        setSeries(seriesList);
        setTotalGoals(totalGoal);

        const initialInputValues = {};
        goalByCategoryList.forEach((item) => {
          initialInputValues[item.categoryName] = item.categoryGoalAmount;
        });
        setInputValues(initialInputValues);

        console.log(totalGoal.goalAmount);
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
    setButtonLabel(editable ? '수정' : '확인');
  };

  const handleCancel = () => {
    // Reset to original state
    setGoalByCategory(JSON.parse(JSON.stringify(originalGoalByCategory)));
    const originalSeries = originalGoalByCategory.map(
      (item) => item.categoryGoalAmount,
    );
    setSeries(originalSeries);

    const originalInputValues = {};
    originalGoalByCategory.forEach((item) => {
      originalInputValues[item.categoryName] = item.categoryGoalAmount;
    });
    setInputValues(originalInputValues);

    const originalTotalGoalAmount = originalSeries.reduce(
      (acc, curr) => acc + curr,
      0,
    );
    setTotalGoals((prevState) => ({
      ...prevState,
      goalAmount: originalTotalGoalAmount,
    }));

    setEditable(false);
    setButtonLabel('수정');
  };
  const handleUpdate = async () => {
    if (editable) {
      try {
        const id = totalGoals.id;
        var goalAmount = 0;
        const newGoalByCategory = goalByCategory.map((item) => {
          goalAmount += item.categoryGoalAmount;
          return {
            categoryName: item.categoryName,
            categoryId: item.categoryId, // 예를 들어, categoryName을 categoryId로 변환하는 함수
            categoryGoalAmount: item.categoryGoalAmount,
          };
        });

        const newTotalGoals = totalGoals;
        newTotalGoals.goalAmount = goalAmount;

        await handleGoalUpdate(id, goalAmount, newGoalByCategory);
        setTotalGoals(newTotalGoals);
        setOriginalGoalByCategory(newGoalByCategory);

        // const age = sessionStorage.getItem('age');
        // const salary = sessionStorage.getItem('salary');
        // const curDate = format(Date.now(), "yyyy-MM-dd'T'HH:mm:ss.SSS'+09:00'");

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
      <div className="goal-main">
        <div className="goal-container">
          <div className="goal-chart">
            {goalByCategory.length > 0 && (
              <DonutChart
                series={series}
                title={'총 목표 소비액'}
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
          <div className="total-goal-container">
            <div className="total-goal">총 목표 금액 : </div>
            <div className="total-goal-amount">{totalGoals.goalAmount}원</div>
          </div>
          {/* 목표 막대 바 */}
          <GoalBar
            color={'#2A4FFA'}
            current={monthlyTotalAmount}
            goal={totalGoals.goalAmount}
            type={'update'}
          />

          <div className="goal-input-button-container">
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
                      placeholder="숫자로 입력"
                      variant="outlined"
                      value={inputValues[category.categoryName] || 0}
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
                      placeholder="숫자로 입력"
                      variant="outlined"
                      value={inputValues[category.categoryName] || 0}
                      readOnly={!editable}
                      onChange={(e) => handleInputChange(e, index + 4)}
                    />
                    원
                  </div>
                ))}
              </div>
            </div>
            <div className="goal-update-button-div">
              <button
                onClick={handleButtonClick}
                className="signup-button duplicatecheck-button"
                style={{
                  margin: '0 0 0 10px',
                  fontSize: 'smaller',
                  padding: '5px 10px',
                }}
              >
                {buttonLabel}
              </button>
              {editable && (
                <button
                  onClick={handleCancel}
                  className="signup-button duplicatecheck-button"
                  style={{
                    margin: '0 0 0 10px',
                    fontSize: 'smaller',
                    padding: '5px 10px',
                  }}
                >
                  취소
                </button>
              )}
            </div>
          </div>
        </div>
        {series.length > 0 && (
          <div className="goal-bar-chart">
            <BarChart series={series} avgConsumptions={avgConsumptions} />
          </div>
        )}
      </div>
      <div className="chat-bot-div">
        <ChatBot />
      </div>
    </div>
  );
};

export default GoalTemplate;
