import React, { useEffect, useState } from 'react';
import './AnalysisTemplate.css';
import { useLocation } from 'react-router-dom';
import handleMonthlyAnalysis from '../../utils/handleMonthlyAnalysis';
import DonutChart from '../../components/common/donut-chart/DonutChart';
import GoalBar from '../../components/common/goal-bar/GoalBar';
import { Icon } from '@iconify/react';

const AnalysisTemplate = () => {
  // useLocation 훅을 사용하여 현재 경로의 search를 읽어옵니다.
  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);
  const currentMonth = searchParams.get('currentMonth');

  const [responseData, setResponseData] = useState([]);
  const [goalByCategory, setGoalByCategory] = useState([]);
  const [monthlyStatistic, setMonthlyStatistic] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await handleMonthlyAnalysis(currentMonth);
        console.log('res', response.data);
        console.log('Load complete');
        setResponseData(response.data); // response 데이터를 상태로 저장
        setGoalByCategory(response.data.data.goalByCategory);
        setMonthlyStatistic(
          response.data.data.monthlyStatistic.amountByCategory,
        );
      } catch (error) {
        console.log(error);
      }
    };

    fetchData();
  }, [currentMonth]);

  // Chart arguments by selected option
  let chartTitle = '월간 총 소비액';

  let chartData = []; // Donut 차트에 표시할 데이터 배열
  let chartLabel = []; // Donut 차트에 표시할 데이터 제목

  let monthlyGoalsByCategory = [];

  let aiText = '';
  if (responseData.data) {
    // 월간 목표 금액
    let goalByCategory = responseData.data.goalByCategory;
    monthlyGoalsByCategory = goalByCategory.map(
      (item) => item.categoryGoalAmount,
    );
    // // 월간 소비 금액
    // let consumption_amount = responseData.data.monthlyStatistic.totalAmount;

    let statisticData = responseData.data.monthlyStatistic.amountByCategory;

    chartLabel = statisticData.map((item) => item.categoryName);
    chartData = statisticData.map((item) => item.amount);

    // ai 텍스트
    aiText = responseData.data.totalGoal.aiAnlaysis;
  }

  const showLegend = false; // 범례를 표시할지 여부
  const legendFontSize = '12px'; // 범례의 글꼴 크기
  const showLabels = true; // 라벨 표시 여부
  const labelFontSize = '14px'; // 라벨 글꼴 크기
  const labelColor = '#333'; // 라벨 텍스트 색상
  const showValues = true; // 값 표시 여부
  const valueFontSize = '16px'; // 값 글꼴 크기
  const valueColor = '#555'; // 값 텍스트 색상
  const chartColors = [
    '#FAF2E8',
    '#BDECEA',
    '#DAB8F1',
    'rgba(243, 213, 182, 0.63)',
    'rgba(208, 228, 197, 0.42)',
    'rgba(255, 170, 180, 0.50)',
    '#CFE4C5',
    'rgba(189, 236, 235, 0.53)',
  ]; // 차트의 섹션 색상 배열

  const dateObj = new Date(currentMonth);
  // 월을 가져와서 "월"로 변환
  const monthNames = [
    '1월',
    '2월',
    '3월',
    '4월',
    '5월',
    '6월',
    '7월',
    '8월',
    '9월',
    '10월',
    '11월',
    '12월',
  ];

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

  const curMonth = monthNames[dateObj.getUTCMonth()];
  // currentMonth 값 출력
  console.log('현재 월: ' + curMonth);

  // 월 변경 함수
  const changeMonth = (delta) => {
    if (delta === -1 && !responseData.data.hasBeforeMonthlyGoal) {
      // 이전 월로 이동하는 경우, 이전 월 데이터가 없을 때 클릭이 무시됩니다.
      return;
    }

    if (delta === 1 && !responseData.data.hasAfterMonthlyGoal) {
      // 다음 월로 이동하는 경우, 다음 월 데이터가 없을 때 클릭이 무시됩니다.
      return;
    }
    // currentMonth를 JavaScript Date 객체로 변환
    const dateObj = new Date(currentMonth);
    // delta 값에 따라 월을 변경
    dateObj.setUTCMonth(dateObj.getUTCMonth() + delta);
    // 변경된 월을 문자열로 변환
    const newMonth = dateObj.toISOString();
    // URL을 업데이트하여 페이지 새로고침 없이 변경된 월로 이동
    window.location.href = `/analysis?currentMonth=${newMonth}`;
  };

  console.log('응답데이터 : ', aiText);
  return (
    <div className="analysis-container">
      <h1 className="analysis-title">소비분석 페이지</h1>
      {/* currentMonth 값을 출력 */}
      {/* 여기에 소비 분석 컴포넌트 또는 내용을 추가합니다. */}
      <div className="analysis-main-container">
        <div className="analysis-left-container">
          <div className="change-month-div">
            <Icon
              className={
                !responseData.hasBeforeMonthlyGoal
                  ? 'left-arrow'
                  : 'left-arrow-active'
              }
              icon="bi:arrow-left-circle-fill"
              onClick={() => changeMonth(-1)}
            />
            <h2 className="cur-month-text">{curMonth}</h2>
            <Icon
              className={
                !responseData.hasBeforeMonthlyGoal
                  ? 'right-arrow'
                  : 'right-arrow-active'
              }
              icon="bi:arrow-right-circle-fill"
              onClick={() => changeMonth(1)}
            />
          </div>

          {/* 도넛 차트 */}
          <div className="analysis-goal-chart">
            {responseData.data && (
              <DonutChart
                series={chartData}
                chartLabel={chartLabel}
                title={chartTitle}
                legendShow={showLegend}
                legendFont={legendFontSize}
                labelShow={showLabels}
                labelFont={labelFontSize}
                labelColor={labelColor}
                valueShow={showValues}
                valueFont={valueFontSize}
                valueColor={valueColor}
                colors={chartColors}
              />
            )}
          </div>

          <div className="category-bar-container">
            <div className="analysis-category-bar-left">
              {goalByCategory.slice(0, 4).map((category, index) => (
                <div key={index} className="analysis-category-bar">
                  <div className="analysis-category-name">
                    {categoryNameToKor[category.categoryName]}
                  </div>
                  <div
                    className="analysis-rectangle-label"
                    style={{
                      backgroundColor: chartColors[index],
                    }}
                  ></div>
                  <div className="analysis-goal-bar">
                    <GoalBar
                      color={chartColors[index]}
                      current={monthlyStatistic[index].amount}
                      goal={category.categoryGoalAmount}
                      type={'default'}
                    />
                  </div>
                </div>
              ))}
            </div>
            <div className="analysis-category-bar-right">
              {goalByCategory.slice(4, 8).map((category, index) => (
                <div key={index} className="analysis-category-bar">
                  <div className="analysis-category-name">
                    {categoryNameToKor[category.categoryName]}
                  </div>
                  <div
                    className="analysis-rectangle-label"
                    style={{
                      backgroundColor: chartColors[index + 4],
                    }}
                  ></div>
                  <div className="analysis-goal-bar">
                    <GoalBar
                      color={chartColors[index + 4]}
                      current={monthlyStatistic[index + 4].amount}
                      goal={category.categoryGoalAmount}
                      type={'default'}
                    />
                  </div>
                </div>
              ))}
            </div>
          </div>
        </div>
        <div className="analysis-right-container">
          <h2 className="analysis-monthly-ai-title">나마니 월간분석</h2>
          <div className="analysis-monthly-ai-text">{aiText}</div>
        </div>
      </div>
    </div>
  );
};

export default AnalysisTemplate;
