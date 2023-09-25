import React, { useEffect, useState } from 'react';
import './FirstGoalTemplate.css';
import DonutChart from '../../components/common/donut-chart/DonutChart';
import BarChart from '../../components/common/bar-chart/BarChart';
import handleGoalDetail from '../../utils/handleGoalDetail';
import format from 'date-fns/format';

const FirstGoalTemplate = () => {
  const [series, setSeries] = useState([]);
  const [monthlyAvg, setMonthlyAvg] = useState([]);
  const [isGoalSet, setIsGoalSet] = useState(false);
  const [inputValues, setInputValues] = useState({});
  const [originalValues, setOirinalValues] = useState({});

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
    const age = sessionStorage.getItem('age');
    const salary = sessionStorage.getItem('salary');
    const curDate = format(Date.now(), "yyyy-MM-dd'T'HH:mm:ss.SSS'+09:00'");

    const fetchData = async () => {
      try {
        const response = await handleGoalDetail(age, salary, curDate);
        const myMonthlyStatisticAvg =
          response.data.data.monthlyStatistic.amountByCategory;
        const seriesList = myMonthlyStatisticAvg.map((index) => index.amount);
        console.log(response.data);

        setMonthlyAvg(myMonthlyStatisticAvg);

        const initialInputValues = {};
        myMonthlyStatisticAvg.forEach((item) => {
          initialInputValues[item.categoryName] = item.amount;
        });
        setInputValues(initialInputValues);
        setSeries(seriesList);
        console.log(series);
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
    setInputValues({
      ...inputValues,
      [categoryName]: newValue,
    });
  };

  return (
    <div className="goal-template">
      <h1 style={{ margin: '30px 0' }}>목표 설정</h1>
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
                        value={inputValues[category.categoryName] || ''}
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
                        value={inputValues[category.categoryName] || ''}
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
                  onClick={handleGoalSetToggle}
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
                    onClick={handleGoalSetToggle}
                  >
                    취소
                  </button>
                )}
              </div>
            </div>
          )}
        </div>
        <div className="goal-bar-chart">
          <BarChart />
        </div>
      </div>
    </div>
  );
};

export default FirstGoalTemplate;
