import React, { useEffect, useState } from 'react';
import './FirstGoalTemplate.css';
import DonutChart from '../../components/common/donut-chart/DonutChart';
import BarChart from '../../components/common/bar-chart/BarChart';
import handleGoalDetail from '../../utils/handleGoalDetail';
import format from 'date-fns/format';
import handleRegistGoal from '../../utils/handleRegistGoal';
import { useNavigate } from 'react-router-dom';

const FirstGoalTemplate = () => {
  const [series, setSeries] = useState([]);
  const [monthlyAvg, setMonthlyAvg] = useState([]);
  const [isGoalSet, setIsGoalSet] = useState(false);
  const [inputValues, setInputValues] = useState({});
  const [originalValues, setOriginalValues] = useState({});
  const [avgConsumptions, setAvgConsumption] = useState({});
  const [responseData, setResponseData] = useState({});
  const [totalInputAmount, setTotalInputAmount] = useState(0);
  const [goalInputAmount, setGoalInputAmount] = useState(0);
  const [canComplete, setCanComplete] = useState(false);

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

  const navigate = useNavigate();

  useEffect(() => {
    const age = sessionStorage.getItem('age');
    const salary = sessionStorage.getItem('salary');
    const curDate = format(Date.now(), "yyyy-MM-dd'T'HH:mm:ss.SSS'+09:00'");

    const fetchData = async () => {
      try {
        const response = await handleGoalDetail(age, salary, curDate);
        const myMonthlyStatisticAvg =
          response.data.data.monthlyStatistic.amountByCategory;
        const seriesList = myMonthlyStatisticAvg.map((index) => index.amount);
        console.log('resonse', response.data);
        const avgCom = response.data.data.avgConsumptionAmountAvg.map(
          (index) => index.amount,
        );

        setResponseData(response.data);

        console.log('avgCom', avgCom);
        setTotalInputAmount(response.data.data.monthlyStatistic.totalAmount);
        setGoalInputAmount(response.data.data.monthlyStatistic.totalAmount);

        setMonthlyAvg(myMonthlyStatisticAvg);

        const initialInputValues = {};
        myMonthlyStatisticAvg.forEach((item) => {
          initialInputValues[item.categoryName] = item.amount;
        });
        setInputValues(initialInputValues);
        setSeries(seriesList);
        setOriginalValues(initialInputValues);
        setAvgConsumption(avgCom);
        console.log('series', series);
      } catch (error) {
        console.log(error);
      }
    };
    fetchData();
  }, []);

  const handleGoalSetToggle = () => {
    setIsGoalSet(!isGoalSet);
  };
  const handleInputChange = (categoryName, e) => {
    const newValue = e.target.value;

    // 숫자가 아닌 값이 입력되면 함수를 종료
    if (isNaN(newValue)) return;

    setInputValues((prevValues) => {
      const updatedValues = {
        ...prevValues,
        [categoryName]: newValue,
      };

      // inputValues를 기반으로 series 업데이트
      const updatedSeries = monthlyAvg.map((category) =>
        parseInt(updatedValues[category.categoryName] || 0),
      );

      // inputValues를 기반으로 totalInputAmount 업데이트
      const updatedTotal = Object.values(updatedValues).reduce(
        (acc, val) => acc + parseInt(val, 10),
        0,
      );
      setTotalInputAmount(updatedTotal);

      setSeries(updatedSeries);

      return updatedValues;
    });
  };

  const handleCancel = () => {
    setInputValues(originalValues);
    setIsGoalSet(false);
  };

  const handleComplate = async () => {
    try {
      var goalAmount = 0;
      const goal = Object.keys(inputValues).map((categoryName) => {
        const amount = inputValues[categoryName];
        goalAmount += parseInt(amount, 10);
        const category = monthlyAvg.find(
          (item) => item.categoryName === categoryName,
        );
        return {
          categoryId: category.categoryId,
          categoryGoalAmount: parseInt(amount, 10),
        };
      });

      const response = await handleRegistGoal(goalAmount, goal);
      if (response.data.code === 6001) {
        alert(response.data.message);
      } else if (totalInputAmount > goalInputAmount) {
        alert('목표 설정 금액을 다시 확인해주세요');
      } else {
        alert('목표 설정이 완료되었습니다.');
        navigate('/');
      }
    } catch (error) {
      console.log(error);
    }
  };

  console.log(series);

  return (
    <div className="goal-template">
      <h1 style={{ margin: '30px 0' }}></h1>
      <div className="goal-main">
        <div className="goal-container">
          <div className="goal-chart">
            {monthlyAvg.length > 0 && (
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
          {responseData.data && (
            <div className="first-goal-and-month">
              <div
                className="first-goal-and-month-spend"
                style={{
                  color: totalInputAmount > goalInputAmount ? 'red' : '#007bff',
                }}
              >
                {totalInputAmount} 원 /{' '}
                <input
                  className="input goal-inputs-amount"
                  type="text"
                  defaultValue={goalInputAmount}
                  onChange={(e) => setGoalInputAmount(e.target.value)}
                />{' '}
                원
              </div>
            </div>
          )}
          <div className="first-goal-and-month-title">소비액 / 목표액</div>
          {monthlyAvg.length > 0 && (
            <div className="goal-input-button-container">
              <div className="goal-inputs-container">
                <div className="goal-inputs-left">
                  {monthlyAvg.slice(0, 4).map((category, index) => (
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
                        readOnly={!isGoalSet}
                        onChange={(e) =>
                          handleInputChange(category.categoryName, e)
                        }
                      />
                      원
                    </div>
                  ))}
                </div>
                <div className="goal-inputs-right">
                  {monthlyAvg.slice(4, 8).map((category, index) => (
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
                        readOnly={!isGoalSet}
                        onChange={(e) =>
                          handleInputChange(category.categoryName, e)
                        }
                      />
                      원
                    </div>
                  ))}
                </div>
              </div>
              <div className="goal-update-button-div">
                <button
                  className="signup-button duplicatecheck-button"
                  style={{
                    margin: '0 0 0 10px',
                    fontSize: 'smaller',
                    padding: '5px 10px',
                  }}
                  onClick={isGoalSet ? handleComplate : handleGoalSetToggle}
                >
                  {isGoalSet ? '완료' : '목표 설정'}
                </button>
                {isGoalSet && (
                  <button
                    className="signup-button duplicatecheck-button"
                    style={{
                      margin: '0 0 0 10px',
                      fontSize: 'smaller',
                      padding: '5px 10px',
                    }}
                    onClick={handleCancel}
                  >
                    취소
                  </button>
                )}
              </div>
            </div>
          )}
        </div>
        {series.length > 0 && (
          <div className="goal-bar-chart">
            <BarChart series={series} avgConsumptions={avgConsumptions} />
          </div>
        )}
      </div>
    </div>
  );
};

export default FirstGoalTemplate;
